package org.tensorflow.lite;

import java.io.File;
import java.nio.ByteBuffer;
import org.tensorflow.lite.InterpreterApi;
import org.tensorflow.lite.nnapi.NnApiDelegate;

/* loaded from: classes6.dex */
public interface InterpreterFactoryApi {
    InterpreterApi create(File modelFile, InterpreterApi.Options options);

    InterpreterApi create(ByteBuffer byteBuffer, InterpreterApi.Options options);

    NnApiDelegate.PrivateInterface createNnApiDelegateImpl(NnApiDelegate.Options options);

    String runtimeVersion();

    String schemaVersion();
}
