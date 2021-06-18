package net.agenda.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.agenda.model.Usuario;



public class UsuarioDAOImpl implements UsuarioDAO{
	
	private JdbcTemplate jdbcTemplate;
	 
    public UsuarioDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public void saveOrUpdate(Usuario usuario) {
		if (usuario.getId() > 0) {
	        // update
	        String sql = "UPDATE usuario SET nome=?, email=?, senha=?, "
	                    + "telefone=? WHERE usuario_id=?";
	        jdbcTemplate.update(sql, usuario.getNome(), usuario.getEmail(),
	        		usuario.getSenha(), usuario.getTelefone(), usuario.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO usuario (nome, email, senha, telefone)"
	                    + " VALUES (?, ?, ?, ?)";
	        jdbcTemplate.update(sql, usuario.getNome(), usuario.getEmail(),
	        		usuario.getSenha(), usuario.getTelefone());
	    }
		
	}

	@Override
	public void delete(int usuarioId) {
		String sql = "DELETE FROM usuario WHERE usuario_id=?";
	    jdbcTemplate.update(sql, usuarioId);
		
	}

	@Override
	public Usuario get(int usuarioId) {
		String sql = "SELECT * FROM usuario WHERE usuario_id=" + usuarioId;
	    return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {
	 
	        @Override
	        public Usuario extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Usuario usuario = new Usuario();
	            	usuario.setId(rs.getInt("usuario_id"));
	            	usuario.setNome(rs.getString("nome"));
	            	usuario.setEmail(rs.getString("email"));
	            	usuario.setSenha(rs.getString("senha"));
	            	usuario.setTelefone(rs.getString("telefone"));
	                return usuario;
	            }
	 
	            return null;
	        }
	 
	    });
	}

	@Override
	public List<Usuario> list() {
		String sql = "SELECT * FROM contact";
	    List<Usuario> listUsuario = jdbcTemplate.query(sql, new RowMapper<Usuario>() {
	 
	        @Override
	        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Usuario aUsuario = new Usuario();
	 
	        	aUsuario.setId(rs.getInt("usuario_id"));
	        	aUsuario.setNome(rs.getString("nome"));
	        	aUsuario.setEmail(rs.getString("email"));
	        	aUsuario.setSenha(rs.getString("senha"));
	        	aUsuario.setTelefone(rs.getString("telefone"));
	 
	            return aUsuario;
	        }
	 
	    });
	 
	    return listUsuario;
	}

}
