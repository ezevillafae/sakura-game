package juego;
import java.util.Random;

import entorno.Entorno;
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
	private Ninja [] ninjas;
	private Ninja ninja;
	
	Juego(){
		// Inicializa el objeto entorno
		this.anchoPantalla = 800;
		this.altoPantalla = 600;
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 03 - v1", anchoPantalla, altoPantalla);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.sakura = new Sakura(anchoPantalla/2, altoPantalla/2, 10, 15);
		this.aldea = new Ciudad(anchoPantalla,altoPantalla,3,3,40);
		this.manzanas = aldea.getManzanas();
		// Inicia el juego!
		this.rasengan=null;
		this.entorno.iniciar();
		this.ninjas = new Ninja [aldea.getCallesHorizontales()+aldea.getCallesVerticales()];// crear otro for para ubiar los otrso 3 ninjas 
		crearUbicarN();

	}

	public void tick(){
		aldea.dibujar(entorno);
		movimientoSakura();
		sakura.dibujar(entorno);
		movimientoRasengan();
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i]!=null)
				ninjas[i].dibujar(entorno);
		}
		movimientoNinjas();
	}

	private void crearUbicarN(){
		int cont=1;
		int cont2=1;
		for (int i = 0; i < (ninjas.length)/2; i++) {// crea los ninjas y los ubica en la esquina superior en el centro de las calles
				this.ninjas[i]=new Ninja((manzanas[0][0].getAncho()*(cont2))+ (aldea.getAnchoCalle()*cont/2), 20, 10, 15);
				cont+=2;
				cont2++;
		}
		cont=1;
		cont2=1;
		for (int i = (ninjas.length)/2; i < ninjas.length; i++) {
				this.ninjas[i]=new Ninja(40, (manzanas[0][0].getAlto()*(cont2))+ (aldea.getAnchoCalle()*cont/2), 10, 15);
				cont+=2;
				cont2++;
		}
	}

	private void movimientoNinjas(){
		for (int i = 0; i < (ninjas.length)/2; i++) {
			ninjas[i].moverAbajo();
			if (ninjas[i].getY()==altoPantalla) {
				ninjas[i].setY(0);
				ninjas[i].moverAbajo();
			}
		}
		for (int i = (ninjas.length)/2; i < ninjas.length; i++) {
			ninjas[i].moverDerecha();
			if (ninjas[i].getX()==anchoPantalla) {
				ninjas[i].setX(0);
				ninjas[i].moverDerecha();
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
				
		// asigna a null si el rasengan llega a los limites 
		if(this.rasengan != null) {
			if(colisionRasengan())
				this.rasengan = null;
		}
		

		// se mueve y dibuja el rasengan
		if(this.rasengan != null) {
			this.rasengan.moverse();
			this.rasengan.dibujar(entorno);
		}
	}
	
	
	
	private boolean colisionRasengan() {
		for (int i = 0; i < manzanas.length; i++) {
			for (int j = 0; j < manzanas[i].length; j++) {
				if(Rectangulo.colision(this.rasengan.getRect(), manzanas[i][j].getRect())) {
					return true;
				}
			}
		}
		return this.rasengan.getX() <= 0 || this.rasengan.getX() >= anchoPantalla || this.rasengan.getY() <= 0 || this.rasengan.getY() >= altoPantalla;
	}

	private void movimientoSakura() {
		
		
		// Limites y movimientos arriba abajo izquierda y derecha
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA) && sakura.getX()< anchoPantalla - (sakura.getAncho()/2)) {
			sakura.setDireccion(2);
			sakura.moverse();
			if(colisionSakura()) {
				sakura.moverIzquierda();
			}
		}else if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && sakura.getX() > (sakura.getAncho()/2)) {
			sakura.setDireccion(4);
			sakura.moverse();
			if(colisionSakura()) {
				sakura.moverDerecha();
			}
		}else if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA) && sakura.getY() > (sakura.getAlto()/2)) {
			sakura.setDireccion(1);
			sakura.moverse();
			if(colisionSakura()) {
				sakura.moverAbajo();
			}
		}else if(this.entorno.estaPresionada(entorno.TECLA_ABAJO) && sakura.getY() < altoPantalla -(sakura.getAlto()/2)) {
			sakura.setDireccion(3);
			sakura.moverse();
			if(colisionSakura()) {
				sakura.moverArriba();
			}
		}	
	}
	
	private boolean colisionSakura() {
		for (int i = 0; i < manzanas.length; i++) {
			for (int j = 0; j < manzanas[i].length; j++) {
				if(Rectangulo.colision(sakura.getRect(), manzanas[i][j].getRect())) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
