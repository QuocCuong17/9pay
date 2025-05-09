package com.google.firebase.firestore.model;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ResourcePath extends BasePath<ResourcePath> {
    public static final ResourcePath EMPTY = new ResourcePath(Collections.emptyList());

    @Override // com.google.firebase.firestore.model.BasePath
    /* bridge */ /* synthetic */ ResourcePath createPathWithSegments(List list) {
        return createPathWithSegments2((List<String>) list);
    }

    private ResourcePath(List<String> list) {
        super(list);
    }

    @Override // com.google.firebase.firestore.model.BasePath
    /* renamed from: createPathWithSegments, reason: avoid collision after fix types in other method */
    ResourcePath createPathWithSegments2(List<String> list) {
        return new ResourcePath(list);
    }

    public static ResourcePath fromSegments(List<String> list) {
        return list.isEmpty() ? EMPTY : new ResourcePath(list);
    }

    public static ResourcePath fromString(String str) {
        if (str.contains("//")) {
            throw new IllegalArgumentException("Invalid path (" + str + "). Paths must not contain // in them.");
        }
        String[] split = str.split(RemoteSettings.FORWARD_SLASH_STRING);
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.isEmpty()) {
                arrayList.add(str2);
            }
        }
        return new ResourcePath(arrayList);
    }

    @Override // com.google.firebase.firestore.model.BasePath
    public String canonicalString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.segments.size(); i++) {
            if (i > 0) {
                sb.append(RemoteSettings.FORWARD_SLASH_STRING);
            }
            sb.append(this.segments.get(i));
        }
        return sb.toString();
    }
}
