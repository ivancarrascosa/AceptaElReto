package cosasdelaweb;

import java.util.*;

public class Ventas {
	public static void main(String[] args) {
		// creo un arraylist de double
		ArrayList<Double> ventas = new ArrayList<>();

		// Creo el escáner que voy a usar para leer lo que se introduzca en pantalla
		Scanner reader = new Scanner(System.in);

		// Creo una variable para guardar cada numero que se introduzca
		double ventaTemp;

		// creo el array que voy a utilizar
		double ventasSemanaTemp[] = new double[6];

		// Pido las ventas de cada día hasta hasta que el usuario introduzca -1
		do {
			System.out.println("Indique las ventas del día:");
			ventaTemp = reader.nextDouble();
			if (ventaTemp != -1) {
				ventas.add(ventaTemp);
			}
		} while (ventaTemp != -1);

		// Voy dividiendo la lista de 6 en 6 (que será cada semana) y les aplico la
		// función que me devuelve el máximo, el mínimo y si el domingo se ha vendido
		// más que la media
		for (int i = 0; i < ventas.size(); i++) {
			ventasSemanaTemp[i % 6] = ventas.get(i);
			if (i % 6 == 5) {
				System.out.println(MaxMinMayorQueMedia(ventasSemanaTemp));
			}
		}
		reader.close();
	}

	public static String MaxMinMayorQueMedia(double[] t) {
		// Creo una variable para dar el string de respuesta
		String res = "";
		// Creo una variable para almacenar la media de ventas de la semana
		double media = 0;

		// Creo una variable para almacenar el dia de mas ventas
		double ventasMax = Integer.MIN_VALUE;

		// Creo una variable para almacenar el dia de menos ventas
		double ventasMin = Integer.MAX_VALUE;

		// Creo una variable String que diga si el domingo ha superado la media o no
		String domingoMayorMedia;

		// Creo dos variables, para si hay empate en el mínimo o en el máximo
		String empateMax = "";
		String empateMin = "";

		// Por cada elemento de la tabla compruebo si es mayor o menor que el máximo y
		// el mínimo respectivamente, y en caso de que sea igual establezco que ha
		// habido un empate
		for (int i = 0; i < t.length; i++) {
			if (t[i] <= ventasMin) {
				if (t[i] == ventasMin) {
					empateMin = "EMPATE";
				} else {
					empateMin = "";
				}
				ventasMin = t[i];
			}
			if (t[i] >= ventasMax) {
				if (t[i] == ventasMax) {
					empateMax = "EMPATE";
				} else {
					empateMax = "";
				}
				ventasMax = t[i];
			}
			media += t[i] / 7;
		}
		if (empateMax.equals("")) {
			res += convertirNumeroDia(encontrarPosicionNumero(t, ventasMax)) + " ";
		} else {
			res += empateMax + " ";
		}
		if (empateMin.equals("")) {
			res += convertirNumeroDia(encontrarPosicionNumero(t, ventasMin)) + " ";
		} else {
			res += empateMin + " ";
		}

		if (t[t.length - 1] < media) {
			domingoMayorMedia = "NO";
		} else {
			domingoMayorMedia = "SI";
		}

		res += domingoMayorMedia;

		return res;
	}

	public static int encontrarPosicionNumero(double[] t, double valor) {
		// Creo una variable para devolver la respuesta
		int res = -1;
		for (int i = 0; i < t.length && res == -1; i++) {
			if (t[i] == valor) {
				res = i;
			}
		}
		return res;
	}

	public static String convertirNumeroDia(int n) {
		// Creo un string para devolver el dia que es
		String dia = "";
		switch (n) {
		case 0 -> dia = "MARTES";
		case 1 -> dia = "MIÉRCOLES";
		case 2 -> dia = "JUEVES";
		case 3 -> dia = "VIERNES";
		case 4 -> dia = "SÁBADO";
		case 5 -> dia = "DOMINGO";
		}
		return dia;
	}

}