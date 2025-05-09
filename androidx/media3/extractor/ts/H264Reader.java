package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ParsableNalUnitBitArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes.dex */
public final class H264Reader implements ElementaryStreamReader {
    private final boolean allowNonIdrKeyframes;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private boolean randomAccessIndicator;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private long totalBytesWritten;
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    private final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    private long pesTimeUs = C.TIME_UNSET;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();

    public H264Reader(SeiReader seiReader, boolean z, boolean z2) {
        this.seiReader = seiReader;
        this.allowNonIdrKeyframes = z;
        this.detectAccessUnits = z2;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.totalBytesWritten = 0L;
        this.randomAccessIndicator = false;
        this.pesTimeUs = C.TIME_UNSET;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        SampleReader sampleReader = this.sampleReader;
        if (sampleReader != null) {
            sampleReader.reset();
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
        this.randomAccessIndicator |= (i & 2) != 0;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        assertTracksCreated();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        this.totalBytesWritten += parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                nalUnitData(data, position, limit);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(data, findNalUnit);
            int i = findNalUnit - position;
            if (i > 0) {
                nalUnitData(data, position, findNalUnit);
            }
            int i2 = limit - findNalUnit;
            long j = this.totalBytesWritten - i2;
            endNalUnit(j, i2, i < 0 ? -i : 0, this.pesTimeUs);
            startNalUnit(j, nalUnitType, this.pesTimeUs);
            position = findNalUnit + 3;
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean z) {
        assertTracksCreated();
        if (z) {
            this.sampleReader.end(this.totalBytesWritten);
        }
    }

    @RequiresNonNull({"sampleReader"})
    private void startNalUnit(long j, int i, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.startNalUnit(i);
            this.pps.startNalUnit(i);
        }
        this.sei.startNalUnit(i);
        this.sampleReader.startNalUnit(j, i, j2, this.randomAccessIndicator);
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] bArr, int i, int i2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.appendToNalUnit(bArr, i, i2);
            this.pps.appendToNalUnit(bArr, i, i2);
        }
        this.sei.appendToNalUnit(bArr, i, i2);
        this.sampleReader.appendToNalUnit(bArr, i, i2);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long j, int i, int i2, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.endNalUnit(i2);
            this.pps.endNalUnit(i2);
            if (!this.hasOutputFormat) {
                if (this.sps.isCompleted() && this.pps.isCompleted()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Arrays.copyOf(this.sps.nalData, this.sps.nalLength));
                    arrayList.add(Arrays.copyOf(this.pps.nalData, this.pps.nalLength));
                    NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(this.sps.nalData, 3, this.sps.nalLength);
                    NalUnitUtil.PpsData parsePpsNalUnit = NalUnitUtil.parsePpsNalUnit(this.pps.nalData, 3, this.pps.nalLength);
                    this.output.format(new Format.Builder().setId(this.formatId).setSampleMimeType("video/avc").setCodecs(CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc)).setWidth(parseSpsNalUnit.width).setHeight(parseSpsNalUnit.height).setColorInfo(new ColorInfo.Builder().setColorSpace(parseSpsNalUnit.colorSpace).setColorRange(parseSpsNalUnit.colorRange).setColorTransfer(parseSpsNalUnit.colorTransfer).setLumaBitdepth(parseSpsNalUnit.bitDepthLumaMinus8 + 8).setChromaBitdepth(parseSpsNalUnit.bitDepthChromaMinus8 + 8).build()).setPixelWidthHeightRatio(parseSpsNalUnit.pixelWidthHeightRatio).setInitializationData(arrayList).setMaxNumReorderSamples(parseSpsNalUnit.maxNumReorderFrames).build());
                    this.hasOutputFormat = true;
                    this.sampleReader.putSps(parseSpsNalUnit);
                    this.sampleReader.putPps(parsePpsNalUnit);
                    this.sps.reset();
                    this.pps.reset();
                }
            } else if (this.sps.isCompleted()) {
                this.sampleReader.putSps(NalUnitUtil.parseSpsNalUnit(this.sps.nalData, 3, this.sps.nalLength));
                this.sps.reset();
            } else if (this.pps.isCompleted()) {
                this.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(this.pps.nalData, 3, this.pps.nalLength));
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i2)) {
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(this.sei.nalData, this.sei.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j2, this.seiWrapper);
        }
        if (this.sampleReader.endNalUnit(j, i, this.hasOutputFormat)) {
            this.randomAccessIndicator = false;
        }
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SampleReader {
        private static final int DEFAULT_BUFFER_SIZE = 128;
        private final boolean allowNonIdrKeyframes;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private SliceHeaderData previousSliceHeader;
        private boolean randomAccessIndicator;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader;
        private final SparseArray<NalUnitUtil.SpsData> sps = new SparseArray<>();
        private final SparseArray<NalUnitUtil.PpsData> pps = new SparseArray<>();
        private byte[] buffer = new byte[128];
        private final ParsableNalUnitBitArray bitArray = new ParsableNalUnitBitArray(this.buffer, 0, 0);

        public SampleReader(TrackOutput trackOutput, boolean z, boolean z2) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z;
            this.detectAccessUnits = z2;
            this.previousSliceHeader = new SliceHeaderData();
            this.sliceHeader = new SliceHeaderData();
            reset();
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putSps(NalUnitUtil.SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void putPps(NalUnitUtil.PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j, int i, long j2, boolean z) {
            this.nalUnitType = i;
            this.nalUnitTimeUs = j2;
            this.nalUnitStartPosition = j;
            this.randomAccessIndicator = z;
            if (!this.allowNonIdrKeyframes || i != 1) {
                if (!this.detectAccessUnits) {
                    return;
                }
                if (i != 5 && i != 1 && i != 2) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            sliceHeaderData.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0152  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0102  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void appendToNalUnit(byte[] bArr, int i, int i2) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            if (this.isFilling) {
                int i8 = i2 - i;
                byte[] bArr2 = this.buffer;
                int length = bArr2.length;
                int i9 = this.bufferLength;
                if (length < i9 + i8) {
                    this.buffer = Arrays.copyOf(bArr2, (i9 + i8) * 2);
                }
                System.arraycopy(bArr, i, this.buffer, this.bufferLength, i8);
                int i10 = this.bufferLength + i8;
                this.bufferLength = i10;
                this.bitArray.reset(this.buffer, 0, i10);
                if (this.bitArray.canReadBits(8)) {
                    this.bitArray.skipBit();
                    int readBits = this.bitArray.readBits(2);
                    this.bitArray.skipBits(5);
                    if (this.bitArray.canReadExpGolombCodedNum()) {
                        this.bitArray.readUnsignedExpGolombCodedInt();
                        if (this.bitArray.canReadExpGolombCodedNum()) {
                            int readUnsignedExpGolombCodedInt = this.bitArray.readUnsignedExpGolombCodedInt();
                            if (!this.detectAccessUnits) {
                                this.isFilling = false;
                                this.sliceHeader.setSliceType(readUnsignedExpGolombCodedInt);
                                return;
                            }
                            if (this.bitArray.canReadExpGolombCodedNum()) {
                                int readUnsignedExpGolombCodedInt2 = this.bitArray.readUnsignedExpGolombCodedInt();
                                if (this.pps.indexOfKey(readUnsignedExpGolombCodedInt2) < 0) {
                                    this.isFilling = false;
                                    return;
                                }
                                NalUnitUtil.PpsData ppsData = this.pps.get(readUnsignedExpGolombCodedInt2);
                                NalUnitUtil.SpsData spsData = this.sps.get(ppsData.seqParameterSetId);
                                if (spsData.separateColorPlaneFlag) {
                                    if (!this.bitArray.canReadBits(2)) {
                                        return;
                                    } else {
                                        this.bitArray.skipBits(2);
                                    }
                                }
                                if (this.bitArray.canReadBits(spsData.frameNumLength)) {
                                    int readBits2 = this.bitArray.readBits(spsData.frameNumLength);
                                    if (spsData.frameMbsOnlyFlag) {
                                        z = false;
                                        z2 = false;
                                    } else {
                                        if (!this.bitArray.canReadBits(1)) {
                                            return;
                                        }
                                        boolean readBit = this.bitArray.readBit();
                                        if (readBit) {
                                            if (this.bitArray.canReadBits(1)) {
                                                z = readBit;
                                                z2 = true;
                                                z3 = this.bitArray.readBit();
                                                z4 = this.nalUnitType != 5;
                                                if (z4) {
                                                    i3 = 0;
                                                } else if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                    return;
                                                } else {
                                                    i3 = this.bitArray.readUnsignedExpGolombCodedInt();
                                                }
                                                if (spsData.picOrderCountType != 0) {
                                                    if (!this.bitArray.canReadBits(spsData.picOrderCntLsbLength)) {
                                                        return;
                                                    }
                                                    int readBits3 = this.bitArray.readBits(spsData.picOrderCntLsbLength);
                                                    if (ppsData.bottomFieldPicOrderInFramePresentFlag && !z) {
                                                        if (this.bitArray.canReadExpGolombCodedNum()) {
                                                            i5 = this.bitArray.readSignedExpGolombCodedInt();
                                                            i4 = readBits3;
                                                            i6 = 0;
                                                            i7 = i6;
                                                            this.sliceHeader.setAll(spsData, readBits, readUnsignedExpGolombCodedInt, readBits2, readUnsignedExpGolombCodedInt2, z, z2, z3, z4, i3, i4, i5, i6, i7);
                                                            this.isFilling = false;
                                                        }
                                                        return;
                                                    }
                                                    i4 = readBits3;
                                                    i5 = 0;
                                                } else {
                                                    if (spsData.picOrderCountType == 1 && !spsData.deltaPicOrderAlwaysZeroFlag) {
                                                        if (this.bitArray.canReadExpGolombCodedNum()) {
                                                            int readSignedExpGolombCodedInt = this.bitArray.readSignedExpGolombCodedInt();
                                                            if (!ppsData.bottomFieldPicOrderInFramePresentFlag || z) {
                                                                i6 = readSignedExpGolombCodedInt;
                                                                i4 = 0;
                                                                i5 = 0;
                                                                i7 = 0;
                                                            } else {
                                                                if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                                    return;
                                                                }
                                                                i7 = this.bitArray.readSignedExpGolombCodedInt();
                                                                i6 = readSignedExpGolombCodedInt;
                                                                i4 = 0;
                                                                i5 = 0;
                                                            }
                                                            this.sliceHeader.setAll(spsData, readBits, readUnsignedExpGolombCodedInt, readBits2, readUnsignedExpGolombCodedInt2, z, z2, z3, z4, i3, i4, i5, i6, i7);
                                                            this.isFilling = false;
                                                        }
                                                        return;
                                                    }
                                                    i4 = 0;
                                                    i5 = 0;
                                                }
                                                i6 = i5;
                                                i7 = i6;
                                                this.sliceHeader.setAll(spsData, readBits, readUnsignedExpGolombCodedInt, readBits2, readUnsignedExpGolombCodedInt2, z, z2, z3, z4, i3, i4, i5, i6, i7);
                                                this.isFilling = false;
                                            }
                                            return;
                                        }
                                        z = readBit;
                                        z2 = false;
                                    }
                                    z3 = z2;
                                    if (this.nalUnitType != 5) {
                                    }
                                    if (z4) {
                                    }
                                    if (spsData.picOrderCountType != 0) {
                                    }
                                    i6 = i5;
                                    i7 = i6;
                                    this.sliceHeader.setAll(spsData, readBits, readUnsignedExpGolombCodedInt, readBits2, readUnsignedExpGolombCodedInt2, z, z2, z3, z4, i3, i4, i5, i6, i7);
                                    this.isFilling = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        public boolean endNalUnit(long j, int i, boolean z) {
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (z && this.readingSample) {
                    outputSample(i + ((int) (j - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            setSampleIsKeyframe();
            return this.sampleIsKeyframe;
        }

        public void end(long j) {
            setSampleIsKeyframe();
            this.nalUnitStartPosition = j;
            outputSample(0);
            this.readingSample = false;
        }

        private void setSampleIsKeyframe() {
            boolean isISlice = this.allowNonIdrKeyframes ? this.sliceHeader.isISlice() : this.randomAccessIndicator;
            boolean z = this.sampleIsKeyframe;
            int i = this.nalUnitType;
            boolean z2 = true;
            if (i != 5 && (!isISlice || i != 1)) {
                z2 = false;
            }
            this.sampleIsKeyframe = z | z2;
        }

        private void outputSample(int i) {
            long j = this.sampleTimeUs;
            if (j == C.TIME_UNSET) {
                return;
            }
            boolean z = this.sampleIsKeyframe;
            this.output.sampleMetadata(j, z ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static final class SliceHeaderData {
            private static final int SLICE_TYPE_ALL_I = 7;
            private static final int SLICE_TYPE_I = 2;
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private NalUnitUtil.SpsData spsData;

            private SliceHeaderData() {
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public void setSliceType(int i) {
                this.sliceType = i;
                this.hasSliceType = true;
            }

            public void setAll(NalUnitUtil.SpsData spsData, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.spsData = spsData;
                this.nalRefIdc = i;
                this.sliceType = i2;
                this.frameNum = i3;
                this.picParameterSetId = i4;
                this.fieldPicFlag = z;
                this.bottomFieldFlagPresent = z2;
                this.bottomFieldFlag = z3;
                this.idrPicFlag = z4;
                this.idrPicId = i5;
                this.picOrderCntLsb = i6;
                this.deltaPicOrderCntBottom = i7;
                this.deltaPicOrderCnt0 = i8;
                this.deltaPicOrderCnt1 = i9;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public boolean isISlice() {
                int i;
                return this.hasSliceType && ((i = this.sliceType) == 7 || i == 2);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean isFirstVclNalUnitOfPicture(SliceHeaderData sliceHeaderData) {
                int i;
                int i2;
                boolean z;
                if (!this.isComplete) {
                    return false;
                }
                if (!sliceHeaderData.isComplete) {
                    return true;
                }
                NalUnitUtil.SpsData spsData = (NalUnitUtil.SpsData) Assertions.checkStateNotNull(this.spsData);
                NalUnitUtil.SpsData spsData2 = (NalUnitUtil.SpsData) Assertions.checkStateNotNull(sliceHeaderData.spsData);
                return (this.frameNum == sliceHeaderData.frameNum && this.picParameterSetId == sliceHeaderData.picParameterSetId && this.fieldPicFlag == sliceHeaderData.fieldPicFlag && (!this.bottomFieldFlagPresent || !sliceHeaderData.bottomFieldFlagPresent || this.bottomFieldFlag == sliceHeaderData.bottomFieldFlag) && (((i = this.nalRefIdc) == (i2 = sliceHeaderData.nalRefIdc) || (i != 0 && i2 != 0)) && ((spsData.picOrderCountType != 0 || spsData2.picOrderCountType != 0 || (this.picOrderCntLsb == sliceHeaderData.picOrderCntLsb && this.deltaPicOrderCntBottom == sliceHeaderData.deltaPicOrderCntBottom)) && ((spsData.picOrderCountType != 1 || spsData2.picOrderCountType != 1 || (this.deltaPicOrderCnt0 == sliceHeaderData.deltaPicOrderCnt0 && this.deltaPicOrderCnt1 == sliceHeaderData.deltaPicOrderCnt1)) && (z = this.idrPicFlag) == sliceHeaderData.idrPicFlag && (!z || this.idrPicId == sliceHeaderData.idrPicId))))) ? false : true;
            }
        }
    }
}
