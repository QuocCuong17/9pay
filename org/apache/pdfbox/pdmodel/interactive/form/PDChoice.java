package org.apache.pdfbox.pdmodel.interactive.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.interactive.form.FieldUtils;

/* loaded from: classes5.dex */
public abstract class PDChoice extends PDVariableText {
    public static final int FLAG_COMBO = 131072;
    private static final int FLAG_COMMIT_ON_SEL_CHANGE = 67108864;
    private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
    private static final int FLAG_MULTI_SELECT = 2097152;
    private static final int FLAG_SORT = 524288;

    public PDChoice(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getDictionary().setItem(COSName.FT, (COSBase) COSName.CH);
    }

    public PDChoice(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getDefaultValue() {
        return ((COSString) getInheritableAttribute(COSName.DV)).getString();
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setDefaultValue(String str) {
        if (str != null) {
            if (getOptions().indexOf(str) == -1) {
                throw new IllegalArgumentException("The list box does not contain the given value.");
            }
            getDictionary().setString(COSName.DV, str);
            return;
        }
        getDictionary().removeItem(COSName.DV);
    }

    public List<String> getOptions() {
        return FieldUtils.getPairableItems(getDictionary().getDictionaryObject(COSName.OPT), 0);
    }

    public void setOptions(List<String> list) {
        if (list != null && !list.isEmpty()) {
            if (isSort()) {
                Collections.sort(list);
            }
            getDictionary().setItem(COSName.OPT, (COSBase) COSArrayList.convertStringListToCOSStringCOSArray(list));
            return;
        }
        getDictionary().removeItem(COSName.OPT);
    }

    public void setOptions(List<String> list, List<String> list2) {
        if (list != null && list2 != null && !list.isEmpty() && !list2.isEmpty()) {
            if (list.size() != list2.size()) {
                throw new IllegalArgumentException("The number of entries for exportValue and displayValue shall be the same.");
            }
            List<FieldUtils.KeyValue> keyValueList = FieldUtils.toKeyValueList(list, list2);
            if (isSort()) {
                FieldUtils.sortByValue(keyValueList);
            }
            COSArray cOSArray = new COSArray();
            for (int i = 0; i < list.size(); i++) {
                COSArray cOSArray2 = new COSArray();
                cOSArray2.add((COSBase) new COSString(keyValueList.get(i).getKey()));
                cOSArray2.add((COSBase) new COSString(keyValueList.get(i).getValue()));
                cOSArray.add((COSBase) cOSArray2);
            }
            getDictionary().setItem(COSName.OPT, (COSBase) cOSArray);
            return;
        }
        getDictionary().removeItem(COSName.OPT);
    }

    public List<String> getOptionsDisplayValues() {
        return FieldUtils.getPairableItems(getDictionary().getDictionaryObject(COSName.OPT), 0);
    }

    public List<String> getOptionsExportValues() {
        return getOptions();
    }

    public List<Integer> getSelectedOptionsIndex() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject(COSName.I);
        if (dictionaryObject != null) {
            return COSArrayList.convertIntegerCOSArrayToList((COSArray) dictionaryObject);
        }
        return Collections.emptyList();
    }

    public void setSelectedOptionsIndex(List<Integer> list) {
        if (list != null && !list.isEmpty()) {
            if (!isMultiSelect()) {
                throw new IllegalArgumentException("Setting the indices is not allowed for choice fields not allowing multiple selections.");
            }
            getDictionary().setItem(COSName.I, (COSBase) COSArrayList.converterToCOSArray(list));
            return;
        }
        getDictionary().removeItem(COSName.I);
    }

    public boolean isSort() {
        return getDictionary().getFlag(COSName.FF, 524288);
    }

    public void setSort(boolean z) {
        getDictionary().setFlag(COSName.FF, 524288, z);
    }

    public boolean isMultiSelect() {
        return getDictionary().getFlag(COSName.FF, 2097152);
    }

    public void setMultiSelect(boolean z) {
        getDictionary().setFlag(COSName.FF, 2097152, z);
    }

    public boolean isDoNotSpellCheck() {
        return getDictionary().getFlag(COSName.FF, 4194304);
    }

    public void setDoNotSpellCheck(boolean z) {
        getDictionary().setFlag(COSName.FF, 4194304, z);
    }

    public boolean isCommitOnSelChange() {
        return getDictionary().getFlag(COSName.FF, 67108864);
    }

    public void setCommitOnSelChange(boolean z) {
        getDictionary().setFlag(COSName.FF, 67108864, z);
    }

    public boolean isCombo() {
        return getDictionary().getFlag(COSName.FF, 131072);
    }

    public void setCombo(boolean z) {
        getDictionary().setFlag(COSName.FF, 131072, z);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) {
        if (str != null) {
            if (getOptions().indexOf(str) == -1) {
                throw new IllegalArgumentException("The list box does not contain the given value.");
            }
            getDictionary().setString(COSName.V, str);
            setSelectedOptionsIndex(null);
            return;
        }
        getDictionary().removeItem(COSName.V);
    }

    public void setValue(List<String> list) {
        if (list != null && !list.isEmpty()) {
            if (!isMultiSelect()) {
                throw new IllegalArgumentException("The list box does not allow multiple selections.");
            }
            if (!getOptions().containsAll(list)) {
                throw new IllegalArgumentException("The values are not contained in the selectable options.");
            }
            getDictionary().setItem(COSName.V, (COSBase) COSArrayList.convertStringListToCOSStringCOSArray(list));
            updateSelectedOptionsIndex(list);
            return;
        }
        getDictionary().removeItem(COSName.V);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public List<String> getValue() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject(COSName.V);
        if (dictionaryObject instanceof COSString) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((COSString) dictionaryObject).getString());
            return arrayList;
        }
        if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSStringCOSArrayToList((COSArray) dictionaryObject);
        }
        return Collections.emptyList();
    }

    private void updateSelectedOptionsIndex(List<String> list) {
        List<String> options = getOptions();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(options.indexOf(it.next())));
        }
        Collections.sort(arrayList);
        setSelectedOptionsIndex(arrayList);
    }
}
