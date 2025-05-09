package vn.ninepay.ewallet;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class ContactModel {

    @SerializedName("icon")
    private String icon;

    @SerializedName("name")
    private String name;

    @SerializedName(WorkflowModule.Properties.Section.Component.Keyboard.PHONE)
    private String phone;

    public ContactModel(String str, String str2, String str3) {
        this.icon = str;
        this.name = str2;
        this.phone = str3;
    }

    public ContactModel() {
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }
}
