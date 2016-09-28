package tests;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adapters.AdapterMail;
import eventosBusqueda.NotificadorEmail;
import eventosBusqueda.ResultadoBusqueda;
import herramientas.EntityManagerHelper;
import pois.POI;
import pois.POIDTO;

public class NotificadorEmailTest {
	
		private AdapterMail adapterMailMockeado;
		private NotificadorEmail notificadorEmail;
		private ResultadoBusqueda busqueda1;

		@Before
		public void init() {
			adapterMailMockeado = mock(AdapterMail.class);	
			
			busqueda1 = new ResultadoBusqueda("terminalAbasto", new ArrayList<POIDTO>() , null, 10.0, "hospital");
			
			notificadorEmail = new NotificadorEmail(5.0, "admin@gmail.com", adapterMailMockeado);
			
			EntityManagerHelper.persist(notificadorEmail);

		}
		
		@Test
		public void siNotificoUnaBusquedaEncuentroElMailEnLaLista(){
			notificadorEmail.notificarBusqueda(busqueda1);
			Assert.assertEquals(notificadorEmail.getMailsEnviados().size(),1,0);
			EntityManagerHelper.beginTransaction();
			EntityManagerHelper.flush();
			EntityManagerHelper.getEntityManager().detach(notificadorEmail);
			NotificadorEmail notificadorEmailEncontrado = EntityManagerHelper.getEntityManager().find(NotificadorEmail.class, notificadorEmail.getId());
			EntityManagerHelper.commit();
			Assert.assertEquals(notificadorEmailEncontrado.getMailsEnviados().size(),1,0);
		}
		
	}

	
