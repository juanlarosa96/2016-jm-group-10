package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import adapters.AdapterMail;
import adapters.CgpAdapter;
import adapters.ServicioExternoCGP;
import eventosBusqueda.InteresadoEnBusquedas;
import eventosBusqueda.NotificadorEmail;
import pois.Dispositivo;
import pois.ManejadorDeDispositivos;
import procesos.AccionQuitaarAccionesParaLosUsuarios;
import procesos.CriterioComuna;
import procesos.CriterioTodosLosUsuarios;
import procesos.CriterioUsuariosSeleccionados;
import procesos.ExceptionErrorEjecucionDeAccion;

public class AgregarAccionesUsuariosTest {

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
	private Point posicionComunaValida;
	private Point posicionOtraComunaValida;
	private List<Dispositivo> dispositivosSeleccionados;
	private AccionQuitaarAccionesParaLosUsuarios agregarAccionValidaConComunaValida;
	private AccionQuitaarAccionesParaLosUsuarios agregarAccionValidaConComunaInvalida;
	private AccionQuitaarAccionesParaLosUsuarios agregarAccionValidaTodosLosUsuarios;
	private AccionQuitaarAccionesParaLosUsuarios agregarAccionValidaUsuariosSeleccionados;
	private List<Dispositivo> dispositivosValidos;
	private CgpAdapter cgpAdapter;
	private ServicioExternoCGP servicioExternoCGP;

	@Before
	public void init() {
		posicionComunaValida = new Point(-34.621891, -58.509017);
		posicionOtraComunaValida = new Point(-34.620999, -58.416590);

		dispositivoConPosicionDeComunaValida = new Dispositivo("dispositivoValido", posicionComunaValida);
		dispositivoConPosicionDeComunaValida2 = new Dispositivo("dispositivoValido2", posicionComunaValida);
		dispositivoConPosicionDeComunaInvalida = new Dispositivo("dispostivoValido3", posicionOtraComunaValida);

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
		
		todosLosUsuarios = new CriterioTodosLosUsuarios();

		agregarAccionValidaConComunaValida = new AccionQuitaarAccionesParaLosUsuarios(accionValida, comunaValida);
		agregarAccionValidaConComunaInvalida = new AccionQuitaarAccionesParaLosUsuarios(accionValida, comunaInvalida);
		agregarAccionValidaTodosLosUsuarios = new AccionQuitaarAccionesParaLosUsuarios(accionValida, todosLosUsuarios);
		agregarAccionValidaUsuariosSeleccionados = new AccionQuitaarAccionesParaLosUsuarios(accionValida,
				usuariosSeleccionados);
	}

	@Test
	public void siEjecutoLaAccionConCriterioDeComunaValidaEntoncesLaAccionEsAgregadaALasAccionesDeLosUsuarios() {
		try {
			agregarAccionValidaConComunaValida.ejecutar();
		} catch (Exception e) {
		}

		Assert.assertTrue(dispositivoConPosicionDeComunaValida.getObservers().contains(accionValida));
		Assert.assertTrue(dispositivoConPosicionDeComunaValida2.getObservers().contains(accionValida));
	}

	@Test(expected = ExceptionErrorEjecucionDeAccion.class)
	public void siEjecutoLaAccionConCriterioDeComunaInvalidaEntoncesLaAccionNoEsAgregadaALasAccionesDeLosUsuarios()
			throws Exception {
		agregarAccionValidaConComunaInvalida.ejecutar();
	}

	@Test
	public void siEjecutoLaAccionConCriterioTodosLosUsuariosEntoncesLaAccionEsAgregadaALasAccionesDeTodosLosUsuarios() {
		try {
			agregarAccionValidaTodosLosUsuarios.ejecutar();
		} catch (Exception e) {
		}
		
		List<Dispositivo> dispositivos = manejadorDeDispositivos.getListaDispositivos();
		
		Assert.assertTrue(dispositivos.stream().allMatch(disp -> disp.getObservers().contains(accionValida)));
	}
	
	@Test
	public void siEjecutoLaAccionConCriterioUsuariosSeleccionadosEntoncesLaAccionEsAgregadaALasAccionesDeTodosLosUsuariosSeleccionados() {
		try {
			agregarAccionValidaUsuariosSeleccionados.ejecutar();
		} catch (Exception e) {
		}
		
		Assert.assertTrue(dispositivosSeleccionados.stream().allMatch(disp -> disp.getObservers().contains(accionValida)));
	}

}
