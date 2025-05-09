package org.apache.commons.io.input;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Iterator;
import java.util.Objects;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes5.dex */
public final /* synthetic */ class ReversedLinesFileReader$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(CharSequence charSequence, Iterable iterable) {
        Objects.requireNonNull(charSequence, TtmlNode.RUBY_DELIMITER);
        StringBuilder sb = new StringBuilder();
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            while (true) {
                sb.append((CharSequence) it.next());
                if (!it.hasNext()) {
                    break;
                }
                sb.append(charSequence);
            }
        }
        return sb.toString();
    }
}
