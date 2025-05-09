package org.apache.pdfbox.util;

import com.google.firebase.database.DatabaseError;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public final class DateConverter {
    private static final int DAY = 86400000;
    private static final int HALF_DAY = 43200000;
    private static final int MILLIS_PER_HOUR = 3600000;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final String[] ALPHA_START_FORMATS = {"EEEE, dd MMM yy hh:mm:ss a", "EEEE, MMM dd, yy hh:mm:ss a", "EEEE, MMM dd, yy 'at' hh:mma", "EEEE, MMM dd, yy", "EEEE MMM dd, yy HH:mm:ss", "EEEE MMM dd HH:mm:ss z yy", "EEEE MMM dd HH:mm:ss yy"};
    private static final String[] DIGIT_START_FORMATS = {"dd MMM yy HH:mm:ss", "dd MMM yy HH:mm", "yyyy MMM d", "yyyymmddhh:mm:ss", "H:m M/d/yy", "M/d/yy HH:mm:ss", "M/d/yy HH:mm", "M/d/yy"};

    private DateConverter() {
    }

    public static String toString(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return String.format("D:%1$4tY%1$2tm%1$2td%1$2tH%1$2tM%1$2tS%2$s'", calendar, formatTZoffset(calendar.get(15) + calendar.get(16), "'"));
    }

    public static String toISO8601(Calendar calendar) {
        return String.format("%1$4tY-%1$2tm-%1$2tdT%1$2tH:%1$2tM:%1$2tS%2$s", calendar, formatTZoffset(calendar.get(15) + calendar.get(16), ":"));
    }

    private static int restrainTZoffset(long j) {
        return (int) ((((((j + 43200000) % 86400000) + 86400000) % 86400000) - 43200000) % 43200000);
    }

    static String formatTZoffset(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Z");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(restrainTZoffset(j), "unknown"));
        String format = simpleDateFormat.format(new Date());
        return format.substring(0, 3) + str + format.substring(3);
    }

    private static int parseTimeField(String str, ParsePosition parsePosition, int i, int i2) {
        if (str == null) {
            return i2;
        }
        int i3 = 0;
        int index = parsePosition.getIndex();
        int min = Math.min(i, str.length() - index) + index;
        while (index < min) {
            int charAt = str.charAt(index) - '0';
            if (charAt < 0 || charAt > 9) {
                break;
            }
            i3 = (i3 * 10) + charAt;
            index++;
        }
        if (index == parsePosition.getIndex()) {
            return i2;
        }
        parsePosition.setIndex(index);
        return i3;
    }

    private static char skipOptionals(String str, ParsePosition parsePosition, String str2) {
        char c = ' ';
        while (str != null && parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (str2.indexOf(charAt) < 0) {
                break;
            }
            if (charAt != ' ') {
                c = charAt;
            }
            parsePosition.setIndex(parsePosition.getIndex() + 1);
        }
        return c;
    }

    private static boolean skipString(String str, String str2, ParsePosition parsePosition) {
        if (!str.startsWith(str2, parsePosition.getIndex())) {
            return false;
        }
        parsePosition.setIndex(parsePosition.getIndex() + str2.length());
        return true;
    }

    static GregorianCalendar newGreg() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(Locale.ENGLISH);
        gregorianCalendar.setTimeZone(new SimpleTimeZone(0, "UTC"));
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar;
    }

    private static void adjustTimeZoneNicely(GregorianCalendar gregorianCalendar, TimeZone timeZone) {
        gregorianCalendar.setTimeZone(timeZone);
        gregorianCalendar.add(12, -((gregorianCalendar.get(15) + gregorianCalendar.get(16)) / MILLIS_PER_MINUTE));
    }

    static boolean parseTZoffset(String str, GregorianCalendar gregorianCalendar, ParsePosition parsePosition) {
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        TimeZone simpleTimeZone = new SimpleTimeZone(0, "GMT");
        char skipOptionals = skipOptionals(str, parsePosition2, "Z+- ");
        boolean z = skipOptionals == 'Z' || skipString(str, "GMT", parsePosition2) || skipString(str, "UTC", parsePosition2);
        if (z) {
            skipOptionals = skipOptionals(str, parsePosition2, "+- ");
        }
        int parseTimeField = parseTimeField(str, parsePosition2, 2, DatabaseError.UNKNOWN_ERROR);
        skipOptionals(str, parsePosition2, "': ");
        int parseTimeField2 = parseTimeField(str, parsePosition2, 2, 0);
        skipOptionals(str, parsePosition2, "' ");
        if (parseTimeField != -999) {
            simpleTimeZone.setRawOffset(restrainTZoffset((skipOptionals == '-' ? -1 : 1) * ((parseTimeField * MILLIS_PER_HOUR) + (parseTimeField2 * 60000))));
            simpleTimeZone.setID("unknown");
        } else if (!z) {
            simpleTimeZone = TimeZone.getTimeZone(str.substring(parsePosition.getIndex()).trim());
            if ("GMT".equals(simpleTimeZone.getID())) {
                return false;
            }
            parsePosition2.setIndex(str.length());
        }
        adjustTimeZoneNicely(gregorianCalendar, simpleTimeZone);
        parsePosition.setIndex(parsePosition2.getIndex());
        return true;
    }

    private static GregorianCalendar parseBigEndianDate(String str, ParsePosition parsePosition) {
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        int parseTimeField = parseTimeField(str, parsePosition2, 4, 0);
        if (parsePosition2.getIndex() != parsePosition.getIndex() + 4) {
            return null;
        }
        skipOptionals(str, parsePosition2, "/- ");
        int parseTimeField2 = parseTimeField(str, parsePosition2, 2, 1) - 1;
        skipOptionals(str, parsePosition2, "/- ");
        int parseTimeField3 = parseTimeField(str, parsePosition2, 2, 1);
        skipOptionals(str, parsePosition2, " T");
        int parseTimeField4 = parseTimeField(str, parsePosition2, 2, 0);
        skipOptionals(str, parsePosition2, ": ");
        int parseTimeField5 = parseTimeField(str, parsePosition2, 2, 0);
        skipOptionals(str, parsePosition2, ": ");
        int parseTimeField6 = parseTimeField(str, parsePosition2, 2, 0);
        if (skipOptionals(str, parsePosition2, ".") == '.') {
            parseTimeField(str, parsePosition2, 19, 0);
        }
        GregorianCalendar newGreg = newGreg();
        try {
            newGreg.set(parseTimeField, parseTimeField2, parseTimeField3, parseTimeField4, parseTimeField5, parseTimeField6);
            newGreg.getTimeInMillis();
            parsePosition.setIndex(parsePosition2.getIndex());
            skipOptionals(str, parsePosition, " ");
            return newGreg;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static GregorianCalendar parseSimpleDate(String str, String[] strArr, ParsePosition parsePosition) {
        for (String str2 : strArr) {
            ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.ENGLISH);
            GregorianCalendar newGreg = newGreg();
            simpleDateFormat.setCalendar(newGreg);
            if (simpleDateFormat.parse(str, parsePosition2) != null) {
                parsePosition.setIndex(parsePosition2.getIndex());
                skipOptionals(str, parsePosition, " ");
                return newGreg;
            }
        }
        return null;
    }

    private static Calendar parseDate(String str, ParsePosition parsePosition) {
        String[] strArr;
        GregorianCalendar gregorianCalendar = null;
        if (str == null || str.isEmpty()) {
            return null;
        }
        int i = -999999;
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        skipOptionals(str, parsePosition2, " ");
        int index = parsePosition2.getIndex();
        GregorianCalendar parseBigEndianDate = parseBigEndianDate(str, parsePosition2);
        if (parseBigEndianDate != null && (parsePosition2.getIndex() == str.length() || parseTZoffset(str, parseBigEndianDate, parsePosition2))) {
            i = parsePosition2.getIndex();
            if (i == str.length()) {
                parsePosition.setIndex(i);
                return parseBigEndianDate;
            }
            gregorianCalendar = parseBigEndianDate;
        }
        parsePosition2.setIndex(index);
        if (Character.isDigit(str.charAt(index))) {
            strArr = DIGIT_START_FORMATS;
        } else {
            strArr = ALPHA_START_FORMATS;
        }
        GregorianCalendar parseSimpleDate = parseSimpleDate(str, strArr, parsePosition2);
        if (parseSimpleDate != null && (parsePosition2.getIndex() == str.length() || parseTZoffset(str, parseSimpleDate, parsePosition2))) {
            int index2 = parsePosition2.getIndex();
            if (index2 == str.length()) {
                parsePosition.setIndex(index2);
                return parseSimpleDate;
            }
            if (index2 > i) {
                i = index2;
                gregorianCalendar = parseSimpleDate;
            }
        }
        if (gregorianCalendar == null) {
            return parseSimpleDate;
        }
        parsePosition.setIndex(i);
        return gregorianCalendar;
    }

    public static Calendar toCalendar(COSString cOSString) {
        if (cOSString == null) {
            return null;
        }
        return toCalendar(cOSString.getString());
    }

    public static Calendar toCalendar(String str) {
        if (str != null && !str.trim().isEmpty()) {
            ParsePosition parsePosition = new ParsePosition(0);
            skipOptionals(str, parsePosition, " ");
            skipString(str, "D:", parsePosition);
            Calendar parseDate = parseDate(str, parsePosition);
            if (parseDate != null && parsePosition.getIndex() == str.length()) {
                return parseDate;
            }
        }
        return null;
    }
}
