package kotlin.io.path;

import java.nio.file.FileSystemLoopException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PathTreeWalk.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$bfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 190}, m = "invokeSuspend", n = {"$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "path$iv", "$this$iterator", "queue", "entriesReader"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class PathTreeWalk$bfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PathTreeWalk$bfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$bfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$1 = new PathTreeWalk$bfsIterator$1(this.this$0, continuation);
        pathTreeWalk$bfsIterator$1.L$0 = obj;
        return pathTreeWalk$bfsIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$bfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x008a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00f8 -> B:6:0x0080). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00fa -> B:6:0x0080). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        boolean followLinks;
        Path path;
        Path path2;
        Object keyOf;
        SequenceScope sequenceScope;
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$1;
        ArrayDeque arrayDeque;
        DirectoryEntriesReader directoryEntriesReader;
        Path path3;
        PathTreeWalk pathTreeWalk;
        PathNode pathNode;
        LinkOption[] linkOptionArr;
        SequenceScope sequenceScope2;
        ArrayDeque arrayDeque2;
        DirectoryEntriesReader directoryEntriesReader2;
        PathNode pathNode2;
        PathTreeWalk pathTreeWalk2;
        Path path4;
        boolean createsCycle;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceScope sequenceScope3 = (SequenceScope) this.L$0;
            ArrayDeque arrayDeque3 = new ArrayDeque();
            followLinks = this.this$0.getFollowLinks();
            DirectoryEntriesReader directoryEntriesReader3 = new DirectoryEntriesReader(followLinks);
            path = this.this$0.start;
            path2 = this.this$0.start;
            keyOf = PathTreeWalkKt.keyOf(path2, this.this$0.getLinkOptions());
            arrayDeque3.addLast(new PathNode(path, keyOf, null));
            sequenceScope = sequenceScope3;
            pathTreeWalk$bfsIterator$1 = this;
            arrayDeque = arrayDeque3;
            directoryEntriesReader = directoryEntriesReader3;
        } else if (i == 1) {
            path4 = (Path) this.L$5;
            pathTreeWalk2 = (PathTreeWalk) this.L$4;
            pathNode2 = (PathNode) this.L$3;
            directoryEntriesReader2 = (DirectoryEntriesReader) this.L$2;
            arrayDeque2 = (ArrayDeque) this.L$1;
            sequenceScope2 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            pathTreeWalk$bfsIterator$1 = this;
            SequenceScope sequenceScope4 = sequenceScope2;
            path3 = path4;
            directoryEntriesReader = directoryEntriesReader2;
            pathNode = pathNode2;
            sequenceScope = sequenceScope4;
            ArrayDeque arrayDeque4 = arrayDeque2;
            pathTreeWalk = pathTreeWalk2;
            arrayDeque = arrayDeque4;
            LinkOption[] linkOptions = pathTreeWalk.getLinkOptions();
            linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
            if (Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                arrayDeque.addAll(directoryEntriesReader.readEntries(pathNode));
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            directoryEntriesReader = (DirectoryEntriesReader) this.L$2;
            arrayDeque = (ArrayDeque) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            pathTreeWalk$bfsIterator$1 = this;
        }
        while (!arrayDeque.isEmpty()) {
            pathNode = (PathNode) arrayDeque.removeFirst();
            pathTreeWalk = pathTreeWalk$bfsIterator$1.this$0;
            path3 = pathNode.getPath();
            LinkOption[] linkOptions2 = pathTreeWalk.getLinkOptions();
            LinkOption[] linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptions2, linkOptions2.length);
            if (Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length))) {
                createsCycle = PathTreeWalkKt.createsCycle(pathNode);
                if (!createsCycle) {
                    if (pathTreeWalk.getIncludeDirectories()) {
                        pathTreeWalk$bfsIterator$1.L$0 = sequenceScope;
                        pathTreeWalk$bfsIterator$1.L$1 = arrayDeque;
                        pathTreeWalk$bfsIterator$1.L$2 = directoryEntriesReader;
                        pathTreeWalk$bfsIterator$1.L$3 = pathNode;
                        pathTreeWalk$bfsIterator$1.L$4 = pathTreeWalk;
                        pathTreeWalk$bfsIterator$1.L$5 = path3;
                        pathTreeWalk$bfsIterator$1.label = 1;
                        if (sequenceScope.yield(path3, pathTreeWalk$bfsIterator$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        directoryEntriesReader2 = directoryEntriesReader;
                        path4 = path3;
                        sequenceScope2 = sequenceScope;
                        pathNode2 = pathNode;
                        arrayDeque2 = arrayDeque;
                        pathTreeWalk2 = pathTreeWalk;
                        SequenceScope sequenceScope42 = sequenceScope2;
                        path3 = path4;
                        directoryEntriesReader = directoryEntriesReader2;
                        pathNode = pathNode2;
                        sequenceScope = sequenceScope42;
                        ArrayDeque arrayDeque42 = arrayDeque2;
                        pathTreeWalk = pathTreeWalk2;
                        arrayDeque = arrayDeque42;
                    }
                    LinkOption[] linkOptions3 = pathTreeWalk.getLinkOptions();
                    linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions3, linkOptions3.length);
                    if (Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                    }
                    while (!arrayDeque.isEmpty()) {
                    }
                } else {
                    throw new FileSystemLoopException(path3.toString());
                }
            } else if (Files.exists(path3, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
                pathTreeWalk$bfsIterator$1.L$0 = sequenceScope;
                pathTreeWalk$bfsIterator$1.L$1 = arrayDeque;
                pathTreeWalk$bfsIterator$1.L$2 = directoryEntriesReader;
                pathTreeWalk$bfsIterator$1.L$3 = null;
                pathTreeWalk$bfsIterator$1.L$4 = null;
                pathTreeWalk$bfsIterator$1.L$5 = null;
                pathTreeWalk$bfsIterator$1.label = 2;
                if (sequenceScope.yield(path3, pathTreeWalk$bfsIterator$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
