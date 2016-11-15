package server;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import herramientas.EntityManagerHelper;
import pois.Banco;
import pois.Direccion;
import pois.Dispositivo;
import pois.ManejadorDeDispositivos;
import pois.POI;
import pois.Posicion;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init(){

		//Persistir objetos
		
		//POIs
		
		List<String> etiquetas = new ArrayList<String>(){{
			add("banco");
			add("comafi");
			add("tarjeta");
		}};
		
		Direccion direccion = new Direccion("Rosario", 72, "Av La Plata", "Senillosa", null, null, 1424, "CABA",
				"Caballito", "CABA", "Argentina");
		
		POI poi = new Banco(new Posicion(12.50,25.55), "Banco Comafi Caballito", direccion, etiquetas);
		
		EntityManagerHelper.persistir(poi);
		
		
		//Dispositivos
		
		Dispositivo terminalAbasto = new Dispositivo("Terminal Abasto", new Posicion(25.55, 12.2));
		Dispositivo terminalRecoleta = new Dispositivo("Terminal Recoleta", new Posicion(30.55, 22.2));
		
		ManejadorDeDispositivos.getInstance().agregarDispositivo(terminalAbasto);
		ManejadorDeDispositivos.getInstance().agregarDispositivo(terminalRecoleta);
		
	}
}
