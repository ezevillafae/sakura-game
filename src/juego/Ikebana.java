package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ikebana {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image imgIkebana;
	
	public Ikebana(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imgIkebana = Herramientas.cargarImagen("imagenes/ikebana.png");
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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAlto() {
		return alto;
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgIkebana, x, y, 0, 0.5);
	}
	
	
	
	

}
