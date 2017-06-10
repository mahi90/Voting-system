/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingclient;

import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author moktan
 */
public class MsgSecure{
     public static String EncryptFormat(String message)
    {
        while(message.length() < 16)
        {
            message += " ";
        }
        
        return message;
    }
    public static String NormalFormat(String message)    {
        
        String empty = " ";
        byte[] emptyByte = empty.getBytes();
        
        byte[] bytes = message.getBytes();
        int n = 0;
        
        for(int i = 0; i < bytes.length; i++)
        {
            if(bytes[i] != emptyByte[0]) n++;
        }
        
        byte[] result = new byte[n];
        
        for(int i = 0; i < result.length; i++)
        {
            result[i] = bytes[i];
        }
        
        return new String(result);
    }
    
    public static byte[] Encrypt(String message, String ekey)
    {
        try
        {           
            if(ekey.length() != 16)  return null;
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
            byte[] input = EncryptFormat(message).getBytes();           
            byte[] keyBytes = ekey.getBytes();            
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
             byte[] cipherText = new byte[input.length];
             cipher.init(Cipher.ENCRYPT_MODE, key);
             int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
              ctLength += cipher.doFinal(cipherText, ctLength);              
              return cipherText;              
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
    public static String Decrypt(byte[] cipherText, String ekey)
    {
        try
        {
            if(ekey.length() != 16) return "";
            //System.out.println("Cipher text length: " + cipherText.length());
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
            byte[] keyBytes = ekey.getBytes();
           // byte[] cipherTextAsBytes = cipherText.getBytes();
           byte[] plainText = new byte[16];           
           SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
           
           cipher.init(Cipher.DECRYPT_MODE, key);
          
           int ptLength = cipher.update(cipherText, 0, cipherText.length, plainText, 0);
            
           ptLength += cipher.doFinal(plainText, ptLength);           
            System.out.println("Decryption successful");
           return NormalFormat(new String(plainText));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }
    
}

    

