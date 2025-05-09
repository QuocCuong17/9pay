package net.sf.scuba.data;

/* loaded from: classes5.dex */
public class TestCountry extends Country {
    public static final TestCountry BP;
    public static final TestCountry DV;
    public static final TestCountry UT;
    private static final TestCountry[] VALUES;
    private static final long serialVersionUID = 6977914731950342700L;
    private String alpha2Code;
    private String alpha3Code;
    private int code;
    private String name;
    private String nationality;

    static {
        TestCountry testCountry = new TestCountry(-1, "UT", "UTO", "Utopia", "Utopian");
        UT = testCountry;
        TestCountry testCountry2 = new TestCountry(-1, "BP", "XBP", "BP", "BP");
        BP = testCountry2;
        TestCountry testCountry3 = new TestCountry(-1, "DV", "XDV", "DV", "DV");
        DV = testCountry3;
        VALUES = new TestCountry[]{testCountry, testCountry2, testCountry3};
    }

    private TestCountry() {
    }

    private TestCountry(int i, String str, String str2, String str3, String str4) {
        this.code = i;
        this.alpha2Code = str;
        this.alpha3Code = str2;
        this.name = str3;
        this.nationality = str4;
    }

    public static TestCountry[] values() {
        return VALUES;
    }

    @Override // net.sf.scuba.data.Country
    public int valueOf() {
        return this.code;
    }

    @Override // net.sf.scuba.data.Country
    public String getName() {
        return this.name;
    }

    @Override // net.sf.scuba.data.Country
    public String getNationality() {
        return this.nationality;
    }

    @Override // net.sf.scuba.data.Country
    public String toAlpha2Code() {
        return this.alpha2Code;
    }

    @Override // net.sf.scuba.data.Country
    public String toAlpha3Code() {
        return this.alpha3Code;
    }

    public String toString() {
        return this.alpha2Code;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass().equals(obj.getClass())) {
            return this.alpha3Code.equals(((TestCountry) obj).alpha3Code);
        }
        return false;
    }

    public int hashCode() {
        return (this.alpha3Code.hashCode() * 2) + 31;
    }
}
