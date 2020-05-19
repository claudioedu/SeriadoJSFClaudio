package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.GeneroClaudio;
import br.edu.faculdadedelta.modelo.SerieClaudio;
import br.edu.faculdadedelta.modelo.StatusClaudio;
import br.edu.faculdadedelta.util.Conexao;

public class SeriadoDAOClaudio {
	
	public void incluir(SerieClaudio serie) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "INSERT INTO series (id_genero, id_status, nome, comentario, "
				+ " nota_avaliacao) "
				+ " VALUES (?,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, serie.getGenero().getId());
			ps.setLong(2, serie.getStatus().getId());
			ps.setString(3, serie.getNome().trim());
			ps.setString(4, serie.getComentario().trim());
			ps.setInt(5, serie.getNota());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, null);
		}
	}
	
	public void alterar(SerieClaudio serie) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "UPDATE series "
				+ " SET id_genero = ?, "
				+ " id_stauts = ?, "
				+ " nome = ?, "
				+ " comentario = ?, "
				+ " nota_avaliacao = ? "
				+ " WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, serie.getGenero().getId());
			ps.setLong(2, serie.getStatus().getId());
			ps.setString(3, serie.getNome().trim());
			ps.setString(4, serie.getComentario().trim());
			ps.setInt(5, serie.getNota());
			ps.setLong(6, serie.getId());
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, null);
		}
	}
	
	public void excluir(SerieClaudio serie) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "DELETE FROM series WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, serie.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, null);
		}
	}
	
	public List<SerieClaudio> lista() throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "SELECT s.id AS idSerie, "
				+ " g.id AS idGenero, "
				+ " g.descricao AS descricaoGenero, "
				+ " st.descricao AS descricaoStatus, "
				+ " st.id AS idStatus, "
				+ " s.nome AS nomeSerie, "
				+ " s.comentario AS comentarioSerie, "
				+ " s.nota_avaliacao AS notaAvaliacaoSerie"
				+ " FROM series s"
				+ " INNER JOIN genero g ON s.id_genero = g.id "
				+ " INNER JOIN status st ON s.id_status = st.id";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<SerieClaudio> listaRetorno = new ArrayList<SerieClaudio>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				SerieClaudio serie = new SerieClaudio();
				serie.setId(rs.getLong("idSerie"));
				serie.setNome(rs.getString("nomeSerie").trim());
				serie.setComentario(rs.getString("comentarioSerie").trim());
				serie.setNota(rs.getInt("notaAvaliacaoSerie"));
				
				StatusClaudio status = new StatusClaudio();
				status.setId(rs.getLong("idStatus"));
				status.setDescricao(rs.getString("descricaoStatus").trim());
				
				serie.setStatus(status);
				
				GeneroClaudio genero = new GeneroClaudio();
				genero.setId(rs.getLong("idGenero"));
				genero.setDescricao(rs.getString("descricaoGenero").trim());
				
				serie.setGenero(genero);
				
				listaRetorno.add(serie);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	public SerieClaudio pesquisarSeriePorNome(String nome) throws Exception {
		Connection conn = Conexao.getConnection();
		String sql = "SELECT nome FROM series WHERE nome = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		SerieClaudio retorno = new SerieClaudio();
		try {
			ps.setString(1, nome);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno.setNome(nome);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.closeConnection(conn, ps, rs);
		}
		return retorno;
	}
}
