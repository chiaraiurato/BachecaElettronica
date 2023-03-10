package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Comment;
import java.sql.*;

public class WriteCommentProcedureDAO implements GenericProcedureDAO <Comment>{
    private static WriteCommentProcedureDAO instance = null;

    private WriteCommentProcedureDAO(){
        //Singleton
    }
    public static WriteCommentProcedureDAO getInstance(){
        if(instance == null){
            instance = new WriteCommentProcedureDAO();
        }
        return instance;
    }
    @Override
    public Comment execute(Object... params) throws DAOException, SQLException {
        Comment comment = (Comment) params[0];
        try {

            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call invia_commento(?)}");
            cs.setString(1, comment.getText());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            comment.setIdComment(cs.getInt(2));
        } catch (SQLException e) {
            throw new DAOException("Error write a comment: " + e.getMessage());
        }
        return comment;
    }
}
