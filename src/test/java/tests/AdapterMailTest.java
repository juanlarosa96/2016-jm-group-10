package tests;

import adapters.AdapterMail;
import eventosBusqueda.Busqueda;
import eventosBusqueda.NotificadorEmail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class AdapterMailTest {
	
	private AdapterMail adapterMailMockeado;
	private NotificadorEmail notificadorEmail;
	private Busqueda busquedaConMayorTiempo;
	private Busqueda busquedaConMenorTiempo;
	

	@Before
	public void init(){
	adapterMailMockeado = mock(AdapterMail.class);
	busquedaConMayorTiempo = new Busqueda("terminalAbasto", 3, null, 10.0, "hospital");
	busquedaConMenorTiempo = new Busqueda("terminalCaballito", 4, null, 3.0, "cine");
	notificadorEmail= new NotificadorEmail(5.0,"admin@gmail.com",adapterMailMockeado);
	
	}
	
	@Test
	public void siNotificoUnaBusquedaQueDemoraMasQueElTiempoMaximoEntoncesElNotificadorEnviaElEmail(){
	
	notificadorEmail.notificarBusqueda(busquedaConMayorTiempo);
	verify(adapterMailMockeado).enviarMailPorBusquedaLenta(busquedaConMayorTiempo, "admin@gmail.com");

	}
	
	@Test
	public void siNotificoUnaBusquedaQueDemoraMenosQueElTiempoMaximoDelNotificadorEntoncesNoSeEnviaElEmail(){
	
	notificadorEmail.notificarBusqueda(busquedaConMenorTiempo);
	verify(adapterMailMockeado,Mockito.times(0)).enviarMailPorBusquedaLenta(busquedaConMenorTiempo, "admin@gmail.com");
	
	}

}
