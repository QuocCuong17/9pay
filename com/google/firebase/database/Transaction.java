package com.google.firebase.database;

import com.google.firebase.database.snapshot.Node;

/* loaded from: classes4.dex */
public class Transaction {

    /* loaded from: classes4.dex */
    public interface Handler {
        Result doTransaction(MutableData mutableData);

        void onComplete(DatabaseError databaseError, boolean z, DataSnapshot dataSnapshot);
    }

    /* loaded from: classes4.dex */
    public static class Result {
        private Node data;
        private boolean success;

        private Result(boolean z, Node node) {
            this.success = z;
            this.data = node;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public Node getNode() {
            return this.data;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Result abort() {
        return new Result(false, null);
    }

    public static Result success(MutableData mutableData) {
        return new Result(true, mutableData.getNode());
    }
}
