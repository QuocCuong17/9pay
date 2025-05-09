package com.it_nomads.fluttersecurestorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.Log;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.stats.CodePackage;
import com.it_nomads.fluttersecurestorage.ciphers.StorageCipher;
import com.it_nomads.fluttersecurestorage.ciphers.StorageCipherFactory;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class FlutterSecureStorage {
    private final Context applicationContext;
    private final Charset charset;
    protected Map<String, Object> options;
    private SharedPreferences preferences;
    private StorageCipher storageCipher;
    private StorageCipherFactory storageCipherFactory;
    private final String TAG = "SecureStorageAndroid";
    protected String ELEMENT_PREFERENCES_KEY_PREFIX = "VGhpcyBpcyB0aGUgcHJlZml4IGZvciBhIHNlY3VyZSBzdG9yYWdlCg";
    private String SHARED_PREFERENCES_NAME = "FlutterSecureStorage";
    private Boolean failedToUseEncryptedSharedPreferences = false;

    public FlutterSecureStorage(Context context) {
        this.applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 19) {
            this.charset = StandardCharsets.UTF_8;
        } else {
            this.charset = Charset.forName("UTF-8");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getResetOnError() {
        return this.options.containsKey("resetOnError") && this.options.get("resetOnError").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    private boolean getUseEncryptedSharedPreferences() {
        return !this.failedToUseEncryptedSharedPreferences.booleanValue() && this.options.containsKey("encryptedSharedPreferences") && this.options.get("encryptedSharedPreferences").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) && Build.VERSION.SDK_INT >= 23;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsKey(String str) {
        ensureInitialized();
        return this.preferences.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String read(String str) throws Exception {
        ensureInitialized();
        String string = this.preferences.getString(str, null);
        return getUseEncryptedSharedPreferences() ? string : decodeRawValue(string);
    }

    public Map<String, String> readAll() throws Exception {
        ensureInitialized();
        Map<String, ?> all = this.preferences.getAll();
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            if (entry.getKey().contains(this.ELEMENT_PREFERENCES_KEY_PREFIX)) {
                String replaceFirst = entry.getKey().replaceFirst(this.ELEMENT_PREFERENCES_KEY_PREFIX + '_', "");
                if (getUseEncryptedSharedPreferences()) {
                    hashMap.put(replaceFirst, (String) entry.getValue());
                } else {
                    hashMap.put(replaceFirst, decodeRawValue((String) entry.getValue()));
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void write(String str, String str2) throws Exception {
        ensureInitialized();
        SharedPreferences.Editor edit = this.preferences.edit();
        if (getUseEncryptedSharedPreferences()) {
            edit.putString(str, str2);
        } else {
            edit.putString(str, Base64.encodeToString(this.storageCipher.encrypt(str2.getBytes(this.charset)), 0));
        }
        edit.apply();
    }

    public void delete(String str) {
        ensureInitialized();
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.remove(str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteAll() {
        ensureInitialized();
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.clear();
        if (!getUseEncryptedSharedPreferences()) {
            this.storageCipherFactory.storeCurrentAlgorithms(edit);
        }
        edit.apply();
    }

    private void ensureInitialized() {
        if (this.options.containsKey("sharedPreferencesName") && !((String) this.options.get("sharedPreferencesName")).isEmpty()) {
            this.SHARED_PREFERENCES_NAME = (String) this.options.get("sharedPreferencesName");
        }
        if (this.options.containsKey("preferencesKeyPrefix") && !((String) this.options.get("preferencesKeyPrefix")).isEmpty()) {
            this.ELEMENT_PREFERENCES_KEY_PREFIX = (String) this.options.get("preferencesKeyPrefix");
        }
        SharedPreferences sharedPreferences = this.applicationContext.getSharedPreferences(this.SHARED_PREFERENCES_NAME, 0);
        if (this.storageCipher == null) {
            try {
                initStorageCipher(sharedPreferences);
            } catch (Exception e) {
                Log.e("SecureStorageAndroid", "StorageCipher initialization failed", e);
            }
        }
        if (getUseEncryptedSharedPreferences() && Build.VERSION.SDK_INT >= 23) {
            try {
                SharedPreferences initializeEncryptedSharedPreferencesManager = initializeEncryptedSharedPreferencesManager(this.applicationContext);
                this.preferences = initializeEncryptedSharedPreferencesManager;
                checkAndMigrateToEncrypted(sharedPreferences, initializeEncryptedSharedPreferencesManager);
                return;
            } catch (Exception e2) {
                Log.e("SecureStorageAndroid", "EncryptedSharedPreferences initialization failed", e2);
                this.preferences = sharedPreferences;
                this.failedToUseEncryptedSharedPreferences = true;
                return;
            }
        }
        this.preferences = sharedPreferences;
    }

    private void initStorageCipher(SharedPreferences sharedPreferences) throws Exception {
        this.storageCipherFactory = new StorageCipherFactory(sharedPreferences, this.options);
        if (getUseEncryptedSharedPreferences()) {
            this.storageCipher = this.storageCipherFactory.getSavedStorageCipher(this.applicationContext);
        } else if (this.storageCipherFactory.requiresReEncryption()) {
            reEncryptPreferences(this.storageCipherFactory, sharedPreferences);
        } else {
            this.storageCipher = this.storageCipherFactory.getCurrentStorageCipher(this.applicationContext);
        }
    }

    private void reEncryptPreferences(StorageCipherFactory storageCipherFactory, SharedPreferences sharedPreferences) throws Exception {
        try {
            this.storageCipher = storageCipherFactory.getSavedStorageCipher(this.applicationContext);
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                Object value = entry.getValue();
                String key = entry.getKey();
                if ((value instanceof String) && key.contains(this.ELEMENT_PREFERENCES_KEY_PREFIX)) {
                    hashMap.put(key, decodeRawValue((String) value));
                }
            }
            this.storageCipher = storageCipherFactory.getCurrentStorageCipher(this.applicationContext);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            for (Map.Entry entry2 : hashMap.entrySet()) {
                edit.putString((String) entry2.getKey(), Base64.encodeToString(this.storageCipher.encrypt(((String) entry2.getValue()).getBytes(this.charset)), 0));
            }
            storageCipherFactory.storeCurrentAlgorithms(edit);
            edit.apply();
        } catch (Exception e) {
            Log.e("SecureStorageAndroid", "re-encryption failed", e);
            this.storageCipher = storageCipherFactory.getSavedStorageCipher(this.applicationContext);
        }
    }

    private void checkAndMigrateToEncrypted(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2) {
        try {
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                Object value = entry.getValue();
                String key = entry.getKey();
                if ((value instanceof String) && key.contains(this.ELEMENT_PREFERENCES_KEY_PREFIX)) {
                    sharedPreferences2.edit().putString(key, decodeRawValue((String) value)).apply();
                    sharedPreferences.edit().remove(key).apply();
                }
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            this.storageCipherFactory.removeCurrentAlgorithms(edit);
            edit.apply();
        } catch (Exception e) {
            Log.e("SecureStorageAndroid", "Data migration failed", e);
        }
    }

    private SharedPreferences initializeEncryptedSharedPreferencesManager(Context context) throws GeneralSecurityException, IOException {
        return EncryptedSharedPreferences.create(context, this.SHARED_PREFERENCES_NAME, new MasterKey.Builder(context).setKeyGenParameterSpec(new KeyGenParameterSpec.Builder(MasterKey.DEFAULT_MASTER_KEY_ALIAS, 3).setEncryptionPaddings("NoPadding").setBlockModes(CodePackage.GCM).setKeySize(256).build()).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
    }

    private String decodeRawValue(String str) throws Exception {
        if (str == null) {
            return null;
        }
        return new String(this.storageCipher.decrypt(Base64.decode(str, 0)), this.charset);
    }
}
