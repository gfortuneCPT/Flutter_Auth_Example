package security;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class KeyPairEncryption implements EncryptionHandler {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public KeyPairEncryption(){
        try {
            this.init();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public final PublicKey getPublicKey() {
        return publicKey;
    }
    
    @Override
    public final byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public final String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    public final byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }
    
    @Override
    public final byte[] decrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        return cipher.doFinal(data);
    }

    private void init() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }


}

