package com.otaliastudios.transcoder.internal.utils;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.internal.media.MediaFormatConstants;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class AvcCsdUtils {
    private static final byte AVC_SPS_NAL = 103;
    private static final byte AVC_SPS_NAL_2 = 39;
    private static final byte AVC_SPS_NAL_3 = 71;
    private static final byte[] AVC_START_CODE_3 = {0, 0, 1};
    private static final byte[] AVC_START_CODE_4 = {0, 0, 0, 1};

    public static ByteBuffer getSpsBuffer(MediaFormat mediaFormat) {
        ByteBuffer asReadOnlyBuffer = mediaFormat.getByteBuffer(MediaFormatConstants.KEY_AVC_SPS).asReadOnlyBuffer();
        ByteBuffer order = ByteBuffer.allocate(asReadOnlyBuffer.limit()).order(asReadOnlyBuffer.order());
        order.put(asReadOnlyBuffer);
        order.flip();
        skipStartCode(order);
        byte b = order.get();
        if (b != 103 && b != 39 && b != 71) {
            throw new IllegalStateException("Got non SPS NAL data.");
        }
        return order.slice();
    }

    private static void skipStartCode(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[3];
        byteBuffer.get(bArr);
        if (Arrays.equals(bArr, AVC_START_CODE_3)) {
            return;
        }
        byte[] copyOf = Arrays.copyOf(bArr, 4);
        copyOf[3] = byteBuffer.get();
        if (!Arrays.equals(copyOf, AVC_START_CODE_4)) {
            throw new IllegalStateException("AVC NAL start code not found in csd.");
        }
    }

    private AvcCsdUtils() {
    }
}
