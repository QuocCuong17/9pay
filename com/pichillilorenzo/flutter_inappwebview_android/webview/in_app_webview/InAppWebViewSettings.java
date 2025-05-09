package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.os.Build;
import android.webkit.WebSettings;
import androidx.media3.common.C;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.PreferredContentModeOptionType;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes5.dex */
public class InAppWebViewSettings implements ISettings<InAppWebViewInterface> {
    public static final String LOG_TAG = "InAppWebViewSettings";
    public String appCachePath;
    public byte[] defaultVideoPoster;
    public Integer disabledActionModeMenuItems;
    public String horizontalScrollbarThumbColor;
    public String horizontalScrollbarTrackColor;
    public WebSettings.LayoutAlgorithm layoutAlgorithm;
    public Integer mixedContentMode;
    public String regexToCancelSubFramesLoading;
    public Set<String> requestedWithHeaderOriginAllowList;
    public String verticalScrollbarThumbColor;
    public String verticalScrollbarTrackColor;
    public Map<String, Object> webViewAssetLoader;
    public Boolean useShouldOverrideUrlLoading = false;
    public Boolean useOnLoadResource = false;
    public Boolean useOnDownloadStart = false;

    @Deprecated
    public Boolean clearCache = false;
    public String userAgent = "";
    public String applicationNameForUserAgent = "";
    public Boolean javaScriptEnabled = true;
    public Boolean javaScriptCanOpenWindowsAutomatically = false;
    public Boolean mediaPlaybackRequiresUserGesture = true;
    public Integer minimumFontSize = 8;
    public Boolean verticalScrollBarEnabled = true;
    public Boolean horizontalScrollBarEnabled = true;
    public List<String> resourceCustomSchemes = new ArrayList();
    public List<Map<String, Map<String, Object>>> contentBlockers = new ArrayList();
    public Integer preferredContentMode = Integer.valueOf(PreferredContentModeOptionType.RECOMMENDED.toValue());
    public Boolean useShouldInterceptAjaxRequest = false;
    public Boolean interceptOnlyAsyncAjaxRequests = true;
    public Boolean useShouldInterceptFetchRequest = false;
    public Boolean incognito = false;
    public Boolean cacheEnabled = true;
    public Boolean transparentBackground = false;
    public Boolean disableVerticalScroll = false;
    public Boolean disableHorizontalScroll = false;
    public Boolean disableContextMenu = false;
    public Boolean supportZoom = true;
    public Boolean allowFileAccessFromFileURLs = false;
    public Boolean allowUniversalAccessFromFileURLs = false;
    public Boolean allowBackgroundAudioPlaying = false;
    public Integer textZoom = 100;

    @Deprecated
    public Boolean clearSessionCache = false;
    public Boolean builtInZoomControls = true;
    public Boolean displayZoomControls = false;
    public Boolean databaseEnabled = false;
    public Boolean domStorageEnabled = true;
    public Boolean useWideViewPort = true;
    public Boolean safeBrowsingEnabled = true;
    public Boolean allowContentAccess = true;
    public Boolean allowFileAccess = true;
    public Boolean blockNetworkImage = false;
    public Boolean blockNetworkLoads = false;
    public Integer cacheMode = -1;
    public String cursiveFontFamily = "cursive";
    public Integer defaultFixedFontSize = 16;
    public Integer defaultFontSize = 16;
    public String defaultTextEncodingName = "UTF-8";
    public String fantasyFontFamily = "fantasy";
    public String fixedFontFamily = "monospace";
    public Integer forceDark = 0;
    public Integer forceDarkStrategy = 2;
    public Boolean geolocationEnabled = true;
    public Boolean loadWithOverviewMode = true;
    public Boolean loadsImagesAutomatically = true;
    public Integer minimumLogicalFontSize = 8;
    public Integer initialScale = 0;
    public Boolean needInitialFocus = true;
    public Boolean offscreenPreRaster = false;
    public String sansSerifFontFamily = C.SANS_SERIF_NAME;
    public String serifFontFamily = C.SANS_SERIF_NAME;
    public String standardFontFamily = C.SANS_SERIF_NAME;
    public Boolean saveFormData = true;
    public Boolean thirdPartyCookiesEnabled = true;
    public Boolean hardwareAcceleration = true;
    public Boolean supportMultipleWindows = false;
    public Integer overScrollMode = 1;
    public Boolean networkAvailable = null;
    public Integer scrollBarStyle = 0;
    public Integer verticalScrollbarPosition = 0;
    public Integer scrollBarDefaultDelayBeforeFade = null;
    public Boolean scrollbarFadingEnabled = true;
    public Integer scrollBarFadeDuration = null;
    public Map<String, Object> rendererPriorityPolicy = null;
    public Boolean useShouldInterceptRequest = false;
    public Boolean useOnRenderProcessGone = false;
    public Boolean disableDefaultErrorPage = false;
    public Boolean useHybridComposition = true;
    public Boolean algorithmicDarkeningAllowed = false;
    public Boolean enterpriseAuthenticationAppLinkPolicyEnabled = true;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<InAppWebViewInterface> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0029. Please report as an issue. */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse, reason: avoid collision after fix types in other method */
    public ISettings<InAppWebViewInterface> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c = 65535;
                switch (key.hashCode()) {
                    case -2116596967:
                        if (key.equals("disableHorizontalScroll")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -2095822429:
                        if (key.equals("scrollBarDefaultDelayBeforeFade")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -2020146208:
                        if (key.equals("useWideViewPort")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -2014672109:
                        if (key.equals("allowFileAccessFromFileURLs")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1931277743:
                        if (key.equals("defaultFontSize")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1851090878:
                        if (key.equals("supportZoom")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -1845480382:
                        if (key.equals("scrollbarFadingEnabled")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -1834028884:
                        if (key.equals("defaultTextEncodingName")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -1746033750:
                        if (key.equals("needInitialFocus")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -1712086669:
                        if (key.equals("useShouldOverrideUrlLoading")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case -1673892229:
                        if (key.equals("preferredContentMode")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case -1657552268:
                        if (key.equals("allowContentAccess")) {
                            c = 11;
                            break;
                        }
                        break;
                    case -1626497241:
                        if (key.equals("fixedFontFamily")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case -1615103092:
                        if (key.equals("builtInZoomControls")) {
                            c = '\r';
                            break;
                        }
                        break;
                    case -1607633676:
                        if (key.equals("javaScriptEnabled")) {
                            c = 14;
                            break;
                        }
                        break;
                    case -1578962296:
                        if (key.equals("hardwareAcceleration")) {
                            c = 15;
                            break;
                        }
                        break;
                    case -1578507205:
                        if (key.equals("networkAvailable")) {
                            c = 16;
                            break;
                        }
                        break;
                    case -1574470051:
                        if (key.equals("useShouldInterceptFetchRequest")) {
                            c = 17;
                            break;
                        }
                        break;
                    case -1480607106:
                        if (key.equals("loadsImagesAutomatically")) {
                            c = 18;
                            break;
                        }
                        break;
                    case -1455624881:
                        if (key.equals("resourceCustomSchemes")) {
                            c = 19;
                            break;
                        }
                        break;
                    case -1443655540:
                        if (key.equals("disabledActionModeMenuItems")) {
                            c = 20;
                            break;
                        }
                        break;
                    case -1423657812:
                        if (key.equals("incognito")) {
                            c = 21;
                            break;
                        }
                        break;
                    case -1410791825:
                        if (key.equals("allowBackgroundAudioPlaying")) {
                            c = 22;
                            break;
                        }
                        break;
                    case -1349570230:
                        if (key.equals("webViewAssetLoader")) {
                            c = 23;
                            break;
                        }
                        break;
                    case -1321236988:
                        if (key.equals("overScrollMode")) {
                            c = 24;
                            break;
                        }
                        break;
                    case -1146673624:
                        if (key.equals("domStorageEnabled")) {
                            c = 25;
                            break;
                        }
                        break;
                    case -1143245914:
                        if (key.equals("disableContextMenu")) {
                            c = 26;
                            break;
                        }
                        break;
                    case -1038715033:
                        if (key.equals("useShouldInterceptAjaxRequest")) {
                            c = 27;
                            break;
                        }
                        break;
                    case -1003454816:
                        if (key.equals("textZoom")) {
                            c = 28;
                            break;
                        }
                        break;
                    case -868328270:
                        if (key.equals("interceptOnlyAsyncAjaxRequests")) {
                            c = 29;
                            break;
                        }
                        break;
                    case -800676066:
                        if (key.equals("minimumFontSize")) {
                            c = 30;
                            break;
                        }
                        break;
                    case -767637403:
                        if (key.equals("layoutAlgorithm")) {
                            c = 31;
                            break;
                        }
                        break;
                    case -759238347:
                        if (key.equals("clearCache")) {
                            c = ' ';
                            break;
                        }
                        break;
                    case -742944736:
                        if (key.equals("transparentBackground")) {
                            c = '!';
                            break;
                        }
                        break;
                    case -741649011:
                        if (key.equals("enterpriseAuthenticationAppLinkPolicyEnabled")) {
                            c = '\"';
                            break;
                        }
                        break;
                    case -728016272:
                        if (key.equals("allowUniversalAccessFromFileURLs")) {
                            c = '#';
                            break;
                        }
                        break;
                    case -710246074:
                        if (key.equals("databaseEnabled")) {
                            c = Typography.dollar;
                            break;
                        }
                        break;
                    case -706772569:
                        if (key.equals("useShouldInterceptRequest")) {
                            c = '%';
                            break;
                        }
                        break;
                    case -553792443:
                        if (key.equals("cacheMode")) {
                            c = Typography.amp;
                            break;
                        }
                        break;
                    case -443422049:
                        if (key.equals("horizontalScrollBarEnabled")) {
                            c = '\'';
                            break;
                        }
                        break;
                    case -435719349:
                        if (key.equals("scrollBarStyle")) {
                            c = '(';
                            break;
                        }
                        break;
                    case -421090202:
                        if (key.equals("initialScale")) {
                            c = ')';
                            break;
                        }
                        break;
                    case -321425255:
                        if (key.equals("verticalScrollbarPosition")) {
                            c = '*';
                            break;
                        }
                        break;
                    case -229178645:
                        if (key.equals("disableVerticalScroll")) {
                            c = '+';
                            break;
                        }
                        break;
                    case -227577491:
                        if (key.equals("javaScriptCanOpenWindowsAutomatically")) {
                            c = ',';
                            break;
                        }
                        break;
                    case -225496870:
                        if (key.equals("horizontalScrollbarTrackColor")) {
                            c = '-';
                            break;
                        }
                        break;
                    case -225165915:
                        if (key.equals("offscreenPreRaster")) {
                            c = FilenameUtils.EXTENSION_SEPARATOR;
                            break;
                        }
                        break;
                    case -216514471:
                        if (key.equals("fantasyFontFamily")) {
                            c = '/';
                            break;
                        }
                        break;
                    case -158057575:
                        if (key.equals("rendererPriorityPolicy")) {
                            c = '0';
                            break;
                        }
                        break;
                    case -98561827:
                        if (key.equals("sansSerifFontFamily")) {
                            c = '1';
                            break;
                        }
                        break;
                    case 57717170:
                        if (key.equals("regexToCancelSubFramesLoading")) {
                            c = '2';
                            break;
                        }
                        break;
                    case 100868168:
                        if (key.equals("verticalScrollbarTrackColor")) {
                            c = '3';
                            break;
                        }
                        break;
                    case 174479508:
                        if (key.equals("useOnDownloadStart")) {
                            c = '4';
                            break;
                        }
                        break;
                    case 229242772:
                        if (key.equals("forceDarkStrategy")) {
                            c = '5';
                            break;
                        }
                        break;
                    case 257886264:
                        if (key.equals("cursiveFontFamily")) {
                            c = '6';
                            break;
                        }
                        break;
                    case 273267153:
                        if (key.equals("mediaPlaybackRequiresUserGesture")) {
                            c = '7';
                            break;
                        }
                        break;
                    case 296040698:
                        if (key.equals("blockNetworkImage")) {
                            c = '8';
                            break;
                        }
                        break;
                    case 298870764:
                        if (key.equals("blockNetworkLoads")) {
                            c = '9';
                            break;
                        }
                        break;
                    case 311430650:
                        if (key.equals("userAgent")) {
                            c = ':';
                            break;
                        }
                        break;
                    case 387230482:
                        if (key.equals("useOnRenderProcessGone")) {
                            c = ';';
                            break;
                        }
                        break;
                    case 393481210:
                        if (key.equals("useOnLoadResource")) {
                            c = Typography.less;
                            break;
                        }
                        break;
                    case 397237599:
                        if (key.equals("cacheEnabled")) {
                            c = '=';
                            break;
                        }
                        break;
                    case 408799019:
                        if (key.equals("saveFormData")) {
                            c = Typography.greater;
                            break;
                        }
                        break;
                    case 477312960:
                        if (key.equals("requestedWithHeaderOriginAllowList")) {
                            c = '?';
                            break;
                        }
                        break;
                    case 487904071:
                        if (key.equals("useHybridComposition")) {
                            c = '@';
                            break;
                        }
                        break;
                    case 590869196:
                        if (key.equals("applicationNameForUserAgent")) {
                            c = 'A';
                            break;
                        }
                        break;
                    case 760962753:
                        if (key.equals("mixedContentMode")) {
                            c = 'B';
                            break;
                        }
                        break;
                    case 849171798:
                        if (key.equals("scrollBarFadeDuration")) {
                            c = 'C';
                            break;
                        }
                        break;
                    case 1138246185:
                        if (key.equals("allowFileAccess")) {
                            c = 'D';
                            break;
                        }
                        break;
                    case 1156608422:
                        if (key.equals("appCachePath")) {
                            c = 'E';
                            break;
                        }
                        break;
                    case 1193086767:
                        if (key.equals("horizontalScrollbarThumbColor")) {
                            c = 'F';
                            break;
                        }
                        break;
                    case 1301942064:
                        if (key.equals("standardFontFamily")) {
                            c = 'G';
                            break;
                        }
                        break;
                    case 1320461707:
                        if (key.equals("displayZoomControls")) {
                            c = 'H';
                            break;
                        }
                        break;
                    case 1344414299:
                        if (key.equals("geolocationEnabled")) {
                            c = 'I';
                            break;
                        }
                        break;
                    case 1409728424:
                        if (key.equals("loadWithOverviewMode")) {
                            c = 'J';
                            break;
                        }
                        break;
                    case 1474890029:
                        if (key.equals("safeBrowsingEnabled")) {
                            c = 'K';
                            break;
                        }
                        break;
                    case 1496887792:
                        if (key.equals("serifFontFamily")) {
                            c = Matrix.MATRIX_TYPE_RANDOM_LT;
                            break;
                        }
                        break;
                    case 1519451805:
                        if (key.equals("verticalScrollbarThumbColor")) {
                            c = 'M';
                            break;
                        }
                        break;
                    case 1527546113:
                        if (key.equals("forceDark")) {
                            c = 'N';
                            break;
                        }
                        break;
                    case 1583791742:
                        if (key.equals("disableDefaultErrorPage")) {
                            c = 'O';
                            break;
                        }
                        break;
                    case 1759079762:
                        if (key.equals("contentBlockers")) {
                            c = 'P';
                            break;
                        }
                        break;
                    case 1774215812:
                        if (key.equals("supportMultipleWindows")) {
                            c = 'Q';
                            break;
                        }
                        break;
                    case 1793491907:
                        if (key.equals("defaultFixedFontSize")) {
                            c = Matrix.MATRIX_TYPE_RANDOM_REGULAR;
                            break;
                        }
                        break;
                    case 1812525393:
                        if (key.equals("thirdPartyCookiesEnabled")) {
                            c = 'S';
                            break;
                        }
                        break;
                    case 1956631083:
                        if (key.equals("minimumLogicalFontSize")) {
                            c = 'T';
                            break;
                        }
                        break;
                    case 2011947505:
                        if (key.equals("verticalScrollBarEnabled")) {
                            c = Matrix.MATRIX_TYPE_RANDOM_UT;
                            break;
                        }
                        break;
                    case 2038327673:
                        if (key.equals("clearSessionCache")) {
                            c = 'V';
                            break;
                        }
                        break;
                    case 2056553639:
                        if (key.equals("defaultVideoPoster")) {
                            c = 'W';
                            break;
                        }
                        break;
                    case 2086547246:
                        if (key.equals("algorithmicDarkeningAllowed")) {
                            c = 'X';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.disableHorizontalScroll = (Boolean) value;
                        break;
                    case 1:
                        this.scrollBarDefaultDelayBeforeFade = (Integer) value;
                        break;
                    case 2:
                        this.useWideViewPort = (Boolean) value;
                        break;
                    case 3:
                        this.allowFileAccessFromFileURLs = (Boolean) value;
                        break;
                    case 4:
                        this.defaultFontSize = (Integer) value;
                        break;
                    case 5:
                        this.supportZoom = (Boolean) value;
                        break;
                    case 6:
                        this.scrollbarFadingEnabled = (Boolean) value;
                        break;
                    case 7:
                        this.defaultTextEncodingName = (String) value;
                        break;
                    case '\b':
                        this.needInitialFocus = (Boolean) value;
                        break;
                    case '\t':
                        this.useShouldOverrideUrlLoading = (Boolean) value;
                        break;
                    case '\n':
                        this.preferredContentMode = (Integer) value;
                        break;
                    case 11:
                        this.allowContentAccess = (Boolean) value;
                        break;
                    case '\f':
                        this.fixedFontFamily = (String) value;
                        break;
                    case '\r':
                        this.builtInZoomControls = (Boolean) value;
                        break;
                    case 14:
                        this.javaScriptEnabled = (Boolean) value;
                        break;
                    case 15:
                        this.hardwareAcceleration = (Boolean) value;
                        break;
                    case 16:
                        this.networkAvailable = (Boolean) value;
                        break;
                    case 17:
                        this.useShouldInterceptFetchRequest = (Boolean) value;
                        break;
                    case 18:
                        this.loadsImagesAutomatically = (Boolean) value;
                        break;
                    case 19:
                        this.resourceCustomSchemes = (List) value;
                        break;
                    case 20:
                        this.disabledActionModeMenuItems = (Integer) value;
                        break;
                    case 21:
                        this.incognito = (Boolean) value;
                        break;
                    case 22:
                        this.allowBackgroundAudioPlaying = (Boolean) value;
                        break;
                    case 23:
                        this.webViewAssetLoader = (Map) value;
                        break;
                    case 24:
                        this.overScrollMode = (Integer) value;
                        break;
                    case 25:
                        this.domStorageEnabled = (Boolean) value;
                        break;
                    case 26:
                        this.disableContextMenu = (Boolean) value;
                        break;
                    case 27:
                        this.useShouldInterceptAjaxRequest = (Boolean) value;
                        break;
                    case 28:
                        this.textZoom = (Integer) value;
                        break;
                    case 29:
                        this.interceptOnlyAsyncAjaxRequests = (Boolean) value;
                        break;
                    case 30:
                        this.minimumFontSize = (Integer) value;
                        break;
                    case 31:
                        setLayoutAlgorithm((String) value);
                        break;
                    case ' ':
                        this.clearCache = (Boolean) value;
                        break;
                    case '!':
                        this.transparentBackground = (Boolean) value;
                        break;
                    case '\"':
                        this.enterpriseAuthenticationAppLinkPolicyEnabled = (Boolean) value;
                        break;
                    case '#':
                        this.allowUniversalAccessFromFileURLs = (Boolean) value;
                        break;
                    case '$':
                        this.databaseEnabled = (Boolean) value;
                        break;
                    case '%':
                        this.useShouldInterceptRequest = (Boolean) value;
                        break;
                    case '&':
                        this.cacheMode = (Integer) value;
                        break;
                    case '\'':
                        this.horizontalScrollBarEnabled = (Boolean) value;
                        break;
                    case '(':
                        this.scrollBarStyle = (Integer) value;
                        break;
                    case ')':
                        this.initialScale = (Integer) value;
                        break;
                    case '*':
                        this.verticalScrollbarPosition = (Integer) value;
                        break;
                    case '+':
                        this.disableVerticalScroll = (Boolean) value;
                        break;
                    case ',':
                        this.javaScriptCanOpenWindowsAutomatically = (Boolean) value;
                        break;
                    case '-':
                        this.horizontalScrollbarTrackColor = (String) value;
                        break;
                    case '.':
                        this.offscreenPreRaster = (Boolean) value;
                        break;
                    case '/':
                        this.fantasyFontFamily = (String) value;
                        break;
                    case '0':
                        this.rendererPriorityPolicy = (Map) value;
                        break;
                    case '1':
                        this.sansSerifFontFamily = (String) value;
                        break;
                    case '2':
                        this.regexToCancelSubFramesLoading = (String) value;
                        break;
                    case '3':
                        this.verticalScrollbarTrackColor = (String) value;
                        break;
                    case '4':
                        this.useOnDownloadStart = (Boolean) value;
                        break;
                    case '5':
                        this.forceDarkStrategy = (Integer) value;
                        break;
                    case '6':
                        this.cursiveFontFamily = (String) value;
                        break;
                    case '7':
                        this.mediaPlaybackRequiresUserGesture = (Boolean) value;
                        break;
                    case '8':
                        this.blockNetworkImage = (Boolean) value;
                        break;
                    case '9':
                        this.blockNetworkLoads = (Boolean) value;
                        break;
                    case ':':
                        this.userAgent = (String) value;
                        break;
                    case ';':
                        this.useOnRenderProcessGone = (Boolean) value;
                        break;
                    case '<':
                        this.useOnLoadResource = (Boolean) value;
                        break;
                    case '=':
                        this.cacheEnabled = (Boolean) value;
                        break;
                    case '>':
                        this.saveFormData = (Boolean) value;
                        break;
                    case '?':
                        this.requestedWithHeaderOriginAllowList = new HashSet((List) value);
                        break;
                    case '@':
                        this.useHybridComposition = (Boolean) value;
                        break;
                    case 'A':
                        this.applicationNameForUserAgent = (String) value;
                        break;
                    case 'B':
                        this.mixedContentMode = (Integer) value;
                        break;
                    case 'C':
                        this.scrollBarFadeDuration = (Integer) value;
                        break;
                    case 'D':
                        this.allowFileAccess = (Boolean) value;
                        break;
                    case 'E':
                        this.appCachePath = (String) value;
                        break;
                    case 'F':
                        this.horizontalScrollbarThumbColor = (String) value;
                        break;
                    case 'G':
                        this.standardFontFamily = (String) value;
                        break;
                    case 'H':
                        this.displayZoomControls = (Boolean) value;
                        break;
                    case 'I':
                        this.geolocationEnabled = (Boolean) value;
                        break;
                    case 'J':
                        this.loadWithOverviewMode = (Boolean) value;
                        break;
                    case 'K':
                        this.safeBrowsingEnabled = (Boolean) value;
                        break;
                    case 'L':
                        this.serifFontFamily = (String) value;
                        break;
                    case 'M':
                        this.verticalScrollbarThumbColor = (String) value;
                        break;
                    case 'N':
                        this.forceDark = (Integer) value;
                        break;
                    case 'O':
                        this.disableDefaultErrorPage = (Boolean) value;
                        break;
                    case 'P':
                        this.contentBlockers = (List) value;
                        break;
                    case 'Q':
                        this.supportMultipleWindows = (Boolean) value;
                        break;
                    case 'R':
                        this.defaultFixedFontSize = (Integer) value;
                        break;
                    case 'S':
                        this.thirdPartyCookiesEnabled = (Boolean) value;
                        break;
                    case 'T':
                        this.minimumLogicalFontSize = (Integer) value;
                        break;
                    case 'U':
                        this.verticalScrollBarEnabled = (Boolean) value;
                        break;
                    case 'V':
                        this.clearSessionCache = (Boolean) value;
                        break;
                    case 'W':
                        this.defaultVideoPoster = (byte[]) value;
                        break;
                    case 'X':
                        this.algorithmicDarkeningAllowed = (Boolean) value;
                        break;
                }
            }
        }
        return this;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("useShouldOverrideUrlLoading", this.useShouldOverrideUrlLoading);
        hashMap.put("useOnLoadResource", this.useOnLoadResource);
        hashMap.put("useOnDownloadStart", this.useOnDownloadStart);
        hashMap.put("clearCache", this.clearCache);
        hashMap.put("userAgent", this.userAgent);
        hashMap.put("applicationNameForUserAgent", this.applicationNameForUserAgent);
        hashMap.put("javaScriptEnabled", this.javaScriptEnabled);
        hashMap.put("javaScriptCanOpenWindowsAutomatically", this.javaScriptCanOpenWindowsAutomatically);
        hashMap.put("mediaPlaybackRequiresUserGesture", this.mediaPlaybackRequiresUserGesture);
        hashMap.put("minimumFontSize", this.minimumFontSize);
        hashMap.put("verticalScrollBarEnabled", this.verticalScrollBarEnabled);
        hashMap.put("horizontalScrollBarEnabled", this.horizontalScrollBarEnabled);
        hashMap.put("resourceCustomSchemes", this.resourceCustomSchemes);
        hashMap.put("contentBlockers", this.contentBlockers);
        hashMap.put("preferredContentMode", this.preferredContentMode);
        hashMap.put("useShouldInterceptAjaxRequest", this.useShouldInterceptAjaxRequest);
        hashMap.put("interceptOnlyAsyncAjaxRequests", this.interceptOnlyAsyncAjaxRequests);
        hashMap.put("useShouldInterceptFetchRequest", this.useShouldInterceptFetchRequest);
        hashMap.put("incognito", this.incognito);
        hashMap.put("cacheEnabled", this.cacheEnabled);
        hashMap.put("transparentBackground", this.transparentBackground);
        hashMap.put("disableVerticalScroll", this.disableVerticalScroll);
        hashMap.put("disableHorizontalScroll", this.disableHorizontalScroll);
        hashMap.put("disableContextMenu", this.disableContextMenu);
        hashMap.put("textZoom", this.textZoom);
        hashMap.put("clearSessionCache", this.clearSessionCache);
        hashMap.put("builtInZoomControls", this.builtInZoomControls);
        hashMap.put("displayZoomControls", this.displayZoomControls);
        hashMap.put("supportZoom", this.supportZoom);
        hashMap.put("databaseEnabled", this.databaseEnabled);
        hashMap.put("domStorageEnabled", this.domStorageEnabled);
        hashMap.put("useWideViewPort", this.useWideViewPort);
        hashMap.put("safeBrowsingEnabled", this.safeBrowsingEnabled);
        hashMap.put("mixedContentMode", this.mixedContentMode);
        hashMap.put("allowContentAccess", this.allowContentAccess);
        hashMap.put("allowFileAccess", this.allowFileAccess);
        hashMap.put("allowFileAccessFromFileURLs", this.allowFileAccessFromFileURLs);
        hashMap.put("allowUniversalAccessFromFileURLs", this.allowUniversalAccessFromFileURLs);
        hashMap.put("appCachePath", this.appCachePath);
        hashMap.put("blockNetworkImage", this.blockNetworkImage);
        hashMap.put("blockNetworkLoads", this.blockNetworkLoads);
        hashMap.put("cacheMode", this.cacheMode);
        hashMap.put("cursiveFontFamily", this.cursiveFontFamily);
        hashMap.put("defaultFixedFontSize", this.defaultFixedFontSize);
        hashMap.put("defaultFontSize", this.defaultFontSize);
        hashMap.put("defaultTextEncodingName", this.defaultTextEncodingName);
        hashMap.put("disabledActionModeMenuItems", this.disabledActionModeMenuItems);
        hashMap.put("fantasyFontFamily", this.fantasyFontFamily);
        hashMap.put("fixedFontFamily", this.fixedFontFamily);
        hashMap.put("forceDark", this.forceDark);
        hashMap.put("forceDarkStrategy", this.forceDarkStrategy);
        hashMap.put("geolocationEnabled", this.geolocationEnabled);
        hashMap.put("layoutAlgorithm", getLayoutAlgorithm());
        hashMap.put("loadWithOverviewMode", this.loadWithOverviewMode);
        hashMap.put("loadsImagesAutomatically", this.loadsImagesAutomatically);
        hashMap.put("minimumLogicalFontSize", this.minimumLogicalFontSize);
        hashMap.put("initialScale", this.initialScale);
        hashMap.put("needInitialFocus", this.needInitialFocus);
        hashMap.put("offscreenPreRaster", this.offscreenPreRaster);
        hashMap.put("sansSerifFontFamily", this.sansSerifFontFamily);
        hashMap.put("serifFontFamily", this.serifFontFamily);
        hashMap.put("standardFontFamily", this.standardFontFamily);
        hashMap.put("saveFormData", this.saveFormData);
        hashMap.put("thirdPartyCookiesEnabled", this.thirdPartyCookiesEnabled);
        hashMap.put("hardwareAcceleration", this.hardwareAcceleration);
        hashMap.put("supportMultipleWindows", this.supportMultipleWindows);
        hashMap.put("regexToCancelSubFramesLoading", this.regexToCancelSubFramesLoading);
        hashMap.put("overScrollMode", this.overScrollMode);
        hashMap.put("networkAvailable", this.networkAvailable);
        hashMap.put("scrollBarStyle", this.scrollBarStyle);
        hashMap.put("verticalScrollbarPosition", this.verticalScrollbarPosition);
        hashMap.put("scrollBarDefaultDelayBeforeFade", this.scrollBarDefaultDelayBeforeFade);
        hashMap.put("scrollbarFadingEnabled", this.scrollbarFadingEnabled);
        hashMap.put("scrollBarFadeDuration", this.scrollBarFadeDuration);
        hashMap.put("rendererPriorityPolicy", this.rendererPriorityPolicy);
        hashMap.put("useShouldInterceptRequest", this.useShouldInterceptRequest);
        hashMap.put("useOnRenderProcessGone", this.useOnRenderProcessGone);
        hashMap.put("disableDefaultErrorPage", this.disableDefaultErrorPage);
        hashMap.put("useHybridComposition", this.useHybridComposition);
        hashMap.put("verticalScrollbarThumbColor", this.verticalScrollbarThumbColor);
        hashMap.put("verticalScrollbarTrackColor", this.verticalScrollbarTrackColor);
        hashMap.put("horizontalScrollbarThumbColor", this.horizontalScrollbarThumbColor);
        hashMap.put("horizontalScrollbarTrackColor", this.horizontalScrollbarTrackColor);
        hashMap.put("algorithmicDarkeningAllowed", this.algorithmicDarkeningAllowed);
        hashMap.put("enterpriseAuthenticationAppLinkPolicyEnabled", this.enterpriseAuthenticationAppLinkPolicyEnabled);
        hashMap.put("allowBackgroundAudioPlaying", this.allowBackgroundAudioPlaying);
        hashMap.put("defaultVideoPoster", this.defaultVideoPoster);
        hashMap.put("requestedWithHeaderOriginAllowList", this.requestedWithHeaderOriginAllowList != null ? new ArrayList(this.requestedWithHeaderOriginAllowList) : null);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(InAppWebViewInterface inAppWebViewInterface) {
        Map<String, Object> map = toMap();
        if (inAppWebViewInterface instanceof InAppWebView) {
            InAppWebView inAppWebView = (InAppWebView) inAppWebViewInterface;
            WebSettings settings = inAppWebView.getSettings();
            map.put("userAgent", settings.getUserAgentString());
            map.put("javaScriptEnabled", Boolean.valueOf(settings.getJavaScriptEnabled()));
            map.put("javaScriptCanOpenWindowsAutomatically", Boolean.valueOf(settings.getJavaScriptCanOpenWindowsAutomatically()));
            map.put("mediaPlaybackRequiresUserGesture", Boolean.valueOf(settings.getMediaPlaybackRequiresUserGesture()));
            map.put("minimumFontSize", Integer.valueOf(settings.getMinimumFontSize()));
            map.put("verticalScrollBarEnabled", Boolean.valueOf(inAppWebView.isVerticalScrollBarEnabled()));
            map.put("horizontalScrollBarEnabled", Boolean.valueOf(inAppWebView.isHorizontalScrollBarEnabled()));
            map.put("textZoom", Integer.valueOf(settings.getTextZoom()));
            map.put("builtInZoomControls", Boolean.valueOf(settings.getBuiltInZoomControls()));
            map.put("supportZoom", Boolean.valueOf(settings.supportZoom()));
            map.put("displayZoomControls", Boolean.valueOf(settings.getDisplayZoomControls()));
            map.put("databaseEnabled", Boolean.valueOf(settings.getDatabaseEnabled()));
            map.put("domStorageEnabled", Boolean.valueOf(settings.getDomStorageEnabled()));
            map.put("useWideViewPort", Boolean.valueOf(settings.getUseWideViewPort()));
            if (WebViewFeature.isFeatureSupported("SAFE_BROWSING_ENABLE")) {
                map.put("safeBrowsingEnabled", Boolean.valueOf(WebSettingsCompat.getSafeBrowsingEnabled(settings)));
            } else if (Build.VERSION.SDK_INT >= 26) {
                map.put("safeBrowsingEnabled", Boolean.valueOf(settings.getSafeBrowsingEnabled()));
            }
            if (Build.VERSION.SDK_INT >= 21) {
                map.put("mixedContentMode", Integer.valueOf(settings.getMixedContentMode()));
            }
            map.put("allowContentAccess", Boolean.valueOf(settings.getAllowContentAccess()));
            map.put("allowFileAccess", Boolean.valueOf(settings.getAllowFileAccess()));
            map.put("allowFileAccessFromFileURLs", Boolean.valueOf(settings.getAllowFileAccessFromFileURLs()));
            map.put("allowUniversalAccessFromFileURLs", Boolean.valueOf(settings.getAllowUniversalAccessFromFileURLs()));
            map.put("blockNetworkImage", Boolean.valueOf(settings.getBlockNetworkImage()));
            map.put("blockNetworkLoads", Boolean.valueOf(settings.getBlockNetworkLoads()));
            map.put("cacheMode", Integer.valueOf(settings.getCacheMode()));
            map.put("cursiveFontFamily", settings.getCursiveFontFamily());
            map.put("defaultFixedFontSize", Integer.valueOf(settings.getDefaultFixedFontSize()));
            map.put("defaultFontSize", Integer.valueOf(settings.getDefaultFontSize()));
            map.put("defaultTextEncodingName", settings.getDefaultTextEncodingName());
            if (WebViewFeature.isFeatureSupported("DISABLED_ACTION_MODE_MENU_ITEMS")) {
                map.put("disabledActionModeMenuItems", Integer.valueOf(WebSettingsCompat.getDisabledActionModeMenuItems(settings)));
            }
            if (Build.VERSION.SDK_INT >= 24) {
                map.put("disabledActionModeMenuItems", Integer.valueOf(settings.getDisabledActionModeMenuItems()));
            }
            map.put("fantasyFontFamily", settings.getFantasyFontFamily());
            map.put("fixedFontFamily", settings.getFixedFontFamily());
            if (WebViewFeature.isFeatureSupported("FORCE_DARK")) {
                map.put("forceDark", Integer.valueOf(WebSettingsCompat.getForceDark(settings)));
            } else if (Build.VERSION.SDK_INT >= 29) {
                map.put("forceDark", Integer.valueOf(settings.getForceDark()));
            }
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
                map.put("forceDarkStrategy", Integer.valueOf(WebSettingsCompat.getForceDarkStrategy(settings)));
            }
            map.put("layoutAlgorithm", settings.getLayoutAlgorithm().name());
            map.put("loadWithOverviewMode", Boolean.valueOf(settings.getLoadWithOverviewMode()));
            map.put("loadsImagesAutomatically", Boolean.valueOf(settings.getLoadsImagesAutomatically()));
            map.put("minimumLogicalFontSize", Integer.valueOf(settings.getMinimumLogicalFontSize()));
            if (WebViewFeature.isFeatureSupported("OFF_SCREEN_PRERASTER")) {
                map.put("offscreenPreRaster", Boolean.valueOf(WebSettingsCompat.getOffscreenPreRaster(settings)));
            } else if (Build.VERSION.SDK_INT >= 23) {
                map.put("offscreenPreRaster", Boolean.valueOf(settings.getOffscreenPreRaster()));
            }
            map.put("sansSerifFontFamily", settings.getSansSerifFontFamily());
            map.put("serifFontFamily", settings.getSerifFontFamily());
            map.put("standardFontFamily", settings.getStandardFontFamily());
            map.put("saveFormData", Boolean.valueOf(settings.getSaveFormData()));
            map.put("supportMultipleWindows", Boolean.valueOf(settings.supportMultipleWindows()));
            map.put("overScrollMode", Integer.valueOf(inAppWebView.getOverScrollMode()));
            map.put("scrollBarStyle", Integer.valueOf(inAppWebView.getScrollBarStyle()));
            map.put("verticalScrollbarPosition", Integer.valueOf(inAppWebView.getVerticalScrollbarPosition()));
            map.put("scrollBarDefaultDelayBeforeFade", Integer.valueOf(inAppWebView.getScrollBarDefaultDelayBeforeFade()));
            map.put("scrollbarFadingEnabled", Boolean.valueOf(inAppWebView.isScrollbarFadingEnabled()));
            map.put("scrollBarFadeDuration", Integer.valueOf(inAppWebView.getScrollBarFadeDuration()));
            if (Build.VERSION.SDK_INT >= 26) {
                HashMap hashMap = new HashMap();
                hashMap.put("rendererRequestedPriority", Integer.valueOf(inAppWebView.getRendererRequestedPriority()));
                hashMap.put("waivedWhenNotVisible", Boolean.valueOf(inAppWebView.getRendererPriorityWaivedWhenNotVisible()));
                map.put("rendererPriorityPolicy", hashMap);
            }
            if (WebViewFeature.isFeatureSupported("ALGORITHMIC_DARKENING") && Build.VERSION.SDK_INT >= 29) {
                map.put("algorithmicDarkeningAllowed", Boolean.valueOf(WebSettingsCompat.isAlgorithmicDarkeningAllowed(settings)));
            }
            if (WebViewFeature.isFeatureSupported("ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY")) {
                map.put("enterpriseAuthenticationAppLinkPolicyEnabled", Boolean.valueOf(WebSettingsCompat.getEnterpriseAuthenticationAppLinkPolicyEnabled(settings)));
            }
            if (WebViewFeature.isFeatureSupported("REQUESTED_WITH_HEADER_ALLOW_LIST")) {
                map.put("requestedWithHeaderOriginAllowList", new ArrayList(WebSettingsCompat.getRequestedWithHeaderOriginAllowList(settings)));
            }
        }
        return map;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x002e. Please report as an issue. */
    private void setLayoutAlgorithm(String str) {
        if (str == null) {
            return;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1986416409:
                if (str.equals("NORMAL")) {
                    c = 0;
                    break;
                }
                break;
            case 1055046617:
                if (str.equals("NARROW_COLUMNS")) {
                    c = 1;
                    break;
                }
                break;
            case 1561826623:
                if (str.equals("TEXT_AUTOSIZING")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
                this.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS;
            case 0:
                this.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL;
            case 2:
                if (Build.VERSION.SDK_INT >= 19) {
                    this.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING;
                    return;
                } else {
                    this.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL;
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewSettings$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm;

        static {
            int[] iArr = new int[WebSettings.LayoutAlgorithm.values().length];
            $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm = iArr;
            try {
                iArr[WebSettings.LayoutAlgorithm.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm[WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm[WebSettings.LayoutAlgorithm.NARROW_COLUMNS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private String getLayoutAlgorithm() {
        if (this.layoutAlgorithm == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$android$webkit$WebSettings$LayoutAlgorithm[this.layoutAlgorithm.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return null;
                }
                return "NARROW_COLUMNS";
            }
            if (Build.VERSION.SDK_INT >= 19) {
                return "TEXT_AUTOSIZING";
            }
        }
        return "NORMAL";
    }
}
