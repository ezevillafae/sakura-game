package juego;

import entorno.Entorno;

public class Ciudad {
	
	private double ancho;
	private double alto;
	private int callesVerticales;
	private int callesHorizontales;
	private Manzana[][] manzanas;
	private double anchoCalle;
	private int cantManzanas;
	
			
	public double getAnchoCalle() {
		return anchoCalle;
	}

	public int getCallesVerticales() {
		return callesVerticales;
	}

	public int getCallesHorizontales() {
		return callesHorizontales;
	}


	public Ciudad() {
		this.anchoCalle = 40;
		this.ancho = 800;
		this.alto = 600;
		this.callesHorizontales = 3;
		this.callesVerticales = 3;
		this.cantManzanas = (callesHorizontales+1)*(callesVerticales+1); 
		this.manzanas = new Manzana[callesHorizontales+1][callesVerticales+1];
		crearManzanas();
	}
	
	public Ciudad(double ancho, double alto,int callesVerticales, int callesHorizontales,double anchoCalle) {
		this.anchoCalle = anchoCalle;
		this.ancho = ancho;
		this.alto = alto;
		this.callesHorizontales = callesHorizontales;
		this.callesVerticales = callesVerticales;
		this.manzanas = new Manzana[callesHorizontales+1][callesVerticales+1];
		crearManzanas();
	}
	
	public void dibujar(Entorno entorno) {
		for (int i = 0; i < manzanas.length; i++) {
			for (int j = 0; j < manzanas[i].length; j++) {
				manzanas[i][j].dibujar(entorno);
			}
		}
	}
	
	public void crearManzanas() {
		double anchoManzana = (this.ancho - (this.anchoCalle*callesVerticales))/(callesVerticales+1); // calcula el ancho de las manzanas
		double altoManzana = (this.alto - (this.anchoCalle*callesHorizontales))/(callesHorizontales+1); // calcula el alto de las manzanas
		double x = anchoManzana/2; // pos x inicial de la primera manzana
		double y = altoManzana/2; // pos y inicial de la primera manzana
		
		for (int i = 0; i < manzanas.length; i++) {
			x = anchoManzana/2;
			for (int j = 0; j < manzanas[i].length; j++) {
				manzanas[i][j] = new Manzana(x,y,anchoManzana,altoManzana);
				x += anchoManzana + anchoCalle;
			}
			y += altoManzana + anchoCalle;
		}
	}
	
	public int getCantCasas() {
		return this.cantManzanas;
	}

	public Manzana[][] getManzanas() {
		return manzanas;
	}
	
	
	

}
