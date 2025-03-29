package com.kz.web.config.secure.context;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class RSAUtil {
    public static final String PUB_KEY_FILE = "rsa/pub.der";
    public static final String PRI_KEY_FILE = "rsa/pri.der";

    public static PublicKey publicKey = null;
    public static PrivateKey privateKey = null;

    static {
        try {
            log.info("generate key pair\n");
            generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.info("could not create new key, read exist key\n");
            readExistKey();
        }
    }


    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
        instance.initialize(2048);
        KeyPair keyPair = instance.generateKeyPair();
        Path path = Paths.get(PUB_KEY_FILE);
        Files.createDirectories(path.getParent());
        Files.write(path, keyPair.getPublic().getEncoded(), StandardOpenOption.CREATE_NEW);
        path = Paths.get(PRI_KEY_FILE);
        Files.createDirectories(path.getParent());
        Files.write(path, keyPair.getPrivate().getEncoded(), StandardOpenOption.CREATE_NEW);
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        return keyPair;
    }

    public static void readExistKey() {
        try {
            // pub
            X509EncodedKeySpec spec = new X509EncodedKeySpec(Files.readAllBytes(Path.of(PUB_KEY_FILE)));
            PublicKey rsa = KeyFactory.getInstance("RSA").generatePublic(spec);
            publicKey = rsa;

            // pri
            PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(Files.readAllBytes(Path.of(PRI_KEY_FILE)));
            PrivateKey rsa2 = KeyFactory.getInstance("RSA").generatePrivate(spec2);
            privateKey = rsa2;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
