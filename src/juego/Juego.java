package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	private int anchoPantalla;
	private int altoPantalla; 
	private Sakura sakura;
	private Rasengan rasengan;
	private Manzana manzana;
	private boolean[] direcciones;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.anchoPantalla = 800;
		this.altoPantalla = 600;
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 03 - v1", anchoPantalla, altoPantalla);
		
		// Inicializar lo que haga falta para el juego
		// ...
		direcciones = new boolean[4];
		this.sakura = new Sakura(anchoPantalla/2, altoPantalla/2, 10, 15);
		this.manzana = new Manzana(200, 300, 300, 250);

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...   
		this.manzana.dibujar(entorno);
		ultimaFlechaApretada();
		movimientoSakura();
		movimientoRasengan();
		
		

	}
	
	private void ultimaFlechaApretada() {
		/*
		 * direccion[0] arriba
		 * direccion[1] derecha
		 * direccion[2] abajo 
		 * direccion[3] izquierda
		 */
		
		if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
			direcciones[0] = true; 
			direcciones[1] = false;
			direcciones[2] = false;
			direcciones[3] = false;
		}else if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
			direcciones[0] = false; 
			direcciones[1] = true;
			direcciones[2] = false;
			direcciones[3] = false;
		}else if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
			direcciones[0] = false; 
			direcciones[1] = false;
			direcciones[2] = true;
			direcciones[3] = false;
		}else if(entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
			direcciones[0] = false; 
			direcciones[1] = false;
			direcciones[2] = false;
			direcciones[3] = true;
		}
	}
	
	private void movimientoRasengan() {
		
		// si se presiona la tecla espacio y no existe un disparo, se crea un disparo
		if(this.entorno.estaPresionada(entorno.TECLA_ESPACIO) && this.rasengan == null) {
			this.rasengan = sakura.disparar();
			
			// dependiendo que tecla se apreto por ultima vez se establece la direccion del rasengan
			if (direcciones[0]) {
				this.rasengan.setDireccion(1);
			}else if(direcciones[1]) {
				this.rasengan.setDireccion(2);
			}else if(direcciones[2]) {
				this.rasengan.setDireccion(3);
			}else if(direcciones[3]) {
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
		return this.rasengan.getX() <= 0 || this.rasengan.getX() >= anchoPantalla || this.rasengan.getY() <= 0 || this.rasengan.getY() >= altoPantalla;
	}

	private void movimientoSakura() {
		// Limites y movimientos arriba abajo izquierda y derecha
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA) && sakura.getX()< anchoPantalla - (sakura.getAncho()/2))
			sakura.moverDerecha();
		else if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && sakura.getX() > (sakura.getAncho()/2))
			sakura.moverIzquierda();
		else if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA) && sakura.getY() > (sakura.getAlto()/2))
			sakura.moverArriba();
		else if(this.entorno.estaPresionada(entorno.TECLA_ABAJO) && sakura.getY() < altoPantalla -(sakura.getAlto()/2))
			sakura.moverAbajo();
		sakura.dibujar(entorno);
	}
	
	
	
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
