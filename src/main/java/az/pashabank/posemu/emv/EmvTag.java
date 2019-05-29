package az.pashabank.posemu.emv;

public class EmvTag {

    private final String tag;
    private final String name;
    private String value;

    public EmvTag(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue (String value) {
        this.value = value;
    }
    
}
