package flutter.plugins.contactsservice.contactsservice;

import com.google.firebase.dynamiclinks.DynamicLink;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class Contact implements Comparable<Contact> {
    String androidAccountName;
    String androidAccountType;
    byte[] avatar;
    String birthday;
    String company;
    String displayName;
    ArrayList<Item> emails;
    String familyName;
    String givenName;
    String identifier;
    String jobTitle;
    String middleName;
    String note;
    ArrayList<Item> phones;
    ArrayList<PostalAddress> postalAddresses;
    String prefix;
    String suffix;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Contact(String str) {
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.postalAddresses = new ArrayList<>();
        this.avatar = new byte[0];
        this.identifier = str;
    }

    private Contact() {
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.postalAddresses = new ArrayList<>();
        this.avatar = new byte[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("identifier", this.identifier);
        hashMap.put("displayName", this.displayName);
        hashMap.put("givenName", this.givenName);
        hashMap.put("middleName", this.middleName);
        hashMap.put("familyName", this.familyName);
        hashMap.put("prefix", this.prefix);
        hashMap.put(DynamicLink.Builder.KEY_SUFFIX, this.suffix);
        hashMap.put("company", this.company);
        hashMap.put("jobTitle", this.jobTitle);
        hashMap.put("avatar", this.avatar);
        hashMap.put("note", this.note);
        hashMap.put("birthday", this.birthday);
        hashMap.put("androidAccountType", this.androidAccountType);
        hashMap.put("androidAccountName", this.androidAccountName);
        ArrayList arrayList = new ArrayList();
        Iterator<Item> it = this.emails.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toMap());
        }
        hashMap.put("emails", arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator<Item> it2 = this.phones.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().toMap());
        }
        hashMap.put("phones", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        Iterator<PostalAddress> it3 = this.postalAddresses.iterator();
        while (it3.hasNext()) {
            arrayList3.add(it3.next().toMap());
        }
        hashMap.put("postalAddresses", arrayList3);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Contact fromMap(HashMap hashMap) {
        Contact contact = new Contact();
        contact.identifier = (String) hashMap.get("identifier");
        contact.givenName = (String) hashMap.get("givenName");
        contact.middleName = (String) hashMap.get("middleName");
        contact.familyName = (String) hashMap.get("familyName");
        contact.prefix = (String) hashMap.get("prefix");
        contact.suffix = (String) hashMap.get(DynamicLink.Builder.KEY_SUFFIX);
        contact.company = (String) hashMap.get("company");
        contact.jobTitle = (String) hashMap.get("jobTitle");
        contact.avatar = (byte[]) hashMap.get("avatar");
        contact.note = (String) hashMap.get("note");
        contact.birthday = (String) hashMap.get("birthday");
        contact.androidAccountType = (String) hashMap.get("androidAccountType");
        contact.androidAccountName = (String) hashMap.get("androidAccountName");
        ArrayList arrayList = (ArrayList) hashMap.get("emails");
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                contact.emails.add(Item.fromMap((HashMap) it.next()));
            }
        }
        ArrayList arrayList2 = (ArrayList) hashMap.get("phones");
        if (arrayList2 != null) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                contact.phones.add(Item.fromMap((HashMap) it2.next()));
            }
        }
        ArrayList arrayList3 = (ArrayList) hashMap.get("postalAddresses");
        if (arrayList3 != null) {
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                contact.postalAddresses.add(PostalAddress.fromMap((HashMap) it3.next()));
            }
        }
        return contact;
    }

    @Override // java.lang.Comparable
    public int compareTo(Contact contact) {
        String str;
        String str2 = this.givenName;
        String str3 = "";
        String lowerCase = str2 == null ? "" : str2.toLowerCase();
        if (contact != null && (str = contact.givenName) != null) {
            str3 = str.toLowerCase();
        }
        return lowerCase.compareTo(str3);
    }
}
