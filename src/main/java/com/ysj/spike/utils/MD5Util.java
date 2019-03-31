package com.ysj.spike.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassFromPass(String inputPass) {
        String str = salt.charAt(0)+salt.charAt(1)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String formPassTODBPass(String formPass,String salt) {
        String str = salt.charAt(0)+salt.charAt(1)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String input,String salt) {
        String fromPass = inputPassFromPass(input);
        String dbPass = formPassTODBPass(fromPass,salt);
        return dbPass;
    }
}
