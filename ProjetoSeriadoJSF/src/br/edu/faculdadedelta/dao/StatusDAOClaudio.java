package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.StatusClaudio;
import br.edu.faculdadedelta.util.Conexao;

public class StatusDAOClaudio {

	public void incluir(StatusClaudio  status) throws Exception { 
		Connection conn = Conexao.getConnection();
		String sql = "INSERT INTO status (descricao) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, status.getDescricao().trim());
		
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps,null);
		}
	}
	
	public void alterar(StatusClaudio status) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "UPDATE status SET descricao= ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1,status.getDescricao().trim());


			ps.setLong(2,status.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, null);
		}
	}
	
	public void excluir(StatusClaudio status) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "DELETE FROM status WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, status.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, null);
		}
	}
	
	
	public StatusClaudio pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "SELECT id, descricao FROM status WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		StatusClaudio retorno = new StatusClaudio();
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setDescricao(rs.getString("descricao"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, rs);
		}
		return retorno;
	}
	
	public List<StatusClaudio> listar() throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "SELECT id, descricao FROM status";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<StatusClaudio> listaRetorno = new ArrayList<StatusClaudio>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				StatusClaudio status = new StatusClaudio();
				status.setId(rs.getLong("id"));
				status.setDescricao(rs.getString("descricao"));
				listaRetorno.add(status);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, rs);
		}
		return listaRetorno; 
	}
	
	
	
	
	
	
}


