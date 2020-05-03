package com.boxun.estms.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ThreeDESUtil {
	private static final  byte[] key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7".getBytes();
	private static final byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
	// 算法名称 
    public static final String KEY_ALGORITHM = "desede";
    // 算法名称/加密模式/填充方式 
    public static final String CIPHER_ALGORITHM = "desede/CBC/NoPadding";

    /** 
     * CBC加密 
     * @param key 密钥 
     * @param keyiv IV 
     * @param data 明文 
     * @return Base64编码的密文 
     * @throws Exception 
     */
    public static byte[] des3EncodeCBC(byte[] data) throws Exception {
        Security.addProvider(new BouncyCastleProvider()); 
        Key deskey = keyGenerator(new String(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
//        for (int k = 0; k < bOut.length; k++) {
//            System.out.print(bOut[k] + " ");
//        }
//        System.out.println("");
        return bOut;
    }

    /** 
     *   
     * 生成密钥key对象 
     * @param KeyStr 密钥字符串 
     * @return 密钥对象 
     * @throws InvalidKeyException   
     * @throws NoSuchAlgorithmException   
     * @throws InvalidKeySpecException   
     * @throws Exception 
     */
    private static Key keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }
 
    // 从十六进制字符串到字节数组转换 
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /** 
     * CBC解密 
     * @param key 密钥 
     * @param keyiv IV 
     * @param data Base64编码的密文 
     * @return 明文 
     * @throws Exception 
     */
    public static byte[] des3DecodeCBC(byte[] data) throws Exception {
        Key deskey = keyGenerator(new String(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    
    public static String des3DecodeCBC(String value) throws Exception {
    	byte[] data = new sun.misc.BASE64Decoder().decodeBuffer(value);// Encoder().
		byte[] result = des3EncodeCBC(data);
		//return (new String(result, "UTF-8"));
		return (new String(result));
    }
    
    public static String des3EncodeCBC(String value){
    	try {
			byte[] data = value.getBytes("UTF-8");
			byte[] result = des3EncodeCBC(data);
			return new sun.misc.BASE64Encoder().encode(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    }

    public static void main(String[] args) throws Exception {
       
        
        //byte[] data = "amigoxie".getBytes("UTF-8");
    	String aa = des3EncodeCBC("amigoxie");
        System.out.println(aa);
        String bb = des3DecodeCBC(aa);
        System.out.println(bb);
        //System.out.println("data.length=" + data.length);
        //byte[] str5 = des3EncodeCBC(data);
       // System.out.println(new sun.misc.BASE64Encoder().encode(str5));

        //byte[] str6 = des3DecodeCBC(key, keyiv, str5);
       // System.out.println(new String(str6, "UTF-8"));
    }
}
