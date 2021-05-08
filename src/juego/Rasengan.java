package juego;

import java.awt.Color;

import entorno.Entorno;

public class Rasengan {
	
	public double x;
	public double y;
	public double ancho;
	public double alto;
	
	public Rasengan(double x, double y, double ancho, double alto) {
		
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);
	}
	
	public void moverDerecha() {
		this.x+= 3; 
	}
	
	public void moverIzquierda() {
		this.x-=3;
	}
	
	public void moverArriba() {
		this.y -= 3;
	}
	
	public void moverAbajo() {
		this.y +=3;
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
	
	
	
	
	
	

}
