package az.pashabank.posemu;

class IsoField {
    
    private final int id;
    private final String name;
    private final int minLength;
    private final int maxLength;
    private final int lengthQualifier;
    private boolean isPadded = false;
    private String paddingChar;
    private String value;

    IsoField(int id,
            String name,
            int length) {
        this.id = id;
        this.name = name;
        this.minLength = length;
        this.maxLength = length;
        this.lengthQualifier = 0;
    }   
    
    IsoField(int id,
            String name,
            int length,
            String paddingChar) {
        this(id, name, length);
        this.isPadded = true;
        this.paddingChar = paddingChar;
    }
    
    IsoField(int id, 
            String name, 
            int minLength, 
            int maxLength, 
            int lengthQualifier) {
        this.id = id;
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.lengthQualifier = lengthQualifier;
    }
    
    IsoField(int id, 
            String name, 
            int minLength, 
            int maxLength, 
            int lengthQualifier,
            String paddingChar) {
        this.id = id;
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.lengthQualifier = lengthQualifier;
        this.paddingChar = paddingChar;
    }    

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getMinLength() {
        return minLength;
    }

    int getMaxLength() {
        return maxLength;
    }
    
    boolean isVariableLength () {
        return this.maxLength != this.minLength;
    }
    
    boolean isFixedLength () {
        return this.maxLength == this.minLength;
    }

    int getLengthQualifier() {
        return lengthQualifier;
    }
    
    boolean isPadded () {
        return this.isPadded;
    }
    
    String getPaddingChar () {
        return this.paddingChar;
    }

    String getValue() {
        return value;
    }
    
    void setValue (String value) {
        this.value = value;
    }
    
    boolean isValueSet () {
        return this.value != null;
    }
    
    public String toString() {
        return this.id + " - " + this.name + " - " + this.value;
    }
    
} 
