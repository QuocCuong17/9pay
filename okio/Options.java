package okio;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Options.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001f\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000eH\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", WorkflowRequestType.Method.GET, FirebaseAnalytics.Param.INDEX, "Companion", "okio"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    @JvmStatic
    public static final Options of(ByteString... byteStringArr) {
        return INSTANCE.of(byteStringArr);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains((Options) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf((Options) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf((Options) byteString);
    }

    /* renamed from: getByteStrings$okio, reason: from getter */
    public final ByteString[] getByteStrings() {
        return this.byteStrings;
    }

    /* renamed from: getTrie$okio, reason: from getter */
    public final int[] getTrie() {
        return this.trie;
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* renamed from: getSize */
    public int get_size() {
        return this.byteStrings.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ByteString get(int index) {
        return this.byteStrings[index];
    }

    /* compiled from: Options.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x00ef, code lost:
        
            continue;
         */
        @JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Options of(ByteString... byteStrings) {
            Intrinsics.checkNotNullParameter(byteStrings, "byteStrings");
            int i = 0;
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (byteStrings.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, defaultConstructorMarker);
            }
            List mutableList = ArraysKt.toMutableList(byteStrings);
            CollectionsKt.sort(mutableList);
            ArrayList arrayList = new ArrayList(byteStrings.length);
            for (ByteString byteString : byteStrings) {
                arrayList.add(-1);
            }
            Object[] array = arrayList.toArray(new Integer[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            Integer[] numArr = (Integer[]) array;
            List mutableListOf = CollectionsKt.mutableListOf(Arrays.copyOf(numArr, numArr.length));
            int length = byteStrings.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                mutableListOf.set(CollectionsKt.binarySearch$default(mutableList, byteStrings[i2], 0, 0, 6, (Object) null), Integer.valueOf(i3));
                i2++;
                i3++;
            }
            if (!(((ByteString) mutableList.get(0)).size() > 0)) {
                throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
            }
            int i4 = 0;
            while (i4 < mutableList.size()) {
                ByteString byteString2 = (ByteString) mutableList.get(i4);
                int i5 = i4 + 1;
                int i6 = i5;
                while (i6 < mutableList.size()) {
                    ByteString byteString3 = (ByteString) mutableList.get(i6);
                    if (!byteString3.startsWith(byteString2)) {
                        break;
                    }
                    if (!(byteString3.size() != byteString2.size())) {
                        throw new IllegalArgumentException(Intrinsics.stringPlus("duplicate option: ", byteString3).toString());
                    }
                    if (((Number) mutableListOf.get(i6)).intValue() > ((Number) mutableListOf.get(i4)).intValue()) {
                        mutableList.remove(i6);
                        mutableListOf.remove(i6);
                    } else {
                        i6++;
                    }
                }
                i4 = i5;
            }
            Buffer buffer = new Buffer();
            buildTrieRecursive$default(this, 0L, buffer, 0, mutableList, 0, 0, mutableListOf, 53, null);
            int[] iArr = new int[(int) getIntCount(buffer)];
            while (!buffer.exhausted()) {
                iArr[i] = buffer.readInt();
                i++;
            }
            Object[] copyOf = Arrays.copyOf(byteStrings, byteStrings.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
            return new Options((ByteString[]) copyOf, iArr, defaultConstructorMarker);
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) {
            companion.buildTrieRecursive((i4 & 1) != 0 ? 0L : j, buffer, (i4 & 4) != 0 ? 0 : i, list, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? list.size() : i3, list2);
        }

        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            Buffer buffer;
            int i6 = byteStringOffset;
            if (!(fromIndex < toIndex)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (fromIndex < toIndex) {
                int i7 = fromIndex;
                while (true) {
                    int i8 = i7 + 1;
                    if (!(byteStrings.get(i7).size() >= i6)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    if (i8 >= toIndex) {
                        break;
                    } else {
                        i7 = i8;
                    }
                }
            }
            ByteString byteString = byteStrings.get(fromIndex);
            ByteString byteString2 = byteStrings.get(toIndex - 1);
            if (i6 == byteString.size()) {
                int intValue = indexes.get(fromIndex).intValue();
                int i9 = fromIndex + 1;
                ByteString byteString3 = byteStrings.get(i9);
                i = i9;
                i2 = intValue;
                byteString = byteString3;
            } else {
                i = fromIndex;
                i2 = -1;
            }
            if (byteString.getByte(i6) != byteString2.getByte(i6)) {
                int i10 = i + 1;
                int i11 = 1;
                if (i10 < toIndex) {
                    while (true) {
                        int i12 = i10 + 1;
                        if (byteStrings.get(i10 - 1).getByte(i6) != byteStrings.get(i10).getByte(i6)) {
                            i11++;
                        }
                        if (i12 >= toIndex) {
                            break;
                        } else {
                            i10 = i12;
                        }
                    }
                }
                long intCount = nodeOffset + getIntCount(node) + 2 + (i11 * 2);
                node.writeInt(i11);
                node.writeInt(i2);
                if (i < toIndex) {
                    int i13 = i;
                    while (true) {
                        int i14 = i13 + 1;
                        byte b = byteStrings.get(i13).getByte(i6);
                        if (i13 == i || b != byteStrings.get(i13 - 1).getByte(i6)) {
                            node.writeInt(b & 255);
                        }
                        if (i14 >= toIndex) {
                            break;
                        } else {
                            i13 = i14;
                        }
                    }
                }
                Buffer buffer2 = new Buffer();
                while (i < toIndex) {
                    byte b2 = byteStrings.get(i).getByte(i6);
                    int i15 = i + 1;
                    if (i15 < toIndex) {
                        int i16 = i15;
                        while (true) {
                            int i17 = i16 + 1;
                            if (b2 != byteStrings.get(i16).getByte(i6)) {
                                i4 = i16;
                                break;
                            } else if (i17 >= toIndex) {
                                break;
                            } else {
                                i16 = i17;
                            }
                        }
                    }
                    i4 = toIndex;
                    if (i15 == i4 && i6 + 1 == byteStrings.get(i).size()) {
                        node.writeInt(indexes.get(i).intValue());
                        i5 = i4;
                        buffer = buffer2;
                    } else {
                        node.writeInt(((int) (intCount + getIntCount(buffer2))) * (-1));
                        i5 = i4;
                        buffer = buffer2;
                        buildTrieRecursive(intCount, buffer2, i6 + 1, byteStrings, i, i4, indexes);
                    }
                    i = i5;
                    buffer2 = buffer;
                }
                node.writeAll(buffer2);
                return;
            }
            int min = Math.min(byteString.size(), byteString2.size());
            if (i6 < min) {
                int i18 = i6;
                int i19 = 0;
                while (true) {
                    int i20 = i18 + 1;
                    if (byteString.getByte(i18) != byteString2.getByte(i18)) {
                        break;
                    }
                    i19++;
                    if (i20 >= min) {
                        break;
                    } else {
                        i18 = i20;
                    }
                }
                i3 = i19;
            } else {
                i3 = 0;
            }
            long intCount2 = nodeOffset + getIntCount(node) + 2 + i3 + 1;
            node.writeInt(-i3);
            node.writeInt(i2);
            int i21 = i6 + i3;
            if (i6 < i21) {
                while (true) {
                    int i22 = i6 + 1;
                    node.writeInt(byteString.getByte(i6) & 255);
                    if (i22 >= i21) {
                        break;
                    } else {
                        i6 = i22;
                    }
                }
            }
            if (i + 1 == toIndex) {
                if (!(i21 == byteStrings.get(i).size())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                node.writeInt(indexes.get(i).intValue());
            } else {
                Buffer buffer3 = new Buffer();
                node.writeInt(((int) (getIntCount(buffer3) + intCount2)) * (-1));
                buildTrieRecursive(intCount2, buffer3, i21, byteStrings, i, toIndex, indexes);
                node.writeAll(buffer3);
            }
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / 4;
        }
    }
}
