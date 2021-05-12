package juego;

import entorno.Entorno;

public class Ciudad {
	
	private double ancho;
	private double alto;
	private int callesVerticales;
	private int callesHorizontales;
	private Manzana[][] manzanas;
	private double anchoCalle;
	
			
	public Ciudad() {
		this.anchoCalle = 40;
		this.ancho = 800;
		this.alto = 600;
		this.callesHorizontales = 3;
		this.callesVerticales = 3;
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
		double anchoManzana = (this.ancho - (this.anchoCalle*callesVerticales))/(callesVerticales+1);
		double altoManzana = (this.alto - (this.anchoCalle*callesHorizontales))/(callesHorizontales+1);
		double x = anchoManzana/2;
		double y = altoManzana/2;
		
		for (int i = 0; i < manzanas.length; i++) {
			x = anchoManzana/2;
			for (int j = 0; j < manzanas[i].length; j++) {
				manzanas[i][j] = new Manzana(x,y,anchoManzana,altoManzana);
				System.out.println("ancho " + manzanas[i][j].getAncho() + " alto " + manzanas[i][j].getAlto());
				x += anchoManzana + anchoCalle;
			}
			y += altoManzana + anchoCalle;
		}
	}

	public Manzana[][] getManzanas() {
		return manzanas;
	}
	
	
	

}
