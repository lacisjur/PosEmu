package az.pashabank.posemu;

public class LabelValuePair {

    private final String label;
    private final Object value;

    public LabelValuePair(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
