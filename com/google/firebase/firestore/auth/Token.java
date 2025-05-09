package com.google.firebase.firestore.auth;

/* loaded from: classes4.dex */
public final class Token {
    private final User user;
    private final String value;

    public Token(String str, User user) {
        this.value = str;
        this.user = user;
    }

    public String getValue() {
        return this.value;
    }

    public User getUser() {
        return this.user;
    }
}
