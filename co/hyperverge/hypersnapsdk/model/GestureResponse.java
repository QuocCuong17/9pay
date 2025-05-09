package co.hyperverge.hypersnapsdk.model;

/* loaded from: classes2.dex */
public class GestureResponse extends BaseResponse {
    ResultResponse resultResponse;

    public ResultResponse getResultResponse() {
        return this.resultResponse;
    }

    public int getConf() {
        return this.resultResponse.getConf();
    }

    public boolean getMatch() {
        ResultResponse resultResponse = this.resultResponse;
        if (resultResponse != null) {
            return resultResponse.getMatch().equals("yes");
        }
        return false;
    }

    /* loaded from: classes2.dex */
    class ResultResponse {
        int conf;
        String match = "";

        ResultResponse() {
        }

        public int getConf() {
            return this.conf;
        }

        public String getMatch() {
            return this.match;
        }
    }
}
