package com.example.ginfofx;

import org.mindrot.jbcrypt.BCrypt;

public class Utils {

    public static String hashPassword(String password) {
        String crypt = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("password crypt : " +password+" = "+ crypt);
        return crypt;
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }


}
