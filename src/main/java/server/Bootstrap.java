package server;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import herramientas.EntityManagerHelper;
import pois.Banco;
import pois.Comercio;
import pois.Direccion;
import pois.Dispositivo;
import pois.FranjaHoraria;
import pois.ManejadorDeDispositivos;
import pois.POI;
import pois.Posicion;
import pois.Rubro;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public void init() {

		// Persistir objetos

		// POIs

		// Banco

		List<String> etiquetasBancoComafi = new ArrayList<String>() {
			{
				add("banco");
				add("comafi");
				add("tarjeta");
			}
		};

		Direccion direccionBancoComafi = new Direccion("Rosario", 72, "Av La Plata", "Senillosa", null, null, 1424,
				"CABA", "Caballito", "CABA", "Argentina");

		POI bancoComafi = new Banco(new Posicion(-34.618500, -58.429600), "Banco Comafi Caballito",
				direccionBancoComafi, etiquetasBancoComafi);

		EntityManagerHelper.persistir(bancoComafi);

		// Comercio

		List<String> etiquetasKiosco = new ArrayList<String>() {
			{
				add("nerds");
				add("kiosco");
				add("rueditas");
				add("chocolate");
			}
		};

		List<FranjaHoraria> horariosKiosco = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(2, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(3, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(4, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(5, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(6, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(7, new LocalTime(0, 0), new LocalTime(23, 59)));
			}
		};

		Direccion direccionKiosco = new Direccion("Laprida", 1900, "Peña", "Pacheco de Melo", null, null, 1425, "CABA",
				"Caballito", "CABA", "Argentina");

		POI kiosco = new Comercio(new Rubro("Kioco", 0.2), horariosKiosco, new Posicion(-34.588471, -58.400940),
				"Kiosco Esquina de Rosario", direccionKiosco, etiquetasKiosco);

		EntityManagerHelper.persistir(kiosco);

		// Dispositivos

		Dispositivo terminalAbasto = new Dispositivo("Terminal Abasto", new Posicion(-34.603329, -58.410789));
		Dispositivo terminalRecoleta = new Dispositivo("Terminal Recoleta", new Posicion(-34.5862397, -58.4072864));

		ManejadorDeDispositivos.getInstance().agregarDispositivo(terminalAbasto);
		ManejadorDeDispositivos.getInstance().agregarDispositivo(terminalRecoleta);

	}
}
