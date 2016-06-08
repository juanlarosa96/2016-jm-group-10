package eventosBusqueda;

import adapters.AdapterMail;

public class NotificadorEmail implements InteresadoEnBusquedas {
	
	Double demoraMaximaEnSegundos;
	String emailAdmin;
	AdapterMail adapterMail;
	
	public NotificadorEmail(Double demoraMaximaEnSegundos, String emailAdmin){
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
		this.emailAdmin=emailAdmin;
	}
	
	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		
		if(unaBusqueda.getDemoraEnSegundos()> this.demoraMaximaEnSegundos){			
			adapterMail.enviarMailPorBusquedaLenta(unaBusqueda,emailAdmin);			
		}
	}

	public void setDemoraMaximaEnSegundos(Double demoraMaximaEnSegundos) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

}
