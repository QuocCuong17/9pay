package co.hyperverge.hypersnapsdk.analytics.mixpanel.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.JobIntentService;
import co.hyperverge.hypersnapsdk.data.remote.ApiClient;
import co.hyperverge.hypersnapsdk.helpers.NetworkHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import retrofit2.Response;

@Deprecated
/* loaded from: classes2.dex */
public class MixPanelIntentService extends JobIntentService {
    private static final String ARG_EVENTS = "events";
    private static final int MIX_PANEL_JOB_ID = 328;
    private static final String TAG = "MixPanelIntentService";
    private static EventSyncRepo eventSyncRepo = null;
    private static boolean isEnqueueingOldEvents = false;
    private List<MixPanelEvent> currentEvents;
    private MixPanelApiInterface mixPanelApiClient;

    private static EventSyncRepo getEventSyncRepo(Context context) throws IOException {
        if (eventSyncRepo == null) {
            eventSyncRepo = new EventSyncRepo(context);
        }
        return eventSyncRepo;
    }

    public static void enqueueEvent(Context context, String str, JSONObject jSONObject) {
        Log.v(TAG, "enqueueEvent() called with: eventName = [" + str + "], props = [" + jSONObject.toString() + "]");
        try {
            EventSyncRepo eventSyncRepo2 = getEventSyncRepo(context);
            MixPanelEvent mixPanelEvent = new MixPanelEvent(str, (Map) new Gson().fromJson(jSONObject.toString(), new TypeToken<HashMap<String, Object>>() { // from class: co.hyperverge.hypersnapsdk.analytics.mixpanel.network.MixPanelIntentService.1
            }.getType()));
            if (!eventSyncRepo2.addEvent(mixPanelEvent)) {
                Log.d(TAG, "enqueueEvent: error adding event: [" + mixPanelEvent.toString() + "] to eventQueue");
            }
            enqueuePendingEvents(context);
        } catch (IOException e) {
            Log.e(TAG, "enqueueEvent: failed to create eventSyncRepo " + e.getLocalizedMessage());
        }
    }

    public static void enqueuePendingEvents(Context context) {
        int i;
        try {
            EventSyncRepo eventSyncRepo2 = getEventSyncRepo(context);
            Log.v(TAG, "enqueuePendingEvents: pending events in queue: " + eventSyncRepo2.getEventsCount());
            if (!eventSyncRepo2.hasPendingEvents()) {
                Log.v(TAG, "No pending events to be pushed in eventQueue.");
                return;
            }
            if (!NetworkHelper.isNetworkAvailable(context)) {
                Log.d(TAG, "enqueuePendingEvents: Not connected to the internet, aborting events push");
                return;
            }
            if (isEnqueueingOldEvents) {
                return;
            }
            try {
                ArrayList arrayList = new ArrayList(eventSyncRepo2.getEventsList());
                int size = arrayList.size();
                Log.v(TAG, "enqueuePendingEvents: size: " + size + ", thread - " + Thread.currentThread().getName());
                int i2 = 0;
                i = 0;
                while (i2 < size) {
                    try {
                        int min = Math.min(size - i2, 50);
                        isEnqueueingOldEvents = true;
                        int i3 = i2 + min;
                        enqueueWork(context, (Class<?>) MixPanelIntentService.class, 328, eventsToIntent(context, arrayList.subList(i2, i3)));
                        eventSyncRepo2.removeEvents(min);
                        i += min;
                        i2 = i3;
                    } catch (IOException e) {
                        e = e;
                        Log.e(TAG, Utils.getErrorMessage(e));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                        }
                        Log.v(TAG, "enqueuePendingEvents: enqueued " + i + " events");
                        isEnqueueingOldEvents = false;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                i = 0;
            }
            Log.v(TAG, "enqueuePendingEvents: enqueued " + i + " events");
            isEnqueueingOldEvents = false;
        } catch (IOException e3) {
            Log.e(TAG, "enqueuePendingEvents: failed to create eventSyncRepo " + e3.getLocalizedMessage());
        }
    }

    private static Intent eventsToIntent(Context context, List<MixPanelEvent> list) {
        Log.v(TAG, "eventToIntent() called with: events = [" + list + "]");
        Gson gson = new Gson();
        Intent intent = new Intent(context, (Class<?>) MixPanelIntentService.class);
        intent.putExtra(ARG_EVENTS, gson.toJson(list));
        return intent;
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Log.v(TAG, "onHandleWork() called with: intent = [" + intent + "]");
        if (this.mixPanelApiClient == null) {
            this.mixPanelApiClient = ApiClient.getMixPanelService();
        }
        try {
            Gson gson = new Gson();
            this.currentEvents = (List) gson.fromJson(intent.getStringExtra(ARG_EVENTS), new TypeToken<List<MixPanelEvent>>() { // from class: co.hyperverge.hypersnapsdk.analytics.mixpanel.network.MixPanelIntentService.2
            }.getType());
            Log.v(TAG, "onHandleWork() : posting mixPanelEvents = [" + this.currentEvents + "]");
            Response<Object> postEvents = postEvents(gson.toJson(this.currentEvents));
            this.currentEvents = null;
            Log.v(TAG, "onHandleWork() response = [" + postEvents + "]");
            if (postEvents == null) {
                return;
            }
            Object body = postEvents.body();
            if (body instanceof Double) {
                if (((Double) body).doubleValue() == 0.0d) {
                    Log.d(TAG, "onResponse: error response 0 from MixPanel API");
                    return;
                } else {
                    if (((Double) body).doubleValue() == 1.0d) {
                        Log.v(TAG, "onResponse: success response 1 from MixPanel API");
                        return;
                    }
                    return;
                }
            }
            if (body != null) {
                Log.d(TAG, "onResponse: errorResponse: " + ((MixPanelErrorResponse) gson.fromJson(body.toString(), MixPanelErrorResponse.class)));
            }
        } catch (Exception e) {
            Log.e(TAG, "onHandleWork: ", e);
        }
    }

    private Response<Object> postEvents(String str) {
        Log.v(TAG, "postEvents() called with: requestString = [" + str + "]");
        try {
            return this.mixPanelApiClient.postEvents(str).execute();
        } catch (IOException e) {
            Log.e(TAG, "postEvents: ", e);
            if (eventSyncRepo.addEvents(this.currentEvents)) {
                return null;
            }
            Log.d(TAG, "postEvents: Error adding events to file: " + this.currentEvents.size());
            return null;
        }
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onDestroy() {
        List<MixPanelEvent> list;
        super.onDestroy();
        Log.v(TAG, "onDestroy() called: All events processed");
        EventSyncRepo eventSyncRepo2 = eventSyncRepo;
        if (eventSyncRepo2 != null && (list = this.currentEvents) != null) {
            eventSyncRepo2.addEvents(list);
        }
        eventSyncRepo = null;
    }
}
