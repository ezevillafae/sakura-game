package juego;

import java.awt.Color;

import entorno.Entorno;

public class Rasengan {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private int direccion;
	
	public Rasengan(double x, double y, double ancho, double alto) {
		
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.direccion = 1;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);
	}
	
	public void moverse() {
		if(this.direccion == 1) {
			moverArriba();
		}else if(this.direccion == 2) {
			moverDerecha();
		}else if(this.direccion == 3) {
			moverAbajo();
		}else if(this.direccion == 4) {
			moverIzquierda();
		}
	}
	
	public void setDireccion(int direccion) {
		this.direccion = direccion;
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
