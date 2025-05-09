package org.tensorflow.lite.support.model;

import android.content.Context;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.Map;
import org.tensorflow.lite.InterpreterApi;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes6.dex */
public class Model {
    private final MappedByteBuffer byteModel;
    private final GpuDelegateProxy gpuDelegateProxy;
    private final InterpreterApi interpreter;
    private final String modelPath;

    /* loaded from: classes6.dex */
    public enum Device {
        CPU,
        NNAPI,
        GPU
    }

    /* loaded from: classes6.dex */
    public static class Options {
        private final Device device;
        private final int numThreads;
        private final InterpreterApi.Options.TfLiteRuntime tfLiteRuntime;

        /* synthetic */ Options(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }

        /* loaded from: classes6.dex */
        public static class Builder {
            private Device device = Device.CPU;
            private int numThreads = 1;
            private InterpreterApi.Options.TfLiteRuntime tfLiteRuntime;

            public Builder setDevice(Device device) {
                this.device = device;
                return this;
            }

            public Builder setNumThreads(int numThreads) {
                this.numThreads = numThreads;
                return this;
            }

            public Builder setTfLiteRuntime(InterpreterApi.Options.TfLiteRuntime tfLiteRuntime) {
                this.tfLiteRuntime = tfLiteRuntime;
                return this;
            }

            public Options build() {
                return new Options(this, null);
            }
        }

        private Options(Builder builder) {
            this.device = builder.device;
            this.numThreads = builder.numThreads;
            this.tfLiteRuntime = builder.tfLiteRuntime;
        }
    }

    @Deprecated
    /* loaded from: classes6.dex */
    public static class Builder {
        private final MappedByteBuffer byteModel;
        private final String modelPath;
        private Device device = Device.CPU;
        private int numThreads = 1;

        public Builder(Context context, String modelPath) throws IOException {
            this.modelPath = modelPath;
            this.byteModel = FileUtil.loadMappedFile(context, modelPath);
        }

        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        public Builder setNumThreads(int numThreads) {
            this.numThreads = numThreads;
            return this;
        }

        public Model build() {
            return Model.createModel(this.byteModel, this.modelPath, new Options.Builder().setNumThreads(this.numThreads).setDevice(this.device).build());
        }
    }

    public static Model createModel(Context context, String modelPath) throws IOException {
        return createModel(context, modelPath, new Options.Builder().build());
    }

    public static Model createModel(Context context, String modelPath, Options options) throws IOException {
        SupportPreconditions.checkNotEmpty(modelPath, "Model path in the asset folder cannot be empty.");
        return createModel(FileUtil.loadMappedFile(context, modelPath), modelPath, options);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Model createModel(MappedByteBuffer byteModel, String modelPath, Options options) {
        GpuDelegateProxy gpuDelegateProxy;
        InterpreterApi.Options options2 = new InterpreterApi.Options();
        int i = AnonymousClass1.$SwitchMap$org$tensorflow$lite$support$model$Model$Device[options.device.ordinal()];
        if (i == 1) {
            options2.setUseNNAPI(true);
        } else if (i == 2) {
            gpuDelegateProxy = GpuDelegateProxy.maybeNewInstance();
            SupportPreconditions.checkArgument(gpuDelegateProxy != null, "Cannot inference with GPU. Did you add \"tensorflow-lite-gpu\" as dependency?");
            options2.addDelegate(gpuDelegateProxy);
            options2.setNumThreads(options.numThreads);
            if (options.tfLiteRuntime != null) {
                options2.setRuntime(options.tfLiteRuntime);
            }
            return new Model(modelPath, byteModel, InterpreterApi.CC.create(byteModel, options2), gpuDelegateProxy);
        }
        gpuDelegateProxy = null;
        options2.setNumThreads(options.numThreads);
        if (options.tfLiteRuntime != null) {
        }
        return new Model(modelPath, byteModel, InterpreterApi.CC.create(byteModel, options2), gpuDelegateProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.tensorflow.lite.support.model.Model$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$support$model$Model$Device;

        static {
            int[] iArr = new int[Device.values().length];
            $SwitchMap$org$tensorflow$lite$support$model$Model$Device = iArr;
            try {
                iArr[Device.NNAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$model$Model$Device[Device.GPU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$model$Model$Device[Device.CPU.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public MappedByteBuffer getData() {
        return this.byteModel;
    }

    public String getPath() {
        return this.modelPath;
    }

    public Tensor getInputTensor(int inputIndex) {
        return this.interpreter.getInputTensor(inputIndex);
    }

    public Tensor getOutputTensor(int outputIndex) {
        return this.interpreter.getOutputTensor(outputIndex);
    }

    public int[] getOutputTensorShape(int outputIndex) {
        return this.interpreter.getOutputTensor(outputIndex).shape();
    }

    public void run(Object[] inputs, Map<Integer, Object> outputs) {
        this.interpreter.runForMultipleInputsOutputs(inputs, outputs);
    }

    public void close() {
        InterpreterApi interpreterApi = this.interpreter;
        if (interpreterApi != null) {
            interpreterApi.close();
        }
        GpuDelegateProxy gpuDelegateProxy = this.gpuDelegateProxy;
        if (gpuDelegateProxy != null) {
            gpuDelegateProxy.close();
        }
    }

    private Model(String modelPath, MappedByteBuffer byteModel, InterpreterApi interpreter, GpuDelegateProxy gpuDelegateProxy) {
        this.modelPath = modelPath;
        this.byteModel = byteModel;
        this.interpreter = interpreter;
        this.gpuDelegateProxy = gpuDelegateProxy;
    }
}
