package com.google.firebase.sessions.settings;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsCache.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0010JJ\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0007HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0015\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/google/firebase/sessions/settings/SessionConfigs;", "", "sessionEnabled", "", "sessionSamplingRate", "", "sessionRestartTimeout", "", "cacheDuration", "cacheUpdatedTime", "", "(Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)V", "getCacheDuration", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCacheUpdatedTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSessionEnabled", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSessionRestartTimeout", "getSessionSamplingRate", "()Ljava/lang/Double;", "Ljava/lang/Double;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)Lcom/google/firebase/sessions/settings/SessionConfigs;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class SessionConfigs {
    private final Integer cacheDuration;
    private final Long cacheUpdatedTime;
    private final Boolean sessionEnabled;
    private final Integer sessionRestartTimeout;
    private final Double sessionSamplingRate;

    public static /* synthetic */ SessionConfigs copy$default(SessionConfigs sessionConfigs, Boolean bool, Double d, Integer num, Integer num2, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = sessionConfigs.sessionEnabled;
        }
        if ((i & 2) != 0) {
            d = sessionConfigs.sessionSamplingRate;
        }
        Double d2 = d;
        if ((i & 4) != 0) {
            num = sessionConfigs.sessionRestartTimeout;
        }
        Integer num3 = num;
        if ((i & 8) != 0) {
            num2 = sessionConfigs.cacheDuration;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            l = sessionConfigs.cacheUpdatedTime;
        }
        return sessionConfigs.copy(bool, d2, num3, num4, l);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getSessionEnabled() {
        return this.sessionEnabled;
    }

    /* renamed from: component2, reason: from getter */
    public final Double getSessionSamplingRate() {
        return this.sessionSamplingRate;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSessionRestartTimeout() {
        return this.sessionRestartTimeout;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getCacheDuration() {
        return this.cacheDuration;
    }

    /* renamed from: component5, reason: from getter */
    public final Long getCacheUpdatedTime() {
        return this.cacheUpdatedTime;
    }

    public final SessionConfigs copy(Boolean sessionEnabled, Double sessionSamplingRate, Integer sessionRestartTimeout, Integer cacheDuration, Long cacheUpdatedTime) {
        return new SessionConfigs(sessionEnabled, sessionSamplingRate, sessionRestartTimeout, cacheDuration, cacheUpdatedTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionConfigs)) {
            return false;
        }
        SessionConfigs sessionConfigs = (SessionConfigs) other;
        return Intrinsics.areEqual(this.sessionEnabled, sessionConfigs.sessionEnabled) && Intrinsics.areEqual((Object) this.sessionSamplingRate, (Object) sessionConfigs.sessionSamplingRate) && Intrinsics.areEqual(this.sessionRestartTimeout, sessionConfigs.sessionRestartTimeout) && Intrinsics.areEqual(this.cacheDuration, sessionConfigs.cacheDuration) && Intrinsics.areEqual(this.cacheUpdatedTime, sessionConfigs.cacheUpdatedTime);
    }

    public int hashCode() {
        Boolean bool = this.sessionEnabled;
        int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Double d = this.sessionSamplingRate;
        int hashCode2 = (hashCode + (d == null ? 0 : d.hashCode())) * 31;
        Integer num = this.sessionRestartTimeout;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.cacheDuration;
        int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l = this.cacheUpdatedTime;
        return hashCode4 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "SessionConfigs(sessionEnabled=" + this.sessionEnabled + ", sessionSamplingRate=" + this.sessionSamplingRate + ", sessionRestartTimeout=" + this.sessionRestartTimeout + ", cacheDuration=" + this.cacheDuration + ", cacheUpdatedTime=" + this.cacheUpdatedTime + ')';
    }

    public SessionConfigs(Boolean bool, Double d, Integer num, Integer num2, Long l) {
        this.sessionEnabled = bool;
        this.sessionSamplingRate = d;
        this.sessionRestartTimeout = num;
        this.cacheDuration = num2;
        this.cacheUpdatedTime = l;
    }

    public final Boolean getSessionEnabled() {
        return this.sessionEnabled;
    }

    public final Double getSessionSamplingRate() {
        return this.sessionSamplingRate;
    }

    public final Integer getSessionRestartTimeout() {
        return this.sessionRestartTimeout;
    }

    public final Integer getCacheDuration() {
        return this.cacheDuration;
    }

    public final Long getCacheUpdatedTime() {
        return this.cacheUpdatedTime;
    }
}
