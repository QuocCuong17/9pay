package com.dexterous.flutterlocalnotifications.models;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes2.dex */
public class NotificationChannelGroupDetails implements Serializable {
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";
    private static final String NAME = "name";
    public String description;

    /* renamed from: id, reason: collision with root package name */
    public String f62id;
    public String name;

    public static NotificationChannelGroupDetails from(Map<String, Object> map) {
        NotificationChannelGroupDetails notificationChannelGroupDetails = new NotificationChannelGroupDetails();
        notificationChannelGroupDetails.f62id = (String) map.get("id");
        notificationChannelGroupDetails.name = (String) map.get("name");
        notificationChannelGroupDetails.description = (String) map.get("description");
        return notificationChannelGroupDetails;
    }
}
