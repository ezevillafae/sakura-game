package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image imgCasa;
	private int direcCasa;
	
	
	public Casa(double x, double y, double ancho, double alto, int direcCasa) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.direcCasa=direcCasa;
		elegirCasa();
	}
	private void elegirCasa(){
		if (this.direcCasa==0)
			this.imgCasa=Herramientas.cargarImagen("imagenes/casaArriba.png");
		else if (this.direcCasa==1)
				this.imgCasa=Herramientas.cargarImagen("imagenes/casaAbajo.png");
		else if (this.direcCasa==2)
				this.imgCasa=Herramientas.cargarImagen("imagenes/casaIzquierda.png");
		else if (this.direcCasa==3)
				this.imgCasa=Herramientas.cargarImagen("imagenes/casaDerecha.png");
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgCasa, x, y, 0,1.0);
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
		return new Rectangulo(x, y, ancho+20, alto+20);
	}
	
	

}
