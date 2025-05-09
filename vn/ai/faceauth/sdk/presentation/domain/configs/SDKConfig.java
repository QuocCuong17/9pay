package vn.ai.faceauth.sdk.presentation.domain.configs;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001BG\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bBO\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\rBm\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u0012B\u0095\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0015\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u0018J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0015HÆ\u0003J\t\u0010.\u001a\u00020\u0015HÆ\u0003J\t\u0010/\u001a\u00020\u0015HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u000fHÆ\u0003J©\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010:\u001a\u00020\u000f2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\u0015HÖ\u0001J\t\u0010=\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u0016\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0017\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&¨\u0006>"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/configs/SDKConfig;", "", "primaryColor", "", "secondaryColor", "errorColor", "closeColor", "textColor", "textButtonColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "nonce", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "ovalColor", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "isAutoProcess", "", "isCancelable", "isShowGuideScreen", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;)V", "fontName", "textSizeTitle", "", "textSize", "textSizeButton", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;IIILjava/lang/String;)V", "getBackgroundColor", "()Ljava/lang/String;", "getCloseColor", "getErrorColor", "getFontName", "()Z", "getNonce", "getOvalColor", "getPrimaryColor", "getSecondaryColor", "getTextButtonColor", "getTextColor", "getTextSize", "()I", "getTextSizeButton", "getTextSizeTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class SDKConfig {
    private final String backgroundColor;
    private final String closeColor;
    private final String errorColor;
    private final String fontName;
    private final boolean isAutoProcess;
    private final boolean isCancelable;
    private final boolean isShowGuideScreen;
    private final String nonce;
    private final String ovalColor;
    private final String primaryColor;
    private final String secondaryColor;
    private final String textButtonColor;
    private final String textColor;
    private final int textSize;
    private final int textSizeButton;
    private final int textSizeTitle;

    public SDKConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this(str, str2, str, str3, str4, str5, str6, str7, false, true, false, "", 22, 16, 18, str8);
    }

    public SDKConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this(str, str2, str3, str4, str5, str6, str7, str8, false, true, false, "", 22, 16, 18, str9);
    }

    public SDKConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9) {
        this(str, str2, str3, str4, str5, str6, str7, str8, z, z2, z3, "", 22, 16, 18, str9);
    }

    public SDKConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9, int i, int i2, int i3, String str10) {
        this.primaryColor = str;
        this.secondaryColor = str2;
        this.ovalColor = str3;
        this.errorColor = str4;
        this.closeColor = str5;
        this.textColor = str6;
        this.textButtonColor = str7;
        this.backgroundColor = str8;
        this.isAutoProcess = z;
        this.isCancelable = z2;
        this.isShowGuideScreen = z3;
        this.fontName = str9;
        this.textSizeTitle = i;
        this.textSize = i2;
        this.textSizeButton = i3;
        this.nonce = str10;
    }

    public /* synthetic */ SDKConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9, int i, int i2, int i3, String str10, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, (i4 & 256) != 0 ? false : z, (i4 & 512) != 0 ? true : z2, (i4 & 1024) != 0 ? false : z3, (i4 & 2048) != 0 ? "" : str9, (i4 & 4096) != 0 ? 22 : i, (i4 & 8192) != 0 ? 16 : i2, (i4 & 16384) != 0 ? 18 : i3, (i4 & 32768) != 0 ? "" : str10);
    }

    public /* synthetic */ SDKConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, (i & 256) != 0 ? false : z, (i & 512) != 0 ? true : z2, (i & 1024) != 0 ? false : z3, str9);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPrimaryColor() {
        return this.primaryColor;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getIsCancelable() {
        return this.isCancelable;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getIsShowGuideScreen() {
        return this.isShowGuideScreen;
    }

    /* renamed from: component12, reason: from getter */
    public final String getFontName() {
        return this.fontName;
    }

    /* renamed from: component13, reason: from getter */
    public final int getTextSizeTitle() {
        return this.textSizeTitle;
    }

    /* renamed from: component14, reason: from getter */
    public final int getTextSize() {
        return this.textSize;
    }

    /* renamed from: component15, reason: from getter */
    public final int getTextSizeButton() {
        return this.textSizeButton;
    }

    /* renamed from: component16, reason: from getter */
    public final String getNonce() {
        return this.nonce;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSecondaryColor() {
        return this.secondaryColor;
    }

    /* renamed from: component3, reason: from getter */
    public final String getOvalColor() {
        return this.ovalColor;
    }

    /* renamed from: component4, reason: from getter */
    public final String getErrorColor() {
        return this.errorColor;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCloseColor() {
        return this.closeColor;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTextColor() {
        return this.textColor;
    }

    /* renamed from: component7, reason: from getter */
    public final String getTextButtonColor() {
        return this.textButtonColor;
    }

    /* renamed from: component8, reason: from getter */
    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getIsAutoProcess() {
        return this.isAutoProcess;
    }

    public final SDKConfig copy(String primaryColor, String secondaryColor, String ovalColor, String errorColor, String closeColor, String textColor, String textButtonColor, String backgroundColor, boolean isAutoProcess, boolean isCancelable, boolean isShowGuideScreen, String fontName, int textSizeTitle, int textSize, int textSizeButton, String nonce) {
        return new SDKConfig(primaryColor, secondaryColor, ovalColor, errorColor, closeColor, textColor, textButtonColor, backgroundColor, isAutoProcess, isCancelable, isShowGuideScreen, fontName, textSizeTitle, textSize, textSizeButton, nonce);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SDKConfig)) {
            return false;
        }
        SDKConfig sDKConfig = (SDKConfig) other;
        return Intrinsics.areEqual(this.primaryColor, sDKConfig.primaryColor) && Intrinsics.areEqual(this.secondaryColor, sDKConfig.secondaryColor) && Intrinsics.areEqual(this.ovalColor, sDKConfig.ovalColor) && Intrinsics.areEqual(this.errorColor, sDKConfig.errorColor) && Intrinsics.areEqual(this.closeColor, sDKConfig.closeColor) && Intrinsics.areEqual(this.textColor, sDKConfig.textColor) && Intrinsics.areEqual(this.textButtonColor, sDKConfig.textButtonColor) && Intrinsics.areEqual(this.backgroundColor, sDKConfig.backgroundColor) && this.isAutoProcess == sDKConfig.isAutoProcess && this.isCancelable == sDKConfig.isCancelable && this.isShowGuideScreen == sDKConfig.isShowGuideScreen && Intrinsics.areEqual(this.fontName, sDKConfig.fontName) && this.textSizeTitle == sDKConfig.textSizeTitle && this.textSize == sDKConfig.textSize && this.textSizeButton == sDKConfig.textSizeButton && Intrinsics.areEqual(this.nonce, sDKConfig.nonce);
    }

    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    public final String getCloseColor() {
        return this.closeColor;
    }

    public final String getErrorColor() {
        return this.errorColor;
    }

    public final String getFontName() {
        return this.fontName;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public final String getOvalColor() {
        return this.ovalColor;
    }

    public final String getPrimaryColor() {
        return this.primaryColor;
    }

    public final String getSecondaryColor() {
        return this.secondaryColor;
    }

    public final String getTextButtonColor() {
        return this.textButtonColor;
    }

    public final String getTextColor() {
        return this.textColor;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final int getTextSizeButton() {
        return this.textSizeButton;
    }

    public final int getTextSizeTitle() {
        return this.textSizeTitle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.primaryColor.hashCode();
        int hashCode2 = this.secondaryColor.hashCode();
        int hashCode3 = this.ovalColor.hashCode();
        int hashCode4 = this.errorColor.hashCode();
        int hashCode5 = this.closeColor.hashCode();
        int hashCode6 = this.textColor.hashCode();
        int hashCode7 = this.textButtonColor.hashCode();
        int hashCode8 = this.backgroundColor.hashCode();
        boolean z = this.isAutoProcess;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        boolean z2 = this.isCancelable;
        int i2 = z2;
        if (z2 != 0) {
            i2 = 1;
        }
        boolean z3 = this.isShowGuideScreen;
        int i3 = z3 ? 1 : z3 ? 1 : 0;
        int hashCode9 = this.fontName.hashCode();
        return this.nonce.hashCode() + ((this.textSizeButton + ((this.textSize + ((this.textSizeTitle + ((hashCode9 + ((((((((hashCode8 + ((hashCode7 + ((hashCode6 + ((hashCode5 + ((hashCode4 + ((hashCode3 + ((hashCode2 + (hashCode * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + i) * 31) + i2) * 31) + i3) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final boolean isAutoProcess() {
        return this.isAutoProcess;
    }

    public final boolean isCancelable() {
        return this.isCancelable;
    }

    public final boolean isShowGuideScreen() {
        return this.isShowGuideScreen;
    }

    public String toString() {
        return tfwhgw.rnigpa(CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384) + this.primaryColor + tfwhgw.rnigpa(168) + this.secondaryColor + tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384) + this.ovalColor + tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256) + this.errorColor + tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384) + this.closeColor + tfwhgw.rnigpa(172) + this.textColor + tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384) + this.textButtonColor + tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256) + this.backgroundColor + tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384) + this.isAutoProcess + tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_NULL_SHA256) + this.isCancelable + tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_NULL_SHA384) + this.isShowGuideScreen + tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256) + this.fontName + tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384) + this.textSizeTitle + tfwhgw.rnigpa(180) + this.textSize + tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384) + this.textSizeButton + tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256) + this.nonce + ')';
    }
}
