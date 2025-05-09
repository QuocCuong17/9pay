package com.google.firebase.storage.internal;

/* loaded from: classes4.dex */
public class SleeperImpl implements Sleeper {
    @Override // com.google.firebase.storage.internal.Sleeper
    public void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }
}
