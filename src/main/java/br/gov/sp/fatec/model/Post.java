package br.gov.sp.fatec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "POST_POST")
public class Post implements Serializable {

	private static final long serialVersionUID = -4175224450033765996L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "POST_ID")
	@JsonView(View.Anotacao.class)
	private Long id;
    
    @Column(name = "POST_ASSUNTO", length = 100, nullable = false)
    @JsonView(View.Anotacao.class)
    private String assunto;
    
    @Column(name = "POST_TEXTO", length = 500, nullable = false)
    @JsonView(View.Anotacao.class)
    private String texto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USR_CRIACAO_ID")
    @JsonView(View.Anotacao.class)
    private Usuario usuario;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
