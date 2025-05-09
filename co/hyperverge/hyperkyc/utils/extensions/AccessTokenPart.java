package co.hyperverge.hyperkyc.utils.extensions;

import com.facebook.AuthenticationTokenClaims;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NetworkExts.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0005\b\t\n\u000b\fB\u000f\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "T", "", "key", "", "(Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "AppId", "Expiry", "Hash", "Iat", "Jti", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$AppId;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Expiry;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Hash;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Iat;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Jti;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AccessTokenPart<T> {
    private final String key;

    public /* synthetic */ AccessTokenPart(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    /* compiled from: NetworkExts.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$AppId;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class AppId extends AccessTokenPart<String> {
        public static final AppId INSTANCE = new AppId();

        private AppId() {
            super("appId", null);
        }
    }

    private AccessTokenPart(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }

    /* compiled from: NetworkExts.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Hash;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Hash extends AccessTokenPart<String> {
        public static final Hash INSTANCE = new Hash();

        private Hash() {
            super("hash", null);
        }
    }

    /* compiled from: NetworkExts.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Iat;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Iat extends AccessTokenPart<Integer> {
        public static final Iat INSTANCE = new Iat();

        private Iat() {
            super(AuthenticationTokenClaims.JSON_KEY_IAT, null);
        }
    }

    /* compiled from: NetworkExts.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Expiry;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Expiry extends AccessTokenPart<Integer> {
        public static final Expiry INSTANCE = new Expiry();

        private Expiry() {
            super(AuthenticationTokenClaims.JSON_KEY_EXP, null);
        }
    }

    /* compiled from: NetworkExts.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart$Jti;", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Jti extends AccessTokenPart<String> {
        public static final Jti INSTANCE = new Jti();

        private Jti() {
            super(AuthenticationTokenClaims.JSON_KEY_JIT, null);
        }
    }
}
