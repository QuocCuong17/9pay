package com.google.firebase.database.connection;

import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class CompoundHash {
    private final List<String> hashes;
    private final List<List<String>> posts;

    public CompoundHash(List<List<String>> list, List<String> list2) {
        if (list.size() != list2.size() - 1) {
            throw new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
        }
        this.posts = list;
        this.hashes = list2;
    }

    public List<List<String>> getPosts() {
        return Collections.unmodifiableList(this.posts);
    }

    public List<String> getHashes() {
        return Collections.unmodifiableList(this.hashes);
    }
}
