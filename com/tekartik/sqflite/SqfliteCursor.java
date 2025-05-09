package com.tekartik.sqflite;

import android.database.Cursor;

/* loaded from: classes5.dex */
public class SqfliteCursor {
    final Cursor cursor;
    final int cursorId;
    final int pageSize;

    public SqfliteCursor(int i, int i2, Cursor cursor) {
        this.cursorId = i;
        this.pageSize = i2;
        this.cursor = cursor;
    }
}
