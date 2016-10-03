package eventosBusqueda;

import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import pois.POIDTO;

@Entity
public class ResultadoBusqueda {

	@Id
	private ObjectId id;

	private String nombreTerminal;

	@Embedded
	private List<POIDTO> poisEncontrados;

	private String stringFecha;

	private Double demoraEnSegundos;
	private String descripcionBuscada;

	@SuppressWarnings("unused")
	private ResultadoBusqueda() {
	}

	/**
	 * La fecha de la busqueda debe ser distinto de null
	 */
	public ResultadoBusqueda(String nombreTerminal, List<POIDTO> listaPoisEncontrados, DateTime fecha,
			Double demoraEnSegundos, String descripcionBuscada) {
		this.nombreTerminal = nombreTerminal;
		this.poisEncontrados = listaPoisEncontrados;
		this.stringFecha = fecha.toString();
		this.demoraEnSegundos = demoraEnSegundos;
		this.descripcionBuscada = descripcionBuscada;
	}

	public ObjectId getId() {
		return id;
	}

	public List<POIDTO> getPoisEncontrados() {
		return poisEncontrados;
	}

	public void setPoisEncontrados(List<POIDTO> poisEncontrados) {
		this.poisEncontrados = poisEncontrados;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

	public void setFecha(DateTime fecha) {
		this.stringFecha = fecha.toString();
	}

	public void setDemoraEnSegundos(Double demoraEnSegundos) {
		this.demoraEnSegundos = demoraEnSegundos;
	}

	public void setDescripcionBuscada(String descripcionBuscada) {
		this.descripcionBuscada = descripcionBuscada;
	}

	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public Integer getCantResultados() {
		if (poisEncontrados != null)
			return poisEncontrados.size();
		else
			return 0;
	}

	public DateTime getFecha() {
		return DateTime.parse(stringFecha);
	}

	public Double getDemoraEnSegundos() {
		return demoraEnSegundos;
	}

	public String getDescripcionBuscada() {
		return descripcionBuscada;
	}

}
