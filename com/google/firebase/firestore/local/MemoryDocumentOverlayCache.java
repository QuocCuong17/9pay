package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class MemoryDocumentOverlayCache implements DocumentOverlayCache {
    private final TreeMap<DocumentKey, Overlay> overlays = new TreeMap<>();
    private final Map<Integer, Set<DocumentKey>> overlayByBatchId = new HashMap();

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Overlay getOverlay(DocumentKey documentKey) {
        return this.overlays.get(documentKey);
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> sortedSet) {
        HashMap hashMap = new HashMap();
        for (DocumentKey documentKey : sortedSet) {
            Overlay overlay = this.overlays.get(documentKey);
            if (overlay != null) {
                hashMap.put(documentKey, overlay);
            }
        }
        return hashMap;
    }

    private void saveOverlay(int i, Mutation mutation) {
        Overlay overlay = this.overlays.get(mutation.getKey());
        if (overlay != null) {
            this.overlayByBatchId.get(Integer.valueOf(overlay.getLargestBatchId())).remove(mutation.getKey());
        }
        this.overlays.put(mutation.getKey(), Overlay.create(i, mutation));
        if (this.overlayByBatchId.get(Integer.valueOf(i)) == null) {
            this.overlayByBatchId.put(Integer.valueOf(i), new HashSet());
        }
        this.overlayByBatchId.get(Integer.valueOf(i)).add(mutation.getKey());
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void saveOverlays(int i, Map<DocumentKey, Mutation> map) {
        for (Map.Entry<DocumentKey, Mutation> entry : map.entrySet()) {
            saveOverlay(i, (Mutation) Preconditions.checkNotNull(entry.getValue(), "null value for key: %s", entry.getKey()));
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void removeOverlaysForBatchId(int i) {
        if (this.overlayByBatchId.containsKey(Integer.valueOf(i))) {
            Set<DocumentKey> set = this.overlayByBatchId.get(Integer.valueOf(i));
            this.overlayByBatchId.remove(Integer.valueOf(i));
            Iterator<DocumentKey> it = set.iterator();
            while (it.hasNext()) {
                this.overlays.remove(it.next());
            }
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(ResourcePath resourcePath, int i) {
        HashMap hashMap = new HashMap();
        int length = resourcePath.length() + 1;
        for (Overlay overlay : this.overlays.tailMap(DocumentKey.fromPath(resourcePath.append(""))).values()) {
            DocumentKey key = overlay.getKey();
            if (!resourcePath.isPrefixOf(key.getPath())) {
                break;
            }
            if (key.getPath().length() == length && overlay.getLargestBatchId() > i) {
                hashMap.put(overlay.getKey(), overlay);
            }
        }
        return hashMap;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(String str, int i, int i2) {
        TreeMap treeMap = new TreeMap();
        for (Overlay overlay : this.overlays.values()) {
            if (overlay.getKey().getCollectionGroup().equals(str) && overlay.getLargestBatchId() > i) {
                Map map = (Map) treeMap.get(Integer.valueOf(overlay.getLargestBatchId()));
                if (map == null) {
                    map = new HashMap();
                    treeMap.put(Integer.valueOf(overlay.getLargestBatchId()), map);
                }
                map.put(overlay.getKey(), overlay);
            }
        }
        HashMap hashMap = new HashMap();
        Iterator it = treeMap.values().iterator();
        while (it.hasNext()) {
            hashMap.putAll((Map) it.next());
            if (hashMap.size() >= i2) {
                break;
            }
        }
        return hashMap;
    }
}
