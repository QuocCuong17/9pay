package co.hyperverge.hvnfcsdk.helpers;

import android.content.Context;
import android.nfc.tech.IsoDep;
import android.util.Log;
import co.hyperverge.hvnfcsdk.R;
import co.hyperverge.hvnfcsdk.helpers.threading.ThreadExecutor;
import co.hyperverge.hvnfcsdk.listeners.NFCParserStatus;
import co.hyperverge.hvnfcsdk.model.Authentication;
import co.hyperverge.hvnfcsdk.model.HVNFCError;
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.util.encoders.Base64;
import org.jmrtd.BACKey;
import org.jmrtd.PassportService;
import org.jmrtd.lds.icao.COMFile;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DGParser {
    private Authentication authentication;
    private BACKey bacKey;
    private final Context context;
    private final JSONObject dataGroups;
    private NFCParserStatus delegate;
    private final IsoDep isoDep;
    private final ThreadExecutor threadExecutor;
    private final String TAG = "DGParser";
    private PassportService service = null;
    private final String SUCCESS = "success";
    private final String ERROR = "error";
    private final Map<String, Short> dataGroupsToRead = new HashMap();
    private final Map<Integer, String> dataGroupsMap = new HashMap();

    public DGParser(Context context, IsoDep isoDep, Map<String, String> map) {
        Objects.requireNonNull(context, "context is marked non-null but is null");
        Objects.requireNonNull(isoDep, "isoDep is marked non-null but is null");
        Objects.requireNonNull(map, "authMap is marked non-null but is null");
        this.isoDep = isoDep;
        this.context = context;
        Gson gson = new Gson();
        this.authentication = (Authentication) gson.fromJson(gson.toJson(map), Authentication.class);
        this.dataGroups = new JSONObject();
        this.threadExecutor = ThreadExecutor.getInstance();
        initialiseDataGroupsToRead();
    }

    void initialiseDataGroupsToRead() {
        LogUtils.d("DGParser", "initialiseDataGroupsToRead() called");
        this.dataGroupsToRead.put(DataGroupConstants.SOD, (short) 285);
        this.dataGroupsToRead.put(DataGroupConstants.COM, Short.valueOf(PassportService.EF_COM));
        this.dataGroupsToRead.put(DataGroupConstants.DG1, Short.valueOf(PassportService.EF_DG1));
        this.dataGroupsToRead.put(DataGroupConstants.DG2, Short.valueOf(PassportService.EF_DG2));
        this.dataGroupsToRead.put(DataGroupConstants.DG3, Short.valueOf(PassportService.EF_DG3));
        this.dataGroupsToRead.put(DataGroupConstants.DG4, Short.valueOf(PassportService.EF_DG4));
        this.dataGroupsToRead.put(DataGroupConstants.DG5, Short.valueOf(PassportService.EF_DG5));
        this.dataGroupsToRead.put(DataGroupConstants.DG6, Short.valueOf(PassportService.EF_DG6));
        this.dataGroupsToRead.put(DataGroupConstants.DG7, Short.valueOf(PassportService.EF_DG7));
        this.dataGroupsToRead.put(DataGroupConstants.DG8, Short.valueOf(PassportService.EF_DG8));
        this.dataGroupsToRead.put(DataGroupConstants.DG9, Short.valueOf(PassportService.EF_DG9));
        this.dataGroupsToRead.put(DataGroupConstants.DG10, Short.valueOf(PassportService.EF_DG10));
        this.dataGroupsToRead.put(DataGroupConstants.DG11, Short.valueOf(PassportService.EF_DG11));
        this.dataGroupsToRead.put(DataGroupConstants.DG12, Short.valueOf(PassportService.EF_DG12));
        this.dataGroupsToRead.put(DataGroupConstants.DG13, Short.valueOf(PassportService.EF_DG13));
        this.dataGroupsToRead.put(DataGroupConstants.DG14, Short.valueOf(PassportService.EF_DG14));
        this.dataGroupsToRead.put(DataGroupConstants.DG15, Short.valueOf(PassportService.EF_DG15));
        this.dataGroupsToRead.put(DataGroupConstants.DG16, Short.valueOf(PassportService.EF_DG16));
        initDataMap();
    }

    void initDataMap() {
        this.dataGroupsMap.put(96, DataGroupConstants.COM);
        this.dataGroupsMap.put(97, DataGroupConstants.DG1);
        this.dataGroupsMap.put(117, DataGroupConstants.DG2);
        this.dataGroupsMap.put(99, DataGroupConstants.DG3);
        this.dataGroupsMap.put(118, DataGroupConstants.DG4);
        this.dataGroupsMap.put(101, DataGroupConstants.DG5);
        this.dataGroupsMap.put(102, DataGroupConstants.DG6);
        this.dataGroupsMap.put(103, DataGroupConstants.DG7);
        this.dataGroupsMap.put(104, DataGroupConstants.DG8);
        this.dataGroupsMap.put(105, DataGroupConstants.DG9);
        this.dataGroupsMap.put(106, DataGroupConstants.DG10);
        this.dataGroupsMap.put(107, DataGroupConstants.DG11);
        this.dataGroupsMap.put(108, DataGroupConstants.DG12);
        this.dataGroupsMap.put(109, DataGroupConstants.DG13);
        this.dataGroupsMap.put(110, DataGroupConstants.DG14);
        this.dataGroupsMap.put(111, DataGroupConstants.DG15);
        this.dataGroupsMap.put(112, DataGroupConstants.DG16);
        this.dataGroupsMap.put(119, DataGroupConstants.SOD);
    }

    Map<String, Short> mapComData(InputStream inputStream) {
        LogUtils.d("DGParser", "mapComData() called");
        HashMap hashMap = new HashMap();
        try {
            int[] tagList = new COMFile(inputStream).getTagList();
            hashMap.put(DataGroupConstants.COM, this.dataGroupsToRead.get(DataGroupConstants.COM));
            hashMap.put(DataGroupConstants.SOD, this.dataGroupsToRead.get(DataGroupConstants.SOD));
            for (int i : tagList) {
                if (this.dataGroupsMap.containsKey(Integer.valueOf(i))) {
                    String str = this.dataGroupsMap.get(Integer.valueOf(i));
                    LogUtils.d("DGParser", "Detected dataGroup : " + str);
                    if (this.dataGroupsToRead.containsKey(str)) {
                        hashMap.put(str, this.dataGroupsToRead.get(str));
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e("DGParser", "Unable to read ComData: [" + Utils.getErrorMessage(e) + "]");
        }
        return hashMap.isEmpty() ? this.dataGroupsToRead : hashMap;
    }

    public void setDelegate(NFCParserStatus nFCParserStatus) {
        Objects.requireNonNull(nFCParserStatus, "delegate is marked non-null but is null");
        LogUtils.d("DGParser", "setDelegate() called");
        this.delegate = nFCParserStatus;
    }

    public void parseData() {
        LogUtils.d("DGParser", "parseData() called");
        this.threadExecutor.execute(new Runnable() { // from class: co.hyperverge.hvnfcsdk.helpers.DGParser$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DGParser.this.m395lambda$parseData$0$cohypervergehvnfcsdkhelpersDGParser();
            }
        });
    }

    /* renamed from: lambda$parseData$0$co-hyperverge-hvnfcsdk-helpers-DGParser, reason: not valid java name */
    public /* synthetic */ void m395lambda$parseData$0$cohypervergehvnfcsdkhelpersDGParser() {
        if (this.isoDep == null) {
            debugMode();
            return;
        }
        this.delegate.onIDDetection();
        try {
            String convertDate = Utils.convertDate(this.authentication.getDateOfBirth());
            String convertDate2 = Utils.convertDate(this.authentication.getDateOfExpiry());
            String trimDocumentID = Utils.trimDocumentID(this.authentication.getDocumentId());
            if (trimDocumentID == null) {
                LogUtils.e("DGParser", "DocumentID is null. Returning with error");
                sendResult(null, "error", new HVNFCError(this.context.getResources().getString(R.string.errorDocumentID), 46));
                return;
            }
            if (convertDate != null && convertDate2 != null) {
                this.bacKey = new BACKey(trimDocumentID, convertDate, convertDate2);
                try {
                    CardService cardService = CardService.getInstance(this.isoDep);
                    cardService.open();
                    PassportService passportService = new PassportService(cardService, 256, 224, false, false);
                    this.service = passportService;
                    passportService.open();
                    LogUtils.d("DGParser", "Card Service open with isoDep");
                    this.service.sendSelectApplet(false);
                    try {
                        this.service.doBAC(this.bacKey);
                        LogUtils.d("DGParser", "Card is connected for reading dataGroups");
                        for (Map.Entry<String, Short> entry : mapComData(this.service.getInputStream(PassportService.EF_COM)).entrySet()) {
                            String key = entry.getKey();
                            try {
                                CardFileInputStream inputStream = this.service.getInputStream(entry.getValue().shortValue());
                                if (inputStream != null) {
                                    this.dataGroups.put(key, Base64.toBase64String(IOUtils.toByteArray(inputStream)));
                                }
                            } catch (Exception e) {
                                LogUtils.e("DGParser", "Error while processing data  : " + e.getLocalizedMessage());
                                if (this.service.isConnectionLost(e)) {
                                    this.delegate.onCardDisconnection();
                                    return;
                                }
                            }
                        }
                        sendResult(this.dataGroups, "success", null);
                        return;
                    } catch (CardServiceException e2) {
                        LogUtils.e("DGParser", "Error occurred with message : [ " + Utils.getErrorMessage(e2) + "]");
                        sendResult(null, "error", new HVNFCError(this.context.getResources().getString(R.string.errorAuthFailed), 44));
                        return;
                    }
                } catch (Exception e3) {
                    LogUtils.d("DGParser", "Error occurred with message : [ " + Utils.getErrorMessage(e3) + "]");
                    this.delegate.onCardDisconnection();
                    return;
                }
            }
            LogUtils.e("DGParser", "Date format error. Returning with error");
            sendResult(null, "error", new HVNFCError(this.context.getResources().getString(R.string.errorDateFormat), 46));
        } catch (Exception e4) {
            LogUtils.e("DGParser", "Connection failed with error : [" + Utils.getErrorMessage(e4) + "]");
            sendResult(null, "error", new HVNFCError(this.context.getResources().getString(R.string.errorUnsupported), 47));
        }
    }

    void sendResult(JSONObject jSONObject, String str, HVNFCError hVNFCError) {
        JSONObject jSONObject2;
        Objects.requireNonNull(str, "status is marked non-null but is null");
        LogUtils.d("DGParser", "sendResult() called");
        JSONObject jSONObject3 = null;
        if (jSONObject != null) {
            try {
                jSONObject2 = new JSONObject();
            } catch (Exception e) {
                e = e;
            }
            try {
                jSONObject2.put("nfcData", jSONObject);
                jSONObject3 = jSONObject2;
            } catch (Exception e2) {
                e = e2;
                jSONObject3 = jSONObject2;
                LogUtils.d("DGParser", "Error occurred with message : [ " + Utils.getErrorMessage(e) + "]");
                this.delegate.onScanResult(jSONObject3, str, hVNFCError);
            }
        }
        this.delegate.onScanResult(jSONObject3, str, hVNFCError);
    }

    public void debugMode() {
        LogUtils.d("DGParser", "debugMode() called");
        try {
            COMFile cOMFile = new COMFile(new ByteArrayInputStream(android.util.Base64.decode("YBhfAQQwMTA3XzYGMDQwMDAwXAZhdWNtb24=", 0)));
            for (int i = 0; i < cOMFile.getTagList().length; i++) {
                Log.e("DGParser", "file : " + cOMFile.getTagList()[i]);
            }
        } catch (Exception e) {
            System.err.println("Invalid Base64 string: " + e.getMessage());
        }
    }
}
