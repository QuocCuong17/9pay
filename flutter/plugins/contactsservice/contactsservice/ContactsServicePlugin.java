package flutter.plugins.contactsservice.contactsservice;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.internal.AnalyticsEvents;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ContactsServicePlugin implements MethodChannel.MethodCallHandler, FlutterPlugin, ActivityAware {
    private static final int FORM_COULD_NOT_BE_OPEN = 2;
    private static final int FORM_OPERATION_CANCELED = 1;
    private static final String LOG_TAG = "flutter_contacts";
    private static final String[] PROJECTION = {"contact_id", "display_name", "mimetype", "account_type", "account_name", "data1", "data2", "data5", "data3", "data4", "data6", "data1", "data1", "data2", "data3", "data1", "data1", "data2", "data3", "data1", "data4", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10"};
    private ContentResolver contentResolver;
    private BaseContactsServiceDelegate delegate;
    private final ExecutorService executor = new ThreadPoolExecutor(0, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(1000));
    private MethodChannel methodChannel;
    private Resources resources;

    private void initDelegateWithRegister(PluginRegistry.Registrar registrar) {
        this.delegate = new ContactServiceDelegateOld(registrar);
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        ContactsServicePlugin contactsServicePlugin = new ContactsServicePlugin();
        contactsServicePlugin.initInstance(registrar.messenger(), registrar.context());
        contactsServicePlugin.initDelegateWithRegister(registrar);
    }

    private void initInstance(BinaryMessenger binaryMessenger, Context context) {
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "github.com/clovisnicolas/flutter_contacts");
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        this.contentResolver = context.getContentResolver();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.resources = flutterPluginBinding.getApplicationContext().getResources();
        initInstance(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
        this.delegate = new ContactServiceDelegate(flutterPluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
        this.contentResolver = null;
        this.delegate = null;
        this.resources = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x006c, code lost:
    
        if (r1.equals("addContact") == false) goto L4;
     */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 2;
        switch (str.hashCode()) {
            case -1830951058:
                if (str.equals("openDeviceContactPicker")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1794825126:
                if (str.equals("openContactForm")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1435206593:
                break;
            case -544424169:
                if (str.equals("updateContact")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -490500804:
                if (str.equals("getContactsForEmail")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -480477426:
                if (str.equals("getContactsForPhone")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 290055247:
                if (str.equals("getAvatar")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 331036779:
                if (str.equals("openExistingContact")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 746754037:
                if (str.equals("deleteContact")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1510448585:
                if (str.equals("getContacts")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                openDeviceContactPicker(result, ((Boolean) methodCall.argument("androidLocalizedLabels")).booleanValue());
                return;
            case 1:
                boolean booleanValue = ((Boolean) methodCall.argument("androidLocalizedLabels")).booleanValue();
                BaseContactsServiceDelegate baseContactsServiceDelegate = this.delegate;
                if (baseContactsServiceDelegate != null) {
                    baseContactsServiceDelegate.setResult(result);
                    this.delegate.setLocalizedLabels(booleanValue);
                    this.delegate.openContactForm();
                    return;
                }
                result.success(2);
                return;
            case 2:
                if (addContact(Contact.fromMap((HashMap) methodCall.arguments))) {
                    result.success(null);
                    return;
                } else {
                    result.error(null, "Failed to add the contact", null);
                    return;
                }
            case 3:
                if (updateContact(Contact.fromMap((HashMap) methodCall.arguments))) {
                    result.success(null);
                    return;
                } else {
                    result.error(null, "Failed to update the contact, make sure it has a valid identifier", null);
                    return;
                }
            case 4:
                getContactsForEmail(methodCall.method, (String) methodCall.argument("email"), ((Boolean) methodCall.argument("withThumbnails")).booleanValue(), ((Boolean) methodCall.argument("photoHighResolution")).booleanValue(), ((Boolean) methodCall.argument("orderByGivenName")).booleanValue(), ((Boolean) methodCall.argument("androidLocalizedLabels")).booleanValue(), result);
                return;
            case 5:
                getContactsForPhone(methodCall.method, (String) methodCall.argument(WorkflowModule.Properties.Section.Component.Keyboard.PHONE), ((Boolean) methodCall.argument("withThumbnails")).booleanValue(), ((Boolean) methodCall.argument("photoHighResolution")).booleanValue(), ((Boolean) methodCall.argument("orderByGivenName")).booleanValue(), ((Boolean) methodCall.argument("androidLocalizedLabels")).booleanValue(), result);
                return;
            case 6:
                getAvatar(Contact.fromMap((HashMap) methodCall.argument("contact")), ((Boolean) methodCall.argument("photoHighResolution")).booleanValue(), result);
                return;
            case 7:
                Contact fromMap = Contact.fromMap((HashMap) methodCall.argument("contact"));
                boolean booleanValue2 = ((Boolean) methodCall.argument("androidLocalizedLabels")).booleanValue();
                BaseContactsServiceDelegate baseContactsServiceDelegate2 = this.delegate;
                if (baseContactsServiceDelegate2 != null) {
                    baseContactsServiceDelegate2.setResult(result);
                    this.delegate.setLocalizedLabels(booleanValue2);
                    this.delegate.openExistingContact(fromMap);
                    return;
                }
                result.success(2);
                return;
            case '\b':
                if (deleteContact(Contact.fromMap((HashMap) methodCall.arguments))) {
                    result.success(null);
                    return;
                } else {
                    result.error(null, "Failed to delete the contact, make sure it has a valid identifier", null);
                    return;
                }
            case '\t':
                getContacts(methodCall.method, (String) methodCall.argument("query"), ((Boolean) methodCall.argument("withThumbnails")).booleanValue(), ((Boolean) methodCall.argument("photoHighResolution")).booleanValue(), ((Boolean) methodCall.argument("orderByGivenName")).booleanValue(), ((Boolean) methodCall.argument("androidLocalizedLabels")).booleanValue(), result);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getContacts(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, MethodChannel.Result result) {
        new GetContactsTask(str, result, z, z2, z3, z4).executeOnExecutor(this.executor, str2, false);
    }

    private void getContactsForPhone(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, MethodChannel.Result result) {
        new GetContactsTask(str, result, z, z2, z3, z4).executeOnExecutor(this.executor, str2, true);
    }

    private void getContactsForEmail(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, MethodChannel.Result result) {
        new GetContactsTask(str, result, z, z2, z3, z4).executeOnExecutor(this.executor, str2, true);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        BaseContactsServiceDelegate baseContactsServiceDelegate = this.delegate;
        if (baseContactsServiceDelegate instanceof ContactServiceDelegate) {
            ((ContactServiceDelegate) baseContactsServiceDelegate).bindToActivity(activityPluginBinding);
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        BaseContactsServiceDelegate baseContactsServiceDelegate = this.delegate;
        if (baseContactsServiceDelegate instanceof ContactServiceDelegate) {
            ((ContactServiceDelegate) baseContactsServiceDelegate).unbindActivity();
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        BaseContactsServiceDelegate baseContactsServiceDelegate = this.delegate;
        if (baseContactsServiceDelegate instanceof ContactServiceDelegate) {
            ((ContactServiceDelegate) baseContactsServiceDelegate).bindToActivity(activityPluginBinding);
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        BaseContactsServiceDelegate baseContactsServiceDelegate = this.delegate;
        if (baseContactsServiceDelegate instanceof ContactServiceDelegate) {
            ((ContactServiceDelegate) baseContactsServiceDelegate).unbindActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class BaseContactsServiceDelegate implements PluginRegistry.ActivityResultListener {
        private static final int REQUEST_OPEN_CONTACT_FORM = 52941;
        private static final int REQUEST_OPEN_CONTACT_PICKER = 52943;
        private static final int REQUEST_OPEN_EXISTING_CONTACT = 52942;
        private boolean localizedLabels;
        private MethodChannel.Result result;

        void startIntent(Intent intent, int i) {
        }

        private BaseContactsServiceDelegate() {
        }

        void setResult(MethodChannel.Result result) {
            this.result = result;
        }

        void setLocalizedLabels(boolean z) {
            this.localizedLabels = z;
        }

        void finishWithResult(Object obj) {
            MethodChannel.Result result = this.result;
            if (result != null) {
                result.success(obj);
                this.result = null;
            }
        }

        @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
        public boolean onActivityResult(int i, int i2, Intent intent) {
            if (i == REQUEST_OPEN_EXISTING_CONTACT || i == REQUEST_OPEN_CONTACT_FORM) {
                try {
                    finishWithResult(getContactByIdentifier(intent.getData().getLastPathSegment()));
                } catch (NullPointerException unused) {
                    finishWithResult(1);
                }
                return true;
            }
            if (i != REQUEST_OPEN_CONTACT_PICKER) {
                finishWithResult(2);
                return false;
            }
            if (i2 == 0) {
                finishWithResult(1);
                return true;
            }
            Uri data = intent.getData();
            Cursor query = ContactsServicePlugin.this.contentResolver.query(data, null, null, null, null);
            if (query.moveToFirst()) {
                ContactsServicePlugin.this.getContacts("openDeviceContactPicker", data.getLastPathSegment(), false, false, false, this.localizedLabels, this.result);
            } else {
                Log.e(ContactsServicePlugin.LOG_TAG, "onActivityResult - cursor.moveToFirst() returns false");
                finishWithResult(1);
            }
            query.close();
            return true;
        }

        void openExistingContact(Contact contact) {
            String str = contact.identifier;
            try {
                if (getContactByIdentifier(str) != null) {
                    Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, str);
                    Intent intent = new Intent("android.intent.action.EDIT");
                    intent.setDataAndType(withAppendedPath, "vnd.android.cursor.item/contact");
                    intent.putExtra("finishActivityOnSaveCompleted", true);
                    startIntent(intent, REQUEST_OPEN_EXISTING_CONTACT);
                } else {
                    finishWithResult(2);
                }
            } catch (Exception unused) {
                finishWithResult(2);
            }
        }

        void openContactForm() {
            try {
                Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
                intent.putExtra("finishActivityOnSaveCompleted", true);
                startIntent(intent, REQUEST_OPEN_CONTACT_FORM);
            } catch (Exception unused) {
            }
        }

        void openContactPicker() {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("vnd.android.cursor.dir/contact");
            startIntent(intent, REQUEST_OPEN_CONTACT_PICKER);
        }

        HashMap getContactByIdentifier(String str) {
            Cursor query = ContactsServicePlugin.this.contentResolver.query(ContactsContract.Data.CONTENT_URI, ContactsServicePlugin.PROJECTION, "contact_id = ?", new String[]{str}, null);
            try {
                ArrayList contactsFrom = ContactsServicePlugin.this.getContactsFrom(query, this.localizedLabels);
                if (contactsFrom.size() > 0) {
                    return ((Contact) contactsFrom.iterator().next()).toMap();
                }
                return null;
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
    }

    private void openDeviceContactPicker(MethodChannel.Result result, boolean z) {
        BaseContactsServiceDelegate baseContactsServiceDelegate = this.delegate;
        if (baseContactsServiceDelegate != null) {
            baseContactsServiceDelegate.setResult(result);
            this.delegate.setLocalizedLabels(z);
            this.delegate.openContactPicker();
            return;
        }
        result.success(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ContactServiceDelegateOld extends BaseContactsServiceDelegate {
        private final PluginRegistry.Registrar registrar;

        ContactServiceDelegateOld(PluginRegistry.Registrar registrar) {
            super();
            this.registrar = registrar;
            registrar.addActivityResultListener(this);
        }

        @Override // flutter.plugins.contactsservice.contactsservice.ContactsServicePlugin.BaseContactsServiceDelegate
        void startIntent(Intent intent, int i) {
            if (this.registrar.activity() != null) {
                this.registrar.activity().startActivityForResult(intent, i);
            } else {
                this.registrar.context().startActivity(intent);
            }
        }
    }

    /* loaded from: classes5.dex */
    private class ContactServiceDelegate extends BaseContactsServiceDelegate {
        private ActivityPluginBinding activityPluginBinding;
        private final Context context;

        ContactServiceDelegate(Context context) {
            super();
            this.context = context;
        }

        void bindToActivity(ActivityPluginBinding activityPluginBinding) {
            this.activityPluginBinding = activityPluginBinding;
            activityPluginBinding.addActivityResultListener(this);
        }

        void unbindActivity() {
            this.activityPluginBinding.removeActivityResultListener(this);
            this.activityPluginBinding = null;
        }

        @Override // flutter.plugins.contactsservice.contactsservice.ContactsServicePlugin.BaseContactsServiceDelegate
        void startIntent(Intent intent, int i) {
            if (this.activityPluginBinding != null) {
                if (intent.resolveActivity(this.context.getPackageManager()) != null) {
                    this.activityPluginBinding.getActivity().startActivityForResult(intent, i);
                    return;
                } else {
                    finishWithResult(2);
                    return;
                }
            }
            this.context.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class GetContactsTask extends AsyncTask<Object, Void, ArrayList<HashMap>> {
        private String callMethod;
        private MethodChannel.Result getContactResult;
        private boolean localizedLabels;
        private boolean orderByGivenName;
        private boolean photoHighResolution;
        private boolean withThumbnails;

        public GetContactsTask(String str, MethodChannel.Result result, boolean z, boolean z2, boolean z3, boolean z4) {
            this.callMethod = str;
            this.getContactResult = result;
            this.withThumbnails = z;
            this.photoHighResolution = z2;
            this.orderByGivenName = z3;
            this.localizedLabels = z4;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ArrayList<HashMap> doInBackground(Object... objArr) {
            ArrayList contactsFrom;
            String str = this.callMethod;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1830951058:
                    if (str.equals("openDeviceContactPicker")) {
                        c = 0;
                        break;
                    }
                    break;
                case -490500804:
                    if (str.equals("getContactsForEmail")) {
                        c = 1;
                        break;
                    }
                    break;
                case -480477426:
                    if (str.equals("getContactsForPhone")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1510448585:
                    if (str.equals("getContacts")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    ContactsServicePlugin contactsServicePlugin = ContactsServicePlugin.this;
                    contactsFrom = contactsServicePlugin.getContactsFrom(contactsServicePlugin.getCursor(null, (String) objArr[0]), this.localizedLabels);
                    break;
                case 1:
                    ContactsServicePlugin contactsServicePlugin2 = ContactsServicePlugin.this;
                    contactsFrom = contactsServicePlugin2.getContactsFrom(contactsServicePlugin2.getCursorForEmail((String) objArr[0]), this.localizedLabels);
                    break;
                case 2:
                    ContactsServicePlugin contactsServicePlugin3 = ContactsServicePlugin.this;
                    contactsFrom = contactsServicePlugin3.getContactsFrom(contactsServicePlugin3.getCursorForPhone((String) objArr[0]), this.localizedLabels);
                    break;
                case 3:
                    ContactsServicePlugin contactsServicePlugin4 = ContactsServicePlugin.this;
                    contactsFrom = contactsServicePlugin4.getContactsFrom(contactsServicePlugin4.getCursor((String) objArr[0], null), this.localizedLabels);
                    break;
                default:
                    return null;
            }
            if (this.withThumbnails) {
                Iterator it = contactsFrom.iterator();
                while (it.hasNext()) {
                    Contact contact = (Contact) it.next();
                    byte[] loadContactPhotoHighRes = ContactsServicePlugin.loadContactPhotoHighRes(contact.identifier, this.photoHighResolution, ContactsServicePlugin.this.contentResolver);
                    if (loadContactPhotoHighRes != null) {
                        contact.avatar = loadContactPhotoHighRes;
                    } else {
                        contact.avatar = new byte[0];
                    }
                }
            }
            if (this.orderByGivenName) {
                Collections.sort(contactsFrom, new Comparator<Contact>() { // from class: flutter.plugins.contactsservice.contactsservice.ContactsServicePlugin.GetContactsTask.1
                    @Override // java.util.Comparator
                    public int compare(Contact contact2, Contact contact3) {
                        return contact2.compareTo(contact3);
                    }
                });
            }
            ArrayList<HashMap> arrayList = new ArrayList<>();
            Iterator it2 = contactsFrom.iterator();
            while (it2.hasNext()) {
                arrayList.add(((Contact) it2.next()).toMap());
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ArrayList<HashMap> arrayList) {
            if (arrayList == null) {
                this.getContactResult.notImplemented();
            } else {
                this.getContactResult.success(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cursor getCursor(String str, String str2) {
        String str3;
        ArrayList arrayList = new ArrayList(Arrays.asList("vnd.android.cursor.item/note", "vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/postal-address_v2", "vnd.android.cursor.item/contact_event", "account_type"));
        if (str != null) {
            arrayList = new ArrayList();
            arrayList.add(str + "%");
            str3 = "display_name LIKE ?";
        } else {
            str3 = "(mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR account_type=?)";
        }
        if (str2 != null) {
            arrayList.add(str2);
            str3 = str3 + " AND contact_id =?";
        }
        return this.contentResolver.query(ContactsContract.Data.CONTENT_URI, PROJECTION, str3, (String[]) arrayList.toArray(new String[arrayList.size()]), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cursor getCursorForPhone(String str) {
        if (str.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Cursor query = this.contentResolver.query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"_id"}, null, null, null);
        while (query != null && query.moveToNext()) {
            arrayList.add(query.getString(query.getColumnIndex("_id")));
        }
        if (query != null) {
            query.close();
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return this.contentResolver.query(ContactsContract.Data.CONTENT_URI, PROJECTION, "contact_id IN " + arrayList.toString().replace("[", "(").replace("]", ")"), null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cursor getCursorForEmail(String str) {
        if (str.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList("%" + str + "%"));
        return this.contentResolver.query(ContactsContract.Data.CONTENT_URI, PROJECTION, "data1 LIKE ?", (String[]) arrayList.toArray(new String[arrayList.size()]), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Contact> getContactsFrom(Cursor cursor, boolean z) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (cursor != null && cursor.moveToNext()) {
            String string = cursor.getString(cursor.getColumnIndex("contact_id"));
            if (!linkedHashMap.containsKey(string)) {
                linkedHashMap.put(string, new Contact(string));
            }
            Contact contact = (Contact) linkedHashMap.get(string);
            String string2 = cursor.getString(cursor.getColumnIndex("mimetype"));
            contact.displayName = cursor.getString(cursor.getColumnIndex("display_name"));
            contact.androidAccountType = cursor.getString(cursor.getColumnIndex("account_type"));
            contact.androidAccountName = cursor.getString(cursor.getColumnIndex("account_name"));
            if (string2.equals("vnd.android.cursor.item/name")) {
                contact.givenName = cursor.getString(cursor.getColumnIndex("data2"));
                contact.middleName = cursor.getString(cursor.getColumnIndex("data5"));
                contact.familyName = cursor.getString(cursor.getColumnIndex("data3"));
                contact.prefix = cursor.getString(cursor.getColumnIndex("data4"));
                contact.suffix = cursor.getString(cursor.getColumnIndex("data6"));
            } else if (string2.equals("vnd.android.cursor.item/note")) {
                contact.note = cursor.getString(cursor.getColumnIndex("data1"));
            } else if (string2.equals("vnd.android.cursor.item/phone_v2")) {
                String string3 = cursor.getString(cursor.getColumnIndex("data1"));
                if (!TextUtils.isEmpty(string3)) {
                    int i = cursor.getInt(cursor.getColumnIndex("data2"));
                    contact.phones.add(new Item(Item.getPhoneLabel(this.resources, i, cursor, z), string3, i));
                }
            } else if (string2.equals("vnd.android.cursor.item/email_v2")) {
                String string4 = cursor.getString(cursor.getColumnIndex("data1"));
                int i2 = cursor.getInt(cursor.getColumnIndex("data2"));
                if (!TextUtils.isEmpty(string4)) {
                    contact.emails.add(new Item(Item.getEmailLabel(this.resources, i2, cursor, z), string4, i2));
                }
            } else if (string2.equals("vnd.android.cursor.item/organization")) {
                contact.company = cursor.getString(cursor.getColumnIndex("data1"));
                contact.jobTitle = cursor.getString(cursor.getColumnIndex("data4"));
            } else if (string2.equals("vnd.android.cursor.item/postal-address_v2")) {
                int i3 = cursor.getInt(cursor.getColumnIndex("data2"));
                contact.postalAddresses.add(new PostalAddress(PostalAddress.getLabel(this.resources, i3, cursor, z), cursor.getString(cursor.getColumnIndex("data4")), cursor.getString(cursor.getColumnIndex("data7")), cursor.getString(cursor.getColumnIndex("data9")), cursor.getString(cursor.getColumnIndex("data8")), cursor.getString(cursor.getColumnIndex("data10")), i3));
            } else if (string2.equals("vnd.android.cursor.item/contact_event") && cursor.getInt(cursor.getColumnIndex("data2")) == 3) {
                contact.birthday = cursor.getString(cursor.getColumnIndex("data1"));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    private void setAvatarDataForContactIfAvailable(Contact contact) {
        Cursor query = this.contentResolver.query(Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Integer.parseInt(contact.identifier)), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO), new String[]{"data15"}, null, null, null);
        if (query != null && query.moveToFirst()) {
            contact.avatar = query.getBlob(0);
        }
        if (query != null) {
            query.close();
        }
    }

    private void getAvatar(Contact contact, boolean z, MethodChannel.Result result) {
        new GetAvatarsTask(contact, z, this.contentResolver, result).executeOnExecutor(this.executor, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class GetAvatarsTask extends AsyncTask<Void, Void, byte[]> {
        final Contact contact;
        final ContentResolver contentResolver;
        final boolean highRes;
        final MethodChannel.Result result;

        GetAvatarsTask(Contact contact, boolean z, ContentResolver contentResolver, MethodChannel.Result result) {
            this.contact = contact;
            this.highRes = z;
            this.contentResolver = contentResolver;
            this.result = result;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public byte[] doInBackground(Void... voidArr) {
            return ContactsServicePlugin.loadContactPhotoHighRes(this.contact.identifier, this.highRes, this.contentResolver);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(byte[] bArr) {
            this.result.success(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] loadContactPhotoHighRes(String str, boolean z, ContentResolver contentResolver) {
        try {
            InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(str)), z);
            if (openContactPhotoInputStream == null) {
                return null;
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(openContactPhotoInputStream);
            openContactPhotoInputStream.close();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeStream.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            return null;
        }
    }

    private boolean addContact(Contact contact) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", contact.givenName).withValue("data5", contact.middleName).withValue("data3", contact.familyName).withValue("data4", contact.prefix).withValue("data6", contact.suffix).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", contact.note).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data1", contact.company).withValue("data4", contact.jobTitle).build());
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("is_super_primary", 1).withValue("data15", contact.avatar).withValue("mimetype", "vnd.android.cursor.item/photo");
        arrayList.add(withValue.build());
        withValue.withYieldAllowed(true);
        Iterator<Item> it = contact.phones.iterator();
        while (it.hasNext()) {
            Item next = it.next();
            ContentProviderOperation.Builder withValue2 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", next.value);
            if (next.type == 0) {
                withValue2.withValue("data2", 0);
                withValue2.withValue("data3", next.label);
            } else {
                withValue2.withValue("data2", Integer.valueOf(next.type));
            }
            arrayList.add(withValue2.build());
        }
        Iterator<Item> it2 = contact.emails.iterator();
        while (it2.hasNext()) {
            Item next2 = it2.next();
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", next2.value).withValue("data2", Integer.valueOf(next2.type)).build());
        }
        Iterator<PostalAddress> it3 = contact.postalAddresses.iterator();
        while (it3.hasNext()) {
            PostalAddress next3 = it3.next();
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data2", Integer.valueOf(next3.type)).withValue("data3", next3.label).withValue("data4", next3.street).withValue("data7", next3.city).withValue("data8", next3.region).withValue("data9", next3.postcode).withValue("data10", next3.country).build());
        }
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/contact_event").withValue("data2", 3).withValue("data1", contact.birthday).build());
        try {
            this.contentResolver.applyBatch("com.android.contacts", arrayList);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean deleteContact(Contact contact) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(contact.identifier)}).build());
        try {
            this.contentResolver.applyBatch("com.android.contacts", arrayList);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean updateContact(Contact contact) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/organization"}).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/phone_v2"}).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/email_v2"}).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/note"}).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/postal-address_v2"}).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/photo"}).build());
        arrayList.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype=?", new String[]{String.valueOf(contact.identifier), "vnd.android.cursor.item/name"}).withValue("data2", contact.givenName).withValue("data5", contact.middleName).withValue("data3", contact.familyName).withValue("data4", contact.prefix).withValue("data6", contact.suffix).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("raw_contact_id", contact.identifier).withValue("data2", 1).withValue("data1", contact.company).withValue("data4", contact.jobTitle).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("mimetype", "vnd.android.cursor.item/note").withValue("raw_contact_id", contact.identifier).withValue("data1", contact.note).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", contact.identifier).withValue("is_super_primary", 1).withValue("data15", contact.avatar).withValue("mimetype", "vnd.android.cursor.item/photo").build());
        Iterator<Item> it = contact.phones.iterator();
        while (it.hasNext()) {
            Item next = it.next();
            ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("raw_contact_id", contact.identifier).withValue("data1", next.value);
            if (next.type == 0) {
                withValue.withValue("data2", 0);
                withValue.withValue("data3", next.label);
            } else {
                withValue.withValue("data2", Integer.valueOf(next.type));
            }
            arrayList.add(withValue.build());
        }
        Iterator<Item> it2 = contact.emails.iterator();
        while (it2.hasNext()) {
            Item next2 = it2.next();
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("raw_contact_id", contact.identifier).withValue("data1", next2.value).withValue("data2", Integer.valueOf(next2.type)).build());
        }
        Iterator<PostalAddress> it3 = contact.postalAddresses.iterator();
        while (it3.hasNext()) {
            PostalAddress next3 = it3.next();
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("raw_contact_id", contact.identifier).withValue("data2", Integer.valueOf(next3.type)).withValue("data4", next3.street).withValue("data7", next3.city).withValue("data8", next3.region).withValue("data9", next3.postcode).withValue("data10", next3.country).build());
        }
        try {
            this.contentResolver.applyBatch("com.android.contacts", arrayList);
            return true;
        } catch (Exception e) {
            Log.e("TAG", "Exception encountered while inserting contact: ");
            e.printStackTrace();
            return false;
        }
    }
}
