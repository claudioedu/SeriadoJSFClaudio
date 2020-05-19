package br.edu.faculdadedelta.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.StatusDAOClaudio;
import br.edu.faculdadedelta.modelo.StatusClaudio;
@ManagedBean
@SessionScoped
public class StatusControllerClaudio {
private StatusClaudio status = new StatusClaudio();
private StatusDAOClaudio dao= new StatusDAOClaudio();
public StatusClaudio getStatus() {
	return status;
}
public void setStatus(StatusClaudio status) {
	this.status = status;
}

public String limpaCampos() {
	status = new StatusClaudio();
	return "cadStatus.xhtml";
}

public void exibirMsg(String mensagem) {
	FacesMessage msg = new FacesMessage(mensagem);
	FacesContext.getCurrentInstance().addMessage(null, msg);
}

public String salvar() {
	try {
		if (status.getId() == null) {
				dao.incluir(status);
				limpaCampos();
				exibirMsg("Inclusão realizada com sucesso!");
		} else {
			dao.alterar(status);
			limpaCampos();
			exibirMsg("Alteração realizada com sucesso!");
		}
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());
	}
	return "cadStatus.xhtml";
}

public String editar() {
	return "cadStatus.xhtml";
}

public String excluir() {
	try {
		dao.excluir(status);
		limpaCampos();
		exibirMsg("Exclusão realizada com sucesso!");
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());			
	}
	return "listaStatus.xhtml";
}

public List<StatusClaudio> getListar() {
	List<StatusClaudio> listaRetorno = new ArrayList<StatusClaudio>();
	try {
		listaRetorno = dao.listar();
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());			
	}
	return listaRetorno;
}
}
