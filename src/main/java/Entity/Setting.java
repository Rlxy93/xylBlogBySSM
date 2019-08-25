package Entity;

import Entity.Setting;

public class Setting {
    private int id;
    private String blogName;
    private String mainblogName;
    private String beian;
    private String qq;
    private String index1;
    private String admin;

    public String toString() {
        return "Setting{id=" + this.id + ", blogName='" + this.blogName + '\'' + ", mainblogname='" + this.mainblogName + '\'' + ", beian='" + this.beian + '\'' + ", qq='" + this.qq + '\'' + ", index1='" + this.index1 + '\'' + ", admin='" + this.admin + '\'' + '}';
    }


    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getBlogName() {
        return this.blogName;
    }


    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }


    public String getMainblogName() {
        return this.mainblogName;
    }


    public void setMainblogName(String mainblogName) {
        this.mainblogName = mainblogName;
    }


    public String getBeian() {
        return this.beian;
    }


    public void setBeian(String beian) {
        this.beian = beian;
    }


    public String getQq() {
        return this.qq;
    }


    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getindex1() {
        return this.index1;
    }


    public void setindex1(String index1) {
        this.index1 = index1;
    }


    public String getAdmin() {
        return this.admin;
    }


    public void setAdmin(String admin) {
        this.admin = admin;
    }


    public Setting(int id, String blogName, String mainblogName, String beian, String qq, String index1, String admin) {
        this.id = id;
        this.blogName = blogName;
        this.mainblogName = mainblogName;
        this.beian = beian;
        this.qq = qq;
        this.index1 = index1;
        this.admin = admin;
    }

    public Setting() {
    }
}