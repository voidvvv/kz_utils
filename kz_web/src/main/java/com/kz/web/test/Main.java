package com.kz.web.test;

import io.jsonwebtoken.Jwts;

import java.security.*;
import java.util.Map;

public class Main {

    public Map<String,String> generateKeyPair () throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        System.out.println(new String(aPrivate.getEncoded()));

        return null;
    }

    public void buildJWT (){
        String jws = Jwts.builder()
                .subject("Joe")
                .claims()
                .add("aaa", "bbb")
                .and()
                .header()
                .add("aaa", "bbb")
                .agreementPartyUInfo("my party")
                .and()

                .compact();
        System.out.printf("jws: %s\n", jws);
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        Main m = new Main();
        m.generateKeyPair();
    }
}
