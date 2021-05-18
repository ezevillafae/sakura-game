package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Puntaje {
	
	private double x;
	private double y;
	private int puntuacion;
	private double ancho;
	private double alto;

	public Puntaje(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.puntuacion = 0;
		
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.blue);
		entorno.cambiarFont("Arial", 20, Color.white);
		entorno.escribirTexto(Integer.toString(puntuacion), x, y);
		
	}
	
	public void sumar(int puntos) {
		this.puntuacion += puntos;
	}
	
	public void restar(int puntos) {
		this.puntuacion -= puntos;
	}



	public double getX() {
		return x;
	}



	public double getY() {
		return y;
	}



	public int getPuntuacion() {
		return puntuacion;
	}
	
	
	
	
	

}
