package az.pashabank.posemu;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Crypto {

    private static final byte[] ZEROES = new byte[8];
    static final String UNSET_KEY = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    
    enum PaddingMethod {
        PKCS5("PKCS5Padding"),
        NO_PADDING("NoPadding");
        
        private final String value;
        
        private PaddingMethod (String value) {
            this.value = value;
        } 
        
        @Override
        public String toString () {
            return this.value;
        }
    }
    
    enum EncryptionMode {
        CBC("CBC"),
        ECB("ECB");
        
        private final String value;
        
        private EncryptionMode (String value) {
            this.value = value;
        } 
        
        @Override 
        public String toString () {
            return this.value;
        }
    }
    
    enum DesKeyLength {
        SINGLE(56, 16),
        DOUBLE(112, 32),
        TRIPLE(168, 48);
        
        private final int bitLength;
        private final int stringLength;
        
        private DesKeyLength (int bitLength, int stringLength) {
            this.bitLength = bitLength;
            this.stringLength = stringLength;
        }
        
        public int getBitLength () {
            return this.bitLength;
        }
        
        public int getStringLength () {
            return this.stringLength;
        }
    }
    
    static String encrypt3Des (String key, String plainData, EncryptionMode mode, PaddingMethod padding) 
            throws Exception {
        return Utils.byteArrayToHexString(encrypt3Des(Utils.hexStringToByteArray(key), 
                Utils.hexStringToByteArray(plainData), mode, padding));
    }
    
    static String generateKeyCheckValue (String key) 
            throws Exception {
        return Utils.byteArrayToHexString(encrypt3Des(Utils.hexStringToByteArray(key), 
                ZEROES, EncryptionMode.ECB, PaddingMethod.NO_PADDING)).substring(0, 6);
    }
    
    private static byte[] encrypt3Des (byte[] key, byte[] plainData, EncryptionMode mode, PaddingMethod padding) 
            throws Exception {
        byte[] encData = null;
        try {
            SecretKey sKey = new SecretKeySpec(key, "DESede");
            Cipher ciph = Cipher.getInstance("DESede/" + mode.toString() + "/" + padding.toString());
            ciph.init(Cipher.ENCRYPT_MODE, sKey);
            encData = ciph.doFinal(plainData);
        } catch (BadPaddingException | 
                IllegalBlockSizeException | 
                InvalidKeyException | 
                NoSuchAlgorithmException | 
                NoSuchPaddingException e) {
            throw new Exception("Failed to encrypt: " + e.getMessage(), e);
        }
        return encData;
    }
    
    static String generate3DesKey (DesKeyLength keyLength) throws Exception {
        String key = Utils.byteArrayToHexString(generate3DesKeyInternal(keyLength));
        return key.substring(0, keyLength.getStringLength());
    }
    
    private static byte[] generate3DesKeyInternal (DesKeyLength keyLength) throws Exception {
        byte[] key = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DESede");
            kg.init(keyLength.getBitLength());
            key = kg.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Failed to generate 3DES key: " + e.getMessage(), e);
        }
        return key;
    }
    
}
