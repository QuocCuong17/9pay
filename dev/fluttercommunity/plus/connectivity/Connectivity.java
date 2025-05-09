package dev.fluttercommunity.plus.connectivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Connectivity {
    static final String CONNECTIVITY_BLUETOOTH = "bluetooth";
    static final String CONNECTIVITY_ETHERNET = "ethernet";
    static final String CONNECTIVITY_MOBILE = "mobile";
    static final String CONNECTIVITY_NONE = "none";
    static final String CONNECTIVITY_OTHER = "other";
    static final String CONNECTIVITY_VPN = "vpn";
    static final String CONNECTIVITY_WIFI = "wifi";
    private final ConnectivityManager connectivityManager;

    public Connectivity(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getNetworkTypes() {
        if (Build.VERSION.SDK_INT >= 23) {
            return getCapabilitiesFromNetwork(this.connectivityManager.getActiveNetwork());
        }
        return getNetworkTypesLegacy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getCapabilitiesFromNetwork(Network network) {
        return getCapabilitiesList(this.connectivityManager.getNetworkCapabilities(network));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getCapabilitiesList(NetworkCapabilities networkCapabilities) {
        ArrayList arrayList = new ArrayList();
        if (networkCapabilities == null || !networkCapabilities.hasCapability(12)) {
            arrayList.add("none");
            return arrayList;
        }
        if (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(5)) {
            arrayList.add(CONNECTIVITY_WIFI);
        }
        if (networkCapabilities.hasTransport(3)) {
            arrayList.add(CONNECTIVITY_ETHERNET);
        }
        if (networkCapabilities.hasTransport(4)) {
            arrayList.add(CONNECTIVITY_VPN);
        }
        if (networkCapabilities.hasTransport(0)) {
            arrayList.add("mobile");
        }
        if (networkCapabilities.hasTransport(2)) {
            arrayList.add(CONNECTIVITY_BLUETOOTH);
        }
        if (arrayList.isEmpty() && networkCapabilities.hasCapability(12)) {
            arrayList.add("other");
        }
        if (arrayList.isEmpty()) {
            arrayList.add("none");
        }
        return arrayList;
    }

    private List<String> getNetworkTypesLegacy() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        ArrayList arrayList = new ArrayList();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            arrayList.add("none");
            return arrayList;
        }
        int type = activeNetworkInfo.getType();
        if (type != 0) {
            if (type != 1) {
                if (type != 4 && type != 5) {
                    if (type != 6) {
                        if (type == 7) {
                            arrayList.add(CONNECTIVITY_BLUETOOTH);
                        } else if (type == 9) {
                            arrayList.add(CONNECTIVITY_ETHERNET);
                        } else if (type == 17) {
                            arrayList.add(CONNECTIVITY_VPN);
                        } else {
                            arrayList.add("other");
                        }
                        return arrayList;
                    }
                }
            }
            arrayList.add(CONNECTIVITY_WIFI);
            return arrayList;
        }
        arrayList.add("mobile");
        return arrayList;
    }

    public ConnectivityManager getConnectivityManager() {
        return this.connectivityManager;
    }
}
