package juego;

import entorno.Entorno;
import java.awt.Color;

public class Ninja {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;

	public Ninja(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}

    public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.MAGENTA);
	}
    public void moverDerecha() {
		this.x+= 1; 
	}
	
	public void moverIzquierda() {
		this.x-=1;
	}
	
	public void moverArriba() {
		this.y -= 1;
	}
	
	public void moverAbajo() {
		this.y +=1;
	}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }
    public Rectangulo getRect() {
		return new Rectangulo(x, y, ancho, alto);
	}
}
