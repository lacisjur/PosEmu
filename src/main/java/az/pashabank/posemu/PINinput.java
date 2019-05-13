/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.pashabank.posemu;

/**
 *
 * @author rsamadov
 */
public class PINinput {

    private static PINinput instance;

    private PINinput() {

    }

    static {
        try {
            instance = new PINinput();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static PINinput getInstance() {
        return instance;
    }

    private String PIN = "";

    String add(String digit) {
        if (PIN.length() == 6) {
            return crypto(this.PIN);
        }
        this.PIN += digit;
        return crypto(this.PIN);
    }

    String crypto(String str) {

        String s = "";
        for (int i = 0; i < str.length(); i++) {
            s = s + "X";
        }
        return s;
    }

    String backspace() {
        if (PIN.equals("") || PIN.length()==0) {
            return "......";
        }

        this.PIN = this.PIN.substring(0, PIN.length() - 1);

        switch (this.PIN.length()) {
            case 0:
                PIN = "";
                return "......";
            default:
                return crypto(this.PIN);
        }

    }

    void reset() {
        this.PIN = "";
    }

    String getPIN() {
        return this.PIN;
    }

}
