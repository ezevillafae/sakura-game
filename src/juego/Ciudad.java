package juego;

import entorno.Entorno;

public class Ciudad {
	
	private double ancho;
	private double alto;
	private int callesVerticales;
	private int callesHorizontales;
	private Manzana[][] manzanas;
			
	public Ciudad() {
		this.ancho = 800;
		this.alto = 600;
		this.callesHorizontales = 5;
		this.callesVerticales = 3;
		this.manzanas = new Manzana[callesVerticales+1][callesHorizontales+1];
	}
	
	public Ciudad(double ancho, double alto,int callesVerticales, int callesHorizontales) {
		this.ancho = ancho;
		this.alto = alto;
		this.callesHorizontales = callesHorizontales;
		this.callesVerticales = callesVerticales;
	}
	
	public void dibujar(Entorno entorno) {
		
	}
	
	
	

}
