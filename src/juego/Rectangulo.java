package juego;

public class Rectangulo {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	
	public Rectangulo(double x, double y, double ancho, double alto) {
		this.x = x-(ancho/2);
		this.y = y-(alto/2);
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
		if(r1.x > r2.x + r2.ancho) {
			return false;
		}else if(r1.x + r1.ancho < r2.x) {
			return false;
		}else if(r1.y > r2.y + r2.alto) {
			return false;
		}else if(r1.y + r1.alto < r2.y) {
			return false;
		}else {
			return true;
		}
	}
	
	

}
