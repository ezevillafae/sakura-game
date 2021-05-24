package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Ninja {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
    private Image imgNinja;
    private double velocidad;

	public Ninja(double x, double y, double ancho, double alto, double velocidad) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
        this.velocidad=velocidad;
        this.imgNinja=Herramientas.cargarImagen("imagenes/imgninja.png");
	}
	
	public Puas soltarPua() {
		return new Puas(x, y, 10, 10);
	}

    public void dibujar(Entorno entorno) {
        entorno.dibujarImagen(imgNinja, x, y, 0,0.70);

	}
    public void moverDerecha() {
		this.x+= this.velocidad; 
	}
	
	public void moverIzquierda() {
		this.x-=this.velocidad;
	}
	
	public void moverArriba() {
		this.y -= this.velocidad;
	}
	
	public void moverAbajo() {
		this.y +=this.velocidad;
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
    public void setVelocidad(double velocidad){
        this.velocidad=velocidad;
    }
    public Rectangulo getRect() {
		return new Rectangulo(x, y, ancho, alto*2);
	}
}
