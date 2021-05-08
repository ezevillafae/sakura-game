package juego;

import java.awt.Color;

import entorno.Entorno;

public class Sakura {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	
	
	public Sakura(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
	}
	
	
	public void moverDerecha() {
		this.x+= 2; 
	}
	
	public void moverIzquierda() {
		this.x-=2;
	}
	
	public void moverArriba() {
		this.y -= 2;
	}
	
	public void moverAbajo() {
		this.y +=2;
	}

	
	
	
	
	

}
