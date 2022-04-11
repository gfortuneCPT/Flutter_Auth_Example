package security;

import java.security.PublicKey;

public interface EncryptionHandler {
    EncryptionHandler INSTANCE = new KeyPairEncryption();

    static EncryptionHandler getInstance() {
        return INSTANCE;
    }

    public byte[] encrypt(byte[] data, PublicKey key) throws Exception;
    public byte[] decrypt(byte[] data)throws Exception;


    static Object getPublicKey();
}
