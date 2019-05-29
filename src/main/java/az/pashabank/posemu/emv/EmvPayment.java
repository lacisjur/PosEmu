package az.pashabank.posemu.emv;

public class EmvPayment {
    
    private EmvSessionContext emvContext;
    
    public EmvPayment () throws Exception { 
        this.emvContext = new EmvSessionContext();
    }
    
    public void initializePayment () {
        
    }
    
}
