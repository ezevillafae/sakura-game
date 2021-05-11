package juego;

import java.awt.Color;

import entorno.Entorno;

public class Manzana {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Casa[] casas;
	
	
	public Manzana(double x, double y, double ancho, double alto) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.casas = new Casa[4];
		casas[0] = new Casa(x-(ancho/2)+((ancho/3)/2), y, ancho/3, alto/3);
		casas[1] = new Casa(x,y-(alto/2)+((alto/3)/2), ancho/3, alto/3);
		casas[2] = new Casa(x+(ancho/2)-((ancho/3)/2),y, ancho/3, alto/3);
		casas[3] = new Casa(x,y+(alto/2)-((alto/3)/2), ancho/3, alto/3);
				
		
		
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN);
		casas[0].dibujar(entorno);
		casas[1].dibujar(entorno);
		casas[2].dibujar(entorno);
		casas[3].dibujar(entorno);
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

	public Casa[] getCasas() {
		return casas;
	}
	
	public Rectangulo getRect() {
		return new Rectangulo(x, y, ancho, alto);
	}
	
	
	
	
	

}
