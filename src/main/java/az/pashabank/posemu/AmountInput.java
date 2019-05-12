package az.pashabank.posemu;

public class AmountInput {
    private String amount = "0";
    
    private String addPoint () {
        String decimal = this.amount.substring(this.amount.length() - 2);
        String whole = this.amount.substring(0, this.amount.length() - 2);
        return whole + "." + decimal;
    }
    
    String add(String digit) {
        if (amount.length() == 12) {
            return addPoint();
        }
        if (amount.equals("0") && digit.equals("0")) {
            return "0.00";
        } else {
            if (amount.length() == 1) {
                if (amount.equals("0")) {
                    amount = digit;
                    return "0.0" + amount;
                } else {
                    amount += digit;
                    return "0." + amount;
                }
            } else {
                this.amount += digit;
                return addPoint();
            }
        }
    }
    
    String backspace () {
        if (amount.equals("0")) {
            return "0.00";
        }
        this.amount = this.amount.substring(0, amount.length() - 1);
        switch (this.amount.length()) {
            case 0:
                amount = "0";
                return "0.00";
            case 1:
                return "0.0" + amount;
            case 2:
                return "0." + amount;
            default:
                return addPoint();
        }
        
    }
    
    void reset () {
        this.amount = "0";
    }
    
    String getAmount()
    {
        return this.amount;
    }
    
}
 