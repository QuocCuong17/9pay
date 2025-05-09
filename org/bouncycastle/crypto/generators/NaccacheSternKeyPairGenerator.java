package org.bouncycastle.crypto.generators;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import io.grpc.internal.GrpcUtil;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.BigIntegers;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes5.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, ISO781611.CREATION_DATE_AND_TIME_TAG, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 139, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, 151, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, BaselineTIFFTagSet.TAG_THRESHHOLDING, BaselineTIFFTagSet.TAG_DOCUMENT_NAME, BaselineTIFFTagSet.TAG_MAKE, BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL, BaselineTIFFTagSet.TAG_MAX_SAMPLE_VALUE, BaselineTIFFTagSet.TAG_Y_RESOLUTION, BaselineTIFFTagSet.TAG_T6_OPTIONS, 307, 311, 313, 317, 331, BaselineTIFFTagSet.TAG_TARGET_PRINTER, BaselineTIFFTagSet.TAG_JPEG_TABLES, 349, 353, 359, 367, 373, 379, 383, 389, 397, TypedValues.CycleType.TYPE_CURVE_FIT, 409, 419, 421, 431, 433, 439, GrpcUtil.DEFAULT_PORT_SSL, 449, 457, 461, 463, 467, 479, 487, 491, 499, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 509, BaselineTIFFTagSet.TAG_JPEG_AC_TABLES, 523, 541, 547, 557};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i2]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger createRandomPrime;
        do {
            createRandomPrime = BigIntegers.createRandomPrime(i, i2, secureRandom);
        } while (createRandomPrime.bitLength() != i);
        return createRandomPrime;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger generatePrime2;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger add2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigInteger7;
        boolean z;
        BigInteger bigInteger8;
        BigInteger bigInteger9;
        int i;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        long j2;
        BigInteger createRandomPrime;
        int i2;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger10 = ONE;
        BigInteger bigInteger11 = bigInteger10;
        for (int i3 = 0; i3 < permuteList.size() / 2; i3++) {
            bigInteger11 = bigInteger11.multiply((BigInteger) permuteList.elementAt(i3));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger10 = bigInteger10.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger11.multiply(bigInteger10);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger11).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger10).shiftLeft(1);
        long j3 = 0;
        while (true) {
            j = j3 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (add.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    BigInteger multiply2 = generatePrime2.multiply(shiftLeft2);
                    bigInteger = shiftLeft2;
                    bigInteger2 = ONE;
                    add2 = multiply2.add(bigInteger2);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    shiftLeft2 = bigInteger;
                }
                bigInteger3 = shiftLeft;
                if (!multiply.gcd(generatePrime.multiply(generatePrime2)).equals(bigInteger2)) {
                    continue;
                } else {
                    if (add.multiply(add2).bitLength() >= strength) {
                        break;
                    }
                    if (isDebug) {
                        System.out.println("key size too small. Should be " + strength + " but is actually " + add.multiply(add2).bitLength());
                    }
                }
            } else {
                bigInteger = shiftLeft2;
                bigInteger3 = shiftLeft;
            }
            j3 = j;
            shiftLeft2 = bigInteger;
            shiftLeft = bigInteger3;
        }
        BigInteger bigInteger12 = generatePrime4;
        if (isDebug) {
            bigInteger4 = generatePrime3;
            System.out.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger4 = generatePrime3;
        }
        BigInteger multiply3 = add.multiply(add2);
        BigInteger multiply4 = add.subtract(bigInteger2).multiply(add2.subtract(bigInteger2));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j4 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger5 = add;
            bigInteger6 = add2;
            int i4 = 0;
            while (i4 != permuteList.size()) {
                BigInteger divide = multiply4.divide((BigInteger) permuteList.elementAt(i4));
                while (true) {
                    j2 = j4 + 1;
                    createRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    i2 = strength;
                    if (createRandomPrime.modPow(divide, multiply3).equals(ONE)) {
                        j4 = j2;
                        strength = i2;
                    }
                }
                vector.addElement(createRandomPrime);
                i4++;
                j4 = j2;
                strength = i2;
            }
            int i5 = strength;
            bigInteger7 = ONE;
            int i6 = 0;
            while (i6 < permuteList.size()) {
                bigInteger7 = bigInteger7.multiply(((BigInteger) vector.elementAt(i6)).modPow(multiply.divide((BigInteger) permuteList.elementAt(i6)), multiply3)).mod(multiply3);
                i6++;
                random = random;
            }
            SecureRandom secureRandom = random;
            int i7 = 0;
            while (true) {
                if (i7 >= permuteList.size()) {
                    z = false;
                    break;
                }
                if (bigInteger7.modPow(multiply4.divide((BigInteger) permuteList.elementAt(i7)), multiply3).equals(ONE)) {
                    if (isDebug) {
                        System.out.println("g has order phi(n)/" + permuteList.elementAt(i7) + "\n g: " + bigInteger7);
                    }
                    z = true;
                } else {
                    i7++;
                }
            }
            if (!z) {
                BigInteger modPow = bigInteger7.modPow(multiply4.divide(BigInteger.valueOf(4L)), multiply3);
                BigInteger bigInteger13 = ONE;
                if (modPow.equals(bigInteger13)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/4\n g:";
                        sb.append(str);
                        sb.append(bigInteger7);
                        printStream.println(sb.toString());
                    }
                } else if (bigInteger7.modPow(multiply4.divide(generatePrime), multiply3).equals(bigInteger13)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/p'\n g: ";
                        sb.append(str);
                        sb.append(bigInteger7);
                        printStream.println(sb.toString());
                    }
                } else if (!bigInteger7.modPow(multiply4.divide(generatePrime2), multiply3).equals(bigInteger13)) {
                    bigInteger8 = bigInteger4;
                    if (!bigInteger7.modPow(multiply4.divide(bigInteger8), multiply3).equals(bigInteger13)) {
                        bigInteger9 = bigInteger12;
                        if (!bigInteger7.modPow(multiply4.divide(bigInteger9), multiply3).equals(bigInteger13)) {
                            break;
                        }
                        if (isDebug) {
                            PrintStream printStream2 = System.out;
                            StringBuilder sb2 = new StringBuilder();
                            i = certainty;
                            sb2.append("g has order phi(n)/b\n g: ");
                            sb2.append(bigInteger7);
                            printStream2.println(sb2.toString());
                        }
                    } else {
                        if (isDebug) {
                            System.out.println("g has order phi(n)/a\n g: " + bigInteger7);
                        }
                        bigInteger9 = bigInteger12;
                    }
                    i = certainty;
                } else if (isDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/q'\n g: ";
                    sb.append(str);
                    sb.append(bigInteger7);
                    printStream.println(sb.toString());
                }
                bigInteger4 = bigInteger8;
                certainty = i;
                add2 = bigInteger6;
                add = bigInteger5;
                strength = i5;
                random = secureRandom;
                bigInteger12 = bigInteger9;
            }
            bigInteger9 = bigInteger12;
            bigInteger8 = bigInteger4;
            i = certainty;
            bigInteger4 = bigInteger8;
            certainty = i;
            add2 = bigInteger6;
            add = bigInteger5;
            strength = i5;
            random = secureRandom;
            bigInteger12 = bigInteger9;
        }
        if (isDebug) {
            System.out.println("needed " + j4 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + permuteList);
            System.out.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream3 = System.out;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("a:.......... ");
            sb3.append(bigInteger8);
            printStream3.println(sb3.toString());
            System.out.println("b:.......... " + bigInteger9);
            System.out.println("p':......... " + generatePrime);
            System.out.println("q':......... " + generatePrime2);
            System.out.println("p:.......... " + bigInteger5);
            System.out.println("q:.......... " + bigInteger6);
            System.out.println("n:.......... " + multiply3);
            System.out.println("phi(n):..... " + multiply4);
            System.out.println("g:.......... " + bigInteger7);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger7, multiply3, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger7, multiply3, multiply.bitLength(), permuteList, multiply4));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
