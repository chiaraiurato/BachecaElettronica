package it.uniroma2.dicii.bd.model.dao;
import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.*;

import java.sql.*;

public class WriteMessageProcedureDAO implements GenericProcedureDAO <Message>{
    private static WriteMessageProcedureDAO instance = null;

    private WriteMessageProcedureDAO(){
        //Singleton
    }
    public static WriteMessageProcedureDAO getInstance(){
        if(instance == null){
            instance = new WriteMessageProcedureDAO();
        }
        return instance;
    }
    @Override
    public Message execute(Object... params) throws DAOException{

        String seller = (String) params[0];
        User user = (User) params[1];
        String text = (String)params[2];
        Message m = null;
        int idConversation;
        try {

            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call invia_messaggio(?,?, ?)}");
            cs.setString(1, seller);
            cs.setString(2, user.getUsername());
            cs.setString(3, text);
            cs.registerOutParameter(4, Types.INTEGER);
            boolean status = cs.execute();
            idConversation = cs.getInt(6);
            if(status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    m = new Message(rs.getDate(2), rs.getTime(3), rs.getString(4));
                    m.setIdConversation(idConversation);
                    m.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error write a message: " + e.getMessage());
        }
        return m;
    }
}
