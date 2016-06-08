package eventosBusqueda;

public class NotificadorEmail implements InteresadoEnBusquedas {
	
	Double demoraMaximaEnSegundos;
	String emailAdmin;
	
	public NotificadorEmail(Double demoraMaximaEnSegundos, String emailAdmin){
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
		this.emailAdmin=emailAdmin;
	}
	
	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		
		if(unaBusqueda.getDemoraEnSegundos()> this.demoraMaximaEnSegundos){			
			//mandar mail a emailAdmin
			//incluir en el mail los elementos de la busqueda realizada
			
		}
	}

	public void setDemoraMaximaEnSegundos(Double demoraMaximaEnSegundos) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

}
