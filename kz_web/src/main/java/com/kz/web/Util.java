package com.kz.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        encryptFile("C:\\myWareHouse\\dev\\mavens\\upload_set\\kz_auth_spring_starter");
    }

    public static void encryptFile(String fileName) throws NoSuchAlgorithmException, IOException {
        // Encrypt file
        MessageDigest MD5 = MessageDigest.getInstance("MD5");
        MessageDigest SHA_1 = MessageDigest.getInstance("SHA-1");

        File file = new File(fileName);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    String name = f.getName();
                    byte[] bytes = Files.readAllBytes(f.toPath());
                    MD5.update(bytes);

                    byte[] digest = MD5.digest();
                    String newFileName = fileName + File.separator + "md5" + File.separator + name + ".md5";
                    String md5Content = byteArrToHexStr(digest);
                    Files.write(new File(newFileName).toPath(), md5Content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

                    SHA_1.update(bytes);
                    digest = SHA_1.digest();
                    newFileName = fileName + File.separator + "sha1" + File.separator + name + ".sha1";
                    String sha1Content = byteArrToHexStr(digest);
                    Files.write(new File(newFileName).toPath(), sha1Content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }

    }

    public static String byteArrToHexStr(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
