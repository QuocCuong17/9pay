package flutter.plugins.contactsservice.contactsservice;

import android.content.res.Resources;
import android.database.Cursor;
import android.provider.ContactsContract;
import co.hyperverge.hyperkyc.data.models.Properties;
import io.sentry.protocol.SentryThread;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class Item {
    public String label;
    int type;
    public String value;

    public Item(String str, String str2, int i) {
        this.label = str;
        this.value = str2;
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap<String, String> toMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("label", this.label);
        hashMap.put("value", this.value);
        hashMap.put("type", String.valueOf(this.type));
        return hashMap;
    }

    public static Item fromMap(HashMap<String, String> hashMap) {
        String str = hashMap.get("label");
        String str2 = hashMap.get("value");
        String str3 = hashMap.get("type");
        return new Item(str, str2, str3 != null ? Integer.parseInt(str3) : -1);
    }

    public static String getPhoneLabel(Resources resources, int i, Cursor cursor, boolean z) {
        if (z) {
            return ContactsContract.CommonDataKinds.Phone.getTypeLabel(resources, i, "").toString().toLowerCase();
        }
        if (i == 10) {
            return "company";
        }
        if (i == 12) {
            return SentryThread.JsonKeys.MAIN;
        }
        switch (i) {
            case 0:
                return cursor.getString(cursor.getColumnIndex("data3")) != null ? cursor.getString(cursor.getColumnIndex("data3")).toLowerCase() : "";
            case 1:
                return "home";
            case 2:
                return Properties.SDK_VERSION_MOBILE_KEY;
            case 3:
                return "work";
            case 4:
                return "fax work";
            case 5:
                return "fax home";
            case 6:
                return "pager";
            default:
                return "other";
        }
    }

    public static String getEmailLabel(Resources resources, int i, Cursor cursor, boolean z) {
        if (z) {
            return ContactsContract.CommonDataKinds.Email.getTypeLabel(resources, i, "").toString().toLowerCase();
        }
        return i != 0 ? i != 1 ? i != 2 ? i != 4 ? "other" : Properties.SDK_VERSION_MOBILE_KEY : "work" : "home" : cursor.getString(cursor.getColumnIndex("data3")) != null ? cursor.getString(cursor.getColumnIndex("data3")).toLowerCase() : "";
    }
}
