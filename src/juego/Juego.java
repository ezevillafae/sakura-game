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
		sakura.dibujar(entorno);
		

	}
	
	public void movimientoSakura() {
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA))
			sakura.moverDerecha();
		else if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			sakura.moverIzquierda();
		else if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA))
			sakura.moverArriba();
		else if(this.entorno.estaPresionada(entorno.TECLA_ABAJO))
			sakura.moverAbajo();
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
