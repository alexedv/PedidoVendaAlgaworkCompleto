package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Usuarios;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuariosFiltrados;

	private Usuario usuarioSelecionado;

	private String nome;

	@Inject
	private Usuarios usuarios;

	public void pesquisar() {
		this.usuariosFiltrados = this.usuarios.filtrados(this.nome);
	}

	public void excluir() {
		this.usuarios.remover(this.usuarioSelecionado);
		this.usuariosFiltrados.remove(this.usuarioSelecionado);

		FacesUtil.addInfoMessage("Usuário " + this.usuarioSelecionado.getNome() + " excluído com sucesso.");
	}

	public List<Usuario> getUsuariosFiltrados() {
		return this.usuariosFiltrados;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuarioSelecionado() {
		return this.usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
