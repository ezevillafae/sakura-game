package juego;
import java.util.Random;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	private int anchoPantalla;
	private int altoPantalla; 
	private Sakura sakura;
	private Rasengan rasengan;
	private Ciudad aldea;
	private Manzana[][] manzanas;
	private Casa casaEntrega;
	private boolean entregado;
	private Ninja [] ninjas;
	private Ninja [] ninjasMuertos;
	private Random rand;
	private Image imgFlecha;
	private int puntaje;
	private int muertes;
	
	Juego(){
		// Inicializa el objeto entorno
		this.anchoPantalla = 800;
		this.altoPantalla = 600;
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 03 - v1", anchoPantalla, altoPantalla);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.puntaje = 0;
		this.aldea = new Ciudad(anchoPantalla,altoPantalla,3,3,40);
		this.sakura = new Sakura(anchoPantalla/2, altoPantalla/2, aldea.getAnchoCalle()/2, this.aldea.getAnchoCalle()/2);
		this.manzanas = aldea.getManzanas();
		this.rand = new Random();
		this.casaEntrega = null;
		this.imgFlecha = Herramientas.cargarImagen("imagenes/flecha2.png");
		this.entregado = false;
		this.rasengan=null;
		this.ninjas = new Ninja [aldea.getCallesHorizontales()+aldea.getCallesVerticales()];
		this.ninjasMuertos =  new Ninja [aldea.getCallesHorizontales()+aldea.getCallesVerticales()];
		iniciarNinjas();
		// Inicia el juego!
		this.entorno.iniciar();
	}

	public void tick(){
		
		aldea.dibujar(entorno);
		entorno.cambiarFont("Arial", 20, Color.blue);
		entorno.escribirTexto("Score :" + Integer.toString(puntaje), 0, 20);
		entorno.escribirTexto("Kills :" + Integer.toString(muertes), 0, 40);
		
		elegirCasa();
		if(this.casaEntrega != null) {
			entorno.dibujarImagen(this.imgFlecha, this.casaEntrega.getX(), this.casaEntrega.getY()-40, 0, 1);
		}
		sakuraEntrego();
		movimientoSakura();
		colisionSakuraNinjas();
		sakura.dibujar(entorno);
		movimientoRasengan();
		colisionRasengan();
		if(this.rasengan != null){
			this.rasengan.dibujar(entorno);
		}
		movimientoNinjas(); 
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i]!=null)
				ninjas[i].dibujar(entorno);
		}
		
		colisionNinjaRasengan();
		restaurarNinjas(); 
	}
	
	private void elegirCasa() {
		if(this.casaEntrega == null) {
			int fila = rand.nextInt(manzanas.length);
			int columna = rand.nextInt(manzanas[0].length);
			Manzana manzanaElegida = manzanas[fila][columna];
			
			
			if(fila == 0) {
				if(columna == 0) {
					int casa = rand.nextInt(3)+1;
					if(casa != 2) {
						this.casaEntrega = manzanaElegida.getCasas()[casa]; // solo elige la casa de la derecha y abajo
					}
				}else if(columna == manzanas[0].length-1) {
					int casa = rand.nextInt(2)+1;
					this.casaEntrega = manzanaElegida.getCasas()[casa]; // solo elige la casa de abajo y la izquierda
				}else {
					int casa = rand.nextInt(3)+1;
					this.casaEntrega = manzanaElegida.getCasas()[casa]; // no elige las casas de arriba
				}
	
			}else if(fila == manzanas.length-1) { // fila inferior
				if(columna == 0) {
					int casa = rand.nextInt(3)+1;
					if(casa != 1 && casa != 2) {
						this.casaEntrega = manzanaElegida.getCasas()[casa]; // solo elige las casas de la derecha y arriba
					}
				}else if(columna == manzanas[0].length-1) {
					int casa = rand.nextInt(3); 
					if(casa != 1) {
						this.casaEntrega = manzanaElegida.getCasas()[casa];
					}
				}else {
					int casa = rand.nextInt(4);
					if(casa != 1) {
						this.casaEntrega = manzanaElegida.getCasas()[casa];
					}
					
				}
			}else { // si no elige la fila superior y la fila inferior
				if(columna == 0) {
					int casa = rand.nextInt(4);
					if(casa != 2) {
						this.casaEntrega = manzanaElegida.getCasas()[casa];
					}
				}else if(columna == manzanas[0].length-1) {
					int casa = rand.nextInt(3);
					this.casaEntrega = manzanaElegida.getCasas()[casa];
				}else {
					int casa = rand.nextInt(4);
					this.casaEntrega = manzanaElegida.getCasas()[casa];
				}
				
				
			}
		}
	}	
	private void iniciarNinjas(){
		int cont=1;
		int cont2=1;
		for (int i = 0; i < (ninjas.length)/2; i++) {// crea los ninjas y los ubica en la esquina superior en el centro de las calles
				this.ninjas[i]=new Ninja((manzanas[0][0].getAncho()*(cont2))+ (aldea.getAnchoCalle()*cont/2),
				 						20, 
				 						aldea.getAnchoCalle()/2, 
				 						this.aldea.getAnchoCalle()/2);
				cont+=2;
				cont2++;
		}
		cont=1;
		cont2=1;
		for (int i = (ninjas.length)/2; i < ninjas.length; i++) {
				this.ninjas[i]=new Ninja(40, 
										(manzanas[0][0].getAlto()*(cont2))+ (aldea.getAnchoCalle()*cont/2), 
										aldea.getAnchoCalle()/2, this.aldea.getAnchoCalle()/2);
				cont+=2;
				cont2++;
		}
	}

	private void movimientoNinjas(){
		for (int i = 0; i < (ninjas.length)/2; i++) {
			if(ninjas[i]!=null) {
				ninjas[i].moverAbajo();
				if (ninjas[i].getY()==altoPantalla+ninjas[i].getAlto()) {
					ninjas[i].setY(0);
					ninjas[i].moverAbajo();
				}
			}
		}
		for (int i = (ninjas.length)/2; i < ninjas.length; i++) {
			if(ninjas[i]!=null) {
				ninjas[i].moverDerecha();
				if (ninjas[i].getX()==anchoPantalla+ninjas[i].getAncho()) {
					ninjas[i].setX(0);
					ninjas[i].moverDerecha();
				}
			}
		}
	}
	
	private void colisionNinjaRasengan() {
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i]!=null && this.rasengan != null) {
				if(Rectangulo.colision(this.ninjas[i].getRect(), this.rasengan.getRect())) {
					this.muertes++;
					this.ninjasMuertos[i]=this.ninjas[i];
					this.ninjas[i] = null;
					this.rasengan = null;
				}
			}
		}
	}
	
	private void restaurarNinjas() {
		int cantVivos=0;
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i]!=null) {
				cantVivos++;
			}
		}
		
		if(cantVivos <=4) {
			for (int i = 0; i < ninjasMuertos.length/2; i++) {
				if(ninjasMuertos[i] != null && ninjas[i]==null) {
					this.ninjasMuertos[i].setY(0);
					this.ninjas[i] = this.ninjasMuertos[i];
				}
			}
			for (int i = ninjasMuertos.length/2; i < ninjasMuertos.length; i++) {
				if(ninjasMuertos[i] != null && ninjas[i]==null) {
					this.ninjasMuertos[i].setX(0);
					this.ninjas[i] = this.ninjasMuertos[i];
				}
			}
		}
	}
	
	private void movimientoRasengan() {
		
		// si se presiona la tecla espacio y no existe un disparo, se crea un disparo
		if(this.entorno.estaPresionada(entorno.TECLA_ESPACIO) && this.rasengan == null) {
			this.rasengan = sakura.disparar();
			
			// dependiendo que tecla se apreto por ultima vez se establece la direccion del rasengan
			if (sakura.getDireccion() == 1) {
				this.rasengan.setDireccion(1);
			}else if(sakura.getDireccion() == 2) {
				this.rasengan.setDireccion(2);
			}else if(sakura.getDireccion() == 3) {
				this.rasengan.setDireccion(3);
			}else if(sakura.getDireccion() == 4) {
				this.rasengan.setDireccion(4);
			}		
		}
			
		// se mueve
		if(this.rasengan != null) {
			this.rasengan.moverse();
		}
	}
	
	
	
	private boolean colisionRasengan() {
		if(this.rasengan != null){
			// colision con manzanas
			for (int i = 0; i < manzanas.length; i++) {
				for (int j = 0; j < manzanas[i].length; j++) {
					if(Rectangulo.colision(this.rasengan.getRect(), manzanas[i][j].getRect())) {
						this.rasengan = null;
						return true;
					}
				}
			}
			//colision con limites
			if(this.rasengan.getX() <= 0 || this.rasengan.getX() >= anchoPantalla || this.rasengan.getY() <= 0 || this.rasengan.getY() >= altoPantalla){
				this.rasengan = null;
				return true;
			}else{
				return false;
			}
			
			
		}else{
			return false;
		}
		
	}

	private void movimientoSakura() {
		
		
		// Limites y movimientos arriba abajo izquierda y derecha
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA) && sakura.getX()< anchoPantalla - (sakura.getAncho()/2)) {
			sakura.setDireccion(2);
			sakura.moverse();
			if(colisionSakuraManzanas()) {
				sakura.moverIzquierda();
			}
		}else if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && sakura.getX() > (sakura.getAncho()/2)) {
			sakura.setDireccion(4);
			sakura.moverse();
			if(colisionSakuraManzanas()) {
				sakura.moverDerecha();
			}
		}else if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA) && sakura.getY() > (sakura.getAlto()/2)) {
			sakura.setDireccion(1);
			sakura.moverse();
			if(colisionSakuraManzanas()) {
				sakura.moverAbajo();
			}
		}else if(this.entorno.estaPresionada(entorno.TECLA_ABAJO) && sakura.getY() < altoPantalla -(sakura.getAlto()/2)) {
			sakura.setDireccion(3);
			sakura.moverse();
			if(colisionSakuraManzanas()) {
				sakura.moverArriba();
			}
		}	
	}
	
	private boolean colisionSakuraManzanas() {
		for (int i = 0; i < manzanas.length; i++) {
			for (int j = 0; j < manzanas[i].length; j++) {
				if(Rectangulo.colision(sakura.getRect(), manzanas[i][j].getRect())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void colisionSakuraNinjas() {
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i] != null) {
				if(Rectangulo.colision(this.ninjas[i].getRect(), this.sakura.getRect())) {
					entorno.dispose();
				}
			}
		}
	}
	
	private void sakuraEntrego() {
		if(this.casaEntrega != null) {
			if(Rectangulo.colision(this.sakura.getRect(), this.casaEntrega.getRect())) {
				this.entregado = true;
				this.puntaje +=5;
				this.casaEntrega = null;
			}
		}
	}
	
	
	
	
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
