package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import adapters.CgpAdapter;
import adapters.ServicioExternoCGP;
import pois.Dispositivo;
import pois.ManejadorDeDispositivos;
import procesos.CriterioComuna;

public class CriterioComunaTest {
	
	private Point posicionComunaValida;
	private Point posicionComunaInvalida;
	private Dispositivo dispositivoConPosicionDeComunaValida;
	private Dispositivo dispositivoConPosicionDeComunaValida2;
	private Dispositivo dispositivoConPosicionDeComunaInvalida;
	private ArrayList<Dispositivo> dispositivosValidos;
	private ManejadorDeDispositivos manejadorDeDispositivos;
	private CriterioComuna criterioComuna;
	private CgpAdapter cgpAdapter;
	private ServicioExternoCGP servicioExternoCGP;

	@Before
	public void init() {
		
		posicionComunaValida = new Point(-34.614978, -58.372815);
		posicionComunaInvalida = new Point(-34.620999, -58.416590);
		cgpAdapter = new CgpAdapter(servicioExternoCGP);
		criterioComuna = new CriterioComuna(1);
		dispositivoConPosicionDeComunaValida = new Dispositivo("dispositivoValido", posicionComunaValida);
		dispositivoConPosicionDeComunaValida2 = new Dispositivo("dispositivoValido2", posicionComunaValida);
		dispositivoConPosicionDeComunaInvalida = new Dispositivo("dispostivoValido3", posicionComunaInvalida);
		
		dispositivosValidos = new ArrayList<Dispositivo>();
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida);
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida2);
		dispositivosValidos.add(dispositivoConPosicionDeComunaInvalida);

	
		manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
		manejadorDeDispositivos.setListaDispositivos(dispositivosValidos);
		manejadorDeDispositivos.setCgpAdapter(cgpAdapter);
	

	}
	
	@Test
	public void siFiltroDispositivosPorComunaValidaDevuelveUnaListaNoVaciaDeDispositivos(){
		try {
			Assert.assertEquals(2, criterioComuna.filtrar().size(),0);
		} catch (Exception e) {
						
		}

	}

}
