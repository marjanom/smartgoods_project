package com.example.smartgoods_project.helper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;

public class Hashing {

    public static ArrayList<byte[]> generateHash(String password) {

        ArrayList<byte[]> list = new ArrayList<>();

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        list.add(salt);

        KeySpec spec = getKeySpec(password, salt);
        SecretKeyFactory factory = null;
        byte[] hash = new byte[0];

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec)
                    .getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        list.add(hash);
        return list;
    }

    public static byte[] generateHash(String password, byte[] salt) {

        KeySpec spec = getKeySpec(password, salt);
        SecretKeyFactory factory = null;
        byte[] hash = new byte[0];

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec)
                    .getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private static KeySpec getKeySpec(String password, byte[] salt) {
        return new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    }

}

