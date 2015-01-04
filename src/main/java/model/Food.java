package model;

public class Food implements Entity {
    private String name;
    private Category category;
    private int price;

    public Food() {
    }

    public Food(String name, String nameOfcategory, int price) {
        this.name = name;
        this.category = new Category(nameOfcategory);
        this.price = price;
    }

    public Food(String name, String nameCategory) {
        this.name = name;
        this.category = new Category(nameCategory);
    }

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
