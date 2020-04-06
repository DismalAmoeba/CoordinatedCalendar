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
import java.awt.Canvas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.*;
import javax.swing.JFrame;

/**
 *
 * @author towns
 */
public class AccountProtect {
    //Need to launch LoginForm on start!
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
//        System.out.println("SHA-256 Hash: " + generateHash(data, algorithm, salt)); 

  }
    


    //public static String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException  {
         public static String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException  {
       //MessageDigest of value assigned to String algorithm --> Java security class
       MessageDigest digest = MessageDigest.getInstance(algorithm);
       digest.reset();
       //update digest with salt in order to eliminate identical hashes
       digest.update(salt);
      byte[] hash = digest.digest(data.getBytes());
      return bytesToStringHex(hash);
            
    }
         
         public String getSecureHashForCompare(String passwordToHash, byte[] salt) throws Exception
         {
             String generatedPass = passwordToHash;
             
             try 
     {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            passwordToHash = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordToHash;
             
         }
          public final static char[] hexArray = "0123456789ABCDEF".toCharArray();

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
