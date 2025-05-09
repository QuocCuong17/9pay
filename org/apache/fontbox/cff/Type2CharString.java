package org.apache.fontbox.cff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.fontbox.type1.Type1CharStringReader;

/* loaded from: classes5.dex */
public class Type2CharString extends Type1CharString {
    private int defWidthX;
    private final int gid;
    private int nominalWidthX;
    private int pathCount;
    private final List<Object> type2sequence;

    private void expandStemHints(List<Integer> list, boolean z) {
    }

    public Type2CharString(Type1CharStringReader type1CharStringReader, String str, String str2, int i, List<Object> list, int i2, int i3) {
        super(type1CharStringReader, str, str2);
        this.defWidthX = 0;
        this.nominalWidthX = 0;
        this.pathCount = 0;
        this.gid = i;
        this.type2sequence = list;
        this.defWidthX = i2;
        this.nominalWidthX = i3;
        convertType1ToType2(list);
    }

    public int getGID() {
        return this.gid;
    }

    public List<Object> getType2Sequence() {
        return this.type2sequence;
    }

    private void convertType1ToType2(List<Object> list) {
        this.type1Sequence = new ArrayList();
        this.pathCount = 0;
        new CharStringHandler() { // from class: org.apache.fontbox.cff.Type2CharString.1
            @Override // org.apache.fontbox.cff.CharStringHandler
            public List<Integer> handleCommand(List<Integer> list2, CharStringCommand charStringCommand) {
                return Type2CharString.this.handleCommand(list2, charStringCommand);
            }
        }.handleSequence(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Integer> handleCommand(List<Integer> list, CharStringCommand charStringCommand) {
        this.commandCount++;
        String str = CharStringCommand.TYPE2_VOCABULARY.get(charStringCommand.getKey());
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
            if (list.size() != 5 && list.size() != 1) {
                r1 = false;
            }
            List<Integer> clearStack2 = clearStack(list, r1);
            closePath();
            if (clearStack2.size() == 4) {
                clearStack2.add(0, 0);
                addCommand(clearStack2, new CharStringCommand(12, 6));
                return null;
            }
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
            numArr[4] = Integer.valueOf(Math.abs(i) > Math.abs(i2) ? list.get(10).intValue() : -i);
            numArr[5] = Integer.valueOf(Math.abs(i) > Math.abs(i2) ? -i2 : list.get(10).intValue());
            addCommandList(Arrays.asList(subList, Arrays.asList(numArr)), new CharStringCommand(8));
            return null;
        }
        if ("hstemhm".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), true);
            return null;
        }
        if ("hintmask".equals(str) || "cntrmask".equals(str)) {
            List<Integer> clearStack5 = clearStack(list, list.size() % 2 != 0);
            if (clearStack5.size() <= 0) {
                return null;
            }
            expandStemHints(clearStack5, false);
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
        if (!this.type1Sequence.isEmpty()) {
            return list;
        }
        if (z) {
            addCommand(Arrays.asList(0, Integer.valueOf(list.get(0).intValue() + this.nominalWidthX)), new CharStringCommand(13));
            return list.subList(1, list.size());
        }
        addCommand(Arrays.asList(0, Integer.valueOf(this.defWidthX)), new CharStringCommand(13));
        return list;
    }

    private void markPath() {
        if (this.pathCount > 0) {
            closePath();
        }
        this.pathCount++;
    }

    private void closePath() {
        CharStringCommand charStringCommand = this.pathCount > 0 ? (CharStringCommand) this.type1Sequence.get(this.type1Sequence.size() - 1) : null;
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
                numArr[4] = Integer.valueOf(z2 ? list.get(4).intValue() : 0);
                numArr[5] = list.get(3);
                addCommand(Arrays.asList(numArr), new CharStringCommand(8));
            } else {
                Integer[] numArr2 = new Integer[6];
                numArr2[0] = 0;
                numArr2[1] = list.get(0);
                numArr2[2] = list.get(1);
                numArr2[3] = list.get(2);
                numArr2[4] = list.get(3);
                numArr2[5] = Integer.valueOf(z2 ? list.get(4).intValue() : 0);
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
        while (list.size() > 0) {
            int i = 4;
            int i2 = list.size() % 4 == 1 ? 1 : 0;
            if (z) {
                Integer[] numArr = new Integer[6];
                numArr[0] = list.get(i2);
                numArr[1] = Integer.valueOf(i2 != 0 ? list.get(0).intValue() : 0);
                numArr[2] = list.get(i2 != 0 ? 2 : 1);
                numArr[3] = list.get(i2 != 0 ? 3 : 2);
                numArr[4] = list.get(i2 != 0 ? 4 : 3);
                numArr[5] = 0;
                addCommand(Arrays.asList(numArr), new CharStringCommand(8));
            } else {
                Integer[] numArr2 = new Integer[6];
                numArr2[0] = Integer.valueOf(i2 != 0 ? list.get(0).intValue() : 0);
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
        Iterator<List<Integer>> it = list.iterator();
        while (it.hasNext()) {
            addCommand(it.next(), charStringCommand);
        }
    }

    private void addCommand(List<Integer> list, CharStringCommand charStringCommand) {
        this.type1Sequence.addAll(list);
        this.type1Sequence.add(charStringCommand);
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
