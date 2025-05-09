package com.tekartik.sqflite.operation;

import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class BatchOperation extends BaseOperation {
    final Map<String, Object> map;
    final boolean noResult;
    final BatchOperationResult operationResult = new BatchOperationResult();

    public BatchOperation(Map<String, Object> map, boolean z) {
        this.map = map;
        this.noResult = z;
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public String getMethod() {
        return (String) this.map.get("method");
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public <T> T getArgument(String str) {
        return (T) this.map.get(str);
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public boolean hasArgument(String str) {
        return this.map.containsKey(str);
    }

    @Override // com.tekartik.sqflite.operation.BaseOperation, com.tekartik.sqflite.operation.BaseReadOperation
    public OperationResult getOperationResult() {
        return this.operationResult;
    }

    public Map<String, Object> getOperationSuccessResult() {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.PARAM_RESULT, this.operationResult.result);
        return hashMap;
    }

    public Map<String, Object> getOperationError() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("code", this.operationResult.errorCode);
        hashMap2.put("message", this.operationResult.errorMessage);
        hashMap2.put("data", this.operationResult.errorData);
        hashMap.put("error", hashMap2);
        return hashMap;
    }

    public void handleError(MethodChannel.Result result) {
        result.error(this.operationResult.errorCode, this.operationResult.errorMessage, this.operationResult.errorData);
    }

    @Override // com.tekartik.sqflite.operation.BaseReadOperation, com.tekartik.sqflite.operation.Operation
    public boolean getNoResult() {
        return this.noResult;
    }

    public void handleSuccess(List<Map<String, Object>> list) {
        if (getNoResult()) {
            return;
        }
        list.add(getOperationSuccessResult());
    }

    public void handleErrorContinue(List<Map<String, Object>> list) {
        if (getNoResult()) {
            return;
        }
        list.add(getOperationError());
    }

    /* loaded from: classes5.dex */
    public class BatchOperationResult implements OperationResult {
        String errorCode;
        Object errorData;
        String errorMessage;
        Object result;

        public BatchOperationResult() {
        }

        @Override // com.tekartik.sqflite.operation.OperationResult
        public void success(Object obj) {
            this.result = obj;
        }

        @Override // com.tekartik.sqflite.operation.OperationResult
        public void error(String str, String str2, Object obj) {
            this.errorCode = str;
            this.errorMessage = str2;
            this.errorData = obj;
        }
    }
}
