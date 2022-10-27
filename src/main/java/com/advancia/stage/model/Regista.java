package com.advancia.stage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "regista")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Regista {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Regista_Sequence")
//	@SequenceGenerator(name = "id_Regista_Sequence", sequenceName = "REGISTA_SEQ")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "data_nascita")
	private Date dataNascita;
	
	@OneToMany(mappedBy = "regista",
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	private List<Film> listaFilm = new ArrayList<>();

	public Regista() {
		super();
	}
	public Regista(String nome, String cognome, Date dataNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public List<Film> getListaFilm() {
		return listaFilm;
	}
	public void setListaFilm(List<Film> listaFilm) {
		this.listaFilm = listaFilm;
	}
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Regista)) return false;
		return id != null && id.equals(((Regista) obj).getId());
	}
	@Override
	public String toString() {
		return "Regista [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", listaFilm=" + listaFilm + "]";
	}
	
}
