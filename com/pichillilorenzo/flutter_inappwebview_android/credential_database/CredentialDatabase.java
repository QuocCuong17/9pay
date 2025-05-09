package com.pichillilorenzo.flutter_inappwebview_android.credential_database;

import android.content.Context;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CredentialDatabase {
    public static final String DATABASE_NAME = "CredentialDatabase.db";
    public static final int DATABASE_VERSION = 2;
    static final String LOG_TAG = "CredentialDatabase";
    private static CredentialDatabase instance;
    public URLCredentialDao credentialDao;
    public CredentialDatabaseHelper db;
    public URLProtectionSpaceDao protectionSpaceDao;

    private CredentialDatabase() {
    }

    private CredentialDatabase(CredentialDatabaseHelper credentialDatabaseHelper, URLProtectionSpaceDao uRLProtectionSpaceDao, URLCredentialDao uRLCredentialDao) {
        this.db = credentialDatabaseHelper;
        this.protectionSpaceDao = uRLProtectionSpaceDao;
        this.credentialDao = uRLCredentialDao;
    }

    public static CredentialDatabase getInstance(Context context) {
        CredentialDatabase credentialDatabase = instance;
        if (credentialDatabase != null) {
            return credentialDatabase;
        }
        CredentialDatabaseHelper credentialDatabaseHelper = new CredentialDatabaseHelper(context);
        CredentialDatabase credentialDatabase2 = new CredentialDatabase(credentialDatabaseHelper, new URLProtectionSpaceDao(credentialDatabaseHelper), new URLCredentialDao(credentialDatabaseHelper));
        instance = credentialDatabase2;
        return credentialDatabase2;
    }

    public List<URLCredential> getHttpAuthCredentials(String str, String str2, String str3, Integer num) {
        ArrayList arrayList = new ArrayList();
        URLProtectionSpace find = this.protectionSpaceDao.find(str, str2, str3, num);
        return find != null ? this.credentialDao.getAllByProtectionSpaceId(find.getId()) : arrayList;
    }

    public void clearAllAuthCredentials() {
        CredentialDatabaseHelper credentialDatabaseHelper = this.db;
        credentialDatabaseHelper.clearAllTables(credentialDatabaseHelper.getWritableDatabase());
    }

    public void removeHttpAuthCredentials(String str, String str2, String str3, Integer num) {
        URLProtectionSpace find = this.protectionSpaceDao.find(str, str2, str3, num);
        if (find != null) {
            this.protectionSpaceDao.delete(find);
        }
    }

    public void removeHttpAuthCredential(String str, String str2, String str3, Integer num, String str4, String str5) {
        URLProtectionSpace find = this.protectionSpaceDao.find(str, str2, str3, num);
        if (find != null) {
            this.credentialDao.delete(this.credentialDao.find(str4, str5, find.getId()));
        }
    }

    public void setHttpAuthCredential(String str, String str2, String str3, Integer num, String str4, String str5) {
        Long id2;
        URLProtectionSpace find = this.protectionSpaceDao.find(str, str2, str3, num);
        if (find == null) {
            id2 = Long.valueOf(this.protectionSpaceDao.insert(new URLProtectionSpace(null, str, str2, str3, num.intValue())));
        } else {
            id2 = find.getId();
        }
        URLCredential find2 = this.credentialDao.find(str4, str5, id2);
        if (find2 != null) {
            boolean z = false;
            boolean z2 = true;
            if (!find2.getUsername().equals(str4)) {
                find2.setUsername(str4);
                z = true;
            }
            if (find2.getPassword().equals(str5)) {
                z2 = z;
            } else {
                find2.setPassword(str5);
            }
            if (z2) {
                this.credentialDao.update(find2);
                return;
            }
            return;
        }
        URLCredential uRLCredential = new URLCredential(null, str4, str5, id2);
        uRLCredential.setId(Long.valueOf(this.credentialDao.insert(uRLCredential)));
    }
}
