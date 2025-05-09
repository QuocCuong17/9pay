package io.flutter.plugins.firebase.messaging;

/* loaded from: classes5.dex */
class PluginRegistrantException extends RuntimeException {
    public PluginRegistrantException() {
        super("PluginRegistrantCallback is not set. Did you forget to call FlutterFirebaseMessagingBackgroundService.setPluginRegistrant? See the documentation for instructions.");
    }
}
