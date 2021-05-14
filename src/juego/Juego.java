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
	private Ninja ninja2;
	
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
		this.entorno.iniciar();
		this.ninjas = new Ninja [6];// coordenada x e y tienen que ser de 1/4 2/4 y 3/4 del ancho y alto
		Random r= new Random();
		for (int i = 0; i < ninjas.length; i++) {
			int valor = r.nextInt(aldea.getCallesHorizontales())+1;
			this.ninjas[i]=new Ninja((( manzanas[0][0].getAncho()*valor)+ (aldea.getAnchoCalle()/2) ), 20, 10, 15);
			
		}
		this.ninja=new Ninja(190, 140, 10, 15);
		this.ninja2=new Ninja((190*2)+20, 140, 10, 15);// (anchomanza)*random cantidad)+anchocalle/2, altomanzana*random de cantidad calles+altocalle/2
	}

	public void tick(){
		aldea.dibujar(entorno);
		movimientoSakura();
		sakura.dibujar(entorno);
		movimientoRasengan();
		for (int i = 0; i < ninjas.length; i++) {
			ninjas[i].dibujar(entorno);
		}
	}


	private void movimientoNinjas(){
		Random r= new Random();
		int valor= r.nextInt(4);
		if (valor==0) {
			
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
