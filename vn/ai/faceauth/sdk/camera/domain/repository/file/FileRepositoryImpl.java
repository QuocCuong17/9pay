package vn.ai.faceauth.sdk.camera.domain.repository.file;

import android.content.Context;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.domain.model.StorageTypeDomain;
import vn.ai.faceauth.sdk.domain.repository.FileRepository;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\nJ\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\nH\u0002J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/repository/file/FileRepositoryImpl;", "Lvn/ai/faceauth/sdk/domain/repository/FileRepository;", "storageType", "Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "context", "Landroid/content/Context;", "(Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;Landroid/content/Context;)V", "deleteDirectory", "", "directory", "Ljava/io/File;", "deleteRecursive", "fileOrDirectory", "deleteStorageFiles", "", "getExternalDirectory", "getInternalStorage", "getPathOfAllPhotos", "", "", "getPhotoFile", "provideOutputDirectory", "provideSimpleDateFormatter", "Ljava/text/SimpleDateFormat;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FileRepositoryImpl implements FileRepository {
    private final Context context;
    private final StorageTypeDomain storageType;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StorageTypeDomain.values().length];
            iArr[StorageTypeDomain.INTERNAL.ordinal()] = 1;
            iArr[StorageTypeDomain.EXTERNAL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FileRepositoryImpl(StorageTypeDomain storageTypeDomain, Context context) {
        this.storageType = storageTypeDomain;
        this.context = context;
        deleteStorageFiles();
    }

    private final File getExternalDirectory() {
        File file = new File(this.context.getFilesDir(), tfwhgw.rnigpa(9));
        file.mkdirs();
        return file.exists() ? file : this.context.getFilesDir();
    }

    private final File getInternalStorage() {
        return this.context.getDir(tfwhgw.rnigpa(10), 0);
    }

    private final File provideOutputDirectory() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.storageType.ordinal()];
        if (i == 1) {
            return getInternalStorage();
        }
        if (i == 2) {
            return getExternalDirectory();
        }
        throw new NoWhenBranchMatchedException();
    }

    private final SimpleDateFormat provideSimpleDateFormatter() {
        return new SimpleDateFormat(tfwhgw.rnigpa(11), Locale.US);
    }

    public final void deleteDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }

    public final void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File file : fileOrDirectory.listFiles()) {
                deleteRecursive(file);
            }
        }
        fileOrDirectory.delete();
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.FileRepository
    public boolean deleteStorageFiles() {
        Object m1202constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            deleteDirectory(getExternalDirectory());
            deleteDirectory(getInternalStorage());
            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        boolean m1209isSuccessimpl = Result.m1209isSuccessimpl(m1202constructorimpl);
        deleteRecursive(getInternalStorage());
        deleteRecursive(getExternalDirectory());
        return m1209isSuccessimpl;
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.FileRepository
    public List<String> getPathOfAllPhotos() {
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filter(FilesKt.walkTopDown(new File(provideOutputDirectory().getPath())), new Function1<File, Boolean>() { // from class: vn.ai.faceauth.sdk.camera.domain.repository.file.FileRepositoryImpl$getPathOfAllPhotos$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(File file) {
                return Boolean.valueOf(!file.isDirectory());
            }
        }), new Function1<File, String>() { // from class: vn.ai.faceauth.sdk.camera.domain.repository.file.FileRepositoryImpl$getPathOfAllPhotos$2
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(File file) {
                return file.getPath();
            }
        }));
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.FileRepository
    public File getPhotoFile() {
        return new File(provideOutputDirectory(), provideSimpleDateFormatter().format(Long.valueOf(System.currentTimeMillis())) + tfwhgw.rnigpa(12));
    }
}
