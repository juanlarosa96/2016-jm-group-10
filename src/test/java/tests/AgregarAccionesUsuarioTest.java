package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import adapters.AdapterMail;
import eventosBusqueda.InteresadoEnBusquedas;
import eventosBusqueda.NotificadorEmail;
import pois.Dispositivo;
import pois.ManejadorDeDispositivos;
import procesos.AccionAgregarAccionesParaLosUsuarios;
import procesos.CriterioComuna;
import procesos.CriterioTodosLosUsuarios;
import procesos.CriterioUsuariosSeleccionados;

public class AgregarAccionesUsuarioTest {
	
	private CriterioComuna comunaValida;
	private CriterioComuna comunaInvalida;
	private CriterioTodosLosUsuarios todosLosUsuarios;
	private CriterioUsuariosSeleccionados usuariosSeleccionados;
	private InteresadoEnBusquedas accionValida;
	private AdapterMail adapterMail;
	private ManejadorDeDispositivos manejadorDeDispositivos;
	private Dispositivo	dispositivoConPosicionDeComunaValida;
	private Dispositivo dispositivoConPosicionDeComunaValida2;
	private Dispositivo dispositivoConPosicionDeComunaInvalida;
	private Point posicionComunaValida;
	private Point posicionComunaInvalida;
	private List<Dispositivo> dispositivosSeleccionados;
	private AccionAgregarAccionesParaLosUsuarios accionValidaConComunaValida;
	private AccionAgregarAccionesParaLosUsuarios accionValidaConComunaInvalida;
	private AccionAgregarAccionesParaLosUsuarios accionValidaTodosLosUsuarios;
	private AccionAgregarAccionesParaLosUsuarios accionValidaUsuariosSeleccionados;
	private List<Dispositivo> dispositivosValidos;
	
	@Before
	public void init(){
		comunaValida = new CriterioComuna(1);
		comunaInvalida = new CriterioComuna(100);

		accionValida = new NotificadorEmail(5.0, "emailAdmin@hotmail.com", adapterMail);
		
		dispositivosSeleccionados = new ArrayList<Dispositivo>();
		dispositivosSeleccionados.add(dispositivoConPosicionDeComunaValida);
		dispositivosSeleccionados.add(dispositivoConPosicionDeComunaInvalida);
		
		usuariosSeleccionados = new CriterioUsuariosSeleccionados(dispositivosSeleccionados);

		dispositivoConPosicionDeComunaValida = new Dispositivo("dispositivoValido", posicionComunaValida);
		dispositivoConPosicionDeComunaValida2 = new Dispositivo("dispositivoValido2", posicionComunaValida);
		dispositivoConPosicionDeComunaInvalida = new Dispositivo("dispostivoValido3", posicionComunaInvalida);
		posicionComunaValida = new Point(-34.614978, -58.372815);
		posicionComunaInvalida = new Point(-34.620999, -58.416590);
		
		dispositivosValidos = new ArrayList<Dispositivo>();
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida);
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida2);
		dispositivosValidos.add(dispositivoConPosicionDeComunaInvalida);
		
		manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
		manejadorDeDispositivos.setListaDispositivos(dispositivosValidos);
		
		accionValidaConComunaValida = new AccionAgregarAccionesParaLosUsuarios(accionValida, comunaValida);
		accionValidaConComunaInvalida = new AccionAgregarAccionesParaLosUsuarios(accionValida, comunaInvalida);
		accionValidaTodosLosUsuarios = new AccionAgregarAccionesParaLosUsuarios(accionValida, todosLosUsuarios);
		accionValidaUsuariosSeleccionados = new AccionAgregarAccionesParaLosUsuarios(accionValida, usuariosSeleccionados);
	}
	
	@Test
	public void siEjecutoLaAccionConUnaComunaValidaEntoncesLaAccionEsAgregadaALasAccionesDeLosUsuarios(){
		try {
			accionValidaConComunaValida.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(1, dispositivoConPosicionDeComunaValida.getObservers().size(),0);
		Assert.assertEquals(1, dispositivoConPosicionDeComunaValida2.getObservers().size(),0);
//		Assert.assertTrue(dispositivoConPosicionDeComunaValida.getObservers().contains(accionValida));
//		Assert.assertTrue(dispositivoConPosicionDeComunaValida2.getObservers().contains(accionValida));
	}
	
	@Test
	public void siEjecutoLaAccionConUnaComunaInvalidaEntoncesLaAccionNoEsAgregadaALasAccionesDeLosUsuarios(){
		try {
			accionValidaConComunaInvalida.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertTrue(dispositivoConPosicionDeComunaValida.getObservers().isEmpty());
		Assert.assertTrue(dispositivoConPosicionDeComunaValida2.getObservers().isEmpty());
	}

}
