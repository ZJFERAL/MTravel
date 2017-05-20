package com.mcy.mtravel.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by machengyuan on 2017/4/12.
 */

public class EncryptUtils {
    public static String encode(String paramString) {
        try {
            Mac localMac = Mac.getInstance("HmacSHA1");
            localMac.init(new SecretKeySpec("reVzSGlbIVHEUJ8f3BPQlcU0Bw1ENHITJQB3KCMpaRbjd/6kdA/DUw==".getBytes("UTF-8"), "HmacSHA1"));
            paramString = new String(encodeHex(localMac.doFinal(paramString.getBytes("UTF-8"))));
            return paramString;
        } catch (Exception e) {

        }
        return null;
    }

    protected static char[] encodeHex(byte[] paramArrayOfByte) {
        char[] arrayOfChar1 = new char[16];
        char[] tmp6_5 = arrayOfChar1;
        tmp6_5[0] = 48;
        char[] tmp11_6 = tmp6_5;
        tmp11_6[1] = 49;
        char[] tmp16_11 = tmp11_6;
        tmp16_11[2] = 50;
        char[] tmp21_16 = tmp16_11;
        tmp21_16[3] = 51;
        char[] tmp26_21 = tmp21_16;
        tmp26_21[4] = 52;
        char[] tmp31_26 = tmp26_21;
        tmp31_26[5] = 53;
        char[] tmp36_31 = tmp31_26;
        tmp36_31[6] = 54;
        char[] tmp42_36 = tmp36_31;
        tmp42_36[7] = 55;
        char[] tmp48_42 = tmp42_36;
        tmp48_42[8] = 56;
        char[] tmp54_48 = tmp48_42;
        tmp54_48[9] = 57;
        char[] tmp60_54 = tmp54_48;
        tmp60_54[10] = 97;
        char[] tmp66_60 = tmp60_54;
        tmp66_60[11] = 98;
        char[] tmp72_66 = tmp66_60;
        tmp72_66[12] = 99;
        char[] tmp78_72 = tmp72_66;
        tmp78_72[13] = 100;
        char[] tmp84_78 = tmp78_72;
        tmp84_78[14] = 101;
        char[] tmp90_84 = tmp84_78;
        tmp90_84[15] = 102;
        int k = paramArrayOfByte.length;
        char[] arrayOfChar2 = new char[k << 1];
        int i = 0;
        int j = 0;
        while (i < k) {
            int m = j + 1;
            arrayOfChar2[j] = arrayOfChar1[((paramArrayOfByte[i] & 0xF0) >>> 4)];
            j = m + 1;
            arrayOfChar2[m] = arrayOfChar1[(paramArrayOfByte[i] & 0xF)];
            i += 1;
        }
        return arrayOfChar2;
    }
}
