package herramientas;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import converters.BigDecimalConverter;
import eventosBusqueda.ResultadoBusqueda;

public class PersistidorMongo {
	
	private Morphia persistidor = new Morphia();
	private Datastore datastore;

	public void inicializarDB(String nombreDB) {
		persistidor.mapPackage("eventosBusqueda");
		persistidor.mapPackage("pois");
		datastore = persistidor.createDatastore(new MongoClient(), nombreDB);
		datastore.ensureIndexes();
		persistidor.getMapper().getConverters().addConverter(BigDecimalConverter.class);
	}

	public List<ResultadoBusqueda> buscarTodosLosResultadosBusqueda() {
		return datastore.createQuery(ResultadoBusqueda.class).asList();
	}

	public void guardar(ResultadoBusqueda busq) {
		datastore.save(busq);
	}

	public void borrarTodosLosResultadosBusquedaDeBD() {
		Query<ResultadoBusqueda> resultadosABorrar = datastore.createQuery(ResultadoBusqueda.class);
		datastore.delete(resultadosABorrar);
	}

}
