package androidx.camera.core.impl;

import android.util.Size;
import android.view.Surface;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_OutputSurface extends OutputSurface {
    private final int imageFormat;
    private final Size size;
    private final Surface surface;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_OutputSurface(Surface surface, Size size, int i) {
        Objects.requireNonNull(surface, "Null surface");
        this.surface = surface;
        Objects.requireNonNull(size, "Null size");
        this.size = size;
        this.imageFormat = i;
    }

    @Override // androidx.camera.core.impl.OutputSurface
    public Surface getSurface() {
        return this.surface;
    }

    @Override // androidx.camera.core.impl.OutputSurface
    public Size getSize() {
        return this.size;
    }

    @Override // androidx.camera.core.impl.OutputSurface
    public int getImageFormat() {
        return this.imageFormat;
    }

    public String toString() {
        return "OutputSurface{surface=" + this.surface + ", size=" + this.size + ", imageFormat=" + this.imageFormat + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutputSurface)) {
            return false;
        }
        OutputSurface outputSurface = (OutputSurface) obj;
        return this.surface.equals(outputSurface.getSurface()) && this.size.equals(outputSurface.getSize()) && this.imageFormat == outputSurface.getImageFormat();
    }

    public int hashCode() {
        return ((((this.surface.hashCode() ^ 1000003) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.imageFormat;
    }
}
