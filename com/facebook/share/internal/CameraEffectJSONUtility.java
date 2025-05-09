package com.facebook.share.internal;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.facebook.share.model.CameraEffectArguments;
import com.tekartik.sqflite.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CameraEffectJSONUtility.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0007R2\u0010\u0003\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/share/internal/CameraEffectJSONUtility;", "", "()V", "SETTERS", "Ljava/util/HashMap;", "Ljava/lang/Class;", "Lcom/facebook/share/internal/CameraEffectJSONUtility$Setter;", "Lkotlin/collections/HashMap;", "convertToCameraEffectArguments", "Lcom/facebook/share/model/CameraEffectArguments;", "jsonObject", "Lorg/json/JSONObject;", "convertToJSON", Constant.PARAM_SQL_ARGUMENTS, "Setter", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CameraEffectJSONUtility {
    public static final CameraEffectJSONUtility INSTANCE = new CameraEffectJSONUtility();
    private static final HashMap<Class<?>, Setter> SETTERS = MapsKt.hashMapOf(TuplesKt.to(String.class, new Setter() { // from class: com.facebook.share.internal.CameraEffectJSONUtility$SETTERS$1
        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(builder, "builder");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
            builder.putArgument(key, (String) value);
        }

        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(key, "key");
            json.put(key, value);
        }
    }), TuplesKt.to(String[].class, new Setter() { // from class: com.facebook.share.internal.CameraEffectJSONUtility$SETTERS$2
        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(builder, "builder");
            Intrinsics.checkNotNullParameter(key, "key");
            throw new IllegalArgumentException("Unexpected type from JSON");
        }

        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(key, "key");
            JSONArray jSONArray = new JSONArray();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.String?>");
            for (String str : (String[]) value) {
                jSONArray.put(str);
            }
            json.put(key, jSONArray);
        }
    }), TuplesKt.to(JSONArray.class, new Setter() { // from class: com.facebook.share.internal.CameraEffectJSONUtility$SETTERS$3
        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(builder, "builder");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type org.json.JSONArray");
            JSONArray jSONArray = (JSONArray) value;
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONArray.get(i);
                if (obj instanceof String) {
                    arrayList.add(obj);
                } else {
                    throw new IllegalArgumentException("Unexpected type in an array: " + obj.getClass());
                }
            }
            builder.putArgument(key, (String[]) arrayList.toArray(new String[0]));
        }

        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(key, "key");
            throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
        }
    }));

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CameraEffectJSONUtility.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&J\"\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&¨\u0006\f"}, d2 = {"Lcom/facebook/share/internal/CameraEffectJSONUtility$Setter;", "", "setOnArgumentsBuilder", "", "builder", "Lcom/facebook/share/model/CameraEffectArguments$Builder;", "key", "", "value", "setOnJSON", WorkflowRequestType.JSON, "Lorg/json/JSONObject;", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface Setter {
        void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException;

        void setOnJSON(JSONObject json, String key, Object value) throws JSONException;
    }

    private CameraEffectJSONUtility() {
    }

    @JvmStatic
    public static final JSONObject convertToJSON(CameraEffectArguments arguments) throws JSONException {
        if (arguments == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : arguments.keySet()) {
            Object obj = arguments.get(str);
            if (obj != null) {
                Setter setter = SETTERS.get(obj.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                }
                setter.setOnJSON(jSONObject, str, obj);
            }
        }
        return jSONObject;
    }

    @JvmStatic
    public static final CameraEffectArguments convertToCameraEffectArguments(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        CameraEffectArguments.Builder builder = new CameraEffectArguments.Builder();
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object obj = jsonObject.get(key);
            if (obj != JSONObject.NULL) {
                Setter setter = SETTERS.get(obj.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                }
                Intrinsics.checkNotNullExpressionValue(key, "key");
                setter.setOnArgumentsBuilder(builder, key, obj);
            }
        }
        return builder.build();
    }
}
