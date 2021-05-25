package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image imgCasa;
	private int direcCasa;
	
	public Casa(double x, double y, double ancho, double alto, int direcCasa) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.direcCasa=direcCasa;
		elegirCasa();
	}

	private void elegirCasa(){
		if (this.direcCasa==0)
			this.imgCasa=Herramientas.cargarImagen("imagenes/casaArriba.png");
		else if (this.direcCasa==1)
			this.imgCasa=Herramientas.cargarImagen("imagenes/casaAbajo.png");
		else if (this.direcCasa==2)
			this.imgCasa=Herramientas.cargarImagen("imagenes/casaIzquierda.png");
		else if (this.direcCasa==3)
			this.imgCasa=Herramientas.cargarImagen("imagenes/casaDerecha.png");
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgCasa, x, y, 0,0.8);
	}
	
	public void setImgCasa(Image image) {
		this.imgCasa = image;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Rectangulo getRect() {
		return new Rectangulo(x, y, ancho+5, alto+5);//hit box de entrega del ikebana o se entiende como la colision de sakura con la casa
	}
	
}
