package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryList {

    private final List<Category> categories = new ArrayList<>();
    public void addCategories(Category category) {
        this.categories.add(category);
    }
    public List<Category> getCategories() {
        return categories;
    }

    public String printTreeCategories() {
        StringBuilder builder = new StringBuilder();
        Iterator<Category> categoryListIterator = categories.iterator();
        while (categoryListIterator.hasNext()) {
            Category c = categoryListIterator.next();
            String id = c.getIdCategory();
            String name = c.getName();

            if (id.length() == 2) {
                builder.append("+").append(name).append("\n");
            } else if (id.length() == 3) {
                builder.append("\t-").append(name).append("\n");
            } else if (id.length() == 5) {
                builder.append("\t\t-").append(name).append("\n");
            }
        }

        return builder.toString();
    }


}
