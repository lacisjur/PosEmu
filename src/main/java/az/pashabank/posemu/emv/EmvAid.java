package az.pashabank.posemu.emv;

import az.pashabank.posemu.Constants;

public class EmvAid {

    private final String rid;
    private final String pix;
    private String name;
    private final Constants.PaymentSystem paymentSystem;

    public EmvAid(String rid, String pix, String name, Constants.PaymentSystem paymentSystem) {
        this.rid = rid;
        this.pix = pix;
        this.name = name;
        this.paymentSystem = paymentSystem;
    }

    public String getRid() {
        return rid;
    }

    public String getPix() {
        return pix;
    }
    
    public String getAid () {
        return this.rid + this.pix;
    }

    public String getName() {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }

    public Constants.PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }
    
    @Override
    public String toString() {
        return this.rid + this.pix + " - " + this.name;
    }
    
}
