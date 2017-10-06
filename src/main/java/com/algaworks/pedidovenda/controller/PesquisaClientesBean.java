package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.repository.Clientes;
import com.algaworks.pedidovenda.repository.filter.ClienteFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientesFiltrados;

	@Inject
	private Clientes clientes;

	private ClienteFilter clienteFilter;

	private Cliente clienteSelecionado;
	
	public PesquisaClientesBean() {
		this.clienteFilter = new ClienteFilter();
	}

	public void pesquisar() {
		this.clientesFiltrados = this.clientes.filtrados(this.clienteFilter);
	}

	public void excluir() {
		this.clientes.remover(this.clienteSelecionado);
		this.clientesFiltrados.remove(this.clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente " + this.clienteSelecionado.getNome() + " excluído com sucesso.");
	}

	public List<Cliente> getClientesFiltrados() {
		return this.clientesFiltrados;
	}

	public ClienteFilter getClienteFilter() {
		return this.clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public Cliente getClienteSelecionado() {
		return this.clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	

}
