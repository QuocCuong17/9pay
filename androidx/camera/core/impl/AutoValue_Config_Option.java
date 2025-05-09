package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AutoValue_Config_Option<T> extends Config.Option<T> {

    /* renamed from: id, reason: collision with root package name */
    private final String f10id;
    private final Object token;
    private final Class<T> valueClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Config_Option(String str, Class<T> cls, Object obj) {
        Objects.requireNonNull(str, "Null id");
        this.f10id = str;
        Objects.requireNonNull(cls, "Null valueClass");
        this.valueClass = cls;
        this.token = obj;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public String getId() {
        return this.f10id;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public Class<T> getValueClass() {
        return this.valueClass;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public Object getToken() {
        return this.token;
    }

    public String toString() {
        return "Option{id=" + this.f10id + ", valueClass=" + this.valueClass + ", token=" + this.token + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Config.Option)) {
            return false;
        }
        Config.Option option = (Config.Option) obj;
        if (this.f10id.equals(option.getId()) && this.valueClass.equals(option.getValueClass())) {
            Object obj2 = this.token;
            if (obj2 == null) {
                if (option.getToken() == null) {
                    return true;
                }
            } else if (obj2.equals(option.getToken())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f10id.hashCode() ^ 1000003) * 1000003) ^ this.valueClass.hashCode()) * 1000003;
        Object obj = this.token;
        return hashCode ^ (obj == null ? 0 : obj.hashCode());
    }
}
