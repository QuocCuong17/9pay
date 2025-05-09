package vn.ai.faceauth.sdk.presentation.presentation.widgets;

/* loaded from: classes6.dex */
public class FrameMetadata {
    private final int height;
    private final int rotation;
    private final int width;

    /* loaded from: classes6.dex */
    public static class Builder {
        private int height;
        private int rotation;
        private int width;

        public FrameMetadata build() {
            return new FrameMetadata(this.width, this.height, this.rotation);
        }

        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        public Builder setRotation(int i) {
            this.rotation = i;
            return this;
        }

        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }
    }

    private FrameMetadata(int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.rotation = i3;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRotation() {
        return this.rotation;
    }

    public int getWidth() {
        return this.width;
    }
}
