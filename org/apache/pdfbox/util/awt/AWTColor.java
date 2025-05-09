package org.apache.pdfbox.util.awt;

import android.graphics.Color;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class AWTColor {
    public static final AWTColor BLACK;
    public static final AWTColor BLUE;
    public static final AWTColor CYAN;
    public static final AWTColor DARK_GRAY;
    public static final AWTColor GRAY;
    public static final AWTColor GREEN;
    public static final AWTColor LIGHT_GRAY;
    public static final AWTColor MAGENTA;
    public static final AWTColor ORANGE;
    public static final AWTColor PINK;
    public static final AWTColor RED;
    public static final AWTColor WHITE;
    public static final AWTColor YELLOW;
    public static final AWTColor black;
    public static final AWTColor blue;
    public static final AWTColor cyan;
    public static final AWTColor darkGray;
    public static final AWTColor gray;
    public static final AWTColor green;
    public static final AWTColor lightGray;
    public static final AWTColor magenta;
    public static final AWTColor orange;
    public static final AWTColor pink;
    public static final AWTColor red;
    public static final AWTColor white;
    public static final AWTColor yellow;
    public int color;

    static {
        AWTColor aWTColor = new AWTColor(255, 255, 255);
        white = aWTColor;
        WHITE = aWTColor;
        AWTColor aWTColor2 = new AWTColor(192, 192, 192);
        lightGray = aWTColor2;
        LIGHT_GRAY = aWTColor2;
        AWTColor aWTColor3 = new AWTColor(128, 128, 128);
        gray = aWTColor3;
        GRAY = aWTColor3;
        AWTColor aWTColor4 = new AWTColor(64, 64, 64);
        darkGray = aWTColor4;
        DARK_GRAY = aWTColor4;
        AWTColor aWTColor5 = new AWTColor(0, 0, 0);
        black = aWTColor5;
        BLACK = aWTColor5;
        AWTColor aWTColor6 = new AWTColor(255, 0, 0);
        red = aWTColor6;
        RED = aWTColor6;
        AWTColor aWTColor7 = new AWTColor(255, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384);
        pink = aWTColor7;
        PINK = aWTColor7;
        AWTColor aWTColor8 = new AWTColor(255, 200, 0);
        orange = aWTColor8;
        ORANGE = aWTColor8;
        AWTColor aWTColor9 = new AWTColor(255, 255, 0);
        yellow = aWTColor9;
        YELLOW = aWTColor9;
        AWTColor aWTColor10 = new AWTColor(0, 255, 0);
        green = aWTColor10;
        GREEN = aWTColor10;
        AWTColor aWTColor11 = new AWTColor(255, 0, 255);
        magenta = aWTColor11;
        MAGENTA = aWTColor11;
        AWTColor aWTColor12 = new AWTColor(0, 255, 255);
        cyan = aWTColor12;
        CYAN = aWTColor12;
        AWTColor aWTColor13 = new AWTColor(0, 0, 255);
        blue = aWTColor13;
        BLUE = aWTColor13;
    }

    public AWTColor(int i) {
        this.color = i;
    }

    public AWTColor(int i, int i2, int i3) {
        this(i, i2, i3, 255);
    }

    public AWTColor(int i, int i2, int i3, int i4) {
        this.color = Color.argb(i4, i, i2, i3);
    }

    public int getRed() {
        return Color.red(this.color);
    }

    public int getGreen() {
        return Color.green(this.color);
    }

    public int getBlue() {
        return Color.blue(this.color);
    }

    public int getAlpha() {
        return Color.alpha(this.color);
    }
}
