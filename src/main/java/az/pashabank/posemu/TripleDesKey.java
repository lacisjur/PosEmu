package az.pashabank.posemu;

public class TripleDesKey {
    
    private final String keyA;
    private final String keyB;
    private final String keyC;
    
    TripleDesKey (String key) {
        this.keyA = key.substring(0, 16);
        this.keyB = key.substring(16, 32);
        this.keyC = this.keyA;
    }
    
    byte[] getEncoded () {
        return Utils.hexStringToByteArray(toString());
    }
    
    @Override
    public String toString () {
        return this.keyA + this.keyB + this.keyC;
    }
    
}
