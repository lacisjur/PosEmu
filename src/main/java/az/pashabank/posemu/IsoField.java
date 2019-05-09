package az.pashabank.posemu;

class IsoField {
    
    private final int id;
    private final String name;
    private final int minLength;
    private final int maxLength;
    private final int lengthQualifier;
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
            String value) {
        this(id, name, length);
        this.value = value;
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
            String value) {
        this(id, name, minLength, maxLength, lengthQualifier);
        this.value = value;
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
