package io.flutter.plugins.firebase.database;

import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class FlutterDataSnapshotPayload {
    private Map<String, Object> payloadMap = new HashMap();

    public FlutterDataSnapshotPayload(DataSnapshot dataSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put("key", dataSnapshot.getKey());
        hashMap.put("value", dataSnapshot.getValue());
        hashMap.put("priority", dataSnapshot.getPriority());
        int childrenCount = (int) dataSnapshot.getChildrenCount();
        if (childrenCount == 0) {
            hashMap.put(Constants.CHILD_KEYS, new ArrayList());
        } else {
            String[] strArr = new String[childrenCount];
            int i = 0;
            Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
            while (it.hasNext()) {
                strArr[i] = it.next().getKey();
                i++;
            }
            hashMap.put(Constants.CHILD_KEYS, Arrays.asList(strArr));
        }
        this.payloadMap.put("snapshot", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlutterDataSnapshotPayload withAdditionalParams(Map<String, Object> map) {
        Map<String, Object> map2 = this.payloadMap;
        HashMap hashMap = new HashMap();
        this.payloadMap = hashMap;
        hashMap.putAll(map2);
        this.payloadMap.putAll(map);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> toMap() {
        return this.payloadMap;
    }
}
