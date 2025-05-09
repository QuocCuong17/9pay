package com.google.firebase.storage;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class ListResult {
    private static final String ITEMS_KEY = "items";
    private static final String NAME_KEY = "name";
    private static final String PAGE_TOKEN_KEY = "nextPageToken";
    private static final String PREFIXES_KEY = "prefixes";
    private final List<StorageReference> items;
    private final String pageToken;
    private final List<StorageReference> prefixes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListResult(List<StorageReference> list, List<StorageReference> list2, String str) {
        this.prefixes = list;
        this.items = list2;
        this.pageToken = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListResult fromJSON(FirebaseStorage firebaseStorage, JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (jSONObject.has(PREFIXES_KEY)) {
            JSONArray jSONArray = jSONObject.getJSONArray(PREFIXES_KEY);
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (string.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                    string = string.substring(0, string.length() - 1);
                }
                arrayList.add(firebaseStorage.getReference(string));
            }
        }
        if (jSONObject.has("items")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("items");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(firebaseStorage.getReference(jSONArray2.getJSONObject(i2).getString("name")));
            }
        }
        return new ListResult(arrayList, arrayList2, jSONObject.optString(PAGE_TOKEN_KEY, null));
    }

    public List<StorageReference> getPrefixes() {
        return this.prefixes;
    }

    public List<StorageReference> getItems() {
        return this.items;
    }

    public String getPageToken() {
        return this.pageToken;
    }
}
