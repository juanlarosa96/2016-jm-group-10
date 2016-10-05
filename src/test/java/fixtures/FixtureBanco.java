package fixtures;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import pois.Banco;
import pois.Direccion;
import pois.Posicion;

public class FixtureBanco {
	//Banco Provincia
	private static Direccion direccionBancoProvincia = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
			"CABA", "Floresta", "CABA", "Argentina");
	private static Posicion posicionBancoProvincia = new Posicion(-34.6327475, -58.4851585);
	private static List<String> etiquetasBancoProvincia = new ArrayList<String>() {
		{
			add("banco");
			add("valido");
			add("depositos");
			add("extracciones");
			add("cajero");
			add("tarjeta");
			add("credito");
			add("debito");
		}
	};
	
	private static Banco bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia, etiquetasBancoProvincia);
	
	//Posiciones
	private static Posicion posicionLejanaBancoProvincia = new Posicion(-34.6184994, -58.4368164);
	private static Posicion posicionCercanaBancoProvincia = new Posicion(-34.6327474, -58.4851584);
	
	//Horarios
	private static DateTime horarioBancario = new DateTime(2016, 4, 4, 10, 0);
	private static DateTime horarioNoBancario = new DateTime(2016, 4, 5, 2, 30);
	private static DateTime horarioCierreBanco = new DateTime(2016, 5, 20, 15, 0, 0);
	private static DateTime horarioAperturaBanco = new DateTime(2016, 5, 20, 9, 0);
	
	public static Banco dameUnBancoValido() {
		return bancoProvincia;
	}
	public static Banco dameOtroBancoValidoConLaMismaPosicion(){
		Banco bancoValido = bancoProvincia;
		bancoValido.setNombre("Banco Santander");
		return bancoValido;
	}
	
	public static Posicion getPosicionCercanaABancoValido() {
		return posicionCercanaBancoProvincia;
	}

	public static Posicion getPosicionLejanaABancoValido() {
		return posicionLejanaBancoProvincia;
	}

	public static DateTime getHorarioBancario() {
		return horarioBancario;
	}

	public static DateTime getHorarioNoBancario() {
		return horarioNoBancario;
	}

	public static DateTime getHorarioCierreBanco() {
		return horarioCierreBanco;
	}

	public static DateTime getHorarioAperturaBanco() {		
		return horarioAperturaBanco ;
	}
	
	
}
