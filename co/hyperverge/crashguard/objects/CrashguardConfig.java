package co.hyperverge.crashguard.objects;

import co.hyperverge.crashguard.BuildConfig;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashguardConfig.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0015\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J9\u0010$\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0013\u0010%\u001a\u00020\u00182\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006*"}, d2 = {"Lco/hyperverge/crashguard/objects/CrashguardConfig;", "", "filters", "", "", "tags", "", "dsn", "(Ljava/util/List;Ljava/util/Map;Ljava/lang/String;)V", "getDsn", "()Ljava/lang/String;", "setDsn", "(Ljava/lang/String;)V", "getFilters", "()Ljava/util/List;", "setFilters", "(Ljava/util/List;)V", "sentryProperties", "Lco/hyperverge/crashguard/objects/SentryProperties;", "getSentryProperties$crashguard_release", "()Lco/hyperverge/crashguard/objects/SentryProperties;", "setSentryProperties$crashguard_release", "(Lco/hyperverge/crashguard/objects/SentryProperties;)V", "shouldReportCrashes", "", "getShouldReportCrashes", "()Z", "setShouldReportCrashes", "(Z)V", "getTags", "()Ljava/util/Map;", "setTags", "(Ljava/util/Map;)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "crashguard_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class CrashguardConfig {
    private String dsn;
    private List<String> filters;
    private SentryProperties sentryProperties;
    private boolean shouldReportCrashes;
    private Map<String, String> tags;

    public CrashguardConfig() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CrashguardConfig(List<String> filters) {
        this(filters, null, null, 6, null);
        Intrinsics.checkNotNullParameter(filters, "filters");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CrashguardConfig(List<String> filters, Map<String, String> tags) {
        this(filters, tags, null, 4, null);
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(tags, "tags");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CrashguardConfig copy$default(CrashguardConfig crashguardConfig, List list, Map map, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = crashguardConfig.filters;
        }
        if ((i & 2) != 0) {
            map = crashguardConfig.tags;
        }
        if ((i & 4) != 0) {
            str = crashguardConfig.dsn;
        }
        return crashguardConfig.copy(list, map, str);
    }

    public final List<String> component1() {
        return this.filters;
    }

    public final Map<String, String> component2() {
        return this.tags;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDsn() {
        return this.dsn;
    }

    public final CrashguardConfig copy(List<String> filters, Map<String, String> tags, String dsn) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(dsn, "dsn");
        return new CrashguardConfig(filters, tags, dsn);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CrashguardConfig)) {
            return false;
        }
        CrashguardConfig crashguardConfig = (CrashguardConfig) other;
        return Intrinsics.areEqual(this.filters, crashguardConfig.filters) && Intrinsics.areEqual(this.tags, crashguardConfig.tags) && Intrinsics.areEqual(this.dsn, crashguardConfig.dsn);
    }

    public int hashCode() {
        return (((this.filters.hashCode() * 31) + this.tags.hashCode()) * 31) + this.dsn.hashCode();
    }

    public String toString() {
        return "CrashguardConfig(filters=" + this.filters + ", tags=" + this.tags + ", dsn=" + this.dsn + ')';
    }

    public CrashguardConfig(List<String> filters, Map<String, String> tags, String dsn) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(dsn, "dsn");
        this.filters = filters;
        this.tags = tags;
        this.dsn = dsn;
        this.sentryProperties = new SentryProperties(dsn);
        this.shouldReportCrashes = true;
        ((HashMap) this.tags).put("crashguard_sdk_version", BuildConfig.CG_VERSION_CODE);
    }

    public /* synthetic */ CrashguardConfig(List list, HashMap hashMap, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? new HashMap() : hashMap, (i & 4) != 0 ? "https://21cef71639181e52da8d135031a8b583@o435277.ingest.sentry.io/4506256630153216" : str);
    }

    public final List<String> getFilters() {
        return this.filters;
    }

    public final void setFilters(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.filters = list;
    }

    public final Map<String, String> getTags() {
        return this.tags;
    }

    public final void setTags(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.tags = map;
    }

    public final String getDsn() {
        return this.dsn;
    }

    public final void setDsn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dsn = str;
    }

    /* renamed from: getSentryProperties$crashguard_release, reason: from getter */
    public final SentryProperties getSentryProperties() {
        return this.sentryProperties;
    }

    public final void setSentryProperties$crashguard_release(SentryProperties sentryProperties) {
        Intrinsics.checkNotNullParameter(sentryProperties, "<set-?>");
        this.sentryProperties = sentryProperties;
    }

    public final boolean getShouldReportCrashes() {
        return this.shouldReportCrashes;
    }

    public final void setShouldReportCrashes(boolean z) {
        this.shouldReportCrashes = z;
    }
}
