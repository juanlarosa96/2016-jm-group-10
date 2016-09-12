package eventosBusqueda;

import javax.persistence.*;

import adapters.AdapterMail;

@Entity
public class NotificadorEmail extends InteresadoEnBusquedas {
	
	@Id @GeneratedValue
	private Integer id;
	
	private Double demoraMaximaEnSegundos;
	private String emailAdmin;
	
	@Transient
	private AdapterMail adapterMail;

	@SuppressWarnings("unused")
	private NotificadorEmail(){}
	
	public NotificadorEmail(Double demoraMaximaEnSegundos, String emailAdmin, AdapterMail unAdapter) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
		this.emailAdmin = emailAdmin;
		this.adapterMail = unAdapter;
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {

		if (this.deboNotificarBusqueda(unaBusqueda)) {
			this.notificarBusquedaPorMail(unaBusqueda);
		}
	}

	private void notificarBusquedaPorMail(ResultadoBusqueda unaBusqueda) {
		adapterMail.enviarMailPorBusquedaLenta(unaBusqueda, emailAdmin);

	}

	private boolean deboNotificarBusqueda(ResultadoBusqueda unaBusqueda) {

		return unaBusqueda.getDemoraEnSegundos() > this.demoraMaximaEnSegundos;
	}

	public void setDemoraMaximaEnSegundos(Double demoraMaximaEnSegundos) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public Double getDemoraMaximaEnSegundos() {
		return demoraMaximaEnSegundos;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}
	
	

}
