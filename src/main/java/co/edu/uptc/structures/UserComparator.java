package co.edu.uptc.structures;

import co.edu.uptc.model.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        if (o1.getName().equals(o2.getName())) {
            return o1.getSurname().compareTo(o2.getSurname());
        }else {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
