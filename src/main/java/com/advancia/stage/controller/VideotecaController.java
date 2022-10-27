package com.advancia.stage.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.advancia.stage.dao.VideotecaDAO;
import com.advancia.stage.model.Film;
import com.advancia.stage.model.Regista;
import com.advancia.stage.model.request.AttorePost;
import com.advancia.stage.model.request.RegistaPost;

@Path("")
public class VideotecaController {
	
	@GET
	@Path("/films")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getAllFilms() {
		return VideotecaDAO.getAllFilms();
	}
	
	@POST
	@Path("/films")
	@Produces(MediaType.APPLICATION_JSON)
	public Film addFilm(String nome, Date dataUscita, Regista regista) {
		return VideotecaDAO.addFilm(nome, dataUscita, regista);
	}
	
	@GET
	@Path("/registi")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Regista> getAllRegisti() {
		return VideotecaDAO.getAllRegisti();
	}
	
	@POST
	@Path("/registi")
	@Produces(MediaType.APPLICATION_JSON)
	public Regista addRegista(RegistaPost regista) {
		return VideotecaDAO.addRegista(regista.getNome(), regista.getCognome(), regista.getDataNascita());
	}
	
	@POST
	@Path("/attori")
	@Produces(MediaType.APPLICATION_JSON)
	public Regista addAttore(AttorePost attore) {
		return VideotecaDAO.addRegista(attore.getNome(), attore.getCognome(), attore.getDataNascita());
	}

}










