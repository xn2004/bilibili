package com.util;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;

public class KeyMatch{

    public static boolean checkKeyMatching(String publicKeyString, String privateKeyString) throws Exception {
        byte[] publicKeyBytes = Base64.decodeBase64(publicKeyString);
        byte[] privateKeyBytes = Base64.decodeBase64(privateKeyString);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return publicKey.equals(privateKey);
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQk33iNdA8Iey7J6XrBsidqn6u8EDLWPHsfEUgLQ3qiTikhPKDTzZkpAfU/O0x6NvSKa7Dp0+uqWT3vnW1De0+3u8mCYdVfOdH94VG4xg5U5UrRJei8HhPiXuvKQ+6NBtebCCW5adZ4pBgOiU14cJLhVmm+dYiLo3IDD5LqrlomQIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJCTfeI10Dwh7LsnpesGyJ2qfq7wQMtY8ex8RSAtDeqJOKSE8oNPNmSkB9T87THo29IprsOnT66pZPe+dbUN7T7e7yYJh1V850f3hUbjGDlTlStEl6LweE+Je68pD7o0G15sIJblp1nikGA6JTXhwkuFWab51iIujcgMPkuquWiZAgMBAAECgYA1UT9mciQWWQh9yNRmhXzssFjB2TZ8B5RIe1fe0t7D9NEf0yvAgzDzEo8U3CX5dv/CVL7vxr8bEbt7phCwsa8hJiLEOr7hLZaJzXVTbvfqb91oCZGNkqDQ3NJfGBMVgUmltEYF2Bbk3U0NDyat+Gu54tRd2OH+adJYKsD0XYeDBQJBAN5FE8E04A4FA1q8mQbVTSVJDYIEJwOrdC0r3iZ7za5CyXGk+br8pFalRePFaksRGdN32+mYhDKVNrNHspAObVMCQQCmhBsD+xiWrmpnrzeIfCW1cX8qRC3/RMkq0ACw3l6YedNFdN2Tb5WsRHmcbCI9y8mfLHiG/X1R+zHZKG67EKjjAkAmvAkGSY2mQ89i160fWLq5/bIh71FRPWbgnF15fWfJr4/lgyeWI4MMKn80g2nTrSZACQpE+jRHkGNY+OywWCNLAkEAli5nvztkfeJpDYK2b16pE/B9ZL2BTs3XMcnQFbU5VAPsTKSOgz8MmwZXOIE+kMWP3wPY4McXlC0eVGFnHUh1SQJAeAl3RPk+XbZDMYfPkStRJwocG9Ap+88mwTgR1I7uPzZ1aM84/WsQskiVMXv2SZLmMWvYtnhIKosL6IACp2AcDA==";

        boolean isMatch = checkKeyMatching(publicKey, privateKey);
        System.out.println("Keys Match: " + isMatch);
    }
}

