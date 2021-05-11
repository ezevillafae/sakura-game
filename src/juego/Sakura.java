package juego;

import java.awt.Color;
import java.awt.Rectangle;

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
	
	public Rasengan disparar() {
		return new Rasengan(x,y,5,5);
	}
	
	
	public void moverDerecha() {
		this.x+= 10; 
	}
	
	public void moverIzquierda() {
		this.x-=10;
	}
	
	public void moverArriba() {
		this.y -= 10;
	}
	
	public void moverAbajo() {
		this.y +=10;
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
