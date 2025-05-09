package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.view.QuerySpec;

/* loaded from: classes4.dex */
public final class TrackedQuery {
    public final boolean active;
    public final boolean complete;

    /* renamed from: id, reason: collision with root package name */
    public final long f82id;
    public final long lastUse;
    public final QuerySpec querySpec;

    public TrackedQuery(long j, QuerySpec querySpec, long j2, boolean z, boolean z2) {
        this.f82id = j;
        if (querySpec.loadsAllData() && !querySpec.isDefault()) {
            throw new IllegalArgumentException("Can't create TrackedQuery for a non-default query that loads all data");
        }
        this.querySpec = querySpec;
        this.lastUse = j2;
        this.complete = z;
        this.active = z2;
    }

    public TrackedQuery updateLastUse(long j) {
        return new TrackedQuery(this.f82id, this.querySpec, j, this.complete, this.active);
    }

    public TrackedQuery setComplete() {
        return new TrackedQuery(this.f82id, this.querySpec, this.lastUse, true, this.active);
    }

    public TrackedQuery setActiveState(boolean z) {
        return new TrackedQuery(this.f82id, this.querySpec, this.lastUse, this.complete, z);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        TrackedQuery trackedQuery = (TrackedQuery) obj;
        return this.f82id == trackedQuery.f82id && this.querySpec.equals(trackedQuery.querySpec) && this.lastUse == trackedQuery.lastUse && this.complete == trackedQuery.complete && this.active == trackedQuery.active;
    }

    public int hashCode() {
        return (((((((Long.valueOf(this.f82id).hashCode() * 31) + this.querySpec.hashCode()) * 31) + Long.valueOf(this.lastUse).hashCode()) * 31) + Boolean.valueOf(this.complete).hashCode()) * 31) + Boolean.valueOf(this.active).hashCode();
    }

    public String toString() {
        return "TrackedQuery{id=" + this.f82id + ", querySpec=" + this.querySpec + ", lastUse=" + this.lastUse + ", complete=" + this.complete + ", active=" + this.active + "}";
    }
}
