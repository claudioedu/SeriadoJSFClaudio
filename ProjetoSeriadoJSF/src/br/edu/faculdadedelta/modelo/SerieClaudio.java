package br.edu.faculdadedelta.modelo;

public class SerieClaudio {
private Long id;
private GeneroClaudio genero;
private StatusClaudio status;
private String nome;
private String comentario;
private int nota;
public SerieClaudio() {
	super();
}
public SerieClaudio(Long id, GeneroClaudio genero, StatusClaudio status, String nome, String comentario, int nota) {
	super();
	this.id = id;
	this.genero = genero;
	this.status = status;
	this.nome = nome;
	this.comentario = comentario;
	this.nota = nota;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public GeneroClaudio getGenero() {
	return genero;
}
public void setGenero(GeneroClaudio genero) {
	this.genero = genero;
}
public StatusClaudio getStatus() {
	return status;
}
public void setStatus(StatusClaudio status) {
	this.status = status;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}
public int getNota() {
	return nota;
}
public void setNota(int nota) {
	this.nota = nota;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	SerieClaudio other = (SerieClaudio) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}




}
