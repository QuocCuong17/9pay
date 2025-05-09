package com.google.firebase.database;

/* loaded from: classes4.dex */
public interface ValueEventListener {
    void onCancelled(DatabaseError databaseError);

    void onDataChange(DataSnapshot dataSnapshot);
}
