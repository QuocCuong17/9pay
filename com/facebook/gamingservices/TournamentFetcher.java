package com.facebook.gamingservices;

import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.bolts.TaskCompletionSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TournamentFetcher.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¨\u0006\u0007"}, d2 = {"Lcom/facebook/gamingservices/TournamentFetcher;", "", "()V", "fetchTournaments", "Lcom/facebook/bolts/TaskCompletionSource;", "", "Lcom/facebook/gamingservices/Tournament;", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TournamentFetcher {
    public final TaskCompletionSource<List<Tournament>> fetchTournaments() {
        final TaskCompletionSource<List<Tournament>> taskCompletionSource = new TaskCompletionSource<>();
        Bundle bundle = new Bundle();
        AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
        if (currentAccessToken == null || currentAccessToken.isExpired()) {
            throw new FacebookException("Attempted to fetch tournament with an invalid access token");
        }
        if (!(currentAccessToken.getGraphDomain() != null && Intrinsics.areEqual(FacebookSdk.GAMING, currentAccessToken.getGraphDomain()))) {
            throw new FacebookException("User is not using gaming login");
        }
        GraphRequest graphRequest = new GraphRequest(AccessToken.INSTANCE.getCurrentAccessToken(), "me/tournaments", bundle, HttpMethod.GET, new GraphRequest.Callback() { // from class: com.facebook.gamingservices.TournamentFetcher$$ExternalSyntheticLambda0
            @Override // com.facebook.GraphRequest.Callback
            public final void onCompleted(GraphResponse graphResponse) {
                TournamentFetcher.fetchTournaments$lambda$1(TaskCompletionSource.this, graphResponse);
            }
        }, null, 32, null);
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
        return taskCompletionSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fetchTournaments$lambda$1(TaskCompletionSource task, GraphResponse response) {
        Intrinsics.checkNotNullParameter(task, "$task");
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getError() != null) {
            FacebookRequestError error = response.getError();
            if ((error != null ? error.getException() : null) != null) {
                FacebookRequestError error2 = response.getError();
                task.setError(error2 != null ? error2.getException() : null);
                return;
            } else {
                task.setError(new GraphAPIException("Graph API Error"));
                return;
            }
        }
        try {
            JSONObject graphObject = response.getGraphObject();
            if (graphObject == null) {
                task.setError(new GraphAPIException("Failed to get response"));
                return;
            }
            JSONArray jSONArray = graphObject.getJSONArray("data");
            if (jSONArray.length() < 1) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ROOT, "No tournament found", Arrays.copyOf(new Object[]{Integer.valueOf(jSONArray.length()), 1}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                task.setError(new GraphAPIException(format));
                return;
            }
            Gson create = new GsonBuilder().create();
            String jSONArray2 = jSONArray.toString();
            Intrinsics.checkNotNullExpressionValue(jSONArray2, "data.toString()");
            Object fromJson = create.fromJson(jSONArray2, (Class<Object>) Tournament[].class);
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(dataString…<Tournament>::class.java)");
            task.setResult(ArraysKt.toList((Object[]) fromJson));
        } catch (JSONException e) {
            task.setError(e);
        }
    }
}
