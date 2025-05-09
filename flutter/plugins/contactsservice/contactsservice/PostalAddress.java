package flutter.plugins.contactsservice.contactsservice;

import android.content.res.Resources;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.facebook.appevents.UserDataStore;
import io.sentry.protocol.Geo;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PostalAddress {
    public String city;
    public String country;
    public String label;
    public String postcode;
    public String region;
    public String street;
    int type;

    public PostalAddress(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.label = str;
        this.street = str2;
        this.city = str3;
        this.postcode = str4;
        this.region = str5;
        this.country = str6;
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap<String, String> toMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("label", this.label);
        hashMap.put("street", this.street);
        hashMap.put(Geo.JsonKeys.CITY, this.city);
        hashMap.put("postcode", this.postcode);
        hashMap.put("region", this.region);
        hashMap.put(UserDataStore.COUNTRY, this.country);
        hashMap.put("type", String.valueOf(this.type));
        return hashMap;
    }

    public static PostalAddress fromMap(HashMap<String, String> hashMap) {
        String str = hashMap.get("label");
        String str2 = hashMap.get("street");
        String str3 = hashMap.get(Geo.JsonKeys.CITY);
        String str4 = hashMap.get("postcode");
        String str5 = hashMap.get("region");
        String str6 = hashMap.get(UserDataStore.COUNTRY);
        String str7 = hashMap.get("type");
        return new PostalAddress(str, str2, str3, str4, str5, str6, str7 != null ? Integer.parseInt(str7) : -1);
    }

    public static String getLabel(Resources resources, int i, Cursor cursor, boolean z) {
        if (z) {
            return ContactsContract.CommonDataKinds.StructuredPostal.getTypeLabel(resources, i, "").toString().toLowerCase();
        }
        int i2 = cursor.getInt(cursor.getColumnIndex("data2"));
        if (i2 != 0) {
            return i2 != 1 ? i2 != 2 ? "other" : "work" : "home";
        }
        String string = cursor.getString(cursor.getColumnIndex("data3"));
        return string != null ? string : "";
    }
}
