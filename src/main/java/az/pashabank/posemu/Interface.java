package az.pashabank.posemu;

public class Interface {

    private final int id;
    private String name;
    private String description;
    private int fieldCount;

    public Interface(int id, String name, String description, int fieldCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fieldCount = fieldCount;
    }
    
    public int getId () {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    @Override
    public String toString() {
        return "Interface = { id = " + id + 
                ", name = " + name + 
                ", description = " + description + 
                ", fieldCount = " + fieldCount + " }";
    }
}
