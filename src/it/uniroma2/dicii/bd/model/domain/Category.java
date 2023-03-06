package it.uniroma2.dicii.bd.model.domain;

public class Category {
    String idCategory;
    String Name;

    public Category(String idCategory, String name) {
        this.idCategory = idCategory;
        Name = name;
    }
    public Category(String name) {
        Name = name;
    }
    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
