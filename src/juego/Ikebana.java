package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Ikebana {
	
	private double x;
	private double y;
	private Image imgIkebana;
	
	public Ikebana(double x, double y) {
		this.x = x;
		this.y = y;
		this.imgIkebana = Herramientas.cargarImagen("imagenes/ikebana.png");
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgIkebana, x, y, 0, 0.5);
	}

}
