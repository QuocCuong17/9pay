package co.hyperverge.hypersnapsdk.helpers.xmlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class Tag {
    private ArrayList<Tag> mChildren = new ArrayList<>();
    private String mContent;
    private String mName;
    private String mPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tag(String str, String str2) {
        this.mPath = str;
        this.mName = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addChild(Tag tag) {
        this.mChildren.add(tag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContent(String str) {
        boolean z = false;
        if (str != null) {
            int i = 0;
            while (true) {
                if (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt != ' ' && charAt != '\n') {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        if (z) {
            this.mContent = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.mName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getContent() {
        return this.mContent;
    }

    ArrayList<Tag> getChildren() {
        return this.mChildren;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasChildren() {
        return this.mChildren.size() > 0;
    }

    int getChildrenCount() {
        return this.mChildren.size();
    }

    Tag getChild(int i) {
        if (i < 0 || i >= this.mChildren.size()) {
            return null;
        }
        return this.mChildren.get(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap<String, ArrayList<Tag>> getGroupedElements() {
        HashMap<String, ArrayList<Tag>> hashMap = new HashMap<>();
        Iterator<Tag> it = this.mChildren.iterator();
        while (it.hasNext()) {
            Tag next = it.next();
            String name = next.getName();
            ArrayList<Tag> arrayList = hashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                hashMap.put(name, arrayList);
            }
            arrayList.add(next);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPath() {
        return this.mPath;
    }

    public String toString() {
        return "Tag: " + this.mName + ", " + this.mChildren.size() + " children, Content: " + this.mContent;
    }
}
