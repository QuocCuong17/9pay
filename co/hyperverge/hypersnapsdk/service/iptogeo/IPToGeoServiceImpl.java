package co.hyperverge.hypersnapsdk.service.iptogeo;

import android.util.Log;
import co.hyperverge.hypersnapsdk.data.DataRepository;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.listeners.APICompletionCallback;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.IPAddress;
import co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.gson.Gson;
import java.util.Date;

/* loaded from: classes2.dex */
public class IPToGeoServiceImpl implements IPToGeoService {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoServiceImpl";

    @Override // co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService
    public void performIp2GeoAddress(final IPToGeoService.IPToGeoCallback iPToGeoCallback) {
        HVLogUtils.d(TAG, "performIp2GeoAddress() called with: ipToGeoCallback = [" + iPToGeoCallback + "]");
        final IPAddress ipToGeoData = SPHelper.getIpToGeoData();
        if (doesIPAddressCacheExist(ipToGeoData) && Utils.timeDifferenceInMinutes(ipToGeoData.getCreatedAt()) <= 30) {
            iPToGeoCallback.onSuccess(ipToGeoData);
        } else {
            DataRepository.getInstance().ipAddressToGeo(new APICompletionCallback() { // from class: co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoServiceImpl$$ExternalSyntheticLambda0
                @Override // co.hyperverge.hypersnapsdk.listeners.APICompletionCallback
                public final void onResult(HVError hVError, HVResponse hVResponse) {
                    IPToGeoServiceImpl.this.m546x1efd920(iPToGeoCallback, ipToGeoData, hVError, hVResponse);
                }
            });
        }
    }

    /* renamed from: lambda$performIp2GeoAddress$0$co-hyperverge-hypersnapsdk-service-iptogeo-IPToGeoServiceImpl, reason: not valid java name */
    public /* synthetic */ void m546x1efd920(IPToGeoService.IPToGeoCallback iPToGeoCallback, IPAddress iPAddress, HVError hVError, HVResponse hVResponse) {
        Log.d(TAG, "onResult() called with: error = [" + hVError + "], hvResponse = [" + hVResponse + "]");
        if (hVResponse != null && hVResponse.getApiResult() != null) {
            IPAddress iPAddress2 = (IPAddress) new Gson().fromJson(String.valueOf(hVResponse.getApiResult()), IPAddress.class);
            iPAddress2.setCreatedAt(new Date());
            SPHelper.saveIpToGeoData(iPAddress2);
            iPToGeoCallback.onSuccess(iPAddress2);
            return;
        }
        if (hVError != null) {
            if (doesIPAddressCacheExist(iPAddress)) {
                iPToGeoCallback.onSuccess(iPAddress);
            } else {
                iPToGeoCallback.onError();
            }
        }
    }

    private boolean doesIPAddressCacheExist(IPAddress iPAddress) {
        HVLogUtils.d(TAG, "doesIPAddressCacheExist() called with: ipDataFromCache = [" + iPAddress + "]");
        return (iPAddress == null || iPAddress.getCreatedAt() == null) ? false : true;
    }
}
