package com.facebook.appevents.iap;

import com.google.firebase.analytics.FirebaseAnalytics;
import io.flutter.plugins.firebase.analytics.Constants;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InAppPurchase.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/facebook/appevents/iap/InAppPurchase;", "", Constants.EVENT_NAME, "", "amount", "", FirebaseAnalytics.Param.CURRENCY, "Ljava/util/Currency;", "(Ljava/lang/String;DLjava/util/Currency;)V", "getAmount", "()D", "getCurrency", "()Ljava/util/Currency;", "getEventName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class InAppPurchase {
    private final double amount;
    private final Currency currency;
    private final String eventName;

    public static /* synthetic */ InAppPurchase copy$default(InAppPurchase inAppPurchase, String str, double d, Currency currency, int i, Object obj) {
        if ((i & 1) != 0) {
            str = inAppPurchase.eventName;
        }
        if ((i & 2) != 0) {
            d = inAppPurchase.amount;
        }
        if ((i & 4) != 0) {
            currency = inAppPurchase.currency;
        }
        return inAppPurchase.copy(str, d, currency);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEventName() {
        return this.eventName;
    }

    /* renamed from: component2, reason: from getter */
    public final double getAmount() {
        return this.amount;
    }

    /* renamed from: component3, reason: from getter */
    public final Currency getCurrency() {
        return this.currency;
    }

    public final InAppPurchase copy(String eventName, double amount, Currency currency) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(currency, "currency");
        return new InAppPurchase(eventName, amount, currency);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InAppPurchase)) {
            return false;
        }
        InAppPurchase inAppPurchase = (InAppPurchase) other;
        return Intrinsics.areEqual(this.eventName, inAppPurchase.eventName) && Double.compare(this.amount, inAppPurchase.amount) == 0 && Intrinsics.areEqual(this.currency, inAppPurchase.currency);
    }

    public int hashCode() {
        return (((this.eventName.hashCode() * 31) + InAppPurchase$$ExternalSyntheticBackport0.m(this.amount)) * 31) + this.currency.hashCode();
    }

    public String toString() {
        return "InAppPurchase(eventName=" + this.eventName + ", amount=" + this.amount + ", currency=" + this.currency + ')';
    }

    public InAppPurchase(String eventName, double d, Currency currency) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(currency, "currency");
        this.eventName = eventName;
        this.amount = d;
        this.currency = currency;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final Currency getCurrency() {
        return this.currency;
    }

    public final String getEventName() {
        return this.eventName;
    }
}
