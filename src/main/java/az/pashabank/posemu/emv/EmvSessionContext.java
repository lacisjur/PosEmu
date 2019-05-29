package az.pashabank.posemu.emv;

import az.pashabank.posemu.EventLog;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

public class EmvSessionContext {
    
    private static final EventLog log = EventLog.getInstance();
    
    private final static TerminalFactory terminalFactory = TerminalFactory.getDefault();
    private final List<CardTerminal> cardReaders;
    private CardTerminal cardReader;
    private Card card;
    private CardChannel cardChannel;
    
    public EmvSessionContext () 
            throws Exception {
        this.cardReaders = getCardReaders();
    }
    
    public void connect (int reader) 
            throws Exception {
        this.cardReader = this.cardReaders.get(reader);
        try {
            this.card = this.cardReader.connect("*");
        } catch (CardException e) {
            log.error("Failed to connect card: " + e.getMessage(), e);
            throw new Exception("Failed to connect card: " + e.getMessage(), e);
        }
        this.cardChannel = this.card.getBasicChannel();
    } 
    
    public static List<CardTerminal> getCardReaders () 
            throws Exception {
        List<CardTerminal> list = null;
        try {
            list = terminalFactory.terminals().list();
        } catch (CardException e) {
            log.error("Failed to get smart card readers: " + e.getMessage(), e);
            throw new Exception("Failed to get smart card readers: " + e.getMessage(), e);
        }
        return list;
    }
}
