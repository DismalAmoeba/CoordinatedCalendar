/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountprotect;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import GetData.GetData;

/**
 *
 * @author towns
 */
public class AccountProtect {
    //Datbase should store --> username, salt value, and generated hash
    public static void main(String[] args) throws Exception  {
       
//        // Prompt for credentials
//        Scanner input = new Scanner(System.in); 
//        System.out.println("Please create a username");
//        String username = input.nextLine();
//        System.out.println("Please enter a password: ");
//        String data = input.nextLine();
//        //Hash code output size (32 bytes) using hash algorithm SHA-256
//        String algorithm = "SHA-256";
//        byte[] salt = createSalt();
//       System.out.println("SHA-256 Hash: " + generateHash(data, algorithm, salt)); 
        
       //ConnectToSQLServer
       GetData result = new GetData();
       try {
           System.out.println(result.getDataFromDataBase());
       } catch (Exception e) {
    }
    }

    private static String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException  {
       //MessageDigest of value assigned to String algorithm --> Java security class
       MessageDigest digest = MessageDigest.getInstance(algorithm);
       //SALT
       digest.reset();
       //update digest with salt in order to eliminate identical hashes
       digest.update(salt);
      byte[] hash = digest.digest(data.getBytes());
      return bytesToStringHex(hash);
            
    }
          private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

          public static String bytesToStringHex(byte[] bytes) {
              char[] hexChars = new char[bytes.length *2];
              for (int j = 0; j < bytes.length; j++) {
                  int v = bytes[j] & 0xFF;
                  hexChars[j *2] = hexArray[v >>> 4];
                  hexChars[j * 2 +1] = hexArray[v & 0x0F];
              }
              return new String(hexChars);
          }

    public static byte[] createSalt() {
        //secure random of 20 bytes
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    
    }
    
}
