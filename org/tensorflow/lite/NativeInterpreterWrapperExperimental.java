package org.tensorflow.lite;

import java.nio.ByteBuffer;
import org.tensorflow.lite.InterpreterImpl;

/* loaded from: classes6.dex */
final class NativeInterpreterWrapperExperimental extends NativeInterpreterWrapper {
    private static native void resetVariableTensors(long interpreterHandle, long errorHandle);

    NativeInterpreterWrapperExperimental(String modelPath) {
        super(modelPath);
    }

    NativeInterpreterWrapperExperimental(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeInterpreterWrapperExperimental(String modelPath, InterpreterImpl.Options options) {
        super(modelPath, options);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeInterpreterWrapperExperimental(ByteBuffer buffer, InterpreterImpl.Options options) {
        super(buffer, options);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetVariableTensors() {
        resetVariableTensors(this.interpreterHandle, this.errorHandle);
    }
}
