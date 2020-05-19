package br.edu.faculdadedelta.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.GeneroDAOClaudio;
import br.edu.faculdadedelta.modelo.GeneroClaudio;

@ManagedBean
@SessionScoped
public class GeneroControllerClaudio {
private GeneroClaudio genero= new GeneroClaudio();
private GeneroDAOClaudio dao= new GeneroDAOClaudio();

public GeneroClaudio getGenero() {
	return genero;
}
public void setGenero(GeneroClaudio genero) {
	this.genero = genero;
}

public String limpaCampos() {
	genero = new GeneroClaudio();
	return "cadGenero.xhtml";
}

public void exibirMsg(String mensagem) {
	FacesMessage msg = new FacesMessage(mensagem);
	FacesContext.getCurrentInstance().addMessage(null, msg);
}

public String salvar() {
	try {
		if (genero.getId() == null) {
				dao.incluir(genero);
				limpaCampos();
				exibirMsg("Inclusão realizada com sucesso!");
		} else {
			dao.alterar(genero);
			limpaCampos();
			exibirMsg("Alteração realizada com sucesso!");
		}
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());
	}
	return "cadastroGenero.xhtml";
}

public String editar() {
	return "cadastroGenero.xhtml";
}

public String excluir() {
	try {
		dao.excluir(genero);
		limpaCampos();
		exibirMsg("Exclusão realizada com sucesso!");
	} catch (Exception e) {
		e.printStackTrace();
		exibirMsg("Erro ao realizar a operação."
				+ " Tente novamente mais tarde. " + e.getMessage());			
	}
	return "listaGenero.xhtml";
}

public List<GeneroClaudio> getListar() {
	List<GeneroClaudio> listaRetorno = new ArrayList<GeneroClaudio>();
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