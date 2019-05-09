package az.pashabank.posemu;

class Currency {

    private final String ccyNum;
    private final String ccyAlpha;
    private final String ccyName;
    
    Currency (String ccyNum, String ccyAlpha, String ccyName) {
        this.ccyNum = ccyNum;
        this.ccyAlpha = ccyAlpha;
        this.ccyName = ccyName;
    } 
    
    String getCurrencyNum () {
        return this.ccyNum;
    }
    
    String getCurrencyAlpha () {
        return this.ccyAlpha;
    }
    
    String getCurrencyName () {
        return this.ccyName;
    }
    
}
