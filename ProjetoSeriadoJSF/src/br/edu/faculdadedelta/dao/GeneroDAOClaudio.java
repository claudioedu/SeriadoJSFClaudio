package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.GeneroClaudio;
import br.edu.faculdadedelta.util.Conexao;

public class GeneroDAOClaudio {


		public void incluir(GeneroClaudio  genero) throws Exception { 
			Connection conn = Conexao.getConnection();
			String sql = "INSERT INTO genero (descricao) VALUES (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, genero.getDescricao().trim());
			
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			} finally {
				Conexao.closeConnection(conn, ps, null);
			}
		}
		
		public void alterar(GeneroClaudio genero) throws Exception {
			Connection conn = Conexao.getConnection();
			String sql = "UPDATE genero SET descricao= ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1,genero.getDescricao().trim());


				ps.setLong(2,genero.getId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			} finally {
				Conexao.closeConnection(conn, ps, null);
			}
		}
		
		public void excluir(GeneroClaudio genero) throws Exception {
			Connection conn = Conexao.getConnection();
			String sql = "DELETE FROM genero WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setLong(1, genero.getId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			} finally {
				Conexao.closeConnection(conn, ps, null);
			}
		}
		
		public GeneroClaudio pesquisarPorId(Long id) throws Exception {
			Connection conn = Conexao.getConnection();
			String sql = "SELECT id,descricao  FROM genero WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			GeneroClaudio retorno = new GeneroClaudio();
			ResultSet rs = null;
			try {
				ps.setLong(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					retorno = popularGenero(rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			} finally {
				Conexao.closeConnection(conn, ps, rs);
			}
			return retorno;
		}
		
		public List<GeneroClaudio> listar() throws Exception {
			Connection conn = Conexao.getConnection();
			String sql = "SELECT id, descricao FROM genero";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<GeneroClaudio> listaRetorno = new ArrayList<GeneroClaudio>();
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				while (rs.next()) {
					listaRetorno.add(popularGenero(rs));
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				Conexao.closeConnection(conn, ps, rs);
			}
			return listaRetorno;
		}
		
		private GeneroClaudio  popularGenero(ResultSet rs) throws SQLException {
			GeneroClaudio genero = new GeneroClaudio();
			genero.setId(rs.getLong("id"));
			genero.setDescricao(rs.getString("descricao"));
			
			return genero;
		}
	}

		
		
		