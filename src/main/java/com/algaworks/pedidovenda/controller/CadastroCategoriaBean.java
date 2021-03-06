package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.service.CadastroCategoriaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Categoria categoria;

	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;

	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroCategoriaService cadastroCategoriaService;

	public CadastroCategoriaBean() {
		categoria = new Categoria();
	}

	public List<Categoria> getCategoriasRaizes() {
		return this.categoriasRaizes;
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.categoriasRaizes = this.categorias.raizes();
		}
	}

	public void salvar() {
		this.cadastroCategoriaService.salvar(this.categoria);
		FacesUtil.addInfoMessage("Categoria salvo com sucesso!");
		this.categoria = new Categoria();
	}

	public boolean isEditando() {
		return this.categoria.getId() != null;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaPai() {
		return this.categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public void setCategoriasRaizes(List<Categoria> categoriasRaizes) {
		this.categoriasRaizes = categoriasRaizes;
	}	


}
