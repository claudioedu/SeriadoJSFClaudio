package br.edu.faculdadedelta.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.SeriadoDAOClaudio;
import br.edu.faculdadedelta.modelo.GeneroClaudio;
import br.edu.faculdadedelta.modelo.SerieClaudio;
import br.edu.faculdadedelta.modelo.StatusClaudio;

@ManagedBean
@SessionScoped
public class SeriadoControllerClaudio {
private SerieClaudio seriado = new SerieClaudio();
private SeriadoDAOClaudio dao= new SeriadoDAOClaudio();
private GeneroClaudio genSel = new GeneroClaudio();
private StatusClaudio statusSelecionado= new StatusClaudio();
public SerieClaudio getSeriado() {
	return seriado;
}
public void setSeriado(SerieClaudio seriado) {
	this.seriado = seriado;
}
public GeneroClaudio getGenSel() {
	return genSel;
}
public void setGenSel(GeneroClaudio genSel) {
	this.genSel = genSel;
}
public StatusClaudio getStatusSelecionado() {
	return statusSelecionado;
}
public void setStatusSelecionado(StatusClaudio statusSelecionado) {
	this.statusSelecionado = statusSelecionado;
}
public String limpaCampos() {
	statusSelecionado = new StatusClaudio();
genSel = new GeneroClaudio();
	seriado = new  SerieClaudio();
	return "cadSeriado.xhtml";
}

public void exibirMsg(String mensagem) {
	FacesMessage msg = new FacesMessage(mensagem);
	FacesContext.getCurrentInstance().addMessage(null, msg);
}

public String salvar() throws Exception {
	try {
		if (seriado.getId() == null) {
			seriado.setGenero(genSel);
			seriado.setStatus(statusSelecionado);
			dao.incluir(seriado);
			limpaCampos();
			exibirMsg("Ordem de serviço cadastrada com sucesso!");
		} else {
			seriado.setGenero(genSel);
			seriado.setStatus(statusSelecionado);
			dao.alterar(seriado);
			limpaCampos();
			exibirMsg("Ordem de serviço alterada com sucesso!");
		}
	} catch (SQLException e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação: " + e.getMessage());
	}
	return "cadSeriado.xhtml";
}


public String alterar() {
	genSel = seriado.getGenero();
	statusSelecionado = seriado.getStatus();
	return "cadSeriado.xhtml";
}

public String excluir() {
	try {
		dao.excluir(seriado);
		limpaCampos();
		exibirMsg("Exclusão realizada com sucesso!");
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());			
	}
	return "listaSeriado.xhtml";
}

public List<SerieClaudio> getLista() {
	List<SerieClaudio> listaRetorno = new ArrayList<SerieClaudio>();
	try {
		listaRetorno = dao.lista();
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());			
	}
	return listaRetorno;
}
}
