package net.sf.scuba.data;

/* loaded from: classes5.dex */
public enum Gender {
    MALE { // from class: net.sf.scuba.data.Gender.1
        @Override // net.sf.scuba.data.Gender
        public int toInt() {
            return 1;
        }
    },
    FEMALE { // from class: net.sf.scuba.data.Gender.2
        @Override // net.sf.scuba.data.Gender
        public int toInt() {
            return 2;
        }
    },
    UNKNOWN { // from class: net.sf.scuba.data.Gender.3
        @Override // net.sf.scuba.data.Gender
        public int toInt() {
            return 255;
        }
    },
    UNSPECIFIED { // from class: net.sf.scuba.data.Gender.4
        @Override // net.sf.scuba.data.Gender
        public int toInt() {
            return 0;
        }
    };

    public abstract int toInt();

    public static Gender getInstance(int i) {
        for (Gender gender : values()) {
            if (gender.toInt() == i) {
                return gender;
            }
        }
        return UNKNOWN;
    }
}
