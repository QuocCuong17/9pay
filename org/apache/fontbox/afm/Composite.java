package org.apache.fontbox.afm;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Composite {
    private String name;
    private List<CompositePart> parts = new ArrayList();

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void addPart(CompositePart compositePart) {
        this.parts.add(compositePart);
    }

    public List<CompositePart> getParts() {
        return this.parts;
    }

    public void setParts(List<CompositePart> list) {
        this.parts = list;
    }
}
