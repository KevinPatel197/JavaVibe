/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author kevin
 */
public class HashUtility {
    
    public String getHashPassword(String password) {
        
        String generatedHashPassword;
        
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                .substring(1));
            }
            generatedHashPassword = sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            
            
            return e.getMessage();
        }
        
        return generatedHashPassword;
    }
    
    
    public boolean checkPassword(String hashPassword, String password) {
        
        if(!hashPassword.isEmpty() && !password.isEmpty()) {
            String enteredPassword = getHashPassword(password);
            
            if(enteredPassword.equals(hashPassword)) {
                return true;
            }
        }
        
        return false;
    }
}
