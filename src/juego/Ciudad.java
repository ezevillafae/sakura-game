package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Ciudad {
	
	private double ancho;
	private double alto;
	private int callesVerticales;
	private int callesHorizontales;
	private Manzana[][] manzanas;
	private double anchoCalle;
	private int cantManzanas;
	private Image calles;
	

	
	public Ciudad(double ancho, double alto,int callesVerticales, int callesHorizontales,double anchoCalle) {
		this.anchoCalle = anchoCalle;
		this.ancho = ancho;
		this.alto = alto;
		this.callesHorizontales = callesHorizontales;
		this.callesVerticales = callesVerticales;
		this.manzanas = new Manzana[callesHorizontales+1][callesVerticales+1];
		this.calles=Herramientas.cargarImagen("imagenes/calles.png");
		crearManzanas();
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(calles, ancho/2, alto/2, 0,1);
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
	public double getAnchoCalle() {
		return anchoCalle;
	}

	public int getCallesVerticales() {
		return callesVerticales;
	}

	public int getCallesHorizontales() {
		return callesHorizontales;
	}

	
	

}
