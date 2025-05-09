package com.otaliastudios.transcoder.internal.video;

import com.otaliastudios.transcoder.internal.utils.Logger;
import kotlin.Metadata;

/* compiled from: FrameDropper.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¨\u0006\u0005"}, d2 = {"FrameDropper", "Lcom/otaliastudios/transcoder/internal/video/FrameDropper;", "inputFps", "", "outputFps", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class FrameDropperKt {
    public static final FrameDropper FrameDropper(final int i, final int i2) {
        return new FrameDropper(i, i2) { // from class: com.otaliastudios.transcoder.internal.video.FrameDropperKt$FrameDropper$1
            final /* synthetic */ int $inputFps;
            final /* synthetic */ int $outputFps;
            private double currentSpf;
            private int frameCount;
            private final double inputSpf;
            private final Logger log = new Logger("FrameDropper");
            private final double outputSpf;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$inputFps = i;
                this.$outputFps = i2;
                this.inputSpf = 1.0d / i;
                this.outputSpf = 1.0d / i2;
            }

            @Override // com.otaliastudios.transcoder.internal.video.FrameDropper
            public boolean shouldRender(long timeUs) {
                double d = this.currentSpf + this.inputSpf;
                this.currentSpf = d;
                int i3 = this.frameCount;
                this.frameCount = i3 + 1;
                if (i3 == 0) {
                    this.log.v("RENDERING (first frame) - currentSpf=" + this.currentSpf + " inputSpf=" + this.inputSpf + " outputSpf=" + this.outputSpf);
                    return true;
                }
                double d2 = this.outputSpf;
                if (d > d2) {
                    this.currentSpf = d - d2;
                    this.log.v("RENDERING - currentSpf=" + this.currentSpf + " inputSpf=" + this.inputSpf + " outputSpf=" + this.outputSpf);
                    return true;
                }
                this.log.v("DROPPING - currentSpf=" + this.currentSpf + " inputSpf=" + this.inputSpf + " outputSpf=" + this.outputSpf);
                return false;
            }
        };
    }
}
