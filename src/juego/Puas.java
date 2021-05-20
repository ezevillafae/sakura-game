package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Puas {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image puasImg;
	
	
	public Puas(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.puasImg=Herramientas.cargarImagen("imagenes/puas.png");

	}
	
	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.YELLOW);
		entorno.dibujarImagen(puasImg, x, y, 0,1);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
	
	public Rectangulo getRect() {
		return new Rectangulo(x, y, ancho, alto);
	}
	
	

}
