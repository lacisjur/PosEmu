package az.pashabank.posemu;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class IsoMessage {
    
    private static final byte[] EMPTY_BMP = new byte[8];
    
    private final IsoMessageType mti;
    private final SortedMap<Integer, IsoField> fields;
    
    IsoMessage (IsoMessageType mti) {
        this.mti = mti;
        this.fields = new TreeMap<>();
    }
    
    IsoMessage (String message) {
        FixedLengthStringParser parser = new FixedLengthStringParser(message);
        this.mti = IsoMessageType.toIsoMessageType(parser.getNextToken(4));
        this.fields = new TreeMap<>();
        String b = parser.getNextToken(16);
        if (Integer.parseInt(parser.peekNextToken(2), 16) >= 128) {
            b += parser.peekNextToken(16);
        }
        byte[] bmp = Utils.hexStringToByteArray(b);
        int k = 1;
        for (int i = 0; i < bmp.length; i++) {
            for (int j = 0; j < 8; j++, k++) {
                if ((bmp[i] >> (7 - j) & 1) == 1) {
                    IsoField fld = Constants.ISO_FIELDS.get(k);
                    if (fld.isVariableLength()) {
                        int valueLength = Integer.parseInt(parser.getNextToken(fld.getLengthQualifier()));
                        fld.setValue(parser.getNextToken(valueLength));
                    } else {
                        fld.setValue(parser.getNextToken(fld.getMaxLength()));
                    }
                    this.fields.put(fld.getId(), fld);
                }
            }
        }
    }

    IsoMessageType getMti() {
        return mti;
    }

    IsoField getField(int id) {
        return fields.get(id);
    }
    
    void setField (IsoField field) {
        this.fields.put(field.getId(), field);
    }
    
    String toIsoMessage () {
        String message = this.mti.toString();
        String bodyFields = "";
        byte[] bmp1 = new byte[8];
        byte[] bmp2 = new byte[8];
        Set<Integer> set = this.fields.keySet();
        for (Integer i : set) {
            IsoField field = this.fields.get(i);
            if (field.getId() <= 64) {
                
            } 
        }
        return null;
    }
    
    private class FixedLengthStringParser {
        
        private int index = 0;
        private final String message;
        
        FixedLengthStringParser (String message) {
            this.message = message;
        }
        
        String getNextToken (int length) {
            String token = this.message.substring(this.index, this.index + length);
            this.index += length;
            return token;
        }
        
        String peekNextToken (int length) {
            return this.message.substring(this.index, this.index + length);
        }
        
        String getLastToken () {
            String token = this.message.substring(this.index);
            this.index = this.message.length();
            return token;
        }
    }
    
    public String toString () {
        String str = "MTI: " + this.mti + "\n";
        Set<Integer> keys = this.fields.keySet();
        for (Integer i : keys) {
            str += this.fields.get(i).toString() + "\n";
        }
        return str;
    }
    
    public static void main(String[] args) {
        Constants.fillIsoFields();
        String message = "1200701405d820c00200165315357289693858000000000000001111160928192518161251010151334420020005166160928000375315357289693858D16122011985704200000POS0020 1101054        1905F2A02084082023800950500000000009A031609289C01009F02060000000011119F03060000000000009F10120110A0800324380073C900000000000000FF9F1A0209819F2608B1C4853CA014D7BC9F2701809F3602000B9F370467AADBD1";
        IsoMessage msg = new IsoMessage(message);
        System.out.println(msg);
    }
    
}
