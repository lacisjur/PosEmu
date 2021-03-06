package az.pashabank.posemu;

import java.util.HashMap;

public class Constants {
    
    public enum PaymentSystem {
        VISA_INTERNATIONAL(0, "Visa International"),
        MASTERCARD_WORLDWIDE(1, "MasterCard Worlwide"),
        AMERICAN_EXPRESS(2, "Americarn Express"),
        UNKNOWN(9, "Unknown");
        
        private final int id;
        private final String name;
        
        private PaymentSystem (int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getName () {
            return this.name;
        }
        
        public int getId () {
            return this.id;
        }
        
        public static PaymentSystem toPaymentSystem(int id) {
            switch (id) {
                case 0: return VISA_INTERNATIONAL;
                case 1: return MASTERCARD_WORLDWIDE;
                case 2: return AMERICAN_EXPRESS;
                default: return UNKNOWN;
            }
        }
        
        public static PaymentSystem toPaymentSystem(String name) {
            switch (name) {
                case "Visa International": return VISA_INTERNATIONAL;
                case "MasterCard Worldwide": return MASTERCARD_WORLDWIDE;
                case "American Express": return AMERICAN_EXPRESS;
                default: return UNKNOWN;    
            }
        }
    }

    public static final String PARAM_ACQ_HOST_IP_ADDRESS = "ACQ_HOST_IP_ADDRESS";
    public static final String PARAM_ACQ_HOST_PORT = "ACQ_HOST_PORT";
    public static final String PARAM_ACQ_HOST_TIMEOUT = "ACQ_HOST_TIMEOUT";
    public static final String PARAM_TERMINAL_ID = "TERMINAL_ID";
    public static final String PARAM_TERMINAL_MERCHANT_ID = "TERMINAL_MERCHANT_ID";
    public static final String PARAM_TERMINAL_INTERFACE_ID = "TERMINAL_INTERFCE_ID";
    public static final String PARAM_PIN_KEY = "PIN_KEY";
    public static final String PARAM_PIN_KEY_IS_USED = "PIN_KEY_IS_USED";
    public static final String PARAM_ENCRYPTION_KEY = "ENCRYPTION_KEY";
    public static final String PARAM_ENCRYPTION_KEY_IS_USED = "ENCRYPTION_KEY_IS_USED";
    public static final String PARAM_MAC_KEY = "MAC_KEY";
    public static final String PARAM_MAC_KEY_IS_USED = "MAC_KEY_IS_USED";
    public static final String PARAM_MASTER_KEY = "MASTER_KEY";
    public static final String PARAM_MASTER_KEY_IS_USED = "MASTER_KEY_IS_USED";
    
    public static final String PARAM_EMV_HW_CONTACT_SUPPORTED = "EMV_HW_CONTACT_SUPPORTED";
    public static final String PARAM_EMV_HW_CONTACT_READER = "EMV_HW_CONTACT_READER";
    public static final String PARAM_EMV_HW_CONTACT_PROTOCOL = "EMV_HW_CONTACT_PROTOCOL";
    public static final String PARAM_EMV_HW_CONTACTLESS_SUPPORTED = "EMV_HW_CONTACTLESS_SUPPORTED";
    public static final String PARAM_EMV_HW_CONTACTLESS_READER = "EMV_HW_CONTACTLESS_READER";
    public static final String PARAM_EMV_HW_CONTACTLESS_PROTOCOL = "EMV_HW_CONTACTLESS_PROTOCOL";
    public static final String PARAM_EMV_PA_PERFORM_PSE_SELECTION = "EMV_PERFORM_PSE_SELECTION";
    public static final String PARAM_EMV_PA_APPLICATION_SELECTION_SUPPORTED = "EMV_APPLICATION_SELECTION_SUPPORTED";
    
    public static final String OBJECT_INTERFACE = "INTERFACE";

    public static final HashMap<Integer, IsoField> ISO_FIELDS = new HashMap<Integer, IsoField>();

    @Deprecated
    static void fillIsoFields() {
        ISO_FIELDS.put(1, new IsoField(1, "Secondary bitmap", 16));
        ISO_FIELDS.put(2, new IsoField(2, "Card number", 1, 19, 2));
        ISO_FIELDS.put(3, new IsoField(3, "Processing code", 6));
        ISO_FIELDS.put(4, new IsoField(4, "Transaction amount", 12));
        ISO_FIELDS.put(5, new IsoField(5, "Amount, reconcillation", 12));
        ISO_FIELDS.put(6, new IsoField(6, "Amount, cardholder billing", 12));
        ISO_FIELDS.put(7, new IsoField(7, "Date and time, transaction", 14));
        ISO_FIELDS.put(8, new IsoField(8, "Amount, cardholder billing fee", 8));
        ISO_FIELDS.put(9, new IsoField(9, "Conversion rate, reconcillation", 8));
        ISO_FIELDS.put(10, new IsoField(10, "Conversion rate, cardholder billing", 8));
        ISO_FIELDS.put(11, new IsoField(11, "System trace audit number", 6));
        ISO_FIELDS.put(12, new IsoField(12, "Date and time, local transaction", 12));
        ISO_FIELDS.put(13, new IsoField(13, "Date, effective", 4));
        ISO_FIELDS.put(14, new IsoField(14, "Date, expiration", 4));
        ISO_FIELDS.put(15, new IsoField(15, "Date, settlement", 14));
        ISO_FIELDS.put(16, new IsoField(16, "Date, conversion", 4));
        ISO_FIELDS.put(17, new IsoField(17, "Date, capture", 4));
        ISO_FIELDS.put(18, new IsoField(18, "Merchant type", 4));
        ISO_FIELDS.put(19, new IsoField(19, "Country code, acquiring institution", 3));
        ISO_FIELDS.put(20, new IsoField(20, "Country code, primary account number", 3));
        ISO_FIELDS.put(21, new IsoField(21, "Country code, forwarding institution", 3));
        ISO_FIELDS.put(22, new IsoField(22, "Point of service data code", 12));
        ISO_FIELDS.put(23, new IsoField(23, "Card sequence number", 3));
        ISO_FIELDS.put(24, new IsoField(24, "Function code", 3));
        ISO_FIELDS.put(25, new IsoField(25, "Message reason code", 4));
        ISO_FIELDS.put(26, new IsoField(26, "Merchant Category Code (MCC));", 4));
        ISO_FIELDS.put(27, new IsoField(27, "Approval code length", 1));
        ISO_FIELDS.put(28, new IsoField(28, "Date reconciliation BDD", 6));
        ISO_FIELDS.put(29, new IsoField(29, "Reconciliation indicator BDDn", 3));
        ISO_FIELDS.put(30, new IsoField(30, "Original amount", 24));
        ISO_FIELDS.put(31, new IsoField(31, "Acquirer reference data", 1, 99, 2));
        ISO_FIELDS.put(32, new IsoField(32, "Acquiring institution identification code", 1, 11, 2));
        ISO_FIELDS.put(33, new IsoField(33, "Forwarding institution identification code", 1, 11, 2));
        ISO_FIELDS.put(34, new IsoField(34, "Primary account number, extended", 1, 28, 2));
        ISO_FIELDS.put(35, new IsoField(35, "Track 2", 1, 37, 2));
        ISO_FIELDS.put(36, new IsoField(36, "Track 3", 1, 104, 3));
        ISO_FIELDS.put(37, new IsoField(37, "Retrieval reference number", 12));
        ISO_FIELDS.put(38, new IsoField(38, "Approval code", 6));
        ISO_FIELDS.put(39, new IsoField(39, "Action code", 3));
        ISO_FIELDS.put(40, new IsoField(40, "Service code", 3));
        ISO_FIELDS.put(41, new IsoField(41, "Card acceptor terminal identifier", 8));
        ISO_FIELDS.put(42, new IsoField(42, "Card acceptor identification code", 15));
        ISO_FIELDS.put(43, new IsoField(43, "Card acceptor name/location", 1, 99, 2));
        ISO_FIELDS.put(44, new IsoField(44, "Additional response data", 1, 99, 2));
        ISO_FIELDS.put(45, new IsoField(45, "Track 1", 1, 76, 2));
        ISO_FIELDS.put(46, new IsoField(46, "Amounts, fees", 1, 204, 3));
        ISO_FIELDS.put(47, new IsoField(47, "Additional data, national", 1, 999, 3));
        ISO_FIELDS.put(48, new IsoField(48, "Additional data, Private", 1, 999, 3));
        ISO_FIELDS.put(49, new IsoField(49, "Currency code, transaction", 3));
        ISO_FIELDS.put(50, new IsoField(50, "Currency code, reconciliation", 3));
        ISO_FIELDS.put(51, new IsoField(51, "Currency code, cardholder billing", 3));
        ISO_FIELDS.put(52, new IsoField(52, "Encrypted PIN", 16));
        ISO_FIELDS.put(53, new IsoField(53, "Security related control information", 1, 96, 2));
        ISO_FIELDS.put(54, new IsoField(54, "Amounts, additional", 1, 360, 3));
        ISO_FIELDS.put(55, new IsoField(55, "ICC Related Data", 1, 255, 3));
        ISO_FIELDS.put(56, new IsoField(56, "Original data", 1, 35, 2));
        ISO_FIELDS.put(57, new IsoField(57, "Authorization life cycle code", 3));
        ISO_FIELDS.put(58, new IsoField(58, "Authorizing agent institution identification code", 1, 11, 2));
        ISO_FIELDS.put(59, new IsoField(59, "Transport data", 1, 999, 3));
        ISO_FIELDS.put(60, new IsoField(60, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(61, new IsoField(61, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(62, new IsoField(62, "Reserved, private use", 1, 999, 3));
        ISO_FIELDS.put(63, new IsoField(63, "Reserved, private use", 1, 999, 3));
        ISO_FIELDS.put(64, new IsoField(64, "Message authentication code (MAC));", 16));
        ISO_FIELDS.put(65, new IsoField(65, "Reserved, ISO use", 16));
        ISO_FIELDS.put(66, new IsoField(66, "Amounts, original fees", 1, 204, 3));
        ISO_FIELDS.put(67, new IsoField(67, "Extended payment data", 2));
        ISO_FIELDS.put(68, new IsoField(68, "Country code, receiving institution", 3));
        ISO_FIELDS.put(69, new IsoField(69, "Country code, settlement institution", 3));
        ISO_FIELDS.put(70, new IsoField(70, "Country code, authorizing agent institution", 3));
        ISO_FIELDS.put(71, new IsoField(71, "Message number", 8));
        ISO_FIELDS.put(72, new IsoField(72, "Data record", 1, 999, 3));
        ISO_FIELDS.put(73, new IsoField(73, "Date, action", 6));
        ISO_FIELDS.put(74, new IsoField(74, "Credits, number", 10));
        ISO_FIELDS.put(75, new IsoField(75, "Credits reversal, number", 10));
        ISO_FIELDS.put(76, new IsoField(76, "Debits, number", 10));
        ISO_FIELDS.put(77, new IsoField(77, "Debits reversal, number", 10));
        ISO_FIELDS.put(78, new IsoField(78, "Transfer, number", 10));
        ISO_FIELDS.put(79, new IsoField(79, "Transfer, reversal number", 10));
        ISO_FIELDS.put(80, new IsoField(80, "Inquiries number", 10));
        ISO_FIELDS.put(81, new IsoField(81, "Authorization, number", 10));
        ISO_FIELDS.put(82, new IsoField(82, "Inquiries, reversal number", 10));
        ISO_FIELDS.put(83, new IsoField(83, "Payments, number", 10));
        ISO_FIELDS.put(84, new IsoField(84, "Payments, reversal number", 10));
        ISO_FIELDS.put(85, new IsoField(85, "Fee collections, number", 10));
        ISO_FIELDS.put(86, new IsoField(86, "Credits, amount", 16));
        ISO_FIELDS.put(87, new IsoField(87, "Credits reversal, amount", 16));
        ISO_FIELDS.put(88, new IsoField(88, "Debits, amount", 16));
        ISO_FIELDS.put(89, new IsoField(89, "Debits reversal, amounts", 16));
        ISO_FIELDS.put(90, new IsoField(90, "Authorizations, reversal number", 10));
        ISO_FIELDS.put(91, new IsoField(91, "Country code, transaction destination institution", 3));
        ISO_FIELDS.put(92, new IsoField(92, "Country code, transaction originator institution", 3));
        ISO_FIELDS.put(93, new IsoField(93, "Transaction destination institution identification code", 1, 11, 2));
        ISO_FIELDS.put(94, new IsoField(94, "Transaction originator institution identification code", 1, 11, 2));
        ISO_FIELDS.put(95, new IsoField(95, "Card issuer reference data", 1, 99, 2));
        ISO_FIELDS.put(96, new IsoField(96, "Key management data", 16, 998, 3));
        ISO_FIELDS.put(97, new IsoField(97, "Amount, net reconciliation", 17));
        ISO_FIELDS.put(98, new IsoField(98, "Payee", 25));
        ISO_FIELDS.put(99, new IsoField(99, "Settlement institution identification code", 1, 11, 2));
        ISO_FIELDS.put(100, new IsoField(100, "Receiving institution identification code", 1, 11, 2));
        ISO_FIELDS.put(101, new IsoField(101, "File name", 1, 17, 2));
        ISO_FIELDS.put(102, new IsoField(102, "Account Identification 1", 1, 28, 2));
        ISO_FIELDS.put(103, new IsoField(103, "Account Identification 2", 1, 28, 2));
        ISO_FIELDS.put(104, new IsoField(104, "Transaction description", 1, 100, 3));
        ISO_FIELDS.put(105, new IsoField(105, "Credits, chargeback amount", 16));
        ISO_FIELDS.put(106, new IsoField(106, "Debits, chargeback amount", 16));
        ISO_FIELDS.put(107, new IsoField(107, "Credits, chargeback number", 10));
        ISO_FIELDS.put(108, new IsoField(108, "Debits, chargeback number", 10));
        ISO_FIELDS.put(109, new IsoField(109, "Credits, fee amounts", 10));
        ISO_FIELDS.put(110, new IsoField(110, "Debits, fee amounts", 10));
        ISO_FIELDS.put(111, new IsoField(111, "Reserved, ISO use", 1, 999, 3));
        ISO_FIELDS.put(112, new IsoField(112, "Reserved, ISO use", 1, 999, 3));
        ISO_FIELDS.put(113, new IsoField(113, "Reserved, ISO use", 1, 999, 3));
        ISO_FIELDS.put(114, new IsoField(114, "Reserved, ISO use", 1, 999, 3));
        ISO_FIELDS.put(115, new IsoField(115, "Reserved, ISO use", 1, 999, 3));
        ISO_FIELDS.put(116, new IsoField(116, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(117, new IsoField(117, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(118, new IsoField(118, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(119, new IsoField(119, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(120, new IsoField(120, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(121, new IsoField(121, "Reserved, national use", 1, 999, 3));
        ISO_FIELDS.put(122, new IsoField(122, "4CSC value", 1, 999, 3));
        ISO_FIELDS.put(123, new IsoField(123, "CVC2 check ", 1, 999, 3));
        ISO_FIELDS.put(124, new IsoField(124, "Statement Data", 1, 999, 3));
        ISO_FIELDS.put(125, new IsoField(125, "Reserved, private use", 1, 999, 3));
        ISO_FIELDS.put(126, new IsoField(126, "Reserved, private use", 1, 999, 3));
        ISO_FIELDS.put(127, new IsoField(127, "Reserved, private use", 1, 999, 3));
        ISO_FIELDS.put(128, new IsoField(128, "Message authentication code (MAC));", 16));
    }
}
