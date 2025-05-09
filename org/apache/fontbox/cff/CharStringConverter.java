package org.apache.fontbox.cff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class CharStringConverter extends CharStringHandler {
    private int defaultWidthX;
    private int nominalWidthX;
    private List<Object> sequence = null;
    private int pathCount = 0;

    private void expandStemHints(List<Integer> list, boolean z) {
    }

    public CharStringConverter(int i, int i2, IndexData indexData, IndexData indexData2) {
        this.defaultWidthX = 0;
        this.nominalWidthX = 0;
        this.defaultWidthX = i;
        this.nominalWidthX = i2;
    }

    public CharStringConverter(int i, int i2) {
        this.defaultWidthX = 0;
        this.nominalWidthX = 0;
        this.defaultWidthX = i;
        this.nominalWidthX = i2;
    }

    public List<Object> convert(List<Object> list) {
        this.sequence = new ArrayList();
        this.pathCount = 0;
        handleSequence(list);
        return this.sequence;
    }

    @Override // org.apache.fontbox.cff.CharStringHandler
    public List<Integer> handleCommand(List<Integer> list, CharStringCommand charStringCommand) {
        if (CharStringCommand.TYPE1_VOCABULARY.containsKey(charStringCommand.getKey())) {
            return handleType1Command(list, charStringCommand);
        }
        return handleType2Command(list, charStringCommand);
    }

    private List<Integer> handleType1Command(List<Integer> list, CharStringCommand charStringCommand) {
        String str = CharStringCommand.TYPE1_VOCABULARY.get(charStringCommand.getKey());
        if ("hstem".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), true);
            return null;
        }
        if ("vstem".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), false);
            return null;
        }
        if ("vmoveto".equals(str)) {
            List<Integer> clearStack = clearStack(list, list.size() > 1);
            markPath();
            addCommand(clearStack, charStringCommand);
            return null;
        }
        if ("rlineto".equals(str)) {
            addCommandList(split(list, 2), charStringCommand);
            return null;
        }
        if ("hlineto".equals(str)) {
            drawAlternatingLine(list, true);
            return null;
        }
        if ("vlineto".equals(str)) {
            drawAlternatingLine(list, false);
            return null;
        }
        if ("rrcurveto".equals(str)) {
            addCommandList(split(list, 6), charStringCommand);
            return null;
        }
        if ("endchar".equals(str)) {
            List<Integer> clearStack2 = clearStack(list, list.size() > 0);
            closePath();
            addCommand(clearStack2, charStringCommand);
            return null;
        }
        if ("rmoveto".equals(str)) {
            List<Integer> clearStack3 = clearStack(list, list.size() > 2);
            markPath();
            addCommand(clearStack3, charStringCommand);
            return null;
        }
        if ("hmoveto".equals(str)) {
            List<Integer> clearStack4 = clearStack(list, list.size() > 1);
            markPath();
            addCommand(clearStack4, charStringCommand);
            return null;
        }
        if ("vhcurveto".equals(str)) {
            drawAlternatingCurve(list, false);
            return null;
        }
        if ("hvcurveto".equals(str)) {
            drawAlternatingCurve(list, true);
            return null;
        }
        if ("return".equals(str)) {
            return list;
        }
        addCommand(list, charStringCommand);
        return null;
    }

    private List<Integer> handleType2Command(List<Integer> list, CharStringCommand charStringCommand) {
        String str = CharStringCommand.TYPE2_VOCABULARY.get(charStringCommand.getKey());
        if ("hflex".equals(str)) {
            addCommandList(Arrays.asList(Arrays.asList(list.get(0), 0, list.get(1), list.get(2), list.get(3), 0), Arrays.asList(list.get(4), 0, list.get(5), Integer.valueOf(-list.get(2).intValue()), list.get(6), 0)), new CharStringCommand(8));
            return null;
        }
        if ("flex".equals(str)) {
            addCommandList(Arrays.asList(list.subList(0, 6), list.subList(6, 12)), new CharStringCommand(8));
            return null;
        }
        if ("hflex1".equals(str)) {
            addCommandList(Arrays.asList(Arrays.asList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), 0), Arrays.asList(list.get(5), 0, list.get(6), list.get(7), list.get(8), 0)), new CharStringCommand(8));
            return null;
        }
        if ("flex1".equals(str)) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                i += list.get(i4).intValue();
                i2 += list.get(i4 + 1).intValue();
            }
            List<Integer> subList = list.subList(0, 6);
            Integer[] numArr = new Integer[6];
            numArr[0] = list.get(6);
            numArr[1] = list.get(7);
            numArr[2] = list.get(8);
            numArr[3] = list.get(9);
            numArr[4] = Math.abs(i) > Math.abs(i2) ? list.get(10) : Integer.valueOf(-i);
            numArr[5] = Math.abs(i) > Math.abs(i2) ? Integer.valueOf(-i2) : list.get(10);
            addCommandList(Arrays.asList(subList, Arrays.asList(numArr)), new CharStringCommand(8));
            return null;
        }
        if ("hstemhm".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), true);
            return null;
        }
        if ("hintmask".equals(str) || "cntrmask".equals(str)) {
            List<Integer> clearStack = clearStack(list, list.size() % 2 != 0);
            if (clearStack.size() <= 0) {
                return null;
            }
            expandStemHints(clearStack, false);
            return null;
        }
        if ("vstemhm".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), false);
            return null;
        }
        if ("rcurveline".equals(str)) {
            addCommandList(split(list.subList(0, list.size() - 2), 6), new CharStringCommand(8));
            addCommand(list.subList(list.size() - 2, list.size()), new CharStringCommand(5));
            return null;
        }
        if ("rlinecurve".equals(str)) {
            addCommandList(split(list.subList(0, list.size() - 6), 2), new CharStringCommand(5));
            addCommand(list.subList(list.size() - 6, list.size()), new CharStringCommand(8));
            return null;
        }
        if ("vvcurveto".equals(str)) {
            drawCurve(list, false);
            return null;
        }
        if ("hhcurveto".equals(str)) {
            drawCurve(list, true);
            return null;
        }
        addCommand(list, charStringCommand);
        return null;
    }

    private List<Integer> clearStack(List<Integer> list, boolean z) {
        if (this.sequence.size() != 0) {
            return list;
        }
        if (z) {
            addCommand(Arrays.asList(0, Integer.valueOf(list.get(0).intValue() + this.nominalWidthX)), new CharStringCommand(13));
            return list.subList(1, list.size());
        }
        addCommand(Arrays.asList(0, Integer.valueOf(this.defaultWidthX)), new CharStringCommand(13));
        return list;
    }

    private void markPath() {
        if (this.pathCount > 0) {
            closePath();
        }
        this.pathCount++;
    }

    private void closePath() {
        CharStringCommand charStringCommand;
        if (this.pathCount > 0) {
            charStringCommand = (CharStringCommand) this.sequence.get(r0.size() - 1);
        } else {
            charStringCommand = null;
        }
        CharStringCommand charStringCommand2 = new CharStringCommand(9);
        if (charStringCommand == null || charStringCommand2.equals(charStringCommand)) {
            return;
        }
        addCommand(Collections.emptyList(), charStringCommand2);
    }

    private void drawAlternatingLine(List<Integer> list, boolean z) {
        while (list.size() > 0) {
            addCommand(list.subList(0, 1), new CharStringCommand(z ? 6 : 7));
            list = list.subList(1, list.size());
            z = !z;
        }
    }

    private void drawAlternatingCurve(List<Integer> list, boolean z) {
        while (list.size() > 0) {
            int i = 5;
            boolean z2 = list.size() == 5;
            if (z) {
                Integer[] numArr = new Integer[6];
                numArr[0] = list.get(0);
                numArr[1] = 0;
                numArr[2] = list.get(1);
                numArr[3] = list.get(2);
                numArr[4] = z2 ? list.get(4) : 0;
                numArr[5] = list.get(3);
                addCommand(Arrays.asList(numArr), new CharStringCommand(8));
            } else {
                Integer[] numArr2 = new Integer[6];
                numArr2[0] = 0;
                numArr2[1] = list.get(0);
                numArr2[2] = list.get(1);
                numArr2[3] = list.get(2);
                numArr2[4] = list.get(3);
                numArr2[5] = z2 ? list.get(4) : 0;
                addCommand(Arrays.asList(numArr2), new CharStringCommand(8));
            }
            if (!z2) {
                i = 4;
            }
            list = list.subList(i, list.size());
            z = !z;
        }
    }

    private void drawCurve(List<Integer> list, boolean z) {
        Integer num;
        while (list.size() > 0) {
            int i = 4;
            int i2 = list.size() % 4 == 1 ? 1 : 0;
            if (z) {
                Integer[] numArr = new Integer[6];
                numArr[0] = list.get(i2);
                numArr[1] = i2 != 0 ? list.get(0) : 0;
                numArr[2] = list.get(i2 != 0 ? 2 : 1);
                numArr[3] = list.get(i2 != 0 ? 3 : 2);
                numArr[4] = list.get(i2 != 0 ? 4 : 3);
                numArr[5] = 0;
                addCommand(Arrays.asList(numArr), new CharStringCommand(8));
            } else {
                Integer[] numArr2 = new Integer[6];
                if (i2 != 0) {
                    num = list.get(0);
                } else {
                    num = 0;
                }
                numArr2[0] = num;
                numArr2[1] = list.get(i2);
                numArr2[2] = list.get(i2 != 0 ? 2 : 1);
                numArr2[3] = list.get(i2 != 0 ? 3 : 2);
                numArr2[4] = 0;
                numArr2[5] = list.get(i2 != 0 ? 4 : 3);
                addCommand(Arrays.asList(numArr2), new CharStringCommand(8));
            }
            if (i2 != 0) {
                i = 5;
            }
            list = list.subList(i, list.size());
        }
    }

    private void addCommandList(List<List<Integer>> list, CharStringCommand charStringCommand) {
        for (int i = 0; i < list.size(); i++) {
            addCommand(list.get(i), charStringCommand);
        }
    }

    private void addCommand(List<Integer> list, CharStringCommand charStringCommand) {
        this.sequence.addAll(list);
        this.sequence.add(charStringCommand);
    }

    private static <E> List<List<E>> split(List<E> list, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size() / i) {
            int i3 = i2 * i;
            i2++;
            arrayList.add(list.subList(i3, i2 * i));
        }
        return arrayList;
    }
}
