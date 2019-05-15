package az.pashabank.posemu;

public class ComboBoxItem {

    private final String  key;
    private final String label;
    

    ComboBoxItem(String key, String label) {
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
    public boolean equals (Object obj) {
        boolean result = false;
        if (obj instanceof ComboBoxItem) {
            ComboBoxItem item = (ComboBoxItem)obj;
            if (this.key == item.key) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.key + " - " + this.label;
    }

}
