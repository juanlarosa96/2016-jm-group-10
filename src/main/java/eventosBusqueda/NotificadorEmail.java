package eventosBusqueda;

import adapters.AdapterMail;

public class NotificadorEmail implements InteresadoEnBusquedas {
	
	Double demoraMaximaEnSegundos;
	String emailAdmin;
	private AdapterMail adapterMail;
	
	public NotificadorEmail(Double demoraMaximaEnSegundos, String emailAdmin,AdapterMail unAdapter){
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
		this.emailAdmin=emailAdmin;
		this.adapterMail=unAdapter;
	}
	
	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		//delegar en metodos privados
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
