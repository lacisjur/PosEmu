package az.pashabank.posemu;

import java.util.HashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class IsoMessage {
    
    static HashMap<Integer, IsoField> isoFields;
    
    private static final byte[] EMPTY_BMP = new byte[8];
    
    private IsoMessageType mti;
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
                    IsoField fld = isoFields.get(k); //Constants.ISO_FIELDS.get(k);
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
    
    void setField (int id, String value) {
        IsoField field = Constants.ISO_FIELDS.get(id);
        field.setValue(value);
        this.fields.put(id, field);
    }
    
    String toIsoMessage () {
        String message = this.mti.toString();
        String bodyFields = "";
        byte[] bmp = new byte[16];
        Set<Integer> set = this.fields.keySet();
        for (Integer i : set) {
            IsoField field = this.fields.get(i);
            int byteIndex = field.getId() / 8;
            if ((field.getId() % 8) == 0) {
                byteIndex -= 1;
            }
            int bitIndex = (field.getId() - (byteIndex * 8) - 1);
            bmp[byteIndex] = (byte)(bmp[byteIndex] | (1 << (7 - (bitIndex))));
            if (field.isVariableLength()) {
                bodyFields += Utils.padLeft(field.getValue().length(), '0', field.getLengthQualifier()) + field.getValue();
            } else {
                String value = field.getValue();
                if (field.isPadded()) {
                    Utils.padRight(value, field.getPaddingChar().charAt(0), field.getMaxLength());
                }
                bodyFields += value;
            }
        }
        bmp[0] += (byte)128;    // extended bitmap is always in message
        message += Utils.byteArrayToHexString(bmp);
        return message + bodyFields;
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
        
        IsoMessage msg2 = new IsoMessage(IsoMessageType.AUTHORISATION_REQUEST);
        msg2.setField(2, "5315357289693858");
        msg2.setField(3, "000000");
        msg2.setField(4, "000000001111");
        msg2.setField(12, "160928192518");
        msg2.setField(14, "1612");
        msg2.setField(22, "510101513344");
        msg2.setField(24, "200");
        msg2.setField(25, "2000");
        msg2.setField(26, "5166");
        msg2.setField(28, "160928");
        msg2.setField(29, "000");
        msg2.setField(35, "5315357289693858D16122011985704200000");
        msg2.setField(41, "POS0020");
        msg2.setField(42, "110105");
        msg2.setField(55, "5F2A02084082023800950500000000009A031609289C01009F02060000000011119F03060000000000009F10120110A0800324380073C900000000000000FF9F1A0209819F2608B1C4853CA014D7BC9F2701809F3602000B9F370467AADBD1");
        String msgStr = msg2.toIsoMessage();
        System.out.println("ISO message: " + msgStr);
    }
    
}
