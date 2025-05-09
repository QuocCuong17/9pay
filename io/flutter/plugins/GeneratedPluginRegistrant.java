package io.flutter.plugins;

import co.hyperverge.hyperkyc_flutter.HyperkycFlutterPlugin;
import com.aifeii.qrcode.tools.QrCodeToolsPlugin;
import com.baseflow.permissionhandler.PermissionHandlerPlugin;
import com.benjaminabel.vibration.VibrationPlugin;
import com.cygnati.social_share_plugin.SocialSharePlugin;
import com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin;
import com.example.appsettings.AppSettingsPlugin;
import com.example.flutter_video_info.FlutterVideoInfoPlugin;
import com.example.video_compress.VideoCompressPlugin;
import com.heqingbao.flutter.plugin.email_launcher.EmailLauncherPlugin;
import com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin;
import com.jrai.flutter_keyboard_visibility.FlutterKeyboardVisibilityPlugin;
import com.llfbandit.app_links.AppLinksPlugin;
import com.mr.flutter.plugin.filepicker.FilePickerPlugin;
import com.ngovang.flutter_lifecycle_detector.FlutterLifecycleDetectorPlugin;
import com.ngovang.otp_listener.OtpListenerPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.ryanheise.audio_session.AudioSessionPlugin;
import com.ryanheise.just_audio.JustAudioPlugin;
import com.tekartik.sqflite.SqflitePlugin;
import com.twwm.share_files_and_screenshot_widgets.ShareFilesAndScreenshotWidgetsPlugin;
import com.w3conext.jailbreak_root_detection.JailbreakRootDetectionPlugin;
import dev.fluttercommunity.plus.connectivity.ConnectivityPlugin;
import dev.fluttercommunity.plus.device_info.DeviceInfoPlusPlugin;
import dev.fluttercommunity.plus.share.SharePlusPlugin;
import dev.fluttercommunity.plus.wakelock.WakelockPlusPlugin;
import dev.knottx.flutter_image_gallery_saver.FlutterImageGallerySaverPlugin;
import flutter.plugins.contactsservice.contactsservice.ContactsServicePlugin;
import id.nizwar.screen_capture_event.ScreenCaptureEventPlugin;
import io.endigo.plugins.pdfviewflutter.PDFViewFlutterPlugin;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin;
import io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin;
import io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin;
import io.flutter.plugins.firebase.database.FirebaseDatabasePlugin;
import io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin;
import io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin;
import io.flutter.plugins.flutter_plugin_android_lifecycle.FlutterAndroidLifecyclePlugin;
import io.flutter.plugins.imagepicker.ImagePickerPlugin;
import io.flutter.plugins.localauth.LocalAuthPlugin;
import io.flutter.plugins.nfcmanager.NfcManagerPlugin;
import io.flutter.plugins.packageinfo.PackageInfoPlugin;
import io.flutter.plugins.pathprovider.PathProviderPlugin;
import io.flutter.plugins.urllauncher.UrlLauncherPlugin;
import io.flutter.plugins.videoplayer.VideoPlayerPlugin;
import io.flutter.plugins.webviewflutter.WebViewFlutterPlugin;
import kent.chien.client_information.ClientInformationPlugin;
import net.touchcapture.qr.flutterqr.FlutterQrPlugin;
import xyz.justsoft.video_thumbnail.VideoThumbnailPlugin;
import xyz.luan.audioplayers.AudioplayersPlugin;

/* loaded from: classes5.dex */
public final class GeneratedPluginRegistrant {
    private static final String TAG = "GeneratedPluginRegistrant";

    public static void registerWith(FlutterEngine flutterEngine) {
        try {
            flutterEngine.getPlugins().add(new AppLinksPlugin());
        } catch (Exception e) {
            Log.e(TAG, "Error registering plugin app_links, com.llfbandit.app_links.AppLinksPlugin", e);
        }
        try {
            flutterEngine.getPlugins().add(new AppSettingsPlugin());
        } catch (Exception e2) {
            Log.e(TAG, "Error registering plugin app_settings, com.example.appsettings.AppSettingsPlugin", e2);
        }
        try {
            flutterEngine.getPlugins().add(new AudioSessionPlugin());
        } catch (Exception e3) {
            Log.e(TAG, "Error registering plugin audio_session, com.ryanheise.audio_session.AudioSessionPlugin", e3);
        }
        try {
            flutterEngine.getPlugins().add(new AudioplayersPlugin());
        } catch (Exception e4) {
            Log.e(TAG, "Error registering plugin audioplayers_android, xyz.luan.audioplayers.AudioplayersPlugin", e4);
        }
        try {
            flutterEngine.getPlugins().add(new ClientInformationPlugin());
        } catch (Exception e5) {
            Log.e(TAG, "Error registering plugin client_information, kent.chien.client_information.ClientInformationPlugin", e5);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseFirestorePlugin());
        } catch (Exception e6) {
            Log.e(TAG, "Error registering plugin cloud_firestore, io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin", e6);
        }
        try {
            flutterEngine.getPlugins().add(new ConnectivityPlugin());
        } catch (Exception e7) {
            Log.e(TAG, "Error registering plugin connectivity_plus, dev.fluttercommunity.plus.connectivity.ConnectivityPlugin", e7);
        }
        try {
            flutterEngine.getPlugins().add(new ContactsServicePlugin());
        } catch (Exception e8) {
            Log.e(TAG, "Error registering plugin contacts_service, flutter.plugins.contactsservice.contactsservice.ContactsServicePlugin", e8);
        }
        try {
            flutterEngine.getPlugins().add(new DeviceInfoPlusPlugin());
        } catch (Exception e9) {
            Log.e(TAG, "Error registering plugin device_info_plus, dev.fluttercommunity.plus.device_info.DeviceInfoPlusPlugin", e9);
        }
        try {
            flutterEngine.getPlugins().add(new EmailLauncherPlugin());
        } catch (Exception e10) {
            Log.e(TAG, "Error registering plugin email_launcher, com.heqingbao.flutter.plugin.email_launcher.EmailLauncherPlugin", e10);
        }
        try {
            flutterEngine.getPlugins().add(new FilePickerPlugin());
        } catch (Exception e11) {
            Log.e(TAG, "Error registering plugin file_picker, com.mr.flutter.plugin.filepicker.FilePickerPlugin", e11);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseAnalyticsPlugin());
        } catch (Exception e12) {
            Log.e(TAG, "Error registering plugin firebase_analytics, io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin", e12);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseCorePlugin());
        } catch (Exception e13) {
            Log.e(TAG, "Error registering plugin firebase_core, io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin", e13);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseCrashlyticsPlugin());
        } catch (Exception e14) {
            Log.e(TAG, "Error registering plugin firebase_crashlytics, io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin", e14);
        }
        try {
            flutterEngine.getPlugins().add(new FirebaseDatabasePlugin());
        } catch (Exception e15) {
            Log.e(TAG, "Error registering plugin firebase_database, io.flutter.plugins.firebase.database.FirebaseDatabasePlugin", e15);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseDynamicLinksPlugin());
        } catch (Exception e16) {
            Log.e(TAG, "Error registering plugin firebase_dynamic_links, io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin", e16);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseMessagingPlugin());
        } catch (Exception e17) {
            Log.e(TAG, "Error registering plugin firebase_messaging, io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin", e17);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterFirebaseStoragePlugin());
        } catch (Exception e18) {
            Log.e(TAG, "Error registering plugin firebase_storage, io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin", e18);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterImageGallerySaverPlugin());
        } catch (Exception e19) {
            Log.e(TAG, "Error registering plugin flutter_image_gallery_saver, dev.knottx.flutter_image_gallery_saver.FlutterImageGallerySaverPlugin", e19);
        }
        try {
            flutterEngine.getPlugins().add(new InAppWebViewFlutterPlugin());
        } catch (Exception e20) {
            Log.e(TAG, "Error registering plugin flutter_inappwebview_android, com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin", e20);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterKeyboardVisibilityPlugin());
        } catch (Exception e21) {
            Log.e(TAG, "Error registering plugin flutter_keyboard_visibility, com.jrai.flutter_keyboard_visibility.FlutterKeyboardVisibilityPlugin", e21);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterLifecycleDetectorPlugin());
        } catch (Exception e22) {
            Log.e(TAG, "Error registering plugin flutter_lifecycle_detector, com.ngovang.flutter_lifecycle_detector.FlutterLifecycleDetectorPlugin", e22);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterLocalNotificationsPlugin());
        } catch (Exception e23) {
            Log.e(TAG, "Error registering plugin flutter_local_notifications, com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin", e23);
        }
        try {
            flutterEngine.getPlugins().add(new PDFViewFlutterPlugin());
        } catch (Exception e24) {
            Log.e(TAG, "Error registering plugin flutter_pdfview, io.endigo.plugins.pdfviewflutter.PDFViewFlutterPlugin", e24);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterAndroidLifecyclePlugin());
        } catch (Exception e25) {
            Log.e(TAG, "Error registering plugin flutter_plugin_android_lifecycle, io.flutter.plugins.flutter_plugin_android_lifecycle.FlutterAndroidLifecyclePlugin", e25);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterSecureStoragePlugin());
        } catch (Exception e26) {
            Log.e(TAG, "Error registering plugin flutter_secure_storage, com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin", e26);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterVideoInfoPlugin());
        } catch (Exception e27) {
            Log.e(TAG, "Error registering plugin flutter_video_info, com.example.flutter_video_info.FlutterVideoInfoPlugin", e27);
        }
        try {
            flutterEngine.getPlugins().add(new HyperkycFlutterPlugin());
        } catch (Exception e28) {
            Log.e(TAG, "Error registering plugin hyperkyc_flutter, co.hyperverge.hyperkyc_flutter.HyperkycFlutterPlugin", e28);
        }
        try {
            flutterEngine.getPlugins().add(new ImagePickerPlugin());
        } catch (Exception e29) {
            Log.e(TAG, "Error registering plugin image_picker_android, io.flutter.plugins.imagepicker.ImagePickerPlugin", e29);
        }
        try {
            flutterEngine.getPlugins().add(new JailbreakRootDetectionPlugin());
        } catch (Exception e30) {
            Log.e(TAG, "Error registering plugin jailbreak_root_detection, com.w3conext.jailbreak_root_detection.JailbreakRootDetectionPlugin", e30);
        }
        try {
            flutterEngine.getPlugins().add(new JustAudioPlugin());
        } catch (Exception e31) {
            Log.e(TAG, "Error registering plugin just_audio, com.ryanheise.just_audio.JustAudioPlugin", e31);
        }
        try {
            flutterEngine.getPlugins().add(new LocalAuthPlugin());
        } catch (Exception e32) {
            Log.e(TAG, "Error registering plugin local_auth_android, io.flutter.plugins.localauth.LocalAuthPlugin", e32);
        }
        try {
            flutterEngine.getPlugins().add(new NfcManagerPlugin());
        } catch (Exception e33) {
            Log.e(TAG, "Error registering plugin nfc_manager, io.flutter.plugins.nfcmanager.NfcManagerPlugin", e33);
        }
        try {
            flutterEngine.getPlugins().add(new OtpListenerPlugin());
        } catch (Exception e34) {
            Log.e(TAG, "Error registering plugin otp_listener, com.ngovang.otp_listener.OtpListenerPlugin", e34);
        }
        try {
            flutterEngine.getPlugins().add(new PackageInfoPlugin());
        } catch (Exception e35) {
            Log.e(TAG, "Error registering plugin package_info, io.flutter.plugins.packageinfo.PackageInfoPlugin", e35);
        }
        try {
            flutterEngine.getPlugins().add(new dev.fluttercommunity.plus.packageinfo.PackageInfoPlugin());
        } catch (Exception e36) {
            Log.e(TAG, "Error registering plugin package_info_plus, dev.fluttercommunity.plus.packageinfo.PackageInfoPlugin", e36);
        }
        try {
            flutterEngine.getPlugins().add(new PathProviderPlugin());
        } catch (Exception e37) {
            Log.e(TAG, "Error registering plugin path_provider_android, io.flutter.plugins.pathprovider.PathProviderPlugin", e37);
        }
        try {
            flutterEngine.getPlugins().add(new PermissionHandlerPlugin());
        } catch (Exception e38) {
            Log.e(TAG, "Error registering plugin permission_handler, com.baseflow.permissionhandler.PermissionHandlerPlugin", e38);
        }
        try {
            flutterEngine.getPlugins().add(new FlutterQrPlugin());
        } catch (Exception e39) {
            Log.e(TAG, "Error registering plugin qr_code_scanner, net.touchcapture.qr.flutterqr.FlutterQrPlugin", e39);
        }
        try {
            flutterEngine.getPlugins().add(new QrCodeToolsPlugin());
        } catch (Exception e40) {
            Log.e(TAG, "Error registering plugin qr_code_tools, com.aifeii.qrcode.tools.QrCodeToolsPlugin", e40);
        }
        try {
            flutterEngine.getPlugins().add(new ScreenCaptureEventPlugin());
        } catch (Exception e41) {
            Log.e(TAG, "Error registering plugin screen_capture_event, id.nizwar.screen_capture_event.ScreenCaptureEventPlugin", e41);
        }
        try {
            flutterEngine.getPlugins().add(new ShareFilesAndScreenshotWidgetsPlugin());
        } catch (Exception e42) {
            Log.e(TAG, "Error registering plugin share_files_and_screenshot_widgets_plus, com.twwm.share_files_and_screenshot_widgets.ShareFilesAndScreenshotWidgetsPlugin", e42);
        }
        try {
            flutterEngine.getPlugins().add(new SharePlusPlugin());
        } catch (Exception e43) {
            Log.e(TAG, "Error registering plugin share_plus, dev.fluttercommunity.plus.share.SharePlusPlugin", e43);
        }
        try {
            flutterEngine.getPlugins().add(new SocialSharePlugin());
        } catch (Exception e44) {
            Log.e(TAG, "Error registering plugin social_share_plugin, com.cygnati.social_share_plugin.SocialSharePlugin", e44);
        }
        try {
            flutterEngine.getPlugins().add(new SqflitePlugin());
        } catch (Exception e45) {
            Log.e(TAG, "Error registering plugin sqflite_android, com.tekartik.sqflite.SqflitePlugin", e45);
        }
        try {
            flutterEngine.getPlugins().add(new UrlLauncherPlugin());
        } catch (Exception e46) {
            Log.e(TAG, "Error registering plugin url_launcher_android, io.flutter.plugins.urllauncher.UrlLauncherPlugin", e46);
        }
        try {
            flutterEngine.getPlugins().add(new VibrationPlugin());
        } catch (Exception e47) {
            Log.e(TAG, "Error registering plugin vibration, com.benjaminabel.vibration.VibrationPlugin", e47);
        }
        try {
            flutterEngine.getPlugins().add(new VideoCompressPlugin());
        } catch (Exception e48) {
            Log.e(TAG, "Error registering plugin video_compress, com.example.video_compress.VideoCompressPlugin", e48);
        }
        try {
            flutterEngine.getPlugins().add(new VideoPlayerPlugin());
        } catch (Exception e49) {
            Log.e(TAG, "Error registering plugin video_player_android, io.flutter.plugins.videoplayer.VideoPlayerPlugin", e49);
        }
        try {
            flutterEngine.getPlugins().add(new VideoThumbnailPlugin());
        } catch (Exception e50) {
            Log.e(TAG, "Error registering plugin video_thumbnail, xyz.justsoft.video_thumbnail.VideoThumbnailPlugin", e50);
        }
        try {
            flutterEngine.getPlugins().add(new WakelockPlusPlugin());
        } catch (Exception e51) {
            Log.e(TAG, "Error registering plugin wakelock_plus, dev.fluttercommunity.plus.wakelock.WakelockPlusPlugin", e51);
        }
        try {
            flutterEngine.getPlugins().add(new WebViewFlutterPlugin());
        } catch (Exception e52) {
            Log.e(TAG, "Error registering plugin webview_flutter_android, io.flutter.plugins.webviewflutter.WebViewFlutterPlugin", e52);
        }
    }
}
