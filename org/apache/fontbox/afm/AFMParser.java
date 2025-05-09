package org.apache.fontbox.afm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: classes5.dex */
public class AFMParser {
    public static final String ASCENDER = "Ascender";
    private static final int BITS_IN_HEX = 16;
    public static final String CAP_HEIGHT = "CapHeight";
    public static final String CC = "CC";
    public static final String CHARACTERS = "Characters";
    public static final String CHARACTER_SET = "CharacterSet";
    public static final String CHARMETRICS_B = "B";
    public static final String CHARMETRICS_C = "C";
    public static final String CHARMETRICS_CH = "CH";
    public static final String CHARMETRICS_L = "L";
    public static final String CHARMETRICS_N = "N";
    public static final String CHARMETRICS_VV = "VV";
    public static final String CHARMETRICS_W = "W";
    public static final String CHARMETRICS_W0 = "W0";
    public static final String CHARMETRICS_W0X = "W0X";
    public static final String CHARMETRICS_W0Y = "W0Y";
    public static final String CHARMETRICS_W1 = "W1";
    public static final String CHARMETRICS_W1X = "W1X";
    public static final String CHARMETRICS_W1Y = "W1Y";
    public static final String CHARMETRICS_WX = "WX";
    public static final String CHARMETRICS_WY = "WY";
    public static final String CHAR_WIDTH = "CharWidth";
    public static final String COMMENT = "Comment";
    public static final String DESCENDER = "Descender";
    public static final String ENCODING_SCHEME = "EncodingScheme";
    public static final String END_CHAR_METRICS = "EndCharMetrics";
    public static final String END_COMPOSITES = "EndComposites";
    public static final String END_FONT_METRICS = "EndFontMetrics";
    public static final String END_KERN_DATA = "EndKernData";
    public static final String END_KERN_PAIRS = "EndKernPairs";
    public static final String END_TRACK_KERN = "EndTrackKern";
    public static final String ESC_CHAR = "EscChar";
    public static final String FAMILY_NAME = "FamilyName";
    public static final String FONT_BBOX = "FontBBox";
    public static final String FONT_NAME = "FontName";
    public static final String FULL_NAME = "FullName";
    public static final String IS_BASE_FONT = "IsBaseFont";
    public static final String IS_FIXED_PITCH = "IsFixedPitch";
    public static final String IS_FIXED_V = "IsFixedV";
    public static final String ITALIC_ANGLE = "ItalicAngle";
    public static final String KERN_PAIR_KP = "KP";
    public static final String KERN_PAIR_KPH = "KPH";
    public static final String KERN_PAIR_KPX = "KPX";
    public static final String KERN_PAIR_KPY = "KPY";
    public static final String MAPPING_SCHEME = "MappingScheme";
    public static final String NOTICE = "Notice";
    public static final String PCC = "PCC";
    public static final String START_CHAR_METRICS = "StartCharMetrics";
    public static final String START_COMPOSITES = "StartComposites";
    public static final String START_FONT_METRICS = "StartFontMetrics";
    public static final String START_KERN_DATA = "StartKernData";
    public static final String START_KERN_PAIRS = "StartKernPairs";
    public static final String START_KERN_PAIRS0 = "StartKernPairs0";
    public static final String START_KERN_PAIRS1 = "StartKernPairs1";
    public static final String START_TRACK_KERN = "StartTrackKern";
    public static final String STD_HW = "StdHW";
    public static final String STD_VW = "StdVW";
    public static final String UNDERLINE_POSITION = "UnderlinePosition";
    public static final String UNDERLINE_THICKNESS = "UnderlineThickness";
    public static final String VERSION = "Version";
    public static final String V_VECTOR = "VVector";
    public static final String WEIGHT = "Weight";
    public static final String X_HEIGHT = "XHeight";
    private final InputStream input;

    private static boolean isEOL(int i) {
        return i == 13 || i == 10;
    }

    private static boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 13 || i == 10;
    }

    public static void main(String[] strArr) throws IOException {
        File[] listFiles = new File("Resources/afm").listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.getPath().toUpperCase().endsWith(".AFM")) {
                    long currentTimeMillis = System.currentTimeMillis();
                    new AFMParser(new FileInputStream(file)).parse();
                    System.out.println("Parsing:" + file.getPath() + " " + (System.currentTimeMillis() - currentTimeMillis));
                }
            }
        }
    }

    public AFMParser(InputStream inputStream) {
        this.input = inputStream;
    }

    public FontMetrics parse() throws IOException {
        return parseFontMetric();
    }

    private FontMetrics parseFontMetric() throws IOException {
        FontMetrics fontMetrics = new FontMetrics();
        String readString = readString();
        if (!START_FONT_METRICS.equals(readString)) {
            throw new IOException("Error: The AFM file should start with StartFontMetrics and not '" + readString + "'");
        }
        fontMetrics.setAFMVersion(readFloat());
        while (true) {
            String readString2 = readString();
            if (END_FONT_METRICS.equals(readString2)) {
                return fontMetrics;
            }
            if (FONT_NAME.equals(readString2)) {
                fontMetrics.setFontName(readLine());
            } else if (FULL_NAME.equals(readString2)) {
                fontMetrics.setFullName(readLine());
            } else if (FAMILY_NAME.equals(readString2)) {
                fontMetrics.setFamilyName(readLine());
            } else if (WEIGHT.equals(readString2)) {
                fontMetrics.setWeight(readLine());
            } else if (FONT_BBOX.equals(readString2)) {
                BoundingBox boundingBox = new BoundingBox();
                boundingBox.setLowerLeftX(readFloat());
                boundingBox.setLowerLeftY(readFloat());
                boundingBox.setUpperRightX(readFloat());
                boundingBox.setUpperRightY(readFloat());
                fontMetrics.setFontBBox(boundingBox);
            } else if (VERSION.equals(readString2)) {
                fontMetrics.setFontVersion(readLine());
            } else if (NOTICE.equals(readString2)) {
                fontMetrics.setNotice(readLine());
            } else if (ENCODING_SCHEME.equals(readString2)) {
                fontMetrics.setEncodingScheme(readLine());
            } else if (MAPPING_SCHEME.equals(readString2)) {
                fontMetrics.setMappingScheme(readInt());
            } else if (ESC_CHAR.equals(readString2)) {
                fontMetrics.setEscChar(readInt());
            } else if (CHARACTER_SET.equals(readString2)) {
                fontMetrics.setCharacterSet(readLine());
            } else if (CHARACTERS.equals(readString2)) {
                fontMetrics.setCharacters(readInt());
            } else if (IS_BASE_FONT.equals(readString2)) {
                fontMetrics.setIsBaseFont(readBoolean());
            } else {
                int i = 0;
                if (V_VECTOR.equals(readString2)) {
                    fontMetrics.setVVector(new float[]{readFloat(), readFloat()});
                } else if (IS_FIXED_V.equals(readString2)) {
                    fontMetrics.setIsFixedV(readBoolean());
                } else if (CAP_HEIGHT.equals(readString2)) {
                    fontMetrics.setCapHeight(readFloat());
                } else if (X_HEIGHT.equals(readString2)) {
                    fontMetrics.setXHeight(readFloat());
                } else if (ASCENDER.equals(readString2)) {
                    fontMetrics.setAscender(readFloat());
                } else if (DESCENDER.equals(readString2)) {
                    fontMetrics.setDescender(readFloat());
                } else if (STD_HW.equals(readString2)) {
                    fontMetrics.setStandardHorizontalWidth(readFloat());
                } else if (STD_VW.equals(readString2)) {
                    fontMetrics.setStandardVerticalWidth(readFloat());
                } else if ("Comment".equals(readString2)) {
                    fontMetrics.addComment(readLine());
                } else if (UNDERLINE_POSITION.equals(readString2)) {
                    fontMetrics.setUnderlinePosition(readFloat());
                } else if (UNDERLINE_THICKNESS.equals(readString2)) {
                    fontMetrics.setUnderlineThickness(readFloat());
                } else if (ITALIC_ANGLE.equals(readString2)) {
                    fontMetrics.setItalicAngle(readFloat());
                } else if (CHAR_WIDTH.equals(readString2)) {
                    fontMetrics.setCharWidth(new float[]{readFloat(), readFloat()});
                } else if (IS_FIXED_PITCH.equals(readString2)) {
                    fontMetrics.setFixedPitch(readBoolean());
                } else if (START_CHAR_METRICS.equals(readString2)) {
                    int readInt = readInt();
                    while (i < readInt) {
                        fontMetrics.addCharMetric(parseCharMetric());
                        i++;
                    }
                    String readString3 = readString();
                    if (!readString3.equals(END_CHAR_METRICS)) {
                        throw new IOException("Error: Expected 'EndCharMetrics' actual '" + readString3 + "'");
                    }
                } else if (START_COMPOSITES.equals(readString2)) {
                    int readInt2 = readInt();
                    while (i < readInt2) {
                        fontMetrics.addComposite(parseComposite());
                        i++;
                    }
                    String readString4 = readString();
                    if (!readString4.equals(END_COMPOSITES)) {
                        throw new IOException("Error: Expected 'EndComposites' actual '" + readString4 + "'");
                    }
                } else if (START_KERN_DATA.equals(readString2)) {
                    parseKernData(fontMetrics);
                } else {
                    throw new IOException("Unknown AFM key '" + readString2 + "'");
                }
            }
        }
    }

    private void parseKernData(FontMetrics fontMetrics) throws IOException {
        while (true) {
            String readString = readString();
            if (readString.equals(END_KERN_DATA)) {
                return;
            }
            int i = 0;
            if (START_TRACK_KERN.equals(readString)) {
                int readInt = readInt();
                while (i < readInt) {
                    TrackKern trackKern = new TrackKern();
                    trackKern.setDegree(readInt());
                    trackKern.setMinPointSize(readFloat());
                    trackKern.setMinKern(readFloat());
                    trackKern.setMaxPointSize(readFloat());
                    trackKern.setMaxKern(readFloat());
                    fontMetrics.addTrackKern(trackKern);
                    i++;
                }
                String readString2 = readString();
                if (!readString2.equals(END_TRACK_KERN)) {
                    throw new IOException("Error: Expected 'EndTrackKern' actual '" + readString2 + "'");
                }
            } else if (START_KERN_PAIRS.equals(readString)) {
                int readInt2 = readInt();
                while (i < readInt2) {
                    fontMetrics.addKernPair(parseKernPair());
                    i++;
                }
                String readString3 = readString();
                if (!readString3.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + readString3 + "'");
                }
            } else if (START_KERN_PAIRS0.equals(readString)) {
                int readInt3 = readInt();
                while (i < readInt3) {
                    fontMetrics.addKernPair0(parseKernPair());
                    i++;
                }
                String readString4 = readString();
                if (!readString4.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + readString4 + "'");
                }
            } else if (START_KERN_PAIRS1.equals(readString)) {
                int readInt4 = readInt();
                while (i < readInt4) {
                    fontMetrics.addKernPair1(parseKernPair());
                    i++;
                }
                String readString5 = readString();
                if (!readString5.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + readString5 + "'");
                }
            } else {
                throw new IOException("Unknown kerning data type '" + readString + "'");
            }
        }
    }

    private KernPair parseKernPair() throws IOException {
        KernPair kernPair = new KernPair();
        String readString = readString();
        if (KERN_PAIR_KP.equals(readString)) {
            String readString2 = readString();
            String readString3 = readString();
            float readFloat = readFloat();
            float readFloat2 = readFloat();
            kernPair.setFirstKernCharacter(readString2);
            kernPair.setSecondKernCharacter(readString3);
            kernPair.setX(readFloat);
            kernPair.setY(readFloat2);
        } else if (KERN_PAIR_KPH.equals(readString)) {
            String hexToString = hexToString(readString());
            String hexToString2 = hexToString(readString());
            float readFloat3 = readFloat();
            float readFloat4 = readFloat();
            kernPair.setFirstKernCharacter(hexToString);
            kernPair.setSecondKernCharacter(hexToString2);
            kernPair.setX(readFloat3);
            kernPair.setY(readFloat4);
        } else if (KERN_PAIR_KPX.equals(readString)) {
            String readString4 = readString();
            String readString5 = readString();
            float readFloat5 = readFloat();
            kernPair.setFirstKernCharacter(readString4);
            kernPair.setSecondKernCharacter(readString5);
            kernPair.setX(readFloat5);
            kernPair.setY(0.0f);
        } else if (KERN_PAIR_KPY.equals(readString)) {
            String readString6 = readString();
            String readString7 = readString();
            float readFloat6 = readFloat();
            kernPair.setFirstKernCharacter(readString6);
            kernPair.setSecondKernCharacter(readString7);
            kernPair.setX(0.0f);
            kernPair.setY(readFloat6);
        } else {
            throw new IOException("Error expected kern pair command actual='" + readString + "'");
        }
        return kernPair;
    }

    private static String hexToString(String str) throws IOException {
        if (str.length() < 2) {
            throw new IOException("Error: Expected hex string of length >= 2 not='" + str);
        }
        if (str.charAt(0) != '<' || str.charAt(str.length() - 1) != '>') {
            throw new IOException("String should be enclosed by angle brackets '" + str + "'");
        }
        String substring = str.substring(1, str.length() - 1);
        byte[] bArr = new byte[substring.length() / 2];
        for (int i = 0; i < substring.length(); i += 2) {
            try {
                bArr[i / 2] = (byte) Integer.parseInt("" + substring.charAt(i) + substring.charAt(i + 1), 16);
            } catch (NumberFormatException e) {
                throw new IOException("Error parsing AFM file:" + e);
            }
        }
        return new String(bArr, "ISO-8859-1");
    }

    private Composite parseComposite() throws IOException {
        Composite composite = new Composite();
        StringTokenizer stringTokenizer = new StringTokenizer(readLine(), " ;");
        String nextToken = stringTokenizer.nextToken();
        if (!nextToken.equals(CC)) {
            throw new IOException("Expected 'CC' actual='" + nextToken + "'");
        }
        composite.setName(stringTokenizer.nextToken());
        try {
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = 0; i < parseInt; i++) {
                CompositePart compositePart = new CompositePart();
                String nextToken2 = stringTokenizer.nextToken();
                if (!nextToken2.equals(PCC)) {
                    throw new IOException("Expected 'PCC' actual='" + nextToken2 + "'");
                }
                String nextToken3 = stringTokenizer.nextToken();
                try {
                    int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
                    int parseInt3 = Integer.parseInt(stringTokenizer.nextToken());
                    compositePart.setName(nextToken3);
                    compositePart.setXDisplacement(parseInt2);
                    compositePart.setYDisplacement(parseInt3);
                    composite.addPart(compositePart);
                } catch (NumberFormatException e) {
                    throw new IOException("Error parsing AFM document:" + e);
                }
            }
            return composite;
        } catch (NumberFormatException e2) {
            throw new IOException("Error parsing AFM document:" + e2);
        }
    }

    private CharMetric parseCharMetric() throws IOException {
        CharMetric charMetric = new CharMetric();
        StringTokenizer stringTokenizer = new StringTokenizer(readLine());
        while (stringTokenizer.hasMoreTokens()) {
            try {
                String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("C")) {
                    charMetric.setCharacterCode(Integer.parseInt(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_CH)) {
                    charMetric.setCharacterCode(Integer.parseInt(stringTokenizer.nextToken(), 16));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_WX)) {
                    charMetric.setWx(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W0X)) {
                    charMetric.setW0x(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W1X)) {
                    charMetric.setW0x(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_WY)) {
                    charMetric.setWy(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W0Y)) {
                    charMetric.setW0y(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W1Y)) {
                    charMetric.setW0y(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("W")) {
                    charMetric.setW(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W0)) {
                    charMetric.setW0(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W1)) {
                    charMetric.setW1(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_VV)) {
                    charMetric.setVv(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("N")) {
                    charMetric.setName(stringTokenizer.nextToken());
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("B")) {
                    String nextToken2 = stringTokenizer.nextToken();
                    String nextToken3 = stringTokenizer.nextToken();
                    String nextToken4 = stringTokenizer.nextToken();
                    String nextToken5 = stringTokenizer.nextToken();
                    BoundingBox boundingBox = new BoundingBox();
                    boundingBox.setLowerLeftX(Float.parseFloat(nextToken2));
                    boundingBox.setLowerLeftY(Float.parseFloat(nextToken3));
                    boundingBox.setUpperRightX(Float.parseFloat(nextToken4));
                    boundingBox.setUpperRightY(Float.parseFloat(nextToken5));
                    charMetric.setBoundingBox(boundingBox);
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("L")) {
                    String nextToken6 = stringTokenizer.nextToken();
                    String nextToken7 = stringTokenizer.nextToken();
                    Ligature ligature = new Ligature();
                    ligature.setSuccessor(nextToken6);
                    ligature.setLigature(nextToken7);
                    charMetric.addLigature(ligature);
                    verifySemicolon(stringTokenizer);
                } else {
                    throw new IOException("Unknown CharMetrics command '" + nextToken + "'");
                }
            } catch (NumberFormatException e) {
                throw new IOException("Error: Corrupt AFM document:" + e);
            }
        }
        return charMetric;
    }

    private static void verifySemicolon(StringTokenizer stringTokenizer) throws IOException {
        if (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(";")) {
                return;
            }
            throw new IOException("Error: Expected semicolon in stream actual='" + nextToken + "'");
        }
        throw new IOException("CharMetrics is missing a semicolon after a command");
    }

    private boolean readBoolean() throws IOException {
        return Boolean.valueOf(readString()).booleanValue();
    }

    private int readInt() throws IOException {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing AFM document:" + e);
        }
    }

    private float readFloat() throws IOException {
        return Float.parseFloat(readString());
    }

    private String readLine() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int read = this.input.read();
        while (isWhitespace(read)) {
            read = this.input.read();
        }
        stringBuffer.append((char) read);
        while (true) {
            int read2 = this.input.read();
            if (!isEOL(read2)) {
                stringBuffer.append((char) read2);
            } else {
                return stringBuffer.toString();
            }
        }
    }

    private String readString() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int read = this.input.read();
        while (isWhitespace(read)) {
            read = this.input.read();
        }
        stringBuffer.append((char) read);
        while (true) {
            int read2 = this.input.read();
            if (!isWhitespace(read2)) {
                stringBuffer.append((char) read2);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
