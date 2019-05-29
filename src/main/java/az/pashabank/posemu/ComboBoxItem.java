package az.pashabank.posemu;

@Deprecated
public class ComboBoxItem {

    private final String  key;
    private final String label;
    

    public ComboBoxItem(String key, String label) {
        this.label = label;
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return this.key + " - " + this.label;
    }

}
