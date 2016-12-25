package cn.edu.gdmec.s07150843.myguard.m9advancedtools.utils;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * Created by chen on 2016/12/20.
 */
public class Crypto {

    public static String encrypt(String seed,String plain) throws Exception{

        byte[] rawKey=getRawKey(seed.getBytes());

        byte[] encrypte=encrypt(rawKey,plain.getBytes());
        return Base64.encodeToString(encrypte,Base64.DEFAULT);
    }

    public static String decrypt(String seed,String encrypted)throws Exception{
        byte[] rawKey=getRawKey(seed.getBytes());
        byte[] enc=Base64.decode(encrypted.getBytes(),Base64.DEFAULT);
        byte[] result=decrypt(rawKey,enc);
        return new String(result);
    }
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator keygen=KeyGenerator.getInstance("AES");
        SecureRandom random=SecureRandom.getInstance("SHAlPRNG");
        random.setSeed(seed);
        keygen.init(128,random);
        SecretKey key=keygen.generateKey();
        byte[] raw=key.getEncoded();
        return raw;

    }
    private static byte[] encrypt(byte[] raw, byte[] plain)throws Exception {
        SecretKeySpec keySpec=new SecretKeySpec(raw,"AES");
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        byte[] encrypted=cipher.doFinal(plain);
        return encrypted;
    }
    private static byte[] decrypt(byte[] raw,byte[] encrypted) throws Exception{
        SecretKeySpec keySpec=new SecretKeySpec(raw,"AES");
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec);
        byte[]decrypted=cipher.doFinal(encrypted);
        return decrypted;
    }
}
