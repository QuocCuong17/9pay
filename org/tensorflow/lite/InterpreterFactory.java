package org.tensorflow.lite;

import java.io.File;
import java.nio.ByteBuffer;
import org.tensorflow.lite.InterpreterApi;

@Deprecated
/* loaded from: classes6.dex */
public class InterpreterFactory {
    public InterpreterApi create(File modelFile, InterpreterApi.Options options) {
        return InterpreterApi.CC.create(modelFile, options);
    }

    public InterpreterApi create(ByteBuffer byteBuffer, InterpreterApi.Options options) {
        return InterpreterApi.CC.create(byteBuffer, options);
    }
}
