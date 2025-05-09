package com.google.firebase.database.core.utilities;

/* loaded from: classes4.dex */
public final class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T t, U u) {
        this.first = t;
        this.second = u;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        T t = this.first;
        if (t == null ? pair.first != null : !t.equals(pair.first)) {
            return false;
        }
        U u = this.second;
        U u2 = pair.second;
        return u == null ? u2 == null : u.equals(u2);
    }

    public int hashCode() {
        T t = this.first;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        U u = this.second;
        return hashCode + (u != null ? u.hashCode() : 0);
    }

    public String toString() {
        return "Pair(" + this.first + "," + this.second + ")";
    }
}
