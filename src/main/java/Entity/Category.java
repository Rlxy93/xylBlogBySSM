package Entity;

import Entity.Category;

public class Category {
    private String category;
    private int count;
    private int id;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getCategory() {
        return this.category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public int getCount() {
        return this.count;
    }


    public void setCount(int count) {
        this.count = count;
    }


    public Category(String category, int count, int id) {
        this.category = category;
        this.count = count;
        this.id = id;
    }

    public Category() {
    }
}