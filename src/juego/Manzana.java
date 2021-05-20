package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Manzana {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Casa[] casas;
	private Image imgManzana;
	
	
	public Manzana(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imgManzana = Herramientas.cargarImagen("imagenes/pasto.png");
		this.casas = new Casa[4];
		//casa arriba
		casas[0] = new Casa(x, y-(alto/2)+((alto/3)/2), ancho/3, alto/3,0);
		//casa abajo
		casas[1] = new Casa(x,y+(alto/2)-((alto/3)/2),ancho/3, alto/3,1);
		//casa izquieda
		casas[2] = new Casa(x-(ancho/2)+((ancho/3)/2),y,ancho/3, alto/3, 2);
		//casa derecha
		casas[3] = new Casa(x+(ancho/2)-((ancho/3)/2),y,ancho/3, alto/3,3);	
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgManzana, x, y, 0, 1);
		//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN);
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
