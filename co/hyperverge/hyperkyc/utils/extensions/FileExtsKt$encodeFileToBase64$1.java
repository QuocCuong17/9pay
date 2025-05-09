package co.hyperverge.hyperkyc.utils.extensions;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FileExts.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.extensions.FileExtsKt", f = "FileExts.kt", i = {0}, l = {BaselineTIFFTagSet.TAG_PAGE_NUMBER}, m = "encodeFileToBase64", n = {"file"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class FileExtsKt$encodeFileToBase64$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileExtsKt$encodeFileToBase64$1(Continuation<? super FileExtsKt$encodeFileToBase64$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FileExtsKt.encodeFileToBase64(null, this);
    }
}
