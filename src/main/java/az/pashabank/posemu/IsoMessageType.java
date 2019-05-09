package az.pashabank.posemu;

public enum IsoMessageType {
    
    AUTHORISATION_REQUEST("1100"),
    AUTHORISATION_RESPONSE("1100"),
    TRANSACTION_REQUEST("1200"),
    TRANSACTION_RESPONSE("1210"),
    TRANSACTION_ADVICE_REQUEST("1220"),
    TRANSACTION_ADVICE_RESPONSE("1230"),
    REVERSAL_REQUEST("1420"),
    REVERSAL_RESPONSE("1430"),
    UNKNOWN("9999");
    
    private final String value;
    
    private IsoMessageType (String value) {
        this.value = value;
    }
    
    static IsoMessageType toIsoMessageType(String mti) {
        switch (mti) {
            case "1100": return AUTHORISATION_REQUEST;
            case "1110": return AUTHORISATION_RESPONSE;
            case "1200": return TRANSACTION_REQUEST;
            case "1210": return TRANSACTION_RESPONSE;
            case "1220": return TRANSACTION_ADVICE_REQUEST;
            case "1230": return TRANSACTION_ADVICE_RESPONSE;
            case "1420": return REVERSAL_REQUEST;
            case "1430": return REVERSAL_RESPONSE;
            default: return UNKNOWN;
        }
    } 
    
    @Override
    public String toString () {
        return this.value;
    }
    
}
