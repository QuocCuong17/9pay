package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.sessions.settings.RemoteSettings;

/* loaded from: classes4.dex */
public class ChildKey implements Comparable<ChildKey> {
    private final String key;
    public static final String MIN_KEY_NAME = "[MIN_NAME]";
    private static final ChildKey MIN_KEY = new ChildKey(MIN_KEY_NAME);
    public static final String MAX_KEY_NAME = "[MAX_KEY]";
    private static final ChildKey MAX_KEY = new ChildKey(MAX_KEY_NAME);
    private static final ChildKey PRIORITY_CHILD_KEY = new ChildKey(".priority");
    private static final ChildKey INFO_CHILD_KEY = new ChildKey(".info");

    protected int intValue() {
        return 0;
    }

    protected boolean isInt() {
        return false;
    }

    public static ChildKey getMinName() {
        return MIN_KEY;
    }

    public static ChildKey getMaxName() {
        return MAX_KEY;
    }

    public static ChildKey getPriorityKey() {
        return PRIORITY_CHILD_KEY;
    }

    public static ChildKey getInfoKey() {
        return INFO_CHILD_KEY;
    }

    private ChildKey(String str) {
        this.key = str;
    }

    public String asString() {
        return this.key;
    }

    public boolean isPriorityChildName() {
        return equals(PRIORITY_CHILD_KEY);
    }

    @Override // java.lang.Comparable
    public int compareTo(ChildKey childKey) {
        if (this == childKey) {
            return 0;
        }
        if (this.key.equals(MIN_KEY_NAME) || childKey.key.equals(MAX_KEY_NAME)) {
            return -1;
        }
        if (childKey.key.equals(MIN_KEY_NAME) || this.key.equals(MAX_KEY_NAME)) {
            return 1;
        }
        if (isInt()) {
            if (!childKey.isInt()) {
                return -1;
            }
            int compareInts = Utilities.compareInts(intValue(), childKey.intValue());
            return compareInts == 0 ? Utilities.compareInts(this.key.length(), childKey.key.length()) : compareInts;
        }
        if (childKey.isInt()) {
            return 1;
        }
        return this.key.compareTo(childKey.key);
    }

    public String toString() {
        return "ChildKey(\"" + this.key + "\")";
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ChildKey)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.key.equals(((ChildKey) obj).key);
    }

    public static ChildKey fromString(String str) {
        Integer tryParseInt = Utilities.tryParseInt(str);
        if (tryParseInt != null) {
            return new IntegerChildKey(str, tryParseInt.intValue());
        }
        if (str.equals(".priority")) {
            return PRIORITY_CHILD_KEY;
        }
        Utilities.hardAssert(!str.contains(RemoteSettings.FORWARD_SLASH_STRING));
        return new ChildKey(str);
    }

    /* loaded from: classes4.dex */
    private static class IntegerChildKey extends ChildKey {
        private final int intValue;

        @Override // com.google.firebase.database.snapshot.ChildKey
        protected boolean isInt() {
            return true;
        }

        @Override // com.google.firebase.database.snapshot.ChildKey, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(ChildKey childKey) {
            return super.compareTo(childKey);
        }

        IntegerChildKey(String str, int i) {
            super(str);
            this.intValue = i;
        }

        @Override // com.google.firebase.database.snapshot.ChildKey
        protected int intValue() {
            return this.intValue;
        }

        @Override // com.google.firebase.database.snapshot.ChildKey
        public String toString() {
            return "IntegerChildName(\"" + ((ChildKey) this).key + "\")";
        }
    }
}
