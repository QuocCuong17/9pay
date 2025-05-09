package com.fasterxml.jackson.databind.jsonFormatVisitors;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.annotation.JsonValue;

/* loaded from: classes3.dex */
public enum JsonValueFormat {
    COLOR("color"),
    DATE(WorkflowModule.Properties.Section.Component.Type.DATE),
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE(WorkflowModule.Properties.Section.Component.Keyboard.PHONE),
    REGEX(WorkflowModule.Properties.Section.Component.Validation.Type.REGEX),
    STYLE("style"),
    TIME("time"),
    URI(ShareConstants.MEDIA_URI),
    UTC_MILLISEC("utc-millisec"),
    UUID("uuid");

    private final String _desc;

    JsonValueFormat(String str) {
        this._desc = str;
    }

    @Override // java.lang.Enum
    @JsonValue
    public String toString() {
        return this._desc;
    }
}
