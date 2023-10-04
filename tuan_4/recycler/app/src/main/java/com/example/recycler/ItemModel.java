package com.example.recycler;

public class ItemModel {

    // Constants to define different item types
    public static final int TYPE_TYPE1 = 1;
    public static final int TYPE_TYPE2 = 2;
    // Add more constants for other button types if needed

    private int type;       // The type of the item (e.g., TYPE_TYPE1 or TYPE_TYPE2)
    private String label;   // A label associated with the item

    // Constructor to create an ItemModel with a type and a label
    public ItemModel(int type, String label) {
        this.type = type;
        this.label = label;
    }

    // Getter method to retrieve the type of the item
    public int getType() {
        return type;
    }

    // Getter method to retrieve the label of the item
    public String getLabel() {
        return label;
    }
}
