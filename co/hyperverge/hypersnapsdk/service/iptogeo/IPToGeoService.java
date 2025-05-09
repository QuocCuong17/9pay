package co.hyperverge.hypersnapsdk.service.iptogeo;

import co.hyperverge.hypersnapsdk.objects.IPAddress;

/* loaded from: classes2.dex */
public interface IPToGeoService {

    /* loaded from: classes2.dex */
    public interface IPToGeoCallback {
        void onError();

        void onSuccess(IPAddress iPAddress);
    }

    void performIp2GeoAddress(IPToGeoCallback iPToGeoCallback);
}
