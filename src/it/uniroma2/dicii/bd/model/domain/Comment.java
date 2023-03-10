package it.uniroma2.dicii.bd.model.domain;

public class Comment {
    int idComment;
    String Text;

    public Comment(String text) {
        Text = text;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }
    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

}
