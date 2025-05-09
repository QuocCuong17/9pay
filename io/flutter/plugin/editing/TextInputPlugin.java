package io.flutter.plugin.editing;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import io.flutter.Log;
import io.flutter.embedding.android.KeyboardManager;
import io.flutter.embedding.engine.systemchannels.TextInputChannel;
import io.flutter.plugin.editing.ListenableEditingState;
import io.flutter.plugin.platform.PlatformViewsController;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class TextInputPlugin implements ListenableEditingState.EditingStateWatcher {
    private static final String TAG = "TextInputPlugin";
    private final AutofillManager afm;
    private SparseArray<TextInputChannel.Configuration> autofillConfiguration;
    private TextInputChannel.Configuration configuration;
    private ImeSyncDeferringInsetsCallback imeSyncCallback;
    private InputTarget inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
    private boolean isInputConnectionLocked;
    private Rect lastClientRect;
    private InputConnection lastInputConnection;
    private ListenableEditingState mEditable;
    private final InputMethodManager mImm;
    private TextInputChannel.TextEditState mLastKnownFrameworkTextEditingState;
    private boolean mRestartInputPending;
    private final View mView;
    private PlatformViewsController platformViewsController;
    private final TextInputChannel textInputChannel;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface MinMax {
        void inspect(double d, double d2);
    }

    public TextInputPlugin(View view, TextInputChannel textInputChannel, PlatformViewsController platformViewsController) {
        this.mView = view;
        this.mEditable = new ListenableEditingState(null, view);
        this.mImm = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (Build.VERSION.SDK_INT >= 26) {
            this.afm = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        } else {
            this.afm = null;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = new ImeSyncDeferringInsetsCallback(view);
            this.imeSyncCallback = imeSyncDeferringInsetsCallback;
            imeSyncDeferringInsetsCallback.install();
        }
        this.textInputChannel = textInputChannel;
        textInputChannel.setTextInputMethodHandler(new TextInputChannel.TextInputMethodHandler() { // from class: io.flutter.plugin.editing.TextInputPlugin.1
            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void show() {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.showTextInput(textInputPlugin.mView);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void hide() {
                if (TextInputPlugin.this.inputTarget.type == InputTarget.Type.PHYSICAL_DISPLAY_PLATFORM_VIEW) {
                    TextInputPlugin.this.notifyViewExited();
                } else {
                    TextInputPlugin textInputPlugin = TextInputPlugin.this;
                    textInputPlugin.hideTextInput(textInputPlugin.mView);
                }
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void requestAutofill() {
                TextInputPlugin.this.notifyViewEntered();
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void finishAutofillContext(boolean z) {
                if (Build.VERSION.SDK_INT < 26 || TextInputPlugin.this.afm == null) {
                    return;
                }
                if (z) {
                    TextInputPlugin.this.afm.commit();
                } else {
                    TextInputPlugin.this.afm.cancel();
                }
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setClient(int i, TextInputChannel.Configuration configuration) {
                TextInputPlugin.this.setTextInputClient(i, configuration);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setPlatformViewClient(int i, boolean z) {
                TextInputPlugin.this.setPlatformViewTextInputClient(i, z);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setEditingState(TextInputChannel.TextEditState textEditState) {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.setTextInputEditingState(textInputPlugin.mView, textEditState);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setEditableSizeAndTransform(double d, double d2, double[] dArr) {
                TextInputPlugin.this.saveEditableSizeAndTransform(d, d2, dArr);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void clearClient() {
                TextInputPlugin.this.clearTextInputClient();
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void sendAppPrivateCommand(String str, Bundle bundle) {
                TextInputPlugin.this.sendTextInputAppPrivateCommand(str, bundle);
            }
        });
        textInputChannel.requestExistingInputState();
        this.platformViewsController = platformViewsController;
        platformViewsController.attachTextInputPlugin(this);
    }

    public InputMethodManager getInputMethodManager() {
        return this.mImm;
    }

    Editable getEditable() {
        return this.mEditable;
    }

    ImeSyncDeferringInsetsCallback getImeSyncCallback() {
        return this.imeSyncCallback;
    }

    public void lockPlatformViewInputConnection() {
        if (this.inputTarget.type == InputTarget.Type.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            this.isInputConnectionLocked = true;
        }
    }

    public void unlockPlatformViewInputConnection() {
        if (this.inputTarget.type == InputTarget.Type.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            this.isInputConnectionLocked = false;
        }
    }

    public void destroy() {
        this.platformViewsController.detachTextInputPlugin();
        this.textInputChannel.setTextInputMethodHandler(null);
        notifyViewExited();
        this.mEditable.removeEditingStateListener(this);
        ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = this.imeSyncCallback;
        if (imeSyncDeferringInsetsCallback != null) {
            imeSyncDeferringInsetsCallback.remove();
        }
    }

    private static int inputTypeFromTextInputType(TextInputChannel.InputType inputType, boolean z, boolean z2, boolean z3, boolean z4, TextInputChannel.TextCapitalization textCapitalization) {
        int i;
        if (inputType.type == TextInputChannel.TextInputType.DATETIME) {
            return 4;
        }
        if (inputType.type == TextInputChannel.TextInputType.NUMBER) {
            int i2 = inputType.isSigned ? InputDeviceCompat.SOURCE_TOUCHSCREEN : 2;
            return inputType.isDecimal ? i2 | 8192 : i2;
        }
        if (inputType.type == TextInputChannel.TextInputType.PHONE) {
            return 3;
        }
        if (inputType.type == TextInputChannel.TextInputType.NONE) {
            return 0;
        }
        int i3 = 1;
        if (inputType.type == TextInputChannel.TextInputType.MULTILINE) {
            i3 = 131073;
        } else if (inputType.type == TextInputChannel.TextInputType.EMAIL_ADDRESS) {
            i3 = 33;
        } else if (inputType.type == TextInputChannel.TextInputType.URL) {
            i3 = 17;
        } else if (inputType.type == TextInputChannel.TextInputType.VISIBLE_PASSWORD) {
            i3 = CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA;
        } else if (inputType.type == TextInputChannel.TextInputType.NAME) {
            i3 = 97;
        } else if (inputType.type == TextInputChannel.TextInputType.POSTAL_ADDRESS) {
            i3 = 113;
        }
        if (z) {
            i = 524288 | i3 | 128;
        } else {
            if (z2) {
                i3 |= 32768;
            }
            i = !z3 ? 524288 | i3 | CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA : i3;
        }
        return textCapitalization == TextInputChannel.TextCapitalization.CHARACTERS ? i | 4096 : textCapitalization == TextInputChannel.TextCapitalization.WORDS ? i | 8192 : textCapitalization == TextInputChannel.TextCapitalization.SENTENCES ? i | 16384 : i;
    }

    public InputConnection createInputConnection(View view, KeyboardManager keyboardManager, EditorInfo editorInfo) {
        int intValue;
        if (this.inputTarget.type == InputTarget.Type.NO_TARGET) {
            this.lastInputConnection = null;
            return null;
        }
        if (this.inputTarget.type == InputTarget.Type.PHYSICAL_DISPLAY_PLATFORM_VIEW) {
            return null;
        }
        if (this.inputTarget.type == InputTarget.Type.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            if (this.isInputConnectionLocked) {
                return this.lastInputConnection;
            }
            InputConnection onCreateInputConnection = this.platformViewsController.getPlatformViewById(this.inputTarget.f121id).onCreateInputConnection(editorInfo);
            this.lastInputConnection = onCreateInputConnection;
            return onCreateInputConnection;
        }
        editorInfo.inputType = inputTypeFromTextInputType(this.configuration.inputType, this.configuration.obscureText, this.configuration.autocorrect, this.configuration.enableSuggestions, this.configuration.enableIMEPersonalizedLearning, this.configuration.textCapitalization);
        editorInfo.imeOptions = PDButton.FLAG_RADIOS_IN_UNISON;
        if (Build.VERSION.SDK_INT >= 26 && !this.configuration.enableIMEPersonalizedLearning) {
            editorInfo.imeOptions |= 16777216;
        }
        if (this.configuration.inputAction == null) {
            intValue = (131072 & editorInfo.inputType) != 0 ? 1 : 6;
        } else {
            intValue = this.configuration.inputAction.intValue();
        }
        if (this.configuration.actionLabel != null) {
            editorInfo.actionLabel = this.configuration.actionLabel;
            editorInfo.actionId = intValue;
        }
        editorInfo.imeOptions = intValue | editorInfo.imeOptions;
        if (this.configuration.contentCommitMimeTypes != null) {
            EditorInfoCompat.setContentMimeTypes(editorInfo, this.configuration.contentCommitMimeTypes);
        }
        InputConnectionAdaptor inputConnectionAdaptor = new InputConnectionAdaptor(view, this.inputTarget.f121id, this.textInputChannel, keyboardManager, this.mEditable, editorInfo);
        editorInfo.initialSelStart = this.mEditable.getSelectionStart();
        editorInfo.initialSelEnd = this.mEditable.getSelectionEnd();
        this.lastInputConnection = inputConnectionAdaptor;
        return inputConnectionAdaptor;
    }

    public InputConnection getLastInputConnection() {
        return this.lastInputConnection;
    }

    public void clearPlatformViewClient(int i) {
        if ((this.inputTarget.type == InputTarget.Type.VIRTUAL_DISPLAY_PLATFORM_VIEW || this.inputTarget.type == InputTarget.Type.PHYSICAL_DISPLAY_PLATFORM_VIEW) && this.inputTarget.f121id == i) {
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            notifyViewExited();
            this.mImm.hideSoftInputFromWindow(this.mView.getApplicationWindowToken(), 0);
            this.mImm.restartInput(this.mView);
            this.mRestartInputPending = false;
        }
    }

    public void sendTextInputAppPrivateCommand(String str, Bundle bundle) {
        this.mImm.sendAppPrivateCommand(this.mView, str, bundle);
    }

    void showTextInput(View view) {
        TextInputChannel.Configuration configuration = this.configuration;
        if (configuration == null || configuration.inputType == null || this.configuration.inputType.type != TextInputChannel.TextInputType.NONE) {
            view.requestFocus();
            this.mImm.showSoftInput(view, 0);
        } else {
            hideTextInput(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTextInput(View view) {
        notifyViewExited();
        this.mImm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    void setTextInputClient(int i, TextInputChannel.Configuration configuration) {
        notifyViewExited();
        this.configuration = configuration;
        this.inputTarget = new InputTarget(InputTarget.Type.FRAMEWORK_CLIENT, i);
        this.mEditable.removeEditingStateListener(this);
        this.mEditable = new ListenableEditingState(configuration.autofill != null ? configuration.autofill.editState : null, this.mView);
        updateAutofillConfigurationIfNeeded(configuration);
        this.mRestartInputPending = true;
        unlockPlatformViewInputConnection();
        this.lastClientRect = null;
        this.mEditable.addEditingStateListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlatformViewTextInputClient(int i, boolean z) {
        if (z) {
            this.mView.requestFocus();
            this.inputTarget = new InputTarget(InputTarget.Type.VIRTUAL_DISPLAY_PLATFORM_VIEW, i);
            this.mImm.restartInput(this.mView);
            this.mRestartInputPending = false;
            return;
        }
        this.inputTarget = new InputTarget(InputTarget.Type.PHYSICAL_DISPLAY_PLATFORM_VIEW, i);
        this.lastInputConnection = null;
    }

    private static boolean composingChanged(TextInputChannel.TextEditState textEditState, TextInputChannel.TextEditState textEditState2) {
        int i = textEditState.composingEnd - textEditState.composingStart;
        if (i != textEditState2.composingEnd - textEditState2.composingStart) {
            return true;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (textEditState.text.charAt(textEditState.composingStart + i2) != textEditState2.text.charAt(textEditState2.composingStart + i2)) {
                return true;
            }
        }
        return false;
    }

    void setTextInputEditingState(View view, TextInputChannel.TextEditState textEditState) {
        TextInputChannel.TextEditState textEditState2;
        if (!this.mRestartInputPending && (textEditState2 = this.mLastKnownFrameworkTextEditingState) != null && textEditState2.hasComposing()) {
            boolean composingChanged = composingChanged(this.mLastKnownFrameworkTextEditingState, textEditState);
            this.mRestartInputPending = composingChanged;
            if (composingChanged) {
                Log.i(TAG, "Composing region changed by the framework. Restarting the input method.");
            }
        }
        this.mLastKnownFrameworkTextEditingState = textEditState;
        this.mEditable.setEditingState(textEditState);
        if (this.mRestartInputPending) {
            this.mImm.restartInput(view);
            this.mRestartInputPending = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveEditableSizeAndTransform(double d, double d2, final double[] dArr) {
        final double[] dArr2 = new double[4];
        final boolean z = dArr[3] == 0.0d && dArr[7] == 0.0d && dArr[15] == 1.0d;
        double d3 = dArr[12] / dArr[15];
        dArr2[1] = d3;
        dArr2[0] = d3;
        double d4 = dArr[13] / dArr[15];
        dArr2[3] = d4;
        dArr2[2] = d4;
        MinMax minMax = new MinMax() { // from class: io.flutter.plugin.editing.TextInputPlugin.2
            @Override // io.flutter.plugin.editing.TextInputPlugin.MinMax
            public void inspect(double d5, double d6) {
                double d7 = 1.0d;
                if (!z) {
                    double[] dArr3 = dArr;
                    d7 = 1.0d / (((dArr3[3] * d5) + (dArr3[7] * d6)) + dArr3[15]);
                }
                double[] dArr4 = dArr;
                double d8 = ((dArr4[0] * d5) + (dArr4[4] * d6) + dArr4[12]) * d7;
                double d9 = ((dArr4[1] * d5) + (dArr4[5] * d6) + dArr4[13]) * d7;
                double[] dArr5 = dArr2;
                if (d8 < dArr5[0]) {
                    dArr5[0] = d8;
                } else if (d8 > dArr5[1]) {
                    dArr5[1] = d8;
                }
                if (d9 < dArr5[2]) {
                    dArr5[2] = d9;
                } else if (d9 > dArr5[3]) {
                    dArr5[3] = d9;
                }
            }
        };
        minMax.inspect(d, 0.0d);
        minMax.inspect(d, d2);
        minMax.inspect(0.0d, d2);
        Float valueOf = Float.valueOf(this.mView.getContext().getResources().getDisplayMetrics().density);
        this.lastClientRect = new Rect((int) (dArr2[0] * valueOf.floatValue()), (int) (dArr2[2] * valueOf.floatValue()), (int) Math.ceil(dArr2[1] * valueOf.floatValue()), (int) Math.ceil(dArr2[3] * valueOf.floatValue()));
    }

    void clearTextInputClient() {
        if (this.inputTarget.type == InputTarget.Type.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            return;
        }
        this.mEditable.removeEditingStateListener(this);
        notifyViewExited();
        this.configuration = null;
        updateAutofillConfigurationIfNeeded(null);
        this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
        unlockPlatformViewInputConnection();
        this.lastClientRect = null;
        this.mImm.restartInput(this.mView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class InputTarget {

        /* renamed from: id, reason: collision with root package name */
        int f121id;
        Type type;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes5.dex */
        public enum Type {
            NO_TARGET,
            FRAMEWORK_CLIENT,
            VIRTUAL_DISPLAY_PLATFORM_VIEW,
            PHYSICAL_DISPLAY_PLATFORM_VIEW
        }

        public InputTarget(Type type, int i) {
            this.type = type;
            this.f121id = i;
        }
    }

    public boolean handleKeyEvent(KeyEvent keyEvent) {
        InputConnection inputConnection;
        if (!getInputMethodManager().isAcceptingText() || (inputConnection = this.lastInputConnection) == null) {
            return false;
        }
        if (inputConnection instanceof InputConnectionAdaptor) {
            return ((InputConnectionAdaptor) inputConnection).handleKeyEvent(keyEvent);
        }
        return inputConnection.sendKeyEvent(keyEvent);
    }

    @Override // io.flutter.plugin.editing.ListenableEditingState.EditingStateWatcher
    public void didChangeEditingState(boolean z, boolean z2, boolean z3) {
        if (z) {
            notifyValueChanged(this.mEditable.toString());
        }
        int selectionStart = this.mEditable.getSelectionStart();
        int selectionEnd = this.mEditable.getSelectionEnd();
        int composingStart = this.mEditable.getComposingStart();
        int composingEnd = this.mEditable.getComposingEnd();
        ArrayList<TextEditingDelta> extractBatchTextEditingDeltas = this.mEditable.extractBatchTextEditingDeltas();
        if (!(this.mLastKnownFrameworkTextEditingState == null || (this.mEditable.toString().equals(this.mLastKnownFrameworkTextEditingState.text) && selectionStart == this.mLastKnownFrameworkTextEditingState.selectionStart && selectionEnd == this.mLastKnownFrameworkTextEditingState.selectionEnd && composingStart == this.mLastKnownFrameworkTextEditingState.composingStart && composingEnd == this.mLastKnownFrameworkTextEditingState.composingEnd))) {
            Log.v(TAG, "send EditingState to flutter: " + this.mEditable.toString());
            if (this.configuration.enableDeltaModel) {
                this.textInputChannel.updateEditingStateWithDeltas(this.inputTarget.f121id, extractBatchTextEditingDeltas);
                this.mEditable.clearBatchDeltas();
            } else {
                this.textInputChannel.updateEditingState(this.inputTarget.f121id, this.mEditable.toString(), selectionStart, selectionEnd, composingStart, composingEnd);
            }
            this.mLastKnownFrameworkTextEditingState = new TextInputChannel.TextEditState(this.mEditable.toString(), selectionStart, selectionEnd, composingStart, composingEnd);
            return;
        }
        this.mEditable.clearBatchDeltas();
    }

    private boolean needsAutofill() {
        return this.autofillConfiguration != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewEntered() {
        if (Build.VERSION.SDK_INT < 26 || this.afm == null || !needsAutofill()) {
            return;
        }
        String str = this.configuration.autofill.uniqueIdentifier;
        int[] iArr = new int[2];
        this.mView.getLocationOnScreen(iArr);
        Rect rect = new Rect(this.lastClientRect);
        rect.offset(iArr[0], iArr[1]);
        this.afm.notifyViewEntered(this.mView, str.hashCode(), rect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewExited() {
        TextInputChannel.Configuration configuration;
        if (Build.VERSION.SDK_INT < 26 || this.afm == null || (configuration = this.configuration) == null || configuration.autofill == null || !needsAutofill()) {
            return;
        }
        this.afm.notifyViewExited(this.mView, this.configuration.autofill.uniqueIdentifier.hashCode());
    }

    private void notifyValueChanged(String str) {
        if (Build.VERSION.SDK_INT < 26 || this.afm == null || !needsAutofill()) {
            return;
        }
        this.afm.notifyValueChanged(this.mView, this.configuration.autofill.uniqueIdentifier.hashCode(), AutofillValue.forText(str));
    }

    private void updateAutofillConfigurationIfNeeded(TextInputChannel.Configuration configuration) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        if (configuration == null || configuration.autofill == null) {
            this.autofillConfiguration = null;
            return;
        }
        TextInputChannel.Configuration[] configurationArr = configuration.fields;
        SparseArray<TextInputChannel.Configuration> sparseArray = new SparseArray<>();
        this.autofillConfiguration = sparseArray;
        if (configurationArr == null) {
            sparseArray.put(configuration.autofill.uniqueIdentifier.hashCode(), configuration);
            return;
        }
        for (TextInputChannel.Configuration configuration2 : configurationArr) {
            TextInputChannel.Configuration.Autofill autofill = configuration2.autofill;
            if (autofill != null) {
                this.autofillConfiguration.put(autofill.uniqueIdentifier.hashCode(), configuration2);
                this.afm.notifyValueChanged(this.mView, autofill.uniqueIdentifier.hashCode(), AutofillValue.forText(autofill.editState.text));
            }
        }
    }

    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        Rect rect;
        if (Build.VERSION.SDK_INT < 26 || !needsAutofill()) {
            return;
        }
        String str = this.configuration.autofill.uniqueIdentifier;
        AutofillId autofillId = viewStructure.getAutofillId();
        for (int i2 = 0; i2 < this.autofillConfiguration.size(); i2++) {
            int keyAt = this.autofillConfiguration.keyAt(i2);
            TextInputChannel.Configuration.Autofill autofill = this.autofillConfiguration.valueAt(i2).autofill;
            if (autofill != null) {
                viewStructure.addChildCount(1);
                ViewStructure newChild = viewStructure.newChild(i2);
                newChild.setAutofillId(autofillId, keyAt);
                if (autofill.hints.length > 0) {
                    newChild.setAutofillHints(autofill.hints);
                }
                newChild.setAutofillType(1);
                newChild.setVisibility(0);
                if (autofill.hintText != null) {
                    newChild.setHint(autofill.hintText);
                }
                if (str.hashCode() == keyAt && (rect = this.lastClientRect) != null) {
                    newChild.setDimens(rect.left, this.lastClientRect.top, 0, 0, this.lastClientRect.width(), this.lastClientRect.height());
                    newChild.setAutofillValue(AutofillValue.forText(this.mEditable));
                } else {
                    newChild.setDimens(0, 0, 0, 0, 1, 1);
                    newChild.setAutofillValue(AutofillValue.forText(autofill.editState.text));
                }
            }
        }
    }

    public void autofill(SparseArray<AutofillValue> sparseArray) {
        TextInputChannel.Configuration configuration;
        if (Build.VERSION.SDK_INT < 26 || (configuration = this.configuration) == null || this.autofillConfiguration == null || configuration.autofill == null) {
            return;
        }
        TextInputChannel.Configuration.Autofill autofill = this.configuration.autofill;
        HashMap<String, TextInputChannel.TextEditState> hashMap = new HashMap<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            TextInputChannel.Configuration configuration2 = this.autofillConfiguration.get(sparseArray.keyAt(i));
            if (configuration2 != null && configuration2.autofill != null) {
                TextInputChannel.Configuration.Autofill autofill2 = configuration2.autofill;
                String charSequence = sparseArray.valueAt(i).getTextValue().toString();
                TextInputChannel.TextEditState textEditState = new TextInputChannel.TextEditState(charSequence, charSequence.length(), charSequence.length(), -1, -1);
                if (autofill2.uniqueIdentifier.equals(autofill.uniqueIdentifier)) {
                    this.mEditable.setEditingState(textEditState);
                } else {
                    hashMap.put(autofill2.uniqueIdentifier, textEditState);
                }
            }
        }
        this.textInputChannel.updateEditingStateWithTag(this.inputTarget.f121id, hashMap);
    }
}
