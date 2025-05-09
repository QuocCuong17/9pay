package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;

/* loaded from: classes.dex */
public class LegacySubtitleUtil {
    private LegacySubtitleUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0047 A[LOOP:0: B:11:0x0041->B:13:0x0047, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void toCuesWithTiming(Subtitle subtitle, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        boolean z;
        int i;
        int startIndex = getStartIndex(subtitle, outputOptions.startTimeUs);
        if (outputOptions.startTimeUs != C.TIME_UNSET && startIndex < subtitle.getEventTimeCount()) {
            List<Cue> cues = subtitle.getCues(outputOptions.startTimeUs);
            long eventTime = subtitle.getEventTime(startIndex);
            if (!cues.isEmpty() && outputOptions.startTimeUs < eventTime) {
                consumer.accept(new CuesWithTiming(cues, outputOptions.startTimeUs, eventTime - outputOptions.startTimeUs));
                z = true;
                for (i = startIndex; i < subtitle.getEventTimeCount(); i++) {
                    outputSubtitleEvent(subtitle, i, consumer);
                }
                if (outputOptions.outputAllCues) {
                    return;
                }
                if (z) {
                    startIndex--;
                }
                for (int i2 = 0; i2 < startIndex; i2++) {
                    outputSubtitleEvent(subtitle, i2, consumer);
                }
                if (z) {
                    consumer.accept(new CuesWithTiming(subtitle.getCues(outputOptions.startTimeUs), subtitle.getEventTime(startIndex), outputOptions.startTimeUs - subtitle.getEventTime(startIndex)));
                    return;
                }
                return;
            }
        }
        z = false;
        while (i < subtitle.getEventTimeCount()) {
        }
        if (outputOptions.outputAllCues) {
        }
    }

    private static int getStartIndex(Subtitle subtitle, long j) {
        if (j == C.TIME_UNSET) {
            return 0;
        }
        int nextEventTimeIndex = subtitle.getNextEventTimeIndex(j);
        if (nextEventTimeIndex == -1) {
            nextEventTimeIndex = subtitle.getEventTimeCount();
        }
        return (nextEventTimeIndex <= 0 || subtitle.getEventTime(nextEventTimeIndex + (-1)) != j) ? nextEventTimeIndex : nextEventTimeIndex - 1;
    }

    private static void outputSubtitleEvent(Subtitle subtitle, int i, Consumer<CuesWithTiming> consumer) {
        long eventTime = subtitle.getEventTime(i);
        List<Cue> cues = subtitle.getCues(eventTime);
        if (cues.isEmpty()) {
            return;
        }
        if (i == subtitle.getEventTimeCount() - 1) {
            throw new IllegalStateException();
        }
        long eventTime2 = subtitle.getEventTime(i + 1) - subtitle.getEventTime(i);
        if (eventTime2 > 0) {
            consumer.accept(new CuesWithTiming(cues, eventTime, eventTime2));
        }
    }
}
