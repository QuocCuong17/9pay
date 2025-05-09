package co.hyperverge.hypersnapsdk.service.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.util.Log;
import co.hyperverge.hypersnapsdk.listeners.LocationProviderCallback;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes2.dex */
public class LocationServiceImpl implements LocationService {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl";
    private static LocationServiceImpl locationProvider;
    private final Context applicationContext;
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private LocationProviderCallback mLocationProviderCallback;
    private boolean isLocationUpdatesRunning = false;
    private final LocationCallback locationCallback = new LocationCallback() { // from class: co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl.1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            if (LocationServiceImpl.this.mLocationProviderCallback != null) {
                LocationServiceImpl.this.mLocationProviderCallback.onResult(locationResult.getLastLocation());
            }
        }
    };
    private Location lastKnownLocation = null;

    private LocationServiceImpl(Context context) {
        this.applicationContext = context;
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context.getApplicationContext());
    }

    public static LocationServiceImpl getInstance(Context context) {
        if (locationProvider == null) {
            locationProvider = new LocationServiceImpl(context);
        }
        return locationProvider;
    }

    public static void destroy() {
        locationProvider = null;
    }

    @Override // co.hyperverge.hypersnapsdk.service.location.LocationService
    public boolean isGPSSwitchedOn() {
        return ((LocationManager) this.applicationContext.getSystemService(FirebaseAnalytics.Param.LOCATION)).isProviderEnabled("gps");
    }

    @Override // co.hyperverge.hypersnapsdk.service.location.LocationService
    public void addLocationCallback(LocationProviderCallback locationProviderCallback) {
        this.mLocationProviderCallback = locationProviderCallback;
    }

    @Override // co.hyperverge.hypersnapsdk.service.location.LocationService
    public void startLocationUpdates() {
        HVLogUtils.d(TAG, "startLocationUpdates() called");
        this.isLocationUpdatesRunning = true;
        try {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(5000L);
            locationRequest.setFastestInterval(1000L);
            locationRequest.setMaxWaitTime(500L);
            locationRequest.setPriority(100);
            new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            this.fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.locationCallback, Looper.getMainLooper());
        } catch (NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "startLocationUpdates(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.d(str, Utils.getErrorMessage(e));
        }
    }

    @Override // co.hyperverge.hypersnapsdk.service.location.LocationService
    public void stopLocationUpdates() {
        HVLogUtils.d(TAG, "stopLocationUpdates() called");
        try {
            FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationProviderClient;
            if (fusedLocationProviderClient == null || !this.isLocationUpdatesRunning) {
                return;
            }
            fusedLocationProviderClient.removeLocationUpdates(this.locationCallback);
            this.isLocationUpdatesRunning = false;
        } catch (NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "stopLocationUpdates(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.d(str, Utils.getErrorMessage(e));
        }
    }

    @Override // co.hyperverge.hypersnapsdk.service.location.LocationService
    public Location getLastKnownLocation() {
        HVLogUtils.d(TAG, "getLastKnownLocation() called");
        try {
            this.fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl.2
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(Location location) {
                    if (location != null) {
                        LocationServiceImpl.this.lastKnownLocation = location;
                    }
                }
            });
        } catch (NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "getLastKnownLocation(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.d(str, Utils.getErrorMessage(e));
        }
        return this.lastKnownLocation;
    }
}
