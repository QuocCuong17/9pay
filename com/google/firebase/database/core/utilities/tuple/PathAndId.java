package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.core.Path;

/* loaded from: classes4.dex */
public class PathAndId {

    /* renamed from: id, reason: collision with root package name */
    private long f83id;
    private Path path;

    public PathAndId(Path path, long j) {
        this.path = path;
        this.f83id = j;
    }

    public Path getPath() {
        return this.path;
    }

    public long getId() {
        return this.f83id;
    }
}
