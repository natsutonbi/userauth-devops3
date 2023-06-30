package com.example.demo.user.utils;
import java.security.MessageDigest;
import java.util.Random;

public class MD5SaltPwd {
    /**
     * byte[]字节数组 => 十六进制字符串
     * 
     * @param arr 要转换的byte[]字节数组
     *
     * @return String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
	    StringBuffer sb=new StringBuffer();
	    for(byte arr_unit:arr){
		    sb.append(Integer.toHexString((arr_unit & 0xFF) | 0x100).substring(1, 3));
	    }
	    return sb.toString();
    }

    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     * 
     * @param str 要加密的内容
     * 
     * @return String 返回加密后的十六进制字符串
     */
    public static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /*
     * 生成char[16]salt
     * 
     * @return salt
     */
    public static String getSalt() {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
              for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String salt = sBuilder.toString();
        return salt;
    }
   /**
     * 生成含有随机盐的密码
     *
     * @param password 要加密的密码
     *
     * @return String 含有随机盐的密码
     */
    
     //md5生成128bit，转16进制字符串就是char[32]
    public static String getSaltedPwd(String password,String salt){
        return md5Hex(password+salt);
    }

   /**
     * 验证加盐后是否和原密码一致
     * 
     * @param password 原密码
     * @param salt 盐值
     * @param saltedPwd 加密之后的密码
     * 
     *@return boolean true表示和原密码一致   false表示和原密码不一致
     */
    public static boolean verify(String password, String salt,String saltedPwd) {
        return getSaltedPwd(password,salt).equals(String.valueOf(saltedPwd));
    }
}
