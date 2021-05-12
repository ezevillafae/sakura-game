package juego;

public class Rectangulo {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	
	public Rectangulo(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
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
	
	public static boolean colision(Rectangulo r1, Rectangulo r2) {
		
		// r1.bordeSuperior > r2.bordeInferior
		if((r1.y - (r1.alto/2)) > (r2.y + (r2.alto/2))) {
			return false;
		//r1.bordeInferior < r2.bordeSuperior
		}else if((r1.y + (r1.alto/2)) < (r2.y - (r2.alto/2))) {
			return false;
		//r1.bordeIzquiedo > r2.bordeDerecho
		}else if((r1.x - (r1.ancho/2)) > (r2.x + (r2.ancho/2))) {
			return false;
		// r1.bordeDerecho < r2.bordeIzquierdo
		}else if((r1.x- (r1.ancho/2)) < (r2.x - (r2.ancho/2))) {
			return false;
		}else {
			return true;
		}
	}
	
	

}
