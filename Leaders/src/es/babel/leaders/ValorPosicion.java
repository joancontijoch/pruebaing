package es.babel.leaders;

import java.util.ArrayList;

public class ValorPosicion {
	
	private int numeroApariciones;
	// posiciones en los que aparece el valor
	private ArrayList<Integer> posiciones;
	private boolean isLeader;
	private boolean isLowestValue;
	
	
	ValorPosicion(){
	}


	ValorPosicion(int valor, int numeroApariciones,ArrayList<Integer> posiciones, boolean isLeader, boolean lowestValue ) {
		this.setValor(valor);
		this.setNumeroApariciones(numeroApariciones);
		this.setPosiciones(posiciones);
		this.setisLeader(isLeader);
		this.setLowestValue(lowestValue);
	}
	
	private int valor;
	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public int getNumeroApariciones() {
		return numeroApariciones;
	}


	public void setNumeroApariciones(int numeroApariciones) {
		this.numeroApariciones = numeroApariciones;
	}


	public ArrayList<Integer> getPosiciones() {
		return posiciones;
	}


	public void setPosiciones(ArrayList<Integer> posiciones) {
		this.posiciones = posiciones;
	}


	public boolean isLeader() {
		return isLeader;
	}


	public void setisLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public boolean isLowestValue() {
		return isLowestValue;
	}


	public void setLowestValue(boolean isLowestValue) {
		this.isLowestValue = isLowestValue;
	}

}
