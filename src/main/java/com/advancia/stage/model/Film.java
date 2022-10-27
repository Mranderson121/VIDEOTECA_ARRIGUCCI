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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "film")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "data_uscita")
	private Date dataUscita;
	
	@ManyToOne
	@JoinColumn(name = "id_regista")
	private Regista regista;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "film_attore",
			joinColumns = @JoinColumn(name = "id_film"),
			inverseJoinColumns = @JoinColumn(name = "id_attore"))
	private List<Attore> attori = new ArrayList<>();
	
	@ManyToMany(mappedBy = "listaFilm")
	private List<Utente> utenti = new ArrayList<>();
	
	public Film() {
		super();
	}
	public Film(String nome, Date dataUscita, Regista regista) {
		super();
		this.nome = nome;
		this.dataUscita = dataUscita;
		this.regista = regista;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataUscita() {
		return dataUscita;
	}
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}
	public Regista getIdRegista() {
		return regista;
	}
	public void setRegista(Regista regista) {
		this.regista = regista;
	}
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Film)) return false;
		return id != null && id.equals(((Film) obj).getId());
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", nome=" + nome + ", dataUscita=" + dataUscita + ", idRegista=" + regista.getId() + "]";
	}
	
	
}





