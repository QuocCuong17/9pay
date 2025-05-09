package com.facebook;

import android.util.Log;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.google.android.material.timepicker.TimeModel;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: GraphResponse.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0002'(B+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010BA\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0012J\b\u0010!\u001a\u0004\u0018\u00010\fJ\b\u0010\"\u001a\u0004\u0018\u00010\tJ\u0010\u0010#\u001a\u0004\u0018\u00010\u00032\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020\u0007H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006)"}, d2 = {"Lcom/facebook/GraphResponse;", "", "request", "Lcom/facebook/GraphRequest;", "connection", "Ljava/net/HttpURLConnection;", "rawResponse", "", "graphObject", "Lorg/json/JSONObject;", "(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Ljava/lang/String;Lorg/json/JSONObject;)V", "graphObjects", "Lorg/json/JSONArray;", "(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Ljava/lang/String;Lorg/json/JSONArray;)V", "error", "Lcom/facebook/FacebookRequestError;", "(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookRequestError;)V", "graphObjectArray", "(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONArray;Lcom/facebook/FacebookRequestError;)V", "getConnection", "()Ljava/net/HttpURLConnection;", "getError", "()Lcom/facebook/FacebookRequestError;", "jsonArray", "getJsonArray", "()Lorg/json/JSONArray;", "jsonObject", "getJsonObject", "()Lorg/json/JSONObject;", "getRawResponse", "()Ljava/lang/String;", "getRequest", "()Lcom/facebook/GraphRequest;", "getJSONArray", "getJSONObject", "getRequestForPagedResults", "direction", "Lcom/facebook/GraphResponse$PagingDirection;", InAppPurchaseConstants.METHOD_TO_STRING, "Companion", "PagingDirection", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GraphResponse {
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final JSONArray jsonArray;
    private final JSONObject jsonObject;
    private final String rawResponse;
    private final GraphRequest request;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = GraphResponse.class.getCanonicalName();

    /* compiled from: GraphResponse.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/facebook/GraphResponse$PagingDirection;", "", "(Ljava/lang/String;I)V", "NEXT", "PREVIOUS", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum PagingDirection {
        NEXT,
        PREVIOUS
    }

    @JvmStatic
    public static final List<GraphResponse> constructErrorResponses(List<GraphRequest> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        return INSTANCE.constructErrorResponses(list, httpURLConnection, facebookException);
    }

    public GraphResponse(GraphRequest request, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        Intrinsics.checkNotNullParameter(request, "request");
        this.request = request;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.graphObject = jSONObject;
        this.graphObjectArray = jSONArray;
        this.error = facebookRequestError;
        this.jsonObject = jSONObject;
        this.jsonArray = jSONArray;
    }

    public final GraphRequest getRequest() {
        return this.request;
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    public final String getRawResponse() {
        return this.rawResponse;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GraphResponse(GraphRequest request, HttpURLConnection httpURLConnection, String rawResponse, JSONObject jSONObject) {
        this(request, httpURLConnection, rawResponse, jSONObject, null, null);
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(rawResponse, "rawResponse");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GraphResponse(GraphRequest request, HttpURLConnection httpURLConnection, String rawResponse, JSONArray graphObjects) {
        this(request, httpURLConnection, rawResponse, null, graphObjects, null);
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(rawResponse, "rawResponse");
        Intrinsics.checkNotNullParameter(graphObjects, "graphObjects");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GraphResponse(GraphRequest request, HttpURLConnection httpURLConnection, FacebookRequestError error) {
        this(request, httpURLConnection, null, null, null, error);
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(error, "error");
    }

    /* renamed from: getJSONObject, reason: from getter */
    public final JSONObject getGraphObject() {
        return this.graphObject;
    }

    public final JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /* renamed from: getJSONArray, reason: from getter */
    public final JSONArray getGraphObjectArray() {
        return this.graphObjectArray;
    }

    public final JSONArray getJsonArray() {
        return this.jsonArray;
    }

    public final GraphRequest getRequestForPagedResults(PagingDirection direction) {
        String str;
        JSONObject optJSONObject;
        Intrinsics.checkNotNullParameter(direction, "direction");
        JSONObject jSONObject = this.graphObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("paging")) == null) {
            str = null;
        } else if (direction == PagingDirection.NEXT) {
            str = optJSONObject.optString("next");
        } else {
            str = optJSONObject.optString("previous");
        }
        if (Utility.isNullOrEmpty(str)) {
            return null;
        }
        if (str != null && Intrinsics.areEqual(str, this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new GraphRequest(this.request.getAccessToken(), new URL(str));
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public String toString() {
        String str;
        try {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            HttpURLConnection httpURLConnection = this.connection;
            objArr[0] = Integer.valueOf(httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
            str = String.format(locale, TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        } catch (IOException unused) {
            str = "unknown";
        }
        String str2 = "{Response:  responseCode: " + str + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
        Intrinsics.checkNotNullExpressionValue(str2, "StringBuilder()\n        …(\"}\")\n        .toString()");
        return str2;
    }

    /* compiled from: GraphResponse.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J*\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0001H\u0002J.\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u0006\u0010\u0015\u001a\u00020\u0001H\u0002J/\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cJ-\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u001e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001fJ#\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u001bH\u0001¢\u0006\u0002\b!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/facebook/GraphResponse$Companion;", "", "()V", "BODY_KEY", "", "CODE_KEY", "NON_JSON_RESPONSE_PROPERTY", "RESPONSE_LOG_TAG", "SUCCESS_KEY", "TAG", "constructErrorResponses", "", "Lcom/facebook/GraphResponse;", "requests", "Lcom/facebook/GraphRequest;", "connection", "Ljava/net/HttpURLConnection;", "error", "Lcom/facebook/FacebookException;", "createResponseFromObject", "request", "sourceObject", "originalResult", "createResponsesFromObject", "createResponsesFromStream", "stream", "Ljava/io/InputStream;", "Lcom/facebook/GraphRequestBatch;", "createResponsesFromStream$facebook_core_release", "createResponsesFromString", "responseString", "createResponsesFromString$facebook_core_release", "fromHttpConnection", "fromHttpConnection$facebook_core_release", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final List<GraphResponse> fromHttpConnection$facebook_core_release(HttpURLConnection connection, GraphRequestBatch requests) {
            List<GraphResponse> constructErrorResponses;
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(requests, "requests");
            InputStream inputStream = null;
            try {
                try {
                } catch (FacebookException e) {
                    Logger.INSTANCE.log(LoggingBehavior.REQUESTS, GraphResponse.RESPONSE_LOG_TAG, "Response <Error>: %s", e);
                    constructErrorResponses = constructErrorResponses(requests, connection, e);
                } catch (Exception e2) {
                    Logger.INSTANCE.log(LoggingBehavior.REQUESTS, GraphResponse.RESPONSE_LOG_TAG, "Response <Error>: %s", e2);
                    constructErrorResponses = constructErrorResponses(requests, connection, new FacebookException(e2));
                }
                if (!FacebookSdk.isFullyInitialized()) {
                    Log.e(GraphResponse.TAG, "GraphRequest can't be used when Facebook SDK isn't fully initialized");
                    throw new FacebookException("GraphRequest can't be used when Facebook SDK isn't fully initialized");
                }
                if (connection.getResponseCode() >= 400) {
                    inputStream = connection.getErrorStream();
                } else {
                    inputStream = connection.getInputStream();
                }
                constructErrorResponses = createResponsesFromStream$facebook_core_release(inputStream, connection, requests);
                return constructErrorResponses;
            } finally {
                Utility.closeQuietly((Closeable) null);
            }
        }

        @JvmStatic
        public final List<GraphResponse> createResponsesFromStream$facebook_core_release(InputStream stream, HttpURLConnection connection, GraphRequestBatch requests) throws FacebookException, JSONException, IOException {
            Intrinsics.checkNotNullParameter(requests, "requests");
            String readStreamToString = Utility.readStreamToString(stream);
            Logger.INSTANCE.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, GraphResponse.RESPONSE_LOG_TAG, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(readStreamToString.length()), readStreamToString);
            return createResponsesFromString$facebook_core_release(readStreamToString, connection, requests);
        }

        @JvmStatic
        public final List<GraphResponse> createResponsesFromString$facebook_core_release(String responseString, HttpURLConnection connection, GraphRequestBatch requests) throws FacebookException, JSONException, IOException {
            Intrinsics.checkNotNullParameter(responseString, "responseString");
            Intrinsics.checkNotNullParameter(requests, "requests");
            Object resultObject = new JSONTokener(responseString).nextValue();
            Intrinsics.checkNotNullExpressionValue(resultObject, "resultObject");
            List<GraphResponse> createResponsesFromObject = createResponsesFromObject(connection, requests, resultObject);
            Logger.INSTANCE.log(LoggingBehavior.REQUESTS, GraphResponse.RESPONSE_LOG_TAG, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", requests.getId(), Integer.valueOf(responseString.length()), createResponsesFromObject);
            return createResponsesFromObject;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x005c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final List<GraphResponse> createResponsesFromObject(HttpURLConnection connection, List<GraphRequest> requests, Object sourceObject) throws FacebookException, JSONException {
            Object obj;
            int size = requests.size();
            ArrayList arrayList = new ArrayList(size);
            if (size == 1) {
                GraphRequest graphRequest = requests.get(0);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("body", sourceObject);
                    jSONObject.put("code", connection != null ? connection.getResponseCode() : 200);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(jSONObject);
                    obj = jSONArray;
                } catch (IOException e) {
                    arrayList.add(new GraphResponse(graphRequest, connection, new FacebookRequestError(connection, e)));
                } catch (JSONException e2) {
                    arrayList.add(new GraphResponse(graphRequest, connection, new FacebookRequestError(connection, e2)));
                }
                if (obj instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    if (jSONArray2.length() == size) {
                        int length = jSONArray2.length();
                        for (int i = 0; i < length; i++) {
                            GraphRequest graphRequest2 = requests.get(i);
                            try {
                                Object obj2 = ((JSONArray) obj).get(i);
                                Intrinsics.checkNotNullExpressionValue(obj2, "obj");
                                arrayList.add(createResponseFromObject(graphRequest2, connection, obj2, sourceObject));
                            } catch (FacebookException e3) {
                                arrayList.add(new GraphResponse(graphRequest2, connection, new FacebookRequestError(connection, e3)));
                            } catch (JSONException e4) {
                                arrayList.add(new GraphResponse(graphRequest2, connection, new FacebookRequestError(connection, e4)));
                            }
                        }
                        return arrayList;
                    }
                }
                throw new FacebookException("Unexpected number of results");
            }
            obj = sourceObject;
            if (obj instanceof JSONArray) {
            }
            throw new FacebookException("Unexpected number of results");
        }

        private final GraphResponse createResponseFromObject(GraphRequest request, HttpURLConnection connection, Object sourceObject, Object originalResult) throws JSONException {
            if (sourceObject instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) sourceObject;
                FacebookRequestError checkResponseAndCreateError = FacebookRequestError.INSTANCE.checkResponseAndCreateError(jSONObject, originalResult, connection);
                if (checkResponseAndCreateError != null) {
                    Log.e(GraphResponse.TAG, checkResponseAndCreateError.toString());
                    if (checkResponseAndCreateError.getErrorCode() == 190 && Utility.isCurrentAccessToken(request.getAccessToken())) {
                        if (checkResponseAndCreateError.getSubErrorCode() != 493) {
                            AccessToken.INSTANCE.setCurrentAccessToken(null);
                        } else {
                            AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
                            boolean z = false;
                            if (currentAccessToken != null && !currentAccessToken.isExpired()) {
                                z = true;
                            }
                            if (z) {
                                AccessToken.INSTANCE.expireCurrentAccessToken();
                            }
                        }
                    }
                    return new GraphResponse(request, connection, checkResponseAndCreateError);
                }
                Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, "body", GraphResponse.NON_JSON_RESPONSE_PROPERTY);
                if (stringPropertyAsJSON instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) stringPropertyAsJSON;
                    return new GraphResponse(request, connection, jSONObject2.toString(), jSONObject2);
                }
                if (stringPropertyAsJSON instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) stringPropertyAsJSON;
                    return new GraphResponse(request, connection, jSONArray.toString(), jSONArray);
                }
                sourceObject = JSONObject.NULL;
                Intrinsics.checkNotNullExpressionValue(sourceObject, "NULL");
            }
            if (sourceObject == JSONObject.NULL) {
                return new GraphResponse(request, connection, sourceObject.toString(), (JSONObject) null);
            }
            throw new FacebookException("Got unexpected object type in response, class: " + sourceObject.getClass().getSimpleName());
        }

        @JvmStatic
        public final List<GraphResponse> constructErrorResponses(List<GraphRequest> requests, HttpURLConnection connection, FacebookException error) {
            Intrinsics.checkNotNullParameter(requests, "requests");
            List<GraphRequest> list = requests;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new GraphResponse((GraphRequest) it.next(), connection, new FacebookRequestError(connection, error)));
            }
            return arrayList;
        }
    }
}
