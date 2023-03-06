package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CategoryList {

    List<Category> categories = new ArrayList<>();
    public void addCategories(Category category) {
        this.categories.add(category);
    }
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        int i;
        StringBuilder builder = new StringBuilder();
        Iterator<Category> categoryListIterator = categories.iterator();
        while (categoryListIterator.hasNext()) {
            Category c = categoryListIterator.next();
            String id = c.getIdCategory();
            String name = c.getName();
            for (i = 1; i <= 3; i++) {
                if (Objects.equals(id, i + "/")) {
                    builder.append("+" + name + "\n");
                } else if (Objects.equals(id, i + "/1")) {
                    builder.append("\t-" + name + "\n");
                } else if (Objects.equals(id, i + "/2")) {
                    builder.append("\t\t-" + name + "\n");
                }
            }

        }

        return builder.toString();
    }
}
