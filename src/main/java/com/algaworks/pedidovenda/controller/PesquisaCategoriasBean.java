package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Categoria> categoriasFiltrados;

	private Categoria categoriaSelecionado;

	private String nomeCategoria;

	@Inject
	private Categorias categorias;

	public PesquisaCategoriasBean() {

	}

	public void excluir() {
		this.categorias.remover(this.categoriaSelecionado);
		this.categoriasFiltrados = this.categorias.filtrados(this.nomeCategoria);

		FacesUtil.addInfoMessage("Categoria " + this.categoriaSelecionado.getDescricao() + " excluído com sucesso.");

	}

	public void pesquisar() {
		this.categoriasFiltrados = this.categorias.filtrados(this.nomeCategoria);
	}

	public String getNomeCategoria() {
		return this.nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<Categoria> getCategoriasFiltrados() {
		return this.categoriasFiltrados;
	}

	public void setCategoriasFiltrados(List<Categoria> categoriasFiltrados) {
		this.categoriasFiltrados = categoriasFiltrados;
	}

	public Categoria getCategoriaSelecionado() {
		return this.categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}

}
