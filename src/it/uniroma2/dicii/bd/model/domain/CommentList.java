package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class CommentList {
    private List<Comment> comments = new ArrayList<>();
    public int getSize(){
        return comments.size();
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Comment comment : comments) {
            sb.append(comment.getText() + "\n");
        }
        return sb.toString();
    }
}
