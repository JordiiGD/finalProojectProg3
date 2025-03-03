package co.edu.uptc.structures;

import co.edu.uptc.model.Category;

import java.util.Comparator;

public class CategoryComparator implements Comparator<Category> {
    @Override
    public int compare(Category o1, Category o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
