package co.edu.uptc.structures;

import co.edu.uptc.model.Comment;

import java.util.Comparator;

public class CommentComparator implements Comparator<Comment> {
    @Override
    public int compare(Comment o1, Comment o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
