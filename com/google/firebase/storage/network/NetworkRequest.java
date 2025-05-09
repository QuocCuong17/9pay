package com.google.firebase.storage.network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactory;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactoryImpl;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class NetworkRequest {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TYPE = "Content-Type";
    static final String DELETE = "DELETE";
    static final String GET = "GET";
    public static final int INITIALIZATION_EXCEPTION = -1;
    private static final int MAXIMUM_TOKEN_WAIT_TIME_MS = 30000;
    public static final int NETWORK_UNAVAILABLE = -2;
    static final String PATCH = "PATCH";
    static final String POST = "POST";
    static final String PUT = "PUT";
    private static final String TAG = "NetworkRequest";
    private static final String UTF_8 = "UTF-8";
    private static final String X_FIREBASE_APPCHECK = "x-firebase-appcheck";
    private static final String X_FIREBASE_GMPID = "x-firebase-gmpid";
    private static String gmsCoreVersion;
    private HttpURLConnection connection;
    private Context context;
    protected Exception mException;
    private String rawStringResponse;
    private Map<String, String> requestHeaders = new HashMap();
    private int resultCode;
    private Map<String, List<String>> resultHeaders;
    private InputStream resultInputStream;
    private int resultingContentLength;
    private StorageReferenceUri storageReferenceUri;
    public static final Uri PROD_BASE_URL = Uri.parse("https://firebasestorage.googleapis.com/v0");
    static HttpURLConnectionFactory connectionFactory = new HttpURLConnectionFactoryImpl();

    protected abstract String getAction();

    protected JSONObject getOutputJSON() {
        return null;
    }

    protected byte[] getOutputRaw() {
        return null;
    }

    protected int getOutputRawSize() {
        return 0;
    }

    protected Map<String, String> getQueryParameters() {
        return null;
    }

    public NetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(storageReferenceUri);
        Preconditions.checkNotNull(firebaseApp);
        this.storageReferenceUri = storageReferenceUri;
        this.context = firebaseApp.getApplicationContext();
        setCustomHeader(X_FIREBASE_GMPID, firebaseApp.getOptions().getApplicationId());
    }

    public static Uri getBaseUrl(EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings != null) {
            return Uri.parse("http://" + emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort() + "/v0");
        }
        return PROD_BASE_URL;
    }

    private static String getPathWithoutBucket(Uri uri) {
        String path = uri.getPath();
        return path == null ? "" : path.startsWith(RemoteSettings.FORWARD_SLASH_STRING) ? path.substring(1) : path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPathWithoutBucket() {
        return getPathWithoutBucket(this.storageReferenceUri.getGsUri());
    }

    public Uri getURL() {
        return this.storageReferenceUri.getHttpUri();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StorageReferenceUri getStorageReferenceUri() {
        return this.storageReferenceUri;
    }

    public final void reset() {
        this.mException = null;
        this.resultCode = 0;
    }

    public void setCustomHeader(String str, String str2) {
        this.requestHeaders.put(str, str2);
    }

    public InputStream getStream() {
        return this.resultInputStream;
    }

    public JSONObject getResultBody() {
        if (!TextUtils.isEmpty(this.rawStringResponse)) {
            try {
                return new JSONObject(this.rawStringResponse);
            } catch (JSONException e) {
                Log.e(TAG, "error parsing result into JSON:" + this.rawStringResponse, e);
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public void performRequestStart(String str, String str2) {
        if (this.mException != null) {
            this.resultCode = -1;
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "sending network request " + getAction() + " " + getURL());
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            this.resultCode = -2;
            this.mException = new SocketException("Network subsystem is unavailable");
            return;
        }
        try {
            HttpURLConnection createConnection = createConnection();
            this.connection = createConnection;
            createConnection.setRequestMethod(getAction());
            constructMessage(this.connection, str, str2);
            parseResponse(this.connection);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "network request result " + this.resultCode);
            }
        } catch (IOException e) {
            Log.w(TAG, "error sending network request " + getAction() + " " + getURL(), e);
            this.mException = e;
            this.resultCode = -2;
        }
    }

    public void performRequestEnd() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private final void performRequest(String str, String str2) {
        performRequestStart(str, str2);
        try {
            processResponseStream();
        } catch (IOException e) {
            Log.w(TAG, "error sending network request " + getAction() + " " + getURL(), e);
            this.mException = e;
            this.resultCode = -2;
        }
        performRequestEnd();
    }

    public void performRequest(String str, String str2, Context context) {
        if (ensureNetworkAvailable(context)) {
            performRequest(str, str2);
        }
    }

    private boolean ensureNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        this.mException = new SocketException("Network subsystem is unavailable");
        this.resultCode = -2;
        return false;
    }

    private HttpURLConnection createConnection() throws IOException {
        Uri url = getURL();
        Map<String, String> queryParameters = getQueryParameters();
        if (queryParameters != null) {
            Uri.Builder buildUpon = url.buildUpon();
            for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            url = buildUpon.build();
        }
        return connectionFactory.createInstance(new URL(url.toString()));
    }

    private static String getGmsCoreVersion(Context context) {
        if (gmsCoreVersion == null) {
            try {
                gmsCoreVersion = context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Unable to find gmscore in package manager", e);
            }
            if (gmsCoreVersion == null) {
                gmsCoreVersion = "[No Gmscore]";
            }
        }
        return gmsCoreVersion;
    }

    private void constructMessage(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        byte[] outputRaw;
        int outputRawSize;
        Preconditions.checkNotNull(httpURLConnection);
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "no auth token for request");
        } else {
            httpURLConnection.setRequestProperty("Authorization", "Firebase " + str);
        }
        if (!TextUtils.isEmpty(str2)) {
            httpURLConnection.setRequestProperty(X_FIREBASE_APPCHECK, str2);
        } else {
            Log.w(TAG, "No App Check token for request.");
        }
        StringBuilder sb = new StringBuilder("Android/");
        String gmsCoreVersion2 = getGmsCoreVersion(this.context);
        if (!TextUtils.isEmpty(gmsCoreVersion2)) {
            sb.append(gmsCoreVersion2);
        }
        httpURLConnection.setRequestProperty("X-Firebase-Storage-Version", sb.toString());
        for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        JSONObject outputJSON = getOutputJSON();
        if (outputJSON != null) {
            outputRaw = outputJSON.toString().getBytes(UTF_8);
            outputRawSize = outputRaw.length;
        } else {
            outputRaw = getOutputRaw();
            outputRawSize = getOutputRawSize();
            if (outputRawSize == 0 && outputRaw != null) {
                outputRawSize = outputRaw.length;
            }
        }
        if (outputRaw != null && outputRaw.length > 0) {
            if (outputJSON != null) {
                httpURLConnection.setRequestProperty("Content-Type", APPLICATION_JSON);
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(outputRawSize));
        } else {
            httpURLConnection.setRequestProperty("Content-Length", "0");
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        if (outputRaw == null || outputRaw.length <= 0) {
            return;
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (outputStream != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            try {
                bufferedOutputStream.write(outputRaw, 0, outputRawSize);
                return;
            } finally {
                bufferedOutputStream.close();
            }
        }
        Log.e(TAG, "Unable to write to the http request!");
    }

    private void parseResponse(HttpURLConnection httpURLConnection) throws IOException {
        Preconditions.checkNotNull(httpURLConnection);
        this.resultCode = httpURLConnection.getResponseCode();
        this.resultHeaders = httpURLConnection.getHeaderFields();
        this.resultingContentLength = httpURLConnection.getContentLength();
        if (isResultSuccess()) {
            this.resultInputStream = httpURLConnection.getInputStream();
        } else {
            this.resultInputStream = httpURLConnection.getErrorStream();
        }
    }

    private void parseResponse(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else {
                        sb.append(readLine);
                    }
                } finally {
                    bufferedReader.close();
                }
            }
        }
        this.rawStringResponse = sb.toString();
        if (isResultSuccess()) {
            return;
        }
        this.mException = new IOException(this.rawStringResponse);
    }

    private void processResponseStream() throws IOException {
        if (isResultSuccess()) {
            parseSuccessulResponse(this.resultInputStream);
        } else {
            parseErrorResponse(this.resultInputStream);
        }
    }

    protected void parseSuccessulResponse(InputStream inputStream) throws IOException {
        parseResponse(inputStream);
    }

    protected void parseErrorResponse(InputStream inputStream) throws IOException {
        parseResponse(inputStream);
    }

    public String getRawResult() {
        return this.rawStringResponse;
    }

    public Map<String, String> getResultHeaders() {
        return this.requestHeaders;
    }

    public Exception getException() {
        return this.mException;
    }

    public Map<String, List<String>> getResultHeadersImpl() {
        return this.resultHeaders;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public boolean isResultSuccess() {
        int i = this.resultCode;
        return i >= 200 && i < 300;
    }

    public String getResultString(String str) {
        List<String> list;
        Map<String, List<String>> resultHeadersImpl = getResultHeadersImpl();
        if (resultHeadersImpl == null || (list = resultHeadersImpl.get(str)) == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public int getResultingContentLength() {
        return this.resultingContentLength;
    }

    public <TResult> void completeTask(TaskCompletionSource<TResult> taskCompletionSource, TResult tresult) {
        Exception exception = getException();
        if (isResultSuccess() && exception == null) {
            taskCompletionSource.setResult(tresult);
        } else {
            taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exception, getResultCode()));
        }
    }
}
