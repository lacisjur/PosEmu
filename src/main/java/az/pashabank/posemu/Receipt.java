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
public class Receipt {
    

    private final String card;
    private final String amount;
    private final String rrn;
    private final String fld_039;
    private final String date;
    


    
    public Receipt(String card, String amount, String rrn, String fld_039, String date) {
        this.card = card;
        this.amount = amount;
        this.rrn = rrn;
        this.fld_039 = fld_039;
        this.date = date;
    }

    public String getCard() {
        return card;
    }

    public String getAmount() {
        return amount;
    }

    public String getRrn() {
        return rrn;
    }

    public String getFld_039() {
        return fld_039;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Receipt{" + "card=" + card + ", amount=" + amount + ", rrn=" + rrn + ", fld_039=" + fld_039 + ", date=" + date + '}';
    }
    
    
    
}
