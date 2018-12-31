package org.orion.app.component.implementation;

import org.orion.app.component.contract.IPasswordEncoder;

import java.security.MessageDigest;
import java.util.Formatter;

public class SHA256PasswordEncoder implements IPasswordEncoder {
    public boolean matches(String clearPassword, String encodedPassword) {
        return encodePassword(clearPassword).equals(encodedPassword);
    }

    public String encodePassword(String password) {
        String encoded = null;
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            encoded = byteToHex(crypt.digest());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return encoded;
    }

    private String byteToHex(final byte[] hash){
        Formatter formatter = new Formatter();
        for(byte b: hash){ formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
