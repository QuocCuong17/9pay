package androidx.media3.exoplayer.offline;

import androidx.media3.exoplayer.offline.DownloadManager;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class DownloadManager$InternalHandler$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ DownloadManager$InternalHandler$$ExternalSyntheticLambda0 INSTANCE = new DownloadManager$InternalHandler$$ExternalSyntheticLambda0();

    private /* synthetic */ DownloadManager$InternalHandler$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareStartTimes;
        compareStartTimes = DownloadManager.InternalHandler.compareStartTimes((Download) obj, (Download) obj2);
        return compareStartTimes;
    }
}
