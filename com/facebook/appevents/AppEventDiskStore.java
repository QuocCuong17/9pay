package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AccessTokenAppIdPair;
import com.facebook.appevents.AppEvent;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Utility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppEventDiskStore.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0017\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/appevents/AppEventDiskStore;", "", "()V", "PERSISTED_EVENTS_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "readAndClearStore", "Lcom/facebook/appevents/PersistedEvents;", "saveEventsToDisk", "", "eventsToPersist", "saveEventsToDisk$facebook_core_release", "MovedClassObjectInputStream", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppEventDiskStore {
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    public static final AppEventDiskStore INSTANCE = new AppEventDiskStore();
    private static final String TAG = AppEventDiskStore.class.getName();

    private AppEventDiskStore() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00aa A[Catch: all -> 0x00b1, TRY_LEAVE, TryCatch #4 {, blocks: (B:4:0x0003, B:10:0x002f, B:12:0x0034, B:16:0x00aa, B:23:0x003f, B:35:0x005b, B:37:0x0060, B:40:0x006b, B:32:0x0071, B:43:0x0076, B:45:0x007b, B:46:0x008f, B:49:0x0086, B:26:0x0091, B:28:0x0096, B:31:0x00a1), top: B:3:0x0003, inners: #0, #5, #7, #9 }] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.Throwable] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final synchronized PersistedEvents readAndClearStore() {
        PersistedEvents persistedEvents;
        Object obj;
        Throwable th;
        MovedClassObjectInputStream movedClassObjectInputStream;
        String str;
        String str2;
        Exception exc;
        Object obj2;
        Context context;
        synchronized (AppEventDiskStore.class) {
            AppEventUtility.assertIsNotMainThread();
            Context applicationContext = FacebookSdk.getApplicationContext();
            persistedEvents = null;
            try {
                try {
                    FileInputStream openFileInput = applicationContext.openFileInput(PERSISTED_EVENTS_FILENAME);
                    Intrinsics.checkNotNullExpressionValue(openFileInput, "context.openFileInput(PERSISTED_EVENTS_FILENAME)");
                    movedClassObjectInputStream = new MovedClassObjectInputStream(new BufferedInputStream(openFileInput));
                    try {
                        Object readObject = movedClassObjectInputStream.readObject();
                        Intrinsics.checkNotNull(readObject, "null cannot be cast to non-null type com.facebook.appevents.PersistedEvents");
                        PersistedEvents persistedEvents2 = (PersistedEvents) readObject;
                        MovedClassObjectInputStream movedClassObjectInputStream2 = movedClassObjectInputStream;
                        Utility.closeQuietly(movedClassObjectInputStream2);
                        try {
                            File fileStreamPath = applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME);
                            fileStreamPath.delete();
                            context = fileStreamPath;
                            obj2 = movedClassObjectInputStream2;
                        } catch (Exception e) {
                            Exception exc2 = e;
                            Log.w(TAG, "Got unexpected exception when removing events file: ", exc2);
                            context = exc2;
                            obj2 = "Got unexpected exception when removing events file: ";
                        }
                        persistedEvents = persistedEvents2;
                        applicationContext = context;
                        obj = obj2;
                    } catch (FileNotFoundException unused) {
                        Utility.closeQuietly(movedClassObjectInputStream);
                        try {
                            applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                        } catch (Exception e2) {
                            str = TAG;
                            str2 = "Got unexpected exception when removing events file: ";
                            exc = e2;
                            Log.w(str, str2, exc);
                            if (persistedEvents == null) {
                            }
                            return persistedEvents;
                        }
                        if (persistedEvents == null) {
                        }
                        return persistedEvents;
                    } catch (Exception e3) {
                        e = e3;
                        Log.w(TAG, "Got unexpected exception while reading events: ", e);
                        MovedClassObjectInputStream movedClassObjectInputStream3 = movedClassObjectInputStream;
                        Utility.closeQuietly(movedClassObjectInputStream3);
                        try {
                            File fileStreamPath2 = applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME);
                            fileStreamPath2.delete();
                            applicationContext = fileStreamPath2;
                            obj = movedClassObjectInputStream3;
                        } catch (Exception e4) {
                            str = TAG;
                            str2 = "Got unexpected exception when removing events file: ";
                            exc = e4;
                            Log.w(str, str2, exc);
                            if (persistedEvents == null) {
                            }
                            return persistedEvents;
                        }
                        if (persistedEvents == null) {
                        }
                        return persistedEvents;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    Utility.closeQuietly((Closeable) obj);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e5) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e5);
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                movedClassObjectInputStream = null;
            } catch (Exception e6) {
                e = e6;
                movedClassObjectInputStream = null;
            } catch (Throwable th3) {
                obj = null;
                th = th3;
                Utility.closeQuietly((Closeable) obj);
                applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                throw th;
            }
            if (persistedEvents == null) {
                persistedEvents = new PersistedEvents();
            }
        }
        return persistedEvents;
    }

    @JvmStatic
    public static final void saveEventsToDisk$facebook_core_release(PersistedEvents eventsToPersist) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput(PERSISTED_EVENTS_FILENAME, 0)));
            try {
                objectOutputStream2.writeObject(eventsToPersist);
                Utility.closeQuietly(objectOutputStream2);
            } catch (Throwable th) {
                th = th;
                objectOutputStream = objectOutputStream2;
                try {
                    Log.w(TAG, "Got unexpected exception while persisting events: ", th);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception unused) {
                    }
                } finally {
                    Utility.closeQuietly(objectOutputStream);
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* compiled from: AppEventDiskStore.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/facebook/appevents/AppEventDiskStore$MovedClassObjectInputStream;", "Ljava/io/ObjectInputStream;", "inputStream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)V", "readClassDescriptor", "Ljava/io/ObjectStreamClass;", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private static final class MovedClassObjectInputStream extends ObjectInputStream {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV2";

        public MovedClassObjectInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.ObjectInputStream
        protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
            ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();
            if (Intrinsics.areEqual(resultClassDescriptor.getName(), ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                resultClassDescriptor = ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            } else if (Intrinsics.areEqual(resultClassDescriptor.getName(), APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                resultClassDescriptor = ObjectStreamClass.lookup(AppEvent.SerializationProxyV2.class);
            }
            Intrinsics.checkNotNullExpressionValue(resultClassDescriptor, "resultClassDescriptor");
            return resultClassDescriptor;
        }
    }
}
