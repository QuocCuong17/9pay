package org.microg.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.internal.view.SupportMenu;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    private static void writeHeader(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
            parcel.writeInt(i2);
        } else {
            parcel.writeInt(i | (i2 << 16));
        }
    }

    @Deprecated
    public static int writeStart(Parcel parcel) {
        return writeObjectHeader(parcel);
    }

    public static int writeObjectHeader(Parcel parcel) {
        writeHeader(parcel, 20293, 65535);
        return parcel.dataPosition();
    }

    private static int writeObjectHeader(Parcel parcel, int i) {
        writeHeader(parcel, i, 65535);
        return parcel.dataPosition();
    }

    @Deprecated
    public static void writeEnd(Parcel parcel, int i) {
        finishObjectHeader(parcel, i);
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static void write(Parcel parcel, int i, Boolean bool) {
        if (bool == null) {
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeInt(bool.booleanValue() ? 1 : 0);
    }

    public static void write(Parcel parcel, int i, Byte b) {
        if (b == null) {
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeInt(b.byteValue());
    }

    public static void write(Parcel parcel, int i, Short sh) {
        if (sh == null) {
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeInt(sh.shortValue());
    }

    public static void write(Parcel parcel, int i, Integer num) {
        if (num == null) {
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeInt(num.intValue());
    }

    public static void write(Parcel parcel, int i, Long l) {
        if (l == null) {
            return;
        }
        writeHeader(parcel, i, 8);
        parcel.writeLong(l.longValue());
    }

    public static void write(Parcel parcel, int i, Float f) {
        if (f == null) {
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeFloat(f.floatValue());
    }

    public static void write(Parcel parcel, int i, Double d) {
        if (d == null) {
            return;
        }
        writeHeader(parcel, i, 8);
        parcel.writeDouble(d.doubleValue());
    }

    public static void write(Parcel parcel, int i, String str, boolean z) {
        if (str == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeString(str);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeBundle(bundle);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeByteArray(bArr);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, int[] iArr, boolean z) {
        if (iArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeIntArray(iArr);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeStringArray(strArr);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void writeStringList(Parcel parcel, int i, List<String> list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeStringList(list);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void writeIntegerList(Parcel parcel, int i, List<Integer> list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeInt(list.size());
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                parcel.writeInt(it.next().intValue());
            }
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void writeLongList(Parcel parcel, int i, List<Long> list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeInt(list.size());
            Iterator<Long> it = list.iterator();
            while (it.hasNext()) {
                parcel.writeLong(it.next().longValue());
            }
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void writeFloatList(Parcel parcel, int i, List<Float> list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeInt(list.size());
            Iterator<Float> it = list.iterator();
            while (it.hasNext()) {
                parcel.writeFloat(it.next().floatValue());
            }
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void writeDoubleList(Parcel parcel, int i, List<Double> list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeInt(list.size());
            Iterator<Double> it = list.iterator();
            while (it.hasNext()) {
                parcel.writeDouble(it.next().doubleValue());
            }
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void writeBooleanList(Parcel parcel, int i, List<Boolean> list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeInt(list.size());
            Iterator<Boolean> it = list.iterator();
            while (it.hasNext()) {
                parcel.writeInt(it.next().booleanValue() ? 1 : 0);
            }
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    private static <T extends Parcelable> void writeArrayPart(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static <T extends Parcelable> void write(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int writeObjectHeader = writeObjectHeader(parcel, i);
        parcel.writeInt(tArr.length);
        for (T t : tArr) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                writeArrayPart(parcel, t, i2);
            }
        }
        finishObjectHeader(parcel, writeObjectHeader);
    }

    public static <T extends Parcelable> void write(Parcel parcel, int i, List<T> list, int i2, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int writeObjectHeader = writeObjectHeader(parcel, i);
        parcel.writeInt(list.size());
        for (T t : list) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                writeArrayPart(parcel, t, i2);
            }
        }
        finishObjectHeader(parcel, writeObjectHeader);
    }

    public static void write(Parcel parcel, int i, Parcel parcel2, boolean z) {
        if (parcel2 == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeList(list);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, Map map, boolean z) {
        if (map == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeMap(map);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }

    public static void write(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder == null) {
            if (z) {
                writeHeader(parcel, i, 0);
            }
        } else {
            int writeObjectHeader = writeObjectHeader(parcel, i);
            parcel.writeStrongBinder(iBinder);
            finishObjectHeader(parcel, writeObjectHeader);
        }
    }
}
