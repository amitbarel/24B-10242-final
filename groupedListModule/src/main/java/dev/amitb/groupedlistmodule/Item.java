package dev.amitb.groupedlistmodule;

public class Item {
    private String name;
    private String category;
    private Double price;

    public Item(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() { return price; }
}

