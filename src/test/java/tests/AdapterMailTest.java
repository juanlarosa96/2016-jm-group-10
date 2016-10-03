package tests;

import adapters.AdapterMail;
import eventosBusqueda.ResultadoBusqueda;
import eventosBusqueda.NotificadorEmail;
import pois.POIDTO;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AdapterMailTest {

	private AdapterMail adapterMailMockeado;
	private NotificadorEmail notificadorEmail;
	private ResultadoBusqueda busquedaConMayorTiempo;
	private ResultadoBusqueda busquedaConMenorTiempo;
	private ResultadoBusqueda busquedaConIgualTiempo;

	@Before
	public void init() {
		adapterMailMockeado = mock(AdapterMail.class);	
		
		busquedaConMayorTiempo = new ResultadoBusqueda("terminalAbasto", new ArrayList<POIDTO>() , DateTime.now(), 10.0, "hospital");
		busquedaConMenorTiempo = new ResultadoBusqueda("terminalCaballito", new ArrayList<POIDTO>()  , DateTime.now(), 3.0, "cine");
		busquedaConIgualTiempo = new ResultadoBusqueda("terminalRecoleta", new ArrayList<POIDTO>() , DateTime.now(), 5.0, "ropa");

		notificadorEmail = new NotificadorEmail(5.0, "admin@gmail.com", adapterMailMockeado);

	}

	@Test
	public void siNotificoUnaBusquedaQueDemoraMasQueElTiempoMaximoEntoncesElNotificadorEnviaElEmail() {

		notificadorEmail.notificarBusqueda(busquedaConMayorTiempo);
		verify(adapterMailMockeado).enviarMailPorBusquedaLenta(busquedaConMayorTiempo, "admin@gmail.com");

	}

	@Test
	public void siNotificoUnaBusquedaQueDemoraMenosQueElTiempoMaximoDelNotificadorEntoncesNoSeEnviaElEmail() {

		notificadorEmail.notificarBusqueda(busquedaConMenorTiempo);
		verify(adapterMailMockeado, Mockito.times(0)).enviarMailPorBusquedaLenta(busquedaConMenorTiempo,
				"admin@gmail.com");

	}

	@Test
	public void siNotificoUnaBusquedaQueDemoraLoMismoQueElTiempoMaximoDelNotificadorEntoncesNoSeEnviaElEmail() {

		notificadorEmail.notificarBusqueda(busquedaConIgualTiempo);
		verify(adapterMailMockeado, Mockito.times(0)).enviarMailPorBusquedaLenta(busquedaConIgualTiempo,
				"admin@gmail.com");

	}

}
