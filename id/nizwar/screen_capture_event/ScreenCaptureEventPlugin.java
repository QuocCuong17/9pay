package id.nizwar.screen_capture_event;

import android.os.Build;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Looper;
import android.webkit.MimeTypeMap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import id.nizwar.screen_capture_event.ScreenCaptureEventPlugin;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class ScreenCaptureEventPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {
    static int SCREEN_CAPTURE_PERMISSION = 101;
    private ActivityPluginBinding activityPluginBinding;
    private MethodChannel channel;
    private FileObserver fileObserver;
    private Handler handler;
    private Timer timeout = new Timer();
    private final Map<String, FileObserver> watchModifier = new HashMap();
    private boolean screenRecording = false;
    private long tempSize = 0;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "screencapture_method");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        int i = 0;
        char c = 65535;
        switch (str.hashCode()) {
            case -2122989593:
                if (str.equals("isRecording")) {
                    c = 0;
                    break;
                }
                break;
            case -1837142483:
                if (str.equals("prevent_screenshot")) {
                    c = 1;
                    break;
                }
                break;
            case -561690241:
                if (str.equals("request_permission")) {
                    c = 2;
                    break;
                }
                break;
            case 112903375:
                if (str.equals("watch")) {
                    c = 3;
                    break;
                }
                break;
            case 1671767583:
                if (str.equals("dispose")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                result.success(Boolean.valueOf(this.screenRecording));
                return;
            case 1:
                if (((Boolean) methodCall.arguments).booleanValue()) {
                    this.activityPluginBinding.getActivity().getWindow().addFlags(8192);
                    return;
                } else {
                    this.activityPluginBinding.getActivity().getWindow().clearFlags(8192);
                    return;
                }
            case 2:
                if (ContextCompat.checkSelfPermission(this.activityPluginBinding.getActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    ActivityCompat.requestPermissions(this.activityPluginBinding.getActivity(), new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 101);
                    return;
                }
                return;
            case 3:
                this.handler = new Handler(Looper.getMainLooper());
                updateScreenRecordStatus();
                if (Build.VERSION.SDK_INT >= 29) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    Path[] values = Path.values();
                    int length = values.length;
                    while (i < length) {
                        Path path = values[i];
                        arrayList.add(new File(path.getPath()));
                        arrayList2.add(path.getPath());
                        i++;
                    }
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList, arrayList2);
                    this.fileObserver = anonymousClass1;
                    anonymousClass1.startWatching();
                    return;
                }
                Path[] values2 = Path.values();
                int length2 = values2.length;
                while (i < length2) {
                    Path path2 = values2[i];
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(path2.getPath(), path2);
                    this.fileObserver = anonymousClass2;
                    anonymousClass2.startWatching();
                    i++;
                }
                return;
            case 4:
                FileObserver fileObserver = this.fileObserver;
                if (fileObserver != null) {
                    fileObserver.stopWatching();
                }
                Iterator<Map.Entry<String, FileObserver>> it = this.watchModifier.entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().stopWatching();
                }
                this.watchModifier.clear();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 extends FileObserver {
        final /* synthetic */ List val$paths;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List list, List list2) {
            super((List<File>) list);
            this.val$paths = list2;
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            String mimeType;
            Iterator it = this.val$paths.iterator();
            while (it.hasNext()) {
                final File file = new File(((String) it.next()) + str);
                if (file.exists() && (mimeType = ScreenCaptureEventPlugin.getMimeType(file.getPath())) != null) {
                    if (i == 256 || i == 2) {
                        if (mimeType.contains("video")) {
                            ScreenCaptureEventPlugin.this.setScreenRecordStatus(true);
                            ScreenCaptureEventPlugin.this.updateScreenRecordStatus();
                        } else if (mimeType.contains("image")) {
                            ScreenCaptureEventPlugin.this.handler.post(new Runnable() { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin$1$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    ScreenCaptureEventPlugin.AnonymousClass1.this.lambda$onEvent$0(file);
                                }
                            });
                        }
                    } else if (mimeType.contains("video")) {
                        ScreenCaptureEventPlugin.this.stopAllRecordWatcher();
                        ScreenCaptureEventPlugin.this.setScreenRecordStatus(false);
                        ScreenCaptureEventPlugin.this.updateScreenRecordStatus();
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onEvent$0(File file) {
            ScreenCaptureEventPlugin.this.channel.invokeMethod("screenshot", file.getPath());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 extends FileObserver {
        final /* synthetic */ Path val$path;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Path path) {
            super(str);
            this.val$path = path;
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            String mimeType;
            final File file = new File(this.val$path.getPath() + str);
            if (!file.exists() || (mimeType = ScreenCaptureEventPlugin.getMimeType(file.getPath())) == null) {
                return;
            }
            if (i == 256 || i == 2) {
                if (mimeType.contains("video")) {
                    ScreenCaptureEventPlugin.this.setScreenRecordStatus(true);
                    ScreenCaptureEventPlugin.this.updateScreenRecordStatus();
                    return;
                } else {
                    if (mimeType.contains("image")) {
                        ScreenCaptureEventPlugin.this.handler.post(new Runnable() { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin$2$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                ScreenCaptureEventPlugin.AnonymousClass2.this.lambda$onEvent$0(file);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            if (mimeType.contains("video")) {
                ScreenCaptureEventPlugin.this.stopAllRecordWatcher();
                ScreenCaptureEventPlugin.this.setScreenRecordStatus(false);
                ScreenCaptureEventPlugin.this.updateScreenRecordStatus();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onEvent$0(File file) {
            ScreenCaptureEventPlugin.this.channel.invokeMethod("screenshot", file.getPath());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAllRecordWatcher() {
        Iterator<Map.Entry<String, FileObserver>> it = this.watchModifier.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().stopWatching();
        }
        this.watchModifier.clear();
        setScreenRecordStatus(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScreenRecordStatus() {
        String mimeType;
        FileObserver fileObserver;
        ArrayList arrayList = new ArrayList();
        for (Path path : Path.values()) {
            arrayList.add(path.getPath());
        }
        for (int i = 0; i < arrayList.size(); i++) {
            final File lastModified = getLastModified((String) arrayList.get(i));
            if (lastModified != null && (mimeType = getMimeType(lastModified.getPath())) != null && mimeType.contains("video") && !this.watchModifier.containsKey(lastModified.getPath())) {
                if (Build.VERSION.SDK_INT >= 29) {
                    fileObserver = new FileObserver(lastModified) { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin.3
                        @Override // android.os.FileObserver
                        public void onEvent(int i2, String str) {
                            ScreenCaptureEventPlugin.this.handleUpdateScreenRecordEvent(i2, lastModified);
                        }
                    };
                } else {
                    fileObserver = new FileObserver(lastModified.getPath()) { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin.4
                        @Override // android.os.FileObserver
                        public void onEvent(int i2, String str) {
                            ScreenCaptureEventPlugin.this.handleUpdateScreenRecordEvent(i2, lastModified);
                        }
                    };
                }
                this.watchModifier.put(lastModified.getPath(), fileObserver);
                FileObserver fileObserver2 = this.watchModifier.get(lastModified.getPath());
                if (fileObserver2 != null) {
                    fileObserver2.startWatching();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdateScreenRecordEvent(int i, final File file) {
        final long length = file.length();
        if (length > this.tempSize) {
            Timer timer = this.timeout;
            if (timer != null) {
                try {
                    timer.cancel();
                    this.timeout = null;
                } catch (Exception unused) {
                }
            }
            setScreenRecordStatus(i == 2);
            this.tempSize = file.length();
        }
        if (this.timeout == null) {
            Timer timer2 = new Timer();
            this.timeout = timer2;
            timer2.schedule(new TimerTask() { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (ScreenCaptureEventPlugin.this.watchModifier.containsKey(file.getPath())) {
                        ScreenCaptureEventPlugin screenCaptureEventPlugin = ScreenCaptureEventPlugin.this;
                        screenCaptureEventPlugin.setScreenRecordStatus(length != screenCaptureEventPlugin.tempSize);
                    }
                }
            }, 1500L);
        }
    }

    void setScreenRecordStatus(final boolean z) {
        if (this.screenRecording != z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenCaptureEventPlugin.this.lambda$setScreenRecordStatus$0(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setScreenRecordStatus$0(boolean z) {
        this.screenRecording = z;
        this.channel.invokeMethod("screenrecord", Boolean.valueOf(z));
    }

    public static String getMimeType(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0 || lastIndexOf >= str.length() - 1) {
            return null;
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(lastIndexOf + 1));
    }

    public static File getLastModified(String str) {
        File file = new File(str);
        File file2 = null;
        if (file.listFiles() == null) {
            return null;
        }
        File[] listFiles = file.listFiles(new FileFilter() { // from class: id.nizwar.screen_capture_event.ScreenCaptureEventPlugin$$ExternalSyntheticLambda0
            @Override // java.io.FileFilter
            public final boolean accept(File file3) {
                return file3.isFile();
            }
        });
        long j = Long.MIN_VALUE;
        if (listFiles != null) {
            for (File file3 : listFiles) {
                if (file3.lastModified() > j) {
                    j = file3.lastModified();
                    file2 = file3;
                }
            }
        }
        return file2;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activityPluginBinding = activityPluginBinding;
    }

    /* loaded from: classes5.dex */
    public enum Path {
        DCIMSAMSUNG(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + "Screen recordings" + File.separator),
        DCIM(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + "Screenshots" + File.separator),
        PICTURES(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + "Screenshots" + File.separator);

        private final String path;

        public String getPath() {
            return this.path;
        }

        Path(String str) {
            this.path = str;
        }
    }
}
