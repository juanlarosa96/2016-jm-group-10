package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.uqbar.geodds.Point;

import adapters.AdapterMail;
import adapters.CgpAdapter;
import adapters.ServicioExternoCGP;
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
	private CgpAdapter cgpAdapter;
	private ServicioExternoCGP servicioExternoCGP;
	
	@Before
	public void init(){
		posicionComunaValida = new Point(-34.621891, -58.509017);
		posicionComunaInvalida = new Point(-34.620999, -58.416590);
		
		dispositivoConPosicionDeComunaValida = new Dispositivo("dispositivoValido", posicionComunaValida);
		dispositivoConPosicionDeComunaValida2 = new Dispositivo("dispositivoValido2", posicionComunaValida);
		dispositivoConPosicionDeComunaInvalida = new Dispositivo("dispostivoValido3", posicionComunaInvalida);
		
		cgpAdapter = new CgpAdapter(servicioExternoCGP);
		
		dispositivosValidos = new ArrayList<Dispositivo>();
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida);
		dispositivosValidos.add(dispositivoConPosicionDeComunaValida2);
		dispositivosValidos.add(dispositivoConPosicionDeComunaInvalida);
		
		manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
		manejadorDeDispositivos.setListaDispositivos(dispositivosValidos);
		manejadorDeDispositivos.setCgpAdapter(cgpAdapter);
		
		comunaValida = new CriterioComuna(10);
		comunaInvalida = new CriterioComuna(100);

		accionValida = new NotificadorEmail(5.0, "emailAdmin@hotmail.com", adapterMail);
		
		dispositivosSeleccionados = new ArrayList<Dispositivo>();
		dispositivosSeleccionados.add(dispositivoConPosicionDeComunaValida);
		dispositivosSeleccionados.add(dispositivoConPosicionDeComunaInvalida);
		
		usuariosSeleccionados = new CriterioUsuariosSeleccionados(dispositivosSeleccionados);
		
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
		}
		
		Assert.assertTrue(dispositivoConPosicionDeComunaValida.getObservers().contains(accionValida));
		Assert.assertTrue(dispositivoConPosicionDeComunaValida2.getObservers().contains(accionValida));
	}
	
	@Test(expected = Exception.class)
	public void siEjecutoLaAccionConUnaComunaInvalidaEntoncesLaAccionNoEsAgregadaALasAccionesDeLosUsuarios() throws Exception{
			accionValidaConComunaInvalida.ejecutar();
	}
	
//	@Test
//	public void si

}
