package eventosBusqueda;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import adapters.AdapterMail;

@Entity
public class NotificadorEmail extends InteresadoEnBusquedas {

	private Double demoraMaximaEnSegundos;
	private String emailAdmin;

	@Transient
	private AdapterMail adapterMail;
	
	//private List<MailEnviado> mailsEnviados;

	@SuppressWarnings("unused")
	private NotificadorEmail() {
	}

	public NotificadorEmail(Double demoraMaximaEnSegundos, String emailAdmin, AdapterMail unAdapter) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
		this.emailAdmin = emailAdmin;
		this.adapterMail = unAdapter;
		//mailsEnviados = new ArrayList<MailEnviado>();
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {

		if (this.deboNotificarBusqueda(unaBusqueda)) {
			this.notificarBusquedaPorMail(unaBusqueda);
		}
	}

	private void notificarBusquedaPorMail(ResultadoBusqueda unaBusqueda) {
		adapterMail.enviarMailPorBusquedaLenta(unaBusqueda, emailAdmin);

		/*mailsEnviados.add(new MailEnviado("dds@utn.com.ar", emailAdmin, "Busqueda Lenta", LocalDateTime.now(),
				this.mensajeBusquedaLenta(unaBusqueda)));*/

	}

	/*private String mensajeBusquedaLenta(ResultadoBusqueda unaBusqueda) {
		return "Se registro una busqueda desde la terminal " + unaBusqueda.getNombreTerminal() + " con una demora de "
				+ unaBusqueda.getDemoraEnSegundos().toString() + "y con " + unaBusqueda.getCantResultados().toString()
				+ " pois encontrados";
	}*/

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
