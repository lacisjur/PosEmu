package az.pashabank.posemu;

public class ComboBoxItem {

    private final int id;
    private final String label;
    

    ComboBoxItem(int id, String label) {
        this.label = label;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals (Object obj) {
        boolean result = false;
        if (obj instanceof ComboBoxItem) {
            ComboBoxItem item = (ComboBoxItem)obj;
            if (this.id == item.id) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.label;
    }

}
