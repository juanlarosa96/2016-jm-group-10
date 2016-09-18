package eventosBusqueda;

import javax.persistence.Embeddable;
import org.joda.time.LocalDateTime;

@Embeddable
public class MailEnviado {

	private String mailOrigen;
	private String mailDestinatario;
	private String asunto;
	private LocalDateTime fecha;
	private String mensaje;
	
	@SuppressWarnings("unused")
	private MailEnviado(){}
	
	public String getMailOrigen() {
		return mailOrigen;
	}

	public void setMailOrigen(String mailOrigen) {
		this.mailOrigen = mailOrigen;
	}

	public String getMailDestinatario() {
		return mailDestinatario;
	}

	public void setMailDestinatario(String mailDestinatario) {
		this.mailDestinatario = mailDestinatario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public MailEnviado(String mailOrigen, String mailDestinatario, String asunto, LocalDateTime fecha, String mensaje) {
		this.mailOrigen = mailOrigen;
		this.mailDestinatario = mailDestinatario;
		this.asunto = asunto;
		this.fecha = fecha;
		this.mensaje = mensaje;
	}
	
	
	
}
