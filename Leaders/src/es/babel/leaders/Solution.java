package es.babel.leaders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
//import org.apache.log4j.Logger;

import es.babel.leaders.ValorPosicion;

public class Solution {
	//private static final Logger log = Logger.getLogger(Solution.class);

	private   HashMap<Integer, ValorPosicion> apariciones = new HashMap<Integer, ValorPosicion>();	
	int[] leaders = null;
	

	private  HashMap<Integer, HashMap<Integer,OperacionSegmento>> hashSegmentos =  new HashMap<Integer, HashMap<Integer,OperacionSegmento>>();
	private boolean puedeSerLeader(int leaderLimit, int numApariciones, int segmentSize) {
		/*
		 * Si por ejemplo, tenemos {1,1,1,1,2,2,2}
		 * Con segmento2, para que el uno sea leader, hay que verificar que haya un segment
		 * que haga que 
		 */
		return (leaderLimit < numApariciones+segmentSize);
		
	}
	
	private boolean determinarSiHavSegmentosQueNoAfecte(ArrayList posiciones,int longitudSegmento, int longitudArray){
	boolean encontrado = false;
	/*
	 * Desde 0 hasta la longitud del array, mientras no se haya encontrado, 
	 * se buscaran X numeros consecutivos que no esten ocupados por los valores de posicion.
	 * Si la salida es true, quiere decir que si ya era un leader por si mismo efectivamente lo
	 * es. Estudia solo las posiciones de un numero concreto, que no el Array A[n] principal
	 * y de este array de posiciones, podria no recorrerse todo, basta con encontrar un segmento.
	 * 
	 */
	int posicionActual = 0;
	boolean KO = false;
	int leaderLimit = longitudArray/2;
	int i = 0;
	if (posiciones.size() > leaderLimit) {
		ArrayList <Integer> posicionesABuscar  = new ArrayList(longitudSegmento);
		while (!encontrado && (posicionActual < longitudArray) ){
			// calculamos las posiciones
			i= 1;
			while (!KO && !encontrado) {
				KO = (posiciones.contains(posicionActual) || (posicionActual == longitudArray));
				encontrado = !KO && (i == longitudSegmento );
				i++;
				posicionActual++;
			}
			
			KO =false;
		}
	}
	return encontrado;
	}
		
	
	public  int[] solution( int K, int M, int[] A) {	
		int[] salida = new int[10];
		ArrayList<Integer>  alSalida = new ArrayList();
		int leaderLimit = A.length /2;
		// Controles iniciales.
		if (validar(K,M,A)) {
			// Una vez validado hay que comprobar
			// 1.- En un primer momento, no podemos afirmar si hay leaders porque
			// habría que saber si no hay ningun segmento que haga variar su numero
			// 2.- Si dado el valor del segmento k, el numero puede ser o no leader
			// Ejemplo si el array es de 7, necesita 4 (more than a half) para ser leader
			// Entonces, podran ser lideres aquello nummeros que sus apariciones 
			// sumadas al tamaño del segmento sean mayores que la mitad mas uno 
			// de la longitud del array. Con eso desecharemos algunos, y los demás 
			// hay que calcularlos. Ahí entra en juego el array de posiciones. 
			// En necesario saber si hay posiciones que puedan pertenecer a un 
			// segmento de longitud suficiente como para sumar las apariciones necesarias
			// para ser un leader.
			Set<Integer> claves =apariciones.keySet();
			
			Iterator itClaves = claves.iterator();
			
			ValorPosicion vp = null;
			int i=0;
			while (itClaves.hasNext()) {
				 int key = (int)itClaves.next();
				 vp = apariciones.get(key);
				 
					 // El primero es un poco diferente, ya que buscamos que el 
					 // segmento no nos afecte
					if (determinarSiHavSegmentosQueNoAfecte(vp.getPosiciones(),K, A.length)) {
						 /// tendríams un leader, si su numero de apariciones es mayor que la mitad
						if (vp.getNumeroApariciones() > leaderLimit) {
							vp.setisLeader(true);
							//log.info("LEADER "+key);
							if (salida!=null) {
								salida[i]=key;
								i++;
								if (!alSalida.contains(key))
								     alSalida.add(key);
							}
						}
						
					 }
				 
			}
			
			int numSegmentos = calcularNumeroSegmentos(A,K,apariciones,hashSegmentos);
			
			// calculados los segmentos, vemos primero en el hash
		    Set <Integer> clavesEvaluadas = null;	
		    Iterator itClavesEnSegmento =  null;
		    
		    int clave = -1;
		    HashMap<Integer,OperacionSegmento> alSegmento = new HashMap<Integer,OperacionSegmento>();
		    OperacionSegmento op = null;
		    int claveEnSegmentos = 0;

		    
		   // System.out.println("alSalida es "+alSalida+" antes de entrar");
		    for (int j=1; j<=numSegmentos;j++) {
		    
		    	alSegmento = (HashMap<Integer,OperacionSegmento>)hashSegmentos.get(j);
		    	if (alSegmento != null) {
			    	
			    		// el for debe cambiarse por un getKeys
			    		clavesEvaluadas = alSegmento.keySet();
			    		itClavesEnSegmento = clavesEvaluadas.iterator();
			    		while (itClavesEnSegmento.hasNext()) {
			    			claveEnSegmentos = (int) itClavesEnSegmento.next();
			    		    //log.info(" clave recuperada"+claveEnSegmentos); 
			    		   op = (OperacionSegmento) alSegmento.get(claveEnSegmentos);
			    		/// necesito saber si el elemento es el menor, para tener en cuenta el valor anterior
			    		if (op!=null) {
			    			//log.info("\n op Operacion segmento OK segmento "+j );
				    		if (op.isPrevioValorMinimo())
				    		if (op.getAparicionesValorAnterior() > leaderLimit) {
				    			if (!alSalida.contains(op.getValorPrevio()))
				    				alSalida.add(op.getValorPrevio());
				    		}
				    		//El caso general, me quedo con el valor actualizado.
				    		if (op.getAparicionesValorActual() > leaderLimit ) {
				    			if (!alSalida.contains(op.getValorIncrementado())) {
				    				alSalida.add(op.getValorIncrementado());
				    				//log.info(" VALOR op.getValorIncrementado()"+op.getValorIncrementado()+" dice que es mayor que leaderLimit: "+leaderLimit);
				    			}
				    		}
			    		}//else System.out.println("\n op Operacion segmento es null");
			    	}	    	
		    	} //else System.out.println(" alSegmento me sale nulo");
		    }
		    
		    
		}
		
		
		try {
		salida = alSalida.stream().mapToInt(x->x).sorted().toArray();
		}catch (Exception ex) {
			System.out.println(" excepcion en mapeo de autoboxing "+ex.getMessage());
		}
		return salida;		
	}
	private boolean validar (int K, int M, int[] A ) {
		boolean valido = true;
		final int MAX_VALUE =100000;
		int N = A.length;
				
		valido = (N<= MAX_VALUE &&  M <=MAX_VALUE);
		if (valido) {
			valido = (K <= N);
		}
		if (valido) {
			// Para minimizar iteraciones, mientras vemos que los items del array están entre 1 y M,
			//se pone en el hash de apariciones
			int valor = 0;
			ValorPosicion vp = null;
			ArrayList <Integer> posiciones = null;
			boolean isLeader = false;
			int numApariciones = 1;
			
			int valorMinimo = MAX_VALUE;
			int posicionValorMinimo = -999;
			if (A!= null) {		
				//int leaderLimit = A.length/2;
				
				for (int i = 0;i< A.length; i++) {
					 valor = A[i];
					
					
					// vemos si está en el hash
					
					if (apariciones.containsKey(valor)) {
						
						
					    // Si está en el hash, hay que recuperar el valor posicion e
						// indicar que su posición y que hay que hay un valor más, salvo que sea un leader
						
						   vp = apariciones.get(valor);
						
							numApariciones = vp.getNumeroApariciones()+1;
							vp.setNumeroApariciones(numApariciones);
							// le indicamos que hay una aparicion más
							posiciones = vp.getPosiciones();
							posiciones.add(i);
							vp.setPosiciones(posiciones);
							// Para saber si ya es un leader, si hay ya mas de la mitad del array
							isLeader = false;//(numApariciones > leaderLimit);
							vp.setisLeader(isLeader);
						
					}else {
						// Si el valor no esta, se incluye
						posiciones = new ArrayList<Integer>(1);
						posiciones.add(i);
						numApariciones=1;
						 isLeader = false;// (numApariaciones > leaderLimit);
						 // comprobamos si es el valor mas bajo
						 if ( A[i]< valorMinimo) {
							 valorMinimo = A[i];
							 posicionValorMinimo = i;
						 }
						 vp = new ValorPosicion(A[i],numApariciones,posiciones,isLeader,false);
						
					}
					apariciones.put(A[i], vp);
					vp = null;
					
				}
				// Al salir del bucle, le indicamos al elemento menor que lo es
				vp = apariciones.get(valorMinimo);
				if (vp!= null)
					vp.setLowestValue(true);
			}	
	}
		return valido;
	}
	private int calcularNumeroSegmentos (int [] A, int K ,HashMap<Integer, ValorPosicion> apariciones, HashMap<Integer, HashMap<Integer,OperacionSegmento>> hashSegmentos ) {
		 HashMap<Integer, OperacionSegmento> segmento = new HashMap<Integer, OperacionSegmento>();	
		
		int longitudArray = A.length;
		int numSegmentos=0;
		int longitudCadenaRestante = longitudArray;
		int posInicialSegmento = 0;
		int posFinalSegmento = 0;
		int valorPrevio = -1;
		int numAparicionesValorAnterior = 0;
		int numAparicionesValorActual = 0;
		OperacionSegmento seg = null;
		int contadorValorSegmento = 0;
		List<Integer> valoresSegmento = null;
		Iterator itValoresSegmentoOdenados = null;
		int posSegmento = 0;
		Stream valoresSegmentoOdenados = null;
		int valorIncrementado = 0;
		while ( longitudCadenaRestante >= K) {
			// K-1 por empezar en 0
			posSegmento = 0;
			posFinalSegmento = posInicialSegmento+(K-1);			
			numSegmentos++;
			/// System.out.println("Segmento "+numSegmentos+" desde "+posInicialSegmento+" hasta "+posFinalSegmento);

			/////
			// Ahora vamos a la proxima estructura, y le ponemos datos
			 valoresSegmento = new ArrayList(K);
			for (int i = posInicialSegmento; i<=posFinalSegmento;i++) {
				valoresSegmento.add(A[i]);
			}
		    valoresSegmentoOdenados = valoresSegmento.stream().sorted(Comparator.reverseOrder());
			itValoresSegmentoOdenados = valoresSegmentoOdenados.iterator();
			while(itValoresSegmentoOdenados.hasNext()) {
				valorPrevio = (int) itValoresSegmentoOdenados.next();
				valorIncrementado = valorPrevio+1;
				//System.out.println("Dentro "+numSegmentos+" posicion "+posSegmento+" valor "+valorPrevio);
				if (segmento.containsKey(valorPrevio)) {
					// eso quiere decir que hay un valor repetido en el segmento
					seg = segmento.get(valorPrevio);
					// Ahora, hay que saber si antes de nosotros, ha cambiado el
					// numero de apariciones de nuestro segmento. Ejemplo segmento
					// 3221 ( k=4) A[N]= 2,2,2,3,1,2,4,4,2,1. Si no se verifica
					// cuando se evalua el 1, sacaria 6 apariciones del 2, cuando hay 2 que ya no proceden.
					
					if (segmento.containsKey(valorIncrementado)) {
						numAparicionesValorActual= segmento.get(valorIncrementado).getAparicionesValorAnterior()+1;
						seg.setAparicionesValorActual(numAparicionesValorActual);
					}else {
					// como ya sabemos todos los datos, solo hay que actualizar los contadores
						numAparicionesValorActual= seg.getAparicionesValorActual()+1;
					    seg.setAparicionesValorActual(numAparicionesValorActual);
					}
					seg.setAparicionesValorAnterior(seg.getAparicionesValorAnterior()-1);
					// hay que hacer un ajuste, debido a que si tenemos un segmento 223
					// los 2 se convierten en 3, pero el 3 pasa a ser 4
					//segmento.put(valorPrevio, seg);  
				}else {
			          seg = new OperacionSegmento();
			          seg.setValorPrevio(valorPrevio);
			          seg.setValorIncrementado(valorPrevio+1);
			          // Ahora vamos a las apariciones, los valores 
			          numAparicionesValorAnterior = (apariciones.get(valorPrevio)).getNumeroApariciones();
			          boolean esMinimo = (apariciones.get(valorPrevio)).isLowestValue();
			          if (esMinimo)
			        	  seg.setPrevioValorMinimo(esMinimo);
			          seg.setAparicionesValorAnterior(numAparicionesValorAnterior-1);
			          		          			          
			          if (segmento.containsKey(valorIncrementado)) {
			        	   // Si resulta que el valor que se incrementa se ve modificado en el segmento
			        	  // hay que tener en cuenta dichos valores.
							numAparicionesValorActual= segmento.get(valorIncrementado).getAparicionesValorAnterior()+1;
							seg.setAparicionesValorActual(numAparicionesValorActual);
						}else {
					          // Al sumarle 1 al valor que esta, podria no estar
					          if (apariciones.containsKey(valorIncrementado))
					             numAparicionesValorActual =  (apariciones.get(valorIncrementado)).getNumeroApariciones();
					          else numAparicionesValorActual = 0;
							seg.setAparicionesValorActual(numAparicionesValorActual+1);
						}
			          
			          
			         
					  //segmento.put(valorPrevio, seg);          
			          
				}
				segmento.put(valorPrevio, seg);
				contadorValorSegmento++;
			    posSegmento++;
			}
			contadorValorSegmento = 0;
			hashSegmentos.put(numSegmentos, segmento);
			// Preparamos los valores para la siguiente iteracion
			longitudCadenaRestante--;
			posInicialSegmento ++;
			seg=null;
			segmento = new HashMap<Integer, OperacionSegmento>();	 
		}
		// Al salir de aqui, tenemos una estructura con las apariciones, y otra con los segmentos
		// System.out.print(" Con una longitud "+longitudArray+" y un k "+K+" me salen "+numSegmentos);
		return numSegmentos;
		
	}
}
