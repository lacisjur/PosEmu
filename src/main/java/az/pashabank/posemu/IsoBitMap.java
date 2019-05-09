package az.pashabank.posemu;

import java.util.Iterator;

public class IsoBitMap implements Iterable<Boolean> {
    
    private final byte[] bmp;
    
    IsoBitMap (byte[] bmp) {
        this.bmp = bmp;
    }

    @Override
    public Iterator<Boolean> iterator() {
        Iterator<Boolean> iter = new Iterator () {
            
            private int byteIndex = 0;
            private int bitIndex = 0;
            
            @Override
            public boolean hasNext() {
                return (this.byteIndex < bmp.length) && (this.bitIndex < 8);
            }

            @Override
            public Object next() {
                Boolean isSet = (bmp[this.byteIndex] >> (7 - this.byteIndex) & 1) == 1;
                this.bitIndex++;
                if (this.bitIndex == 8) {
                    this.bitIndex = 0;
                    this.byteIndex++;
                }
                return isSet;
            }
            
        };
        return iter;
    }
    
}
