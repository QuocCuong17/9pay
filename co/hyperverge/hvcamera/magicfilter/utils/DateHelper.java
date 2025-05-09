package co.hyperverge.hvcamera.magicfilter.utils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class DateHelper {
    static SimpleDateFormat sFormatter;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
