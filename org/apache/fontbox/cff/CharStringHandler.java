package org.apache.fontbox.cff;

import java.util.List;

/* loaded from: classes5.dex */
public abstract class CharStringHandler {
    public abstract List<Integer> handleCommand(List<Integer> list, CharStringCommand charStringCommand);

    /* JADX WARN: Multi-variable type inference failed */
    public List<Integer> handleSequence(List<Object> list) {
        int size = list.size();
        int i = 0;
        List<Integer> list2 = null;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof CharStringCommand) {
                if (list2 == null) {
                    list2 = list.subList(i, i2);
                } else {
                    list2.addAll(list.subList(i, i2));
                }
                List<Integer> handleCommand = handleCommand(list2, (CharStringCommand) obj);
                list2 = (handleCommand == null || handleCommand.isEmpty()) ? null : handleCommand;
                i = i2 + 1;
            }
        }
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        return list2;
    }
}
