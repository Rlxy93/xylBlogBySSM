package Entity;

import Entity.Blog;

public class Blog {
    private int id;
    private String title;
    private String body;
    private String time;
    private String category;
    private String mm;
    private String yzmm;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return this.body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getCategory() {
        return this.category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getMm() {
        return this.mm;
    }


    public void setMm(String mm) {
        this.mm = mm;
    }


    public String getYzmm() {
        return this.yzmm;
    }


    public void setYzmm(String yzmm) {
        this.yzmm = yzmm;
    }


    public Blog() {
    }


    public Blog(int id, String title, String body, String time, String category, String mm, String yzmm) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
        this.category = category;
        this.mm = mm;
        this.yzmm = yzmm;
    }
}