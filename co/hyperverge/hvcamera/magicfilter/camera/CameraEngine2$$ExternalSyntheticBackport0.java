package co.hyperverge.hvcamera.magicfilter.camera;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Objects;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class CameraEngine2$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(CharSequence charSequence, CharSequence[] charSequenceArr) {
        Objects.requireNonNull(charSequence, TtmlNode.RUBY_DELIMITER);
        StringBuilder sb = new StringBuilder();
        if (charSequenceArr.length > 0) {
            sb.append(charSequenceArr[0]);
            for (int i = 1; i < charSequenceArr.length; i++) {
                sb.append(charSequence);
                sb.append(charSequenceArr[i]);
            }
        }
        return sb.toString();
    }
}
