package dgdsoft.model.dao;

import dgdsoft.model.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public Usuario buscar(Usuario usuario){
        String sql = "select * from usuario where user = ? and password = ?";
        Usuario retorno = new Usuario();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,usuario.getUser());
            stmt.setString(2, usuario.getPassword());
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                usuario.setUser(resultado.getString("user"));
                usuario.setPassword(resultado.getString("password"));
                retorno = usuario;
            }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        return retorno;
    }
    
}
