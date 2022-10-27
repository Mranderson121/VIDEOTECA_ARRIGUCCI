package com.advancia.stage.model;

import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "utente")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "utente_film",
			joinColumns = @JoinColumn(name = "id_utente"),
			inverseJoinColumns = @JoinColumn(name = "id_film"))
	private List<Film> listaFilm = new ArrayList<>();

	public Utente() {
		super();
	}
	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Utente)) return false;
		return id != null && id.equals(((Utente) obj).getId());
	}
	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
