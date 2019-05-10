package az.pashabank.posemu;

public class TripleDesKey {
    
    private static final String UNSET_KEY = "FFFFFFFFFFFFFFFF";
    
    private final String keyA;
    private final String keyB;
    private final String keyC;
    
    TripleDesKey (String key) {
        this.keyA = key.substring(0, 16);
        this.keyB = key.substring(16, 32);
        this.keyC = (key.length() == 48) ? key.substring(32) : this.keyA;
    }
    
    TripleDesKey(byte[] key) {
        this(Utils.byteArrayToHexString(key));
    }

    String getKeyA() {
        return keyA;
    }

    String getKeyB() {
        return keyB;
    }

    String getKeyC() {
        return keyC;
    }
    
    byte[] getEncoded () {
        return Utils.hexStringToByteArray(this.keyA + this.keyB + this.keyC);
    }
    
    boolean isUnset () {
        return (this.keyA.equals(UNSET_KEY) && 
                this.keyB.equals(UNSET_KEY) &&
                this.keyC.equals(UNSET_KEY));
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.keyA).append(this.keyB);
        if (!this.keyA.equals(this.keyC)) {
            sb.append(this.keyC);
        }
        return sb.toString();
    }
    
    
}
