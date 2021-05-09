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
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.anchoPantalla = 800;
		this.altoPantalla = 600;
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 03 - v1", anchoPantalla, altoPantalla);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.sakura = new Sakura(anchoPantalla/2, altoPantalla/2, 10, 15);

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
		
		
		movimientoSakura();
		movimientoRasengan();
		
		

	}
	
	private void movimientoRasengan() {
		
		// si se presiona la tecla espacio y no existe un disparo, se crea un disparo
		if(this.entorno.estaPresionada(entorno.TECLA_ESPACIO) && this.rasengan == null) {
			this.rasengan = sakura.disparar();
			
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				this.rasengan.setDireccion(2);			
			}else if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				this.rasengan.setDireccion(4);
			}else if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				this.rasengan.setDireccion(1);
			}else if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				this.rasengan.setDireccion(3);
			}
			
				
		}
			
			
		
		
			
		// asigna a null si el rasengan llega a los limites 
		if(this.rasengan != null) {
			if(colisionRasengan())
				this.rasengan = null;
		}
		

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
