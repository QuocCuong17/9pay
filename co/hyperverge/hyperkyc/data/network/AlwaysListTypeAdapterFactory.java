package co.hyperverge.hyperkyc.data.network;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: typeAdapters.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \f*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J,\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\u0016¨\u0006\r"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/AlwaysListTypeAdapterFactory;", "E", "Lcom/google/gson/TypeAdapterFactory;", "()V", "create", "Lcom/google/gson/TypeAdapter;", "T", "gson", "Lcom/google/gson/Gson;", "typeToken", "Lcom/google/gson/reflect/TypeToken;", "AlwaysListTypeAdapter", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AlwaysListTypeAdapterFactory<E> implements TypeAdapterFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    private AlwaysListTypeAdapterFactory() {
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Intrinsics.checkNotNullParameter(gson, "gson");
        Intrinsics.checkNotNullParameter(typeToken, "typeToken");
        if (!List.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        Companion companion = INSTANCE;
        Type type = typeToken.getType();
        Intrinsics.checkNotNullExpressionValue(type, "typeToken.type");
        TypeAdapter<T> adapter = gson.getAdapter(TypeToken.get(companion.resolveTypeArgument(type)));
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.google.gson.TypeAdapter<E of co.hyperverge.hyperkyc.data.network.AlwaysListTypeAdapterFactory>");
        TypeAdapter<List<? extends E>> nullSafe = new AlwaysListTypeAdapter(adapter).nullSafe();
        Intrinsics.checkNotNull(nullSafe, "null cannot be cast to non-null type com.google.gson.TypeAdapter<T of co.hyperverge.hyperkyc.data.network.AlwaysListTypeAdapterFactory.create>");
        return nullSafe;
    }

    /* compiled from: typeAdapters.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\"\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0003H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/AlwaysListTypeAdapterFactory$AlwaysListTypeAdapter;", "E", "Lcom/google/gson/TypeAdapter;", "", "elementTypeAdapter", "(Lcom/google/gson/TypeAdapter;)V", "read", "jsonReader", "Lcom/google/gson/stream/JsonReader;", "write", "", "out", "Lcom/google/gson/stream/JsonWriter;", WorkflowModule.Properties.Section.Component.Type.LIST, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private static final class AlwaysListTypeAdapter<E> extends TypeAdapter<List<? extends E>> {
        private final TypeAdapter<E> elementTypeAdapter;

        /* compiled from: typeAdapters.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[JsonToken.values().length];
                try {
                    iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[JsonToken.NULL.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[JsonToken.STRING.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[JsonToken.NUMBER.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[JsonToken.BOOLEAN.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[JsonToken.NAME.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[JsonToken.END_ARRAY.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[JsonToken.END_OBJECT.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[JsonToken.END_DOCUMENT.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public AlwaysListTypeAdapter(TypeAdapter<E> elementTypeAdapter) {
            Intrinsics.checkNotNullParameter(elementTypeAdapter, "elementTypeAdapter");
            this.elementTypeAdapter = elementTypeAdapter;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, List<? extends E> list) {
            if (list == null) {
                if (out != null) {
                    out.nullValue();
                }
            } else if (out != null) {
                out.beginArray();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    this.elementTypeAdapter.write(out, it.next());
                }
                out.endArray();
                out.flush();
            }
        }

        @Override // com.google.gson.TypeAdapter
        public List<E> read(JsonReader jsonReader) throws IOException {
            Intrinsics.checkNotNullParameter(jsonReader, "jsonReader");
            ArrayList arrayList = new ArrayList();
            JsonToken peek = jsonReader.peek();
            Intrinsics.checkNotNullExpressionValue(peek, "jsonReader.peek()");
            switch (WhenMappings.$EnumSwitchMapping$0[peek.ordinal()]) {
                case 1:
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        arrayList.add(this.elementTypeAdapter.read(jsonReader));
                    }
                    jsonReader.endArray();
                    return arrayList;
                case 2:
                    return null;
                case 3:
                case 4:
                case 5:
                case 6:
                    arrayList.add(this.elementTypeAdapter.read(jsonReader));
                    return arrayList;
                case 7:
                case 8:
                case 9:
                case 10:
                    throw new MalformedJsonException("Unexpected token: " + peek);
                default:
                    throw new AssertionError("Must never happen: " + peek);
            }
        }
    }

    /* compiled from: typeAdapters.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/AlwaysListTypeAdapterFactory$Companion;", "", "()V", "resolveTypeArgument", "Ljava/lang/reflect/Type;", "type", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Type resolveTypeArgument(Type type) {
            if (!(type instanceof ParameterizedType)) {
                return Object.class;
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            Intrinsics.checkNotNullExpressionValue(type2, "parameterizedType.actualTypeArguments[0]");
            return type2;
        }
    }
}
