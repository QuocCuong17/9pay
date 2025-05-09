package com.tekartik.sqflite.operation;

import com.tekartik.sqflite.Constant;
import com.tekartik.sqflite.SqlCommand;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BaseReadOperation implements Operation {
    protected abstract OperationResult getOperationResult();

    private String getSql() {
        return (String) getArgument(Constant.PARAM_SQL);
    }

    private List<Object> getSqlArguments() {
        return (List) getArgument(Constant.PARAM_SQL_ARGUMENTS);
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public Integer getTransactionId() {
        return (Integer) getArgument("transactionId");
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public boolean hasNullTransactionId() {
        return hasArgument("transactionId") && getTransactionId() == null;
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public SqlCommand getSqlCommand() {
        return new SqlCommand(getSql(), getSqlArguments());
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public Boolean getInTransactionChange() {
        return getBoolean(Constant.PARAM_IN_TRANSACTION_CHANGE);
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public boolean getNoResult() {
        return Boolean.TRUE.equals(getArgument(Constant.PARAM_NO_RESULT));
    }

    @Override // com.tekartik.sqflite.operation.Operation
    public boolean getContinueOnError() {
        return Boolean.TRUE.equals(getArgument(Constant.PARAM_CONTINUE_OR_ERROR));
    }

    private Boolean getBoolean(String str) {
        Object argument = getArgument(str);
        if (argument instanceof Boolean) {
            return (Boolean) argument;
        }
        return null;
    }

    public String toString() {
        return getMethod() + " " + getSql() + " " + getSqlArguments();
    }
}
