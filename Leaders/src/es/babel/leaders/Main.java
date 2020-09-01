package es.babel.leaders;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		// Entrada del array de lideres.
		// Entradas de K = longitud del segmento
		// Entrada de M -> valor mas alto del array
		
		
		int[] array1= new int[] {1,2,2,1,2}; // K4 lideres 2 y 3 OK
		int[] array2= new int[] {1,1,1,1,2,2,2}; // k3 1 y 2 KO sigue faltando combinar resultados
		int[] array3= new int[] {2,1,3,2,3,3,3};
		int[] array4= new int[] {2,1,3,1,2,2,3};
		int[] array5= new int[] {2,2,2,1,1,1,1};
		int[] array6= new int[] {2,2,2,3,1,2,4,4,2,1};
		int[] array7= new int[] {3,3,4,1,2,2,4,4,2,1,2,2,2,3,1,2,2,2,2,1};
		int[] array8= new int[] {1,1,2,2,2,1,2,2,1,1,2,2,1,2,1};
		int[] arrayPruebas = array5;
		
		int N = arrayPruebas.length;
		int K = 2;
		int M = 3;
		
		/*** Construccion array de pruebas automatico, setear a true **/
		boolean activarPruebaAuto = false;
		long tiempoInicio = 0L;
		long tiempoFin = 0L;
		Solution s = new Solution();
		int [] salida = null;
		
		if (activarPruebaAuto) {
			int longitudPrueba = 30;
			int[] arrayPruebasAuto = new int[longitudPrueba];
			
			// Tambien pintamos el array de entrada para poder verlo */
			System.out.println("Array Generado automaticamente de longitud "+longitudPrueba);
			System.out.print("[");
			for (int i=0; i<longitudPrueba;i++) {	
					arrayPruebasAuto[i] = ThreadLocalRandom.current().nextInt(1, M );
					if (i!= 0) 
						System.out.print(",");
					System.out.print(arrayPruebasAuto[i]);
			}
			System.out.println("]");
			tiempoInicio = System.currentTimeMillis();
			salida = s.solution(K, M, arrayPruebasAuto);
			tiempoFin = System.currentTimeMillis();		
		}else { System.out.println("Array Manual");
				System.out.print("[");
				for (int i = 0; i< arrayPruebas.length;i++) {
					if (i!= 0) 
						System.out.print(",");
					System.out.print(arrayPruebas[i]);
				}
				System.out.println("]");
				tiempoInicio = System.currentTimeMillis();
				salida = s.solution(K, M, arrayPruebas);
				tiempoFin = System.currentTimeMillis();
		}
		
		/* Pintamos resultados por pantalla*/
		System.out.println("Array de lideres");
		System.out.print("[");
			for (int i = 0; i< salida.length;i++) {
				if (i!= 0) 
					System.out.print(",");
				System.out.print(salida[i]);
			}
		System.out.println("]");
		
		System.out.println("Tiempo de proceso "+ (tiempoFin-tiempoInicio) +" milisegundos." );
	}

}
