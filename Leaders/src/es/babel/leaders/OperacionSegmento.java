package es.babel.leaders;

public class OperacionSegmento {
	/*
	 * Ejemplo, en el array original sale el 3, en 2 ocasiones Al hacer la operacion+1
	 * este valor 3 (valorPrevio) se convierte 4 (valorIncrementado). 
	 * Se recoge de la estructura de datos el valor de las apariciones dek valorPrevio,
	 * y se resta 1 , y del valorIncrementado, se le suma 1.
	 * */
	private int valorPrevio;
	private int valorIncrementado;
	private int aparicionesValorAnterior;
	private int aparicionesValorActual;
	private boolean previoValorMinimo;
	
	OperacionSegmento(){
		
	}
	OperacionSegmento(int valorIncrementado, int aparicionesValorAnteriores,
	 int aparicionesValorActual, int valorPrevio){
		this.setValorIncrementado(valorIncrementado);
		this.setValorPrevio(valorPrevio);
		this.setAparicionesValorActual(aparicionesValorActual);
		this.setAparicionesValorAnterior(aparicionesValorAnterior);
		
	}
	
	public int getValorPrevio() {
		return valorPrevio;
	}
	public void setValorPrevio(int valorPrevio) {
		this.valorPrevio = valorPrevio;
	}
	public int getValorIncrementado() {
		return valorIncrementado;
	}
	public void setValorIncrementado(int valorIncrementado) {
		this.valorIncrementado = valorIncrementado;
	}
	public int getAparicionesValorAnterior() {
		return aparicionesValorAnterior;
	}
	public void setAparicionesValorAnterior(int aparicionesValorAnterior) {
		this.aparicionesValorAnterior = aparicionesValorAnterior;
	}
	public int getAparicionesValorActual() {
		return aparicionesValorActual;
	}
	public void setAparicionesValorActual(int aparicionesValorActual) {
		this.aparicionesValorActual = aparicionesValorActual;
	}
	public boolean isPrevioValorMinimo() {
		return previoValorMinimo;
	}
	public void setPrevioValorMinimo(boolean previoValorMinimo) {
		this.previoValorMinimo = previoValorMinimo;
	}


}
