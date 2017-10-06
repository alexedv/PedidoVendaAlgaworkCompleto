package com.algaworks.pedidovenda.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Pedidos;
import com.algaworks.pedidovenda.security.UsuarioLogado;
import com.algaworks.pedidovenda.security.UsuarioSistema;


@Named
@RequestScoped
public class GraficoPedidosCriadoBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy"); 
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private CartesianChartModel model;
	
	public void preRender(){
		this.model = new CartesianChartModel();
		
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus Pedidos", usuarioLogado.getUsuario());
	}

	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		ChartSeries serie = new ChartSeries(rotulo);
		
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(15, criadoPor);
		
		for(Date data : valoresPorData.keySet()){
			serie.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}
		
		this.model.addSeries(serie);
	}

	public CartesianChartModel getModel() {
		return model;
	}
}
