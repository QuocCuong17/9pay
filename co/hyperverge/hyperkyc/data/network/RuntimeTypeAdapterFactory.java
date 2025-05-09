package co.hyperverge.hyperkyc.data.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: typeAdapters.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u0016*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0016B'\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ,\u0010\r\u001a\n\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u000e\"\u0004\b\u0001\u0010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013H\u0016J,\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0010\u0010\u0012\u001a\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0007R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00060\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/RuntimeTypeAdapterFactory;", "T", "Lcom/google/gson/TypeAdapterFactory;", "baseType", "Ljava/lang/Class;", "typeFieldName", "", "maintainType", "", "(Ljava/lang/Class;Ljava/lang/String;Z)V", "labelToSubtype", "", "subtypeToLabel", "create", "Lcom/google/gson/TypeAdapter;", "R", "gson", "Lcom/google/gson/Gson;", "type", "Lcom/google/gson/reflect/TypeToken;", "registerSubtype", "label", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Class<?> baseType;
    private final Map<String, Class<?>> labelToSubtype;
    private final boolean maintainType;
    private final Map<Class<?>, String> subtypeToLabel;
    private final String typeFieldName;

    public /* synthetic */ RuntimeTypeAdapterFactory(Class cls, String str, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(cls, str, z);
    }

    public final RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> cls) {
        return registerSubtype$default(this, cls, null, 2, null);
    }

    private RuntimeTypeAdapterFactory(Class<?> cls, String str, boolean z) {
        this.labelToSubtype = new LinkedHashMap();
        this.subtypeToLabel = new LinkedHashMap();
        if (str == null || cls == null) {
            throw null;
        }
        this.baseType = cls;
        this.typeFieldName = str;
        this.maintainType = z;
    }

    public static /* synthetic */ RuntimeTypeAdapterFactory registerSubtype$default(RuntimeTypeAdapterFactory runtimeTypeAdapterFactory, Class cls, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            Intrinsics.checkNotNull(cls);
            str = cls.getSimpleName();
        }
        return runtimeTypeAdapterFactory.registerSubtype(cls, str);
    }

    public final RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, String label) {
        if (type == null || label == null) {
            throw null;
        }
        if (this.subtypeToLabel.containsKey(type) || this.labelToSubtype.containsKey(label)) {
            throw new IllegalArgumentException("types and labels must be unique");
        }
        this.labelToSubtype.put(label, type);
        this.subtypeToLabel.put(type, label);
        return this;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        Intrinsics.checkNotNullParameter(gson, "gson");
        Intrinsics.checkNotNullParameter(type, "type");
        if (!this.baseType.isAssignableFrom(type.getRawType())) {
            return null;
        }
        final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, Class<?>> entry : this.labelToSubtype.entrySet()) {
            String key = entry.getKey();
            Class<?> value = entry.getValue();
            TypeAdapter<T> delegate = gson.getDelegateAdapter(this, TypeToken.get((Class) value));
            Intrinsics.checkNotNullExpressionValue(delegate, "delegate");
            linkedHashMap.put(key, delegate);
            linkedHashMap2.put(value, delegate);
        }
        return new TypeAdapter<R>() { // from class: co.hyperverge.hyperkyc.data.network.RuntimeTypeAdapterFactory$create$1
            @Override // com.google.gson.TypeAdapter
            public R read(JsonReader in) throws IOException {
                boolean z;
                String str;
                JsonElement remove;
                Class cls;
                String str2;
                Class cls2;
                String str3;
                Intrinsics.checkNotNullParameter(in, "in");
                JsonElement read = adapter.read(in);
                z = ((RuntimeTypeAdapterFactory) this).maintainType;
                if (z) {
                    JsonObject asJsonObject = read.getAsJsonObject();
                    str3 = ((RuntimeTypeAdapterFactory) this).typeFieldName;
                    remove = asJsonObject.get(str3);
                } else {
                    JsonObject asJsonObject2 = read.getAsJsonObject();
                    str = ((RuntimeTypeAdapterFactory) this).typeFieldName;
                    remove = asJsonObject2.remove(str);
                }
                if (remove == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("cannot deserialize ");
                    cls = ((RuntimeTypeAdapterFactory) this).baseType;
                    sb.append(cls);
                    sb.append(" because it does not define a field named ");
                    str2 = ((RuntimeTypeAdapterFactory) this).typeFieldName;
                    sb.append(str2);
                    throw new JsonParseException(sb.toString());
                }
                String asString = remove.getAsString();
                TypeAdapter<?> typeAdapter = linkedHashMap.get(asString);
                if (typeAdapter == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("cannot deserialize ");
                    cls2 = ((RuntimeTypeAdapterFactory) this).baseType;
                    sb2.append(cls2);
                    sb2.append(" subtype named ");
                    sb2.append(asString);
                    sb2.append("; did you forget to register a subtype?");
                    throw new JsonParseException(sb2.toString());
                }
                return (R) typeAdapter.fromJsonTree(read);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, R value2) throws IOException {
                Map map;
                boolean z;
                String str;
                String str2;
                String str3;
                Intrinsics.checkNotNullParameter(out, "out");
                Intrinsics.checkNotNull(value2);
                Class<?> cls = value2.getClass();
                map = ((RuntimeTypeAdapterFactory) this).subtypeToLabel;
                String str4 = (String) map.get(cls);
                TypeAdapter<?> typeAdapter = linkedHashMap2.get(cls);
                if (typeAdapter == null) {
                    throw new JsonParseException("cannot serialize " + cls.getName() + "; did you forget to register a subtype?");
                }
                JsonObject asJsonObject = typeAdapter.toJsonTree(value2).getAsJsonObject();
                z = ((RuntimeTypeAdapterFactory) this).maintainType;
                if (z) {
                    adapter.write(out, asJsonObject);
                    return;
                }
                JsonObject jsonObject = new JsonObject();
                str = ((RuntimeTypeAdapterFactory) this).typeFieldName;
                if (!asJsonObject.has(str)) {
                    str3 = ((RuntimeTypeAdapterFactory) this).typeFieldName;
                    jsonObject.add(str3, new JsonPrimitive(str4));
                    for (Map.Entry<String, JsonElement> entry2 : asJsonObject.entrySet()) {
                        Intrinsics.checkNotNullExpressionValue(entry2, "jsonObject.entrySet()");
                        Map.Entry<String, JsonElement> entry3 = entry2;
                        jsonObject.add(entry3.getKey(), entry3.getValue());
                    }
                    adapter.write(out, jsonObject);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("cannot serialize ");
                sb.append(cls.getName());
                sb.append(" because it already defines a field named ");
                str2 = ((RuntimeTypeAdapterFactory) this).typeFieldName;
                sb.append(str2);
                throw new JsonParseException(sb.toString());
            }
        }.nullSafe();
    }

    /* compiled from: typeAdapters.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0007J,\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tJ4\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/RuntimeTypeAdapterFactory$Companion;", "", "()V", "of", "Lco/hyperverge/hyperkyc/data/network/RuntimeTypeAdapterFactory;", "T", "baseType", "Ljava/lang/Class;", "typeFieldName", "", "maintainType", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName, boolean maintainType) {
            return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName, maintainType, null);
        }

        public final <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
            return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName, false, null);
        }

        public final <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType) {
            return new RuntimeTypeAdapterFactory<>(baseType, "type", false, null);
        }
    }
}
