package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.User;

import javax.management.openmbean.OpenMBeanAttributeInfo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObtainUserProcedureDAO implements GenericProcedureDAO <User>{
    private static ObtainUserProcedureDAO instance = null;

    private ObtainUserProcedureDAO(){
        //Singleton
    }
    public static ObtainUserProcedureDAO getInstance(){
        if(instance == null){
            instance = new ObtainUserProcedureDAO();
        }
        return instance;
    }
    @Override
    public User execute(Object... params) throws DAOException, SQLException {
        String username= (String) params[0];
        User user= null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ottieni_user(?)}");
            cs.setString(1, username);
            boolean status = cs.execute();
            if(status) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {
                    user = new User(username, rs.getString(3), rs.getString(4), rs.getDate(5),
                            rs.getString(6));

                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error obtaining user: " + e.getMessage());
        }
        return user;
    }
}
