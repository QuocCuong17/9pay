package com.beust.jcommander;

import com.beust.jcommander.FuzzyMap;

/* loaded from: classes2.dex */
public class StringKey implements FuzzyMap.IKey {
    private String m_name;

    public StringKey(String str) {
        this.m_name = str;
    }

    @Override // com.beust.jcommander.FuzzyMap.IKey
    public String getName() {
        return this.m_name;
    }

    public String toString() {
        return this.m_name;
    }

    public int hashCode() {
        String str = this.m_name;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StringKey stringKey = (StringKey) obj;
        String str = this.m_name;
        if (str == null) {
            if (stringKey.m_name != null) {
                return false;
            }
        } else if (!str.equals(stringKey.m_name)) {
            return false;
        }
        return true;
    }
}
