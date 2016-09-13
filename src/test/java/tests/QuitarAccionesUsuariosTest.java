package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pois.Posicion;

import adapters.AdapterMail;
import adapters.CgpAdapter;
import adapters.ServicioExternoCGP;
import eventosBusqueda.InteresadoEnBusquedas;
import eventosBusqueda.NotificadorEmail;
import pois.Dispositivo;
import pois.ManejadorDeDispositivos;
import procesos.AccionQuitarAccionesParaLosUsuarios;
import procesos.CriterioComuna;
import procesos.CriterioTodosLosUsuarios;
import procesos.CriterioUsuariosSeleccionados;
import procesos.ExceptionErrorEjecucionDeAccion;

public class QuitarAccionesUsuariosTest {

	private CriterioComuna comunaValida;
	private CriterioComuna comunaInvalida;
	private CriterioTodosLosUsuarios todosLosUsuarios;
	private CriterioUsuariosSeleccionados usuariosSeleccionados;
	private InteresadoEnBusquedas accionValida;
	private AdapterMail adapterMail;
	private ManejadorDeDispositivos manejadorDeDispositivos;
	private Dispositivo dispositivoConPosicionDeComunaValida;
	private Dispositivo dispositivoConPosicionDeComunaValida2;
	private Dispositivo dispositivoConPosicionDeComunaInvalida;
	private Posicion posicionComunaValida;
	private Posicion posicionOtraComunaValida;
	private List<Dispositivo> dispositivosSeleccionados;
	private AccionQuitarAccionesParaLosUsuarios quitarAccionValidaConComunaValida;
	private AccionQuitarAccionesParaLosUsuarios quitarAccionValidaConComunaInvalida;
	private AccionQuitarAccionesParaLosUsuarios quitarAccionValidaTodosLosUsuarios;
	private AccionQuitarAccionesParaLosUsuarios quitarAccionValidaUsuariosSeleccionados;
	private List<Dispositivo> dispositivosValidos;
	private CgpAdapter cgpAdapter;
	private ServicioExternoCGP servicioExternoCGP;

	@Before
	public void init() {
		accionValida = new NotificadorEmail(5.0, "emailAdmin@hotmail.com", adapterMail);

		posicionComunaValida = new Posicion(-34.621891, -58.509017);
		posicionOtraComunaValida = new Posicion(-34.620999, -58.416590);

		dispositivoConPosicionDeComunaValida = new Dispositivo("dispositivoValido", posicionComunaValida);
		dispositivoConPosicionDeComunaValida2 = new Dispositivo("dispositivoValido2", posicionComunaValida);
		dispositivoConPosicionDeComunaInvalida = new Dispositivo("dispostivoValido3", posicionOtraComunaValida);
		
		dispositivoConPosicionDeComunaValida.agregarInteresadoEnBusquedas(accionValida);
		dispositivoConPosicionDeComunaValida2.agregarInteresadoEnBusquedas(accionValida);
		dispositivoConPosicionDeComunaInvalida.agregarInteresadoEnBusquedas(accionValida);

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

		dispositivosSeleccionados = new ArrayList<Dispositivo>();
		dispositivosSeleccionados.add(dispositivoConPosicionDeComunaValida);
		dispositivosSeleccionados.add(dispositivoConPosicionDeComunaInvalida);

		usuariosSeleccionados = new CriterioUsuariosSeleccionados(dispositivosSeleccionados);
		
		todosLosUsuarios = new CriterioTodosLosUsuarios();

		quitarAccionValidaConComunaValida = new AccionQuitarAccionesParaLosUsuarios(accionValida, comunaValida);
		quitarAccionValidaConComunaInvalida = new AccionQuitarAccionesParaLosUsuarios(accionValida, comunaInvalida);
		quitarAccionValidaTodosLosUsuarios = new AccionQuitarAccionesParaLosUsuarios(accionValida, todosLosUsuarios);
		quitarAccionValidaUsuariosSeleccionados = new AccionQuitarAccionesParaLosUsuarios(accionValida,
				usuariosSeleccionados);
	}

	@Test
	public void siEjecutoLaAccionConCriterioDeComunaValidaEntoncesLaAccionEsEliminadaDeLasAccionesDeLosUsuarios() {
		try {
			quitarAccionValidaConComunaValida.ejecutar();
		} catch (Exception e) {
		}

		Assert.assertTrue(dispositivoConPosicionDeComunaValida.getObservers().isEmpty());
		Assert.assertTrue(dispositivoConPosicionDeComunaValida2.getObservers().isEmpty());
	}

	@Test(expected = ExceptionErrorEjecucionDeAccion.class)
	public void siEjecutoLaAccionConCriterioDeComunaInvalidaEntoncesLaAccionNoEsAgregadaALasAccionesDeLosUsuarios()
			throws Exception {
		quitarAccionValidaConComunaInvalida.ejecutar();
	}

	@Test
	public void siEjecutoLaAccionConCriterioTodosLosUsuariosEntoncesLaAccionEsAgregadaALasAccionesDeTodosLosUsuarios() {
		try {
			quitarAccionValidaTodosLosUsuarios.ejecutar();
		} catch (Exception e) {
		}
		
		List<Dispositivo> dispositivos = manejadorDeDispositivos.getListaDispositivos();
		
		Assert.assertTrue(dispositivos.stream().allMatch(disp -> disp.getObservers().isEmpty()));
	}
	
	@Test
	public void siEjecutoLaAccionConCriterioUsuariosSeleccionadosEntoncesLaAccionEsAgregadaALasAccionesDeTodosLosUsuariosSeleccionados() {
		try {
			quitarAccionValidaUsuariosSeleccionados.ejecutar();
		} catch (Exception e) {
		}
		
		Assert.assertTrue(dispositivosSeleccionados.stream().allMatch(disp -> disp.getObservers().isEmpty()));
	}

}
