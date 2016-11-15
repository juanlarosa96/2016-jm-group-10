package herramientas;

import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.Converters;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import converters.BigDecimalConverter;
import converters.DateTimeConverter;
import converters.LocalTimeConverter;
import eventosBusqueda.ResultadoBusqueda;

public class PersistidorMongo {

	private Morphia persistidor = new Morphia();
	private Datastore datastore;

	public void inicializarDB(String nombreDB) {
		persistidor.mapPackage("eventosBusqueda");
		persistidor.mapPackage("pois");
		datastore = persistidor.createDatastore(new MongoClient(), nombreDB);
		datastore.ensureIndexes();

		Converters converters = persistidor.getMapper().getConverters();
		converters.addConverter(BigDecimalConverter.class);
		converters.addConverter(DateTimeConverter.class);
		converters.addConverter(LocalTimeConverter.class);

	}

	public List<ResultadoBusqueda> obtenerTodosLosResultadosBusqueda() {
		return datastore.createQuery(ResultadoBusqueda.class).asList();
	}

	public void guardar(ResultadoBusqueda busq) {
		datastore.save(busq);
	}

	public void borrarTodosLosResultadosBusquedaDeBD() {
		Query<ResultadoBusqueda> resultadosABorrar = datastore.createQuery(ResultadoBusqueda.class);
		datastore.delete(resultadosABorrar);
	}

	public ResultadoBusqueda obtenerResultadoBusqueda(String id) {
		List<ResultadoBusqueda> busquedas = this.obtenerTodosLosResultadosBusqueda();

		return busquedas.stream().filter(busq -> busq.getId().toString().equals(id)).collect(Collectors.toList())
				.get(0);

	}

}
