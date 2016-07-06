package tests;

import java.util.ArrayList;

import org.junit.Before;
import org.uqbar.geodds.Point;

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

	@Before
	public void init() {
		
		posicionComunaValida = new Point(-34.614978, -58.372815);
		posicionComunaInvalida = new Point(-34.620999, -58.416590);
		dispositivoConPosicionDeComunaValida = new Dispositivo("dispositivoValido", posicionComunaValida);
		dispositivoConPosicionDeComunaValida2 = new Dispositivo("dispositivoValido2", posicionComunaValida);
		dispositivoConPosicionDeComunaInvalida = new Dispositivo("dispostivoValido3", posicionComunaInvalida);
		
		dispositivosValidos = new ArrayList<Dispositivo>();
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida);
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida2);
		dispositivosValidos.add(dispositivoConPosicionDeComunaInvalida);

	
		manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
		manejadorDeDispositivos.setListaDispositivos(dispositivosValidos);
		
		criterioComuna = new CriterioComuna(1);

	}

}
