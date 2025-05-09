package vn.ai.faceauth.sdk.domain.model;

import kotlin.Metadata;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/StepLivenessDomain;", "", "textValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTextValue", "()Ljava/lang/String;", "STEP_LUMINOSITY", "STEP_N0_SMILE", "STEP_HEAD_FRONTAL", "STEP_FIRST_CHECK", "STEP_BLINK", "STEP_ZOOM_IN", "STEP_ZOOM_OUT", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public enum StepLivenessDomain {
    STEP_LUMINOSITY(tfwhgw.rnigpa(217)),
    STEP_N0_SMILE(tfwhgw.rnigpa(219)),
    STEP_HEAD_FRONTAL(tfwhgw.rnigpa(221)),
    STEP_FIRST_CHECK(tfwhgw.rnigpa(223)),
    STEP_BLINK(tfwhgw.rnigpa(225)),
    STEP_ZOOM_IN(tfwhgw.rnigpa(227)),
    STEP_ZOOM_OUT(tfwhgw.rnigpa(229));

    private final String textValue;

    StepLivenessDomain(String str) {
        this.textValue = str;
    }

    public final String getTextValue() {
        return this.textValue;
    }
}
