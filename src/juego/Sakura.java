package juego;
import java.awt.Image;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private int direccion;
	private Image sakuraImg;
	
	
	public Sakura(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.sakuraImg=Herramientas.cargarImagen("imagenes/sakura.png");
	}
	
	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
		entorno.dibujarImagen(sakuraImg, x, y, 0, 1);
	}
	
	public Rasengan disparar() {
		return new Rasengan(x,y,5,5);
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
	
	public int getDireccion() {
		return this.direccion;
	}
	
	public void setDireccion(int direccion) {
		this.direccion = direccion;
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
