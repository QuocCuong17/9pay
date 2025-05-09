package vn.ai.faceauth.sdk.presentation.presentation.utils;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/utils/Logger;", "", "()V", "dataStep", "", "Lvn/ai/faceauth/sdk/presentation/presentation/utils/LogEntry;", "getDataStep", "()Ljava/util/List;", "setDataStep", "(Ljava/util/List;)V", "addStep", "", "step", "Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "toJson", "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class Logger {
    public static final Logger INSTANCE = new Logger();
    private static List<LogEntry> dataStep = new ArrayList();

    private Logger() {
    }

    public final void addStep(StepLiveness step) {
        String format = new SimpleDateFormat(tfwhgw.rnigpa(25)).format(Calendar.getInstance().getTime());
        List<LogEntry> list = dataStep;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((LogEntry) it.next()).getStepLiveness() == step) {
                    break;
                }
            }
        }
        z = false;
        if (z) {
            return;
        }
        dataStep.add(new LogEntry(step, format.toString()));
    }

    public final List<LogEntry> getDataStep() {
        return dataStep;
    }

    public final void setDataStep(List<LogEntry> list) {
        dataStep = list;
    }

    public final String toJson() {
        return new Gson().toJson(dataStep);
    }
}
