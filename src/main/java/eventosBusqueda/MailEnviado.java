package eventosBusqueda;

import org.joda.time.LocalDateTime;

public class MailEnviado {

	private String mailOrigen;
	private String mailDestinatario;
	private String asunto;
	private LocalDateTime fecha;
	private String mensaje;
	
	
	public MailEnviado(String mailOrigen, String mailDestinatario, String asunto, LocalDateTime fecha, String mensaje) {
		this.mailOrigen = mailOrigen;
		this.mailDestinatario = mailDestinatario;
		this.asunto = asunto;
		this.fecha = fecha;
		this.mensaje = mensaje;
	}
	
	
	
}
