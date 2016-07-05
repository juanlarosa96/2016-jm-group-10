package procesos;

public class CriterioDeManejoDeErrorConReintentos implements CriterioDeManejoDeError {

	private Integer cantReintentos;
	private Integer nroIntento;
	private CriterioDeManejoDeError criterioManejoDeError;

	public CriterioDeManejoDeErrorConReintentos(Integer cantReintentos, CriterioDeManejoDeError criterioManejoDeError) {

		this.cantReintentos = cantReintentos;
		this.nroIntento = 0;
		this.criterioManejoDeError = criterioManejoDeError;
	}

	@Override
	public void manejarError(Proceso proceso) {

		if (nroIntento < this.cantReintentos) {
			this.nroIntento++;
			proceso.ejecutar();
		} else {
			criterioManejoDeError.manejarError(proceso);
		}

	}

}
