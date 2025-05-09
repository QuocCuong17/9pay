package org.jmrtd.lds.icao;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.data.Country;

/* loaded from: classes6.dex */
public class ICAOCountry extends Country {
    public static final ICAOCountry DE;
    public static final ICAOCountry EUE;
    public static final ICAOCountry GBD;
    public static final ICAOCountry GBN;
    public static final ICAOCountry GBO;
    public static final ICAOCountry GBP;
    public static final ICAOCountry GBS;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final ICAOCountry UNA;
    public static final ICAOCountry UNK;
    public static final ICAOCountry UNO;
    private static final ICAOCountry[] VALUES;
    public static final ICAOCountry XBA;
    public static final ICAOCountry XCC;
    public static final ICAOCountry XCO;
    public static final ICAOCountry XEC;
    public static final ICAOCountry XIM;
    public static final ICAOCountry XOM;
    public static final ICAOCountry XPO;
    public static final ICAOCountry XXA;
    public static final ICAOCountry XXB;
    public static final ICAOCountry XXC;
    public static final ICAOCountry XXX;
    private static final long serialVersionUID = 2942942609311086138L;
    private String alpha2Code;
    private String alpha3Code;
    private String name;
    private String nationality;

    @Override // net.sf.scuba.data.Country
    public int valueOf() {
        return -1;
    }

    static {
        ICAOCountry iCAOCountry = new ICAOCountry("DE", "D<<", "Germany", "German");
        DE = iCAOCountry;
        ICAOCountry iCAOCountry2 = new ICAOCountry("GB", "GBD", "British Dependent territories citizen");
        GBD = iCAOCountry2;
        ICAOCountry iCAOCountry3 = new ICAOCountry("GB", "GBN", "British National (Overseas)");
        GBN = iCAOCountry3;
        ICAOCountry iCAOCountry4 = new ICAOCountry("GB", "GBO", "British Overseas citizen");
        GBO = iCAOCountry4;
        ICAOCountry iCAOCountry5 = new ICAOCountry("GB", "GBP", "British Protected person");
        GBP = iCAOCountry5;
        ICAOCountry iCAOCountry6 = new ICAOCountry("GB", "GBS", "British Subject");
        GBS = iCAOCountry6;
        ICAOCountry iCAOCountry7 = new ICAOCountry("XX", "XXA", "Stateless person", "Stateless");
        XXA = iCAOCountry7;
        ICAOCountry iCAOCountry8 = new ICAOCountry("XX", "XXB", "Refugee", "Refugee");
        XXB = iCAOCountry8;
        ICAOCountry iCAOCountry9 = new ICAOCountry("XX", "XXC", "Refugee (other)", "Refugee (other)");
        XXC = iCAOCountry9;
        ICAOCountry iCAOCountry10 = new ICAOCountry("XX", "XXX", "Unspecified", "Unspecified");
        XXX = iCAOCountry10;
        ICAOCountry iCAOCountry11 = new ICAOCountry("EU", "EUE", "Europe", "European");
        EUE = iCAOCountry11;
        ICAOCountry iCAOCountry12 = new ICAOCountry("UN", "UNO", "United Nations Organization");
        UNO = iCAOCountry12;
        ICAOCountry iCAOCountry13 = new ICAOCountry("UN", "UNA", "United Nations Agency");
        UNA = iCAOCountry13;
        ICAOCountry iCAOCountry14 = new ICAOCountry("UN", "UNK", "United Nations Interim Administration Mission in Kosovo");
        UNK = iCAOCountry14;
        ICAOCountry iCAOCountry15 = new ICAOCountry("XX", "XBA", "African Development Bank (ADB)");
        XBA = iCAOCountry15;
        ICAOCountry iCAOCountry16 = new ICAOCountry("XX", "XIM", "African Export-Import Bank (AFREXIM bank)");
        XIM = iCAOCountry16;
        ICAOCountry iCAOCountry17 = new ICAOCountry("XC", "XCC", "Carribean Community or one of its emissaries (CARICOM)");
        XCC = iCAOCountry17;
        ICAOCountry iCAOCountry18 = new ICAOCountry("XX", "XCO", "Common Market for Eastern an Southern Africa (COMESA)");
        XCO = iCAOCountry18;
        ICAOCountry iCAOCountry19 = new ICAOCountry("XX", "XEC", "Economic Community of West African States (ECOWAS)");
        XEC = iCAOCountry19;
        ICAOCountry iCAOCountry20 = new ICAOCountry("XP", "XPO", "International Criminal Police Organization (INTERPOL)");
        XPO = iCAOCountry20;
        ICAOCountry iCAOCountry21 = new ICAOCountry("XO", "XOM", "Sovereign Military Order of Malta or one of its emissaries");
        XOM = iCAOCountry21;
        VALUES = new ICAOCountry[]{iCAOCountry, iCAOCountry2, iCAOCountry3, iCAOCountry4, iCAOCountry5, iCAOCountry6, iCAOCountry7, iCAOCountry8, iCAOCountry9, iCAOCountry10, iCAOCountry11, iCAOCountry12, iCAOCountry13, iCAOCountry14, iCAOCountry15, iCAOCountry16, iCAOCountry17, iCAOCountry18, iCAOCountry19, iCAOCountry20, iCAOCountry21};
    }

    private ICAOCountry() {
    }

    private ICAOCountry(String str, String str2, String str3) {
        this(str, str2, str3, str3);
    }

    private ICAOCountry(String str, String str2, String str3, String str4) {
        this.alpha2Code = str;
        this.alpha3Code = str2;
        this.name = str3;
        this.nationality = str4;
    }

    public static Country getInstance(String str) {
        for (ICAOCountry iCAOCountry : VALUES) {
            if (iCAOCountry.alpha3Code.equals(str)) {
                return iCAOCountry;
            }
        }
        try {
            return Country.getInstance(str);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Unknown country", (Throwable) e);
            throw new IllegalArgumentException("Illegal ICAO country alpha 3 code " + str);
        }
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
}
