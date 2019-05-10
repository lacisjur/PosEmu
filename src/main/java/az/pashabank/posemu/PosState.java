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

public enum PosState {    
    IDLE,
    INIT,
    CHOOSE_TRN,
    ENTER_AMT,
    SWIPE_CARD,    
    ENTER_PIN,
    CALL_HOST,
    RECEIVE_ANSWER,
    PRINT_RECEIPT,
    CANCEL,
    CONFIGURATION,
    UNKNOWN;
        
    public PosState nextState () {
        switch (this) {
            case IDLE: return INIT;
            case INIT: return CHOOSE_TRN;
            case CHOOSE_TRN: return ENTER_AMT;
            case ENTER_AMT: return SWIPE_CARD;
            case SWIPE_CARD: return ENTER_PIN;
            case ENTER_PIN: return CALL_HOST;
            case CALL_HOST: return RECEIVE_ANSWER;
            case RECEIVE_ANSWER: return PRINT_RECEIPT;
            case PRINT_RECEIPT: return IDLE;              
        }
        return UNKNOWN; 
    }
    
    public PosState cancelState () {      
        return CANCEL; 
    }
    
}
