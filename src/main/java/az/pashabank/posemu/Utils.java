package az.pashabank.posemu;

public class Utils {

    public byte[] xorByteArrays (byte[] ba1, byte[] ba2) {
        byte[] ba3 = new byte[ba1.length];
        for (int i = 0; i < ba3.length; i++) {
            ba3[i] = (byte)(ba1[i] ^ ba2[i]);
        }
        return ba3;
    }

    public static byte[] xorBytes (byte[] ba1, byte[] ba2) {
        int len1 = ba1.length;
        int len2 = ba2.length;
        int i;
        byte[] res;
        if (len1 > len2) {
            int len = len1;
            res = new byte[len];
            int dif = len1 - len2;
            for (i = 0; i < dif; i++) {
                res[i] = ba1[i];
            }
            for (i = dif; i < len; i++) {
                res[i] = (byte)(ba1[i] ^ ba2[i - dif]);
            }
        }
        else {
            int len = len2;
            res = new byte[len];
            int dif = len2 - len1;
            for (i = 0; i < dif; i++) {
                res[i] = ba2[i];
            }
            for (i = dif; i < len; i++) {
                res[i] = (byte)(ba1[i - dif] ^ ba2[i]);
            }
        }
        return res;
    }

    
    public static byte[] hexStringToByteArray (String s) {
        int i, j, len, nib1, nib2;
        byte bs[] = null;
        bs = new byte[s.length() / 2];
        for (i = 0, j = 0, len = s.length(); i < len; i += 2, ++j) {
            nib1 = Character.digit(s.charAt(i), 16);
            nib2 = Character.digit(s.charAt(i+1), 16);
            bs[j] = (byte) ((nib1 << 4) + nib2);
        }
        return bs;
    }

    public static String byteArrayToHexString (byte bs[]) {
        int i;
        String s = new String();
        String hex_digits = "0123456789ABCDEF";
        byte c;
        if (bs == null || bs.length == 0) {
            return s;
        }
        for (i = 0; i < bs.length; ++i) {
            c = bs[i];
            s += hex_digits.charAt((c >> 4) & 0xf);
            s += hex_digits.charAt(c & 0xf);
        }
        return s;
    }    
}
