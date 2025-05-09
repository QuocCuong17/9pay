package co.hyperverge.hypersnapsdk.utils;

import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes2.dex */
public class NonBlockingFixedSizeQueue<E> extends ArrayBlockingQueue<E> {
    private static final long serialVersionUID = -7772085623838075506L;
    private int size;

    public NonBlockingFixedSizeQueue(int i) {
        super(i);
        this.size = i;
    }

    @Override // java.util.concurrent.ArrayBlockingQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.concurrent.BlockingQueue
    public synchronized boolean add(E e) {
        if (super.size() == this.size) {
            remove();
        }
        return super.add(e);
    }
}
