package xyz.luan.audioplayers.source;

import android.media.MediaPlayer;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import xyz.luan.audioplayers.player.SoundPoolPlayer;

/* compiled from: UrlSource.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000fH\u0002J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\u0006\u0010\u0013\u001a\u00020\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006 "}, d2 = {"Lxyz/luan/audioplayers/source/UrlSource;", "Lxyz/luan/audioplayers/source/Source;", "url", "", "isLocal", "", "(Ljava/lang/String;Z)V", "()Z", "getUrl", "()Ljava/lang/String;", "component1", "component2", "copy", "downloadUrl", "", "Ljava/net/URL;", "equals", "other", "", "getAudioPathForSoundPool", "hashCode", "", "loadTempFileFromNetwork", "Ljava/io/File;", "setForMediaPlayer", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "setForSoundPool", "soundPoolPlayer", "Lxyz/luan/audioplayers/player/SoundPoolPlayer;", InAppPurchaseConstants.METHOD_TO_STRING, "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class UrlSource implements Source {
    private final boolean isLocal;
    private final String url;

    public static /* synthetic */ UrlSource copy$default(UrlSource urlSource, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = urlSource.url;
        }
        if ((i & 2) != 0) {
            z = urlSource.isLocal;
        }
        return urlSource.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsLocal() {
        return this.isLocal;
    }

    public final UrlSource copy(String url, boolean isLocal) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new UrlSource(url, isLocal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UrlSource)) {
            return false;
        }
        UrlSource urlSource = (UrlSource) other;
        return Intrinsics.areEqual(this.url, urlSource.url) && this.isLocal == urlSource.isLocal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.url.hashCode() * 31;
        boolean z = this.isLocal;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "UrlSource(url=" + this.url + ", isLocal=" + this.isLocal + ')';
    }

    public UrlSource(String url, boolean z) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.isLocal = z;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean isLocal() {
        return this.isLocal;
    }

    @Override // xyz.luan.audioplayers.source.Source
    public void setForMediaPlayer(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "mediaPlayer");
        mediaPlayer.setDataSource(this.url);
    }

    @Override // xyz.luan.audioplayers.source.Source
    public void setForSoundPool(SoundPoolPlayer soundPoolPlayer) {
        Intrinsics.checkNotNullParameter(soundPoolPlayer, "soundPoolPlayer");
        soundPoolPlayer.release();
        soundPoolPlayer.setUrlSource(this);
    }

    public final String getAudioPathForSoundPool() {
        if (this.isLocal) {
            return StringsKt.removePrefix(this.url, (CharSequence) "file://");
        }
        String absolutePath = loadTempFileFromNetwork().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "loadTempFileFromNetwork().absolutePath");
        return absolutePath;
    }

    private final File loadTempFileFromNetwork() {
        URL url = URI.create(this.url).toURL();
        Intrinsics.checkNotNullExpressionValue(url, "create(url).toURL()");
        byte[] downloadUrl = downloadUrl(url);
        File tempFile = File.createTempFile("sound", "");
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        try {
            fileOutputStream.write(downloadUrl);
            tempFile.deleteOnExit();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            Intrinsics.checkNotNullExpressionValue(tempFile, "tempFile");
            return tempFile;
        } finally {
        }
    }

    private final byte[] downloadUrl(URL url) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream openStream = url.openStream();
        try {
            InputStream inputStream = openStream;
            byte[] bArr = new byte[4096];
            while (true) {
                Integer valueOf = Integer.valueOf(inputStream.read(bArr));
                if (!(valueOf.intValue() > 0)) {
                    valueOf = null;
                }
                if (valueOf != null) {
                    byteArrayOutputStream.write(bArr, 0, valueOf.intValue());
                } else {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(openStream, null);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    Intrinsics.checkNotNullExpressionValue(byteArray, "outputStream.toByteArray()");
                    return byteArray;
                }
            }
        } finally {
        }
    }
}
