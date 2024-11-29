package co.edu.uptc.model;

import java.time.LocalDateTime;

public class Comment {

    private User user;
    private String comment;
    private LocalDateTime creationDate;

    public Comment(User user, String comment, LocalDateTime creationDate) {
        this.user = user;
        this.comment = comment;
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
