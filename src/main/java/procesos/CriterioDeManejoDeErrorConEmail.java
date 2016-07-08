package procesos;

import adapters.AdapterMail;

public class CriterioDeManejoDeErrorConEmail implements CriterioDeManejoDeError {

	private AdapterMail adapterMail;
	private String emailAdmin;

	public CriterioDeManejoDeErrorConEmail(AdapterMail adapterMail, String emailAdmin) {
		this.adapterMail = adapterMail;
		this.emailAdmin = emailAdmin;
	}

	@Override
	public void manejarError(Proceso proceso) {

		adapterMail.enviarMailPorErrorDeProceso(proceso, emailAdmin);

		ResultadoEjecucion resultadoEjecucion = ResultadoEjecucion
				.dameUnResultadoDeErrorConElMensaje("Error. Email enviado.");

		proceso.setResultadoEjecucion(resultadoEjecucion);

	}

}
