package org.tensorflow.lite;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public interface InterpreterApi extends AutoCloseable {
    void allocateTensors();

    @Override // java.lang.AutoCloseable
    void close();

    int getInputIndex(String opName);

    Tensor getInputTensor(int inputIndex);

    int getInputTensorCount();

    Long getLastNativeInferenceDurationNanoseconds();

    int getOutputIndex(String opName);

    Tensor getOutputTensor(int outputIndex);

    int getOutputTensorCount();

    void resizeInput(int idx, int[] dims);

    void resizeInput(int idx, int[] dims, boolean strict);

    void run(Object input, Object output);

    void runForMultipleInputsOutputs(Object[] inputs, Map<Integer, Object> outputs);

    /* loaded from: classes6.dex */
    public static class Options {
        Boolean allowCancellation;
        private final List<DelegateFactory> delegateFactories;
        final List<Delegate> delegates;
        int numThreads;
        TfLiteRuntime runtime;
        Boolean useNNAPI;

        /* loaded from: classes6.dex */
        public enum TfLiteRuntime {
            FROM_APPLICATION_ONLY,
            FROM_SYSTEM_ONLY,
            PREFER_SYSTEM_OVER_APPLICATION
        }

        public Options() {
            this.runtime = TfLiteRuntime.FROM_APPLICATION_ONLY;
            this.numThreads = -1;
            this.delegates = new ArrayList();
            this.delegateFactories = new ArrayList();
        }

        public Options(Options other) {
            this.runtime = TfLiteRuntime.FROM_APPLICATION_ONLY;
            this.numThreads = -1;
            this.numThreads = other.numThreads;
            this.useNNAPI = other.useNNAPI;
            this.allowCancellation = other.allowCancellation;
            this.delegates = new ArrayList(other.delegates);
            this.delegateFactories = new ArrayList(other.delegateFactories);
            this.runtime = other.runtime;
        }

        public Options setNumThreads(int numThreads) {
            this.numThreads = numThreads;
            return this;
        }

        public int getNumThreads() {
            return this.numThreads;
        }

        public Options setUseNNAPI(boolean useNNAPI) {
            this.useNNAPI = Boolean.valueOf(useNNAPI);
            return this;
        }

        public boolean getUseNNAPI() {
            Boolean bool = this.useNNAPI;
            return bool != null && bool.booleanValue();
        }

        public Options setCancellable(boolean allow) {
            this.allowCancellation = Boolean.valueOf(allow);
            return this;
        }

        public boolean isCancellable() {
            Boolean bool = this.allowCancellation;
            return bool != null && bool.booleanValue();
        }

        public Options addDelegate(Delegate delegate) {
            this.delegates.add(delegate);
            return this;
        }

        public List<Delegate> getDelegates() {
            return Collections.unmodifiableList(this.delegates);
        }

        public Options addDelegateFactory(DelegateFactory delegateFactory) {
            this.delegateFactories.add(delegateFactory);
            return this;
        }

        public List<DelegateFactory> getDelegateFactories() {
            return Collections.unmodifiableList(this.delegateFactories);
        }

        public Options setRuntime(TfLiteRuntime runtime) {
            this.runtime = runtime;
            return this;
        }

        public TfLiteRuntime getRuntime() {
            return this.runtime;
        }
    }

    /* renamed from: org.tensorflow.lite.InterpreterApi$-CC, reason: invalid class name */
    /* loaded from: classes6.dex */
    public final /* synthetic */ class CC {
        public static InterpreterApi create(File modelFile, Options options) {
            return TensorFlowLite.getFactory(options == null ? null : options.getRuntime()).create(modelFile, options);
        }

        public static InterpreterApi create(ByteBuffer byteBuffer, Options options) {
            return TensorFlowLite.getFactory(options == null ? null : options.getRuntime()).create(byteBuffer, options);
        }
    }
}
