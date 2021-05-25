package juego;

import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ... 
	private Sakura sakura;
	private Rasengan rasengan;
	private Ciudad ciudad;
	private Casa floreria;
	private Ikebana ikebana;
	private Casa casaEntrega;
	private Manzana[][] manzanas;
	private Ninja [] ninjas;
	private Ninja [] ninjasMuertos;
	private Puas [] puas;
	private Random rand;
	private Image imgFlecha;
	private Image fondoMenu;
	private int anchoPantalla;
	private int altoPantalla; 
	private int puntaje;
	private int muertes;
	private int tickPuas;
	private boolean juegoTerminado;
	
	Juego(){
		// Inicializa el objeto entorno
		this.anchoPantalla = 800;
		this.altoPantalla = 600;
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 03 - v1", anchoPantalla, altoPantalla);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.puntaje = 0;
		this.muertes = 0;
		this.juegoTerminado=true;
		
		this.ciudad = new Ciudad(anchoPantalla,altoPantalla,3,3,40);
		this.manzanas = ciudad.getManzanas();
		
		this.sakura = new Sakura(anchoPantalla/2, altoPantalla/2, ciudad.getAnchoCalle()/2, this.ciudad.getAnchoCalle()/2);
		this.rasengan=null;
		this.ikebana = null;
		this.rand = new Random();
		this.casaEntrega = null;

		this.floreria = manzanas[0][manzanas.length-1].getCasas()[1]; // la floreria siempre es la casa de abajo de la manzana superior derecha
		this.floreria.setImgCasa(Herramientas.cargarImagen("imagenes/floreria.png")); //se le cambia la imagen a la floreria
		this.imgFlecha = Herramientas.cargarImagen("imagenes/flecha.png");
		this.fondoMenu=Herramientas.cargarImagen("imagenes/fondopantalla.png");
		
		this.ninjas = new Ninja [ciudad.getCallesHorizontales()+ciudad.getCallesVerticales()];
		iniciarNinjas();
		this.ninjasMuertos =  new Ninja [ciudad.getCallesHorizontales()+ciudad.getCallesVerticales()];
		this.puas = new Puas[ciudad.getCallesHorizontales()+ciudad.getCallesVerticales()];
		this.tickPuas = 700;
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	public void tick(){
		if (juegoTerminado)
			menu();
		else
			pantallaJuego();
	}

	private void pantallaJuego(){
		dibujarEntidades();
		
		//contadores
		this.tickPuas--;
		
		elegirCasa();
		
		restaurarNinjas();
		restaurarNinja();

		//movimiento de entidades
		movimientoSakura();
		movimientoRasengan();
		movimientoNinjas();
		movimientoIkebana();
		
		//colisiones 
		colisionSakuraNinjas();
		colisionRasengan();
		colisionNinjaRasengan();
		colisionSakuraPuas();
		colisionSakuraFloreria();

		sakuraEntrego();

		//las puas aparecen a partir de un cierto puntaje a la vez que cambia la velocidad de mov ninja
		soltarPuas();
		if(this.tickPuas == 150) {
			quitarPuas();
		}
		cambiarVelocidadNinjas();
	}

	private void dibujarEntidades() {
		ciudad.dibujar(entorno);
		dibujarPuntaje();

		señalizarFloreria();
		
		//dibuja la flecha arriba de la casa
		if(this.casaEntrega != null) {
			entorno.dibujarImagen(this.imgFlecha, this.casaEntrega.getX(), this.casaEntrega.getY()-30, 0, 0.77);
		}
		
		sakura.dibujar(entorno);
		
		if(this.ikebana != null) {
			this.ikebana.dibujar(entorno);
		}
		
		if(this.rasengan != null){
			this.rasengan.dibujar(entorno);
		}
		
		for (int i = 0; i < puas.length; i++) {
			if(this.puas[i] != null) {
				this.puas[i].dibujar(entorno);
			}
		}
		
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i]!=null)
				ninjas[i].dibujar(entorno);
		}
	}
	
	private void menu(){
		entorno.dibujarImagen(fondoMenu, anchoPantalla/2, altoPantalla/2, 0,1);
		if(entorno.estaPresionada(entorno.TECLA_ENTER))
			this.juegoTerminado=false;
	}
	
	private void soltarPuas() {
		if(this.tickPuas <= 0 && this.puntaje>=20) {
			this.tickPuas = 700;
			for (int i = 0; i < ninjas.length; i++) {
				if(this.ninjas[i]!=null) {
					this.puas[i] = this.ninjas[i].soltarPua();
				}
			}
		}	
	}
	
	private void quitarPuas() {
		for (int i = 0; i < puas.length; i++) {
			if(this.puas[i] != null) {
				this.puas[i] = null;
			}
		}
	}
	
	private void dibujarPuntaje() {
		entorno.dibujarRectangulo(100/2, 45/2, 100, 45, 0, Color.black);
		entorno.cambiarFont("Lucida Console", 20, Color.yellow);
		entorno.escribirTexto("Score :" + Integer.toString(puntaje), 2, 20);
		entorno.escribirTexto("Kills :" + Integer.toString(muertes), 2, 40);
	}
	
	private void elegirCasa() {
		if(this.casaEntrega == null && this.ikebana != null) {
			int fila = rand.nextInt(manzanas.length);
			int columna = rand.nextInt(manzanas[0].length);
			Manzana manzanaElegida = manzanas[fila][columna]; // se elige una manzana aleatoriamente
			
			/* casa 0 = arriba
			 * casa 1 = abajo
			 * casa 2 = izquierda
			 * casa 3 = derecha
			 */
			
			if(fila == 0) { // manzanas superiores
				if(columna == 0) { // manzana superior izquierda
					int [] numeros = {1,3}; // solo se elije la casa 1 (abajo) y casa 3 (derecha)
					int casa = rand.nextInt(2); // posicion 0 o 1
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]];
				}else if(columna == manzanas[0].length-1) { // manzana superior derecha
					int [] numeros = {1}; 
					int casa = rand.nextInt(1);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]]; // solo elige la casa de la izquierda (no elige floreria)
				}else {
					int [] numeros = {1,2,3}; 
					int casa = rand.nextInt(3);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]]; // no elige las casas de arriba
				}
	
			}else if(fila == manzanas.length-1) { // manzanas inferiores
				if(columna == 0) {
					int [] numeros = {0,3}; 
					int casa = rand.nextInt(2);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]]; // solo elige las casas de la derecha y arriba
				}else if(columna == manzanas[0].length-1) {
					int [] numeros = {0,2}; 
					int casa = rand.nextInt(2); 
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]]; //solo elige las casas de la izquierda y arriba
				}else {
					int [] numeros = {0,2,3}; 
					int casa = rand.nextInt(3);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]]; //no elige las casas de abajo
				}
			}else { // si no elige la fila superior y la fila inferior
				if(columna == 0) { 
					int [] numeros = {0,1,3}; 
					int casa = rand.nextInt(3);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]]; // no elige las casas de la izquierda
				}else if(columna == manzanas[0].length-1) {
					int [] numeros = {0,1,2}; 
					int casa = rand.nextInt(3);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]];
				}else {
					int [] numeros = {0,1,2,3}; 
					int casa = rand.nextInt(4);
					this.casaEntrega = manzanaElegida.getCasas()[numeros[casa]];
				}	
			}
		}
	}	
	
	private void señalizarFloreria() {
		if(this.ikebana == null) {
			entorno.dibujarImagen(imgFlecha, this.floreria.getX(), this.floreria.getY()-40, 0, 0.77);
		}
	}
	
	private void iniciarNinjas(){
		int contCalles=1;
		int contManzanas=1;
		for (int i = 0; i < (ninjas.length)/2; i++) {// crea los ninjas de las calles verticales
			this.ninjas[i]=new Ninja((manzanas[0][0].getAncho()*(contManzanas))+ (ciudad.getAnchoCalle()*contCalles/2),//eje x
				 					20, 
				 					ciudad.getAnchoCalle()/2, 
				 					ciudad.getAnchoCalle()/2,
									1);
			contCalles+=2;
			contManzanas++;
		}
		contCalles=1;
		contManzanas=1;
		for (int i = (ninjas.length)/2; i < ninjas.length; i++) { // crea los ninjas de las calles horizontales
			this.ninjas[i]=new Ninja(40, 
									(manzanas[0][0].getAlto()*(contManzanas))+ (ciudad.getAnchoCalle()*contCalles/2), 
									ciudad.getAnchoCalle()/2,
									ciudad.getAnchoCalle()/2,
									1);
			contCalles+=2;
			contManzanas++;
		}
	}

	private void movimientoNinjas(){
		for (int i = 0; i < (ninjas.length)/2; i++) { //movimiento de ninjas calles verticales
			if(ninjas[i]!=null) {
				ninjas[i].moverAbajo();
				if (ninjas[i].getY()>=altoPantalla+ninjas[i].getAlto()) { //cuando llegan al final de la pantalla regresan al inicio
					ninjas[i].setY(0);
				}
			}
		}
		for (int i = (ninjas.length)/2; i < ninjas.length; i++) { //movimiento de ninjas calles horizontales
			if(ninjas[i]!=null) {
				ninjas[i].moverDerecha();
				if (ninjas[i].getX()>=anchoPantalla+ninjas[i].getAncho()) {
					ninjas[i].setX(0);
				}
			}
		}
	}
	
	private void colisionNinjaRasengan() {
		for (int i = 0; i < ninjas.length; i++) {
			if(ninjas[i]!=null && this.rasengan != null) {
				if(Rectangulo.colision(this.ninjas[i].getRect(), this.rasengan.getRect())) {
					sumarMuerte();
					this.ninjasMuertos[i]=this.ninjas[i]; //se agrega el ninja a el arreglo de ninjas muertos
					this.ninjas[i] = null;
					this.rasengan = null; 
				}
			}
		}
	}
	
	private void restaurarNinjas() { 
		int cantVivos=0;
		for (int i = 0; i < ninjas.length; i++) { // se cuenta la cantidad de ninjas vivos
			if(ninjas[i]!=null) {
				cantVivos++;
			}
		}
		if(cantVivos <=4) {
			for (int i = 0; i < ninjasMuertos.length/2; i++) { // se recorren las posiciones del arreglo de ninjas muertos calles verticales
				if(ninjasMuertos[i] != null) {
					this.ninjasMuertos[i].setY(-40);
					this.ninjas[i] = this.ninjasMuertos[i];
					this.ninjasMuertos[i]=null;
				}
			}
			for (int i = ninjasMuertos.length/2; i < ninjasMuertos.length; i++) { // se recorren las posiciones del arreglo de ninjas muertos calles horizontales
				if(ninjasMuertos[i] != null) {
					this.ninjasMuertos[i].setX(-40);
					this.ninjas[i] = this.ninjasMuertos[i];
					this.ninjasMuertos[i]=null;
				}
			}
		}
	}
	
	private void restaurarNinja() {
			for (int i = 0; i < ninjasMuertos.length/2; i++) {
				if(this.ninjasMuertos[i] != null) {
					this.ninjasMuertos[i].setY(-200); //para que tarden en aparecer
					this.ninjas[i] = this.ninjasMuertos[i];
					this.ninjasMuertos[i]=null;
					return;
				}
			}
			for (int i = ninjasMuertos.length/2; i < ninjasMuertos.length; i++) {
				if(ninjasMuertos[i] != null) {
					this.ninjasMuertos[i].setX(-200); //para que tarden en aparecer
					this.ninjas[i] = this.ninjasMuertos[i];
					this.ninjasMuertos[i]=null;
					return;
				}
			}
	}
	
	private void movimientoRasengan() {
		
		// si se presiona la tecla espacio y no existe un disparo, se crea un disparo
		if(this.entorno.estaPresionada(entorno.TECLA_ESPACIO) && this.rasengan == null) {
			this.rasengan = sakura.disparar();	
		}
			
		// se mueve
		if(this.rasengan != null) {
			this.rasengan.moverse();
		}
	}
	
	private void colisionRasengan() {
		if(this.rasengan != null){
			// colision con manzanas
			for (int i = 0; i < manzanas.length; i++) {
				for (int j = 0; j < manzanas[i].length; j++) {
					if(Rectangulo.colision(this.rasengan.getRect(), manzanas[i][j].getRect())) {
						this.rasengan = null;
						return;
					}
				}
			}
			//colision con limites
			if(this.rasengan.getX() <= 0 || this.rasengan.getX() >= anchoPantalla || this.rasengan.getY() <= 0 || this.rasengan.getY() >= altoPantalla){
				this.rasengan = null;
				return;
			}
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
					resetarJuego();
				}
			}
		}
	}
	
	private void colisionSakuraPuas() {
		for (int i = 0; i < puas.length; i++) {
			if(this.puas[i] != null) {
				if(Rectangulo.colision(this.puas[i].getRect(), this.sakura.getRect())) {
					resetarJuego();
				}
			}
		}
	}
	
	private void sakuraEntrego() {
		if(this.casaEntrega != null) {
			if(Rectangulo.colision(this.sakura.getRect(), this.casaEntrega.getRect()) && this.ikebana != null) {
				this.ikebana = null;
				sumarPuntos();
				this.casaEntrega = null;
			}
		}
	}
	
	private void sumarMuerte() {
		this.muertes ++;
	}
	
	private void sumarPuntos() {
		this.puntaje += 5;
	}
	
	private void resetarJuego() {
		this.juegoTerminado = true;
		this.rasengan = null;
		this.casaEntrega = null;
		this.ikebana = null;
		quitarPuas();
		iniciarNinjas();
		this.sakura = new Sakura(anchoPantalla/2, altoPantalla/2, ciudad.getAnchoCalle()/2, this.ciudad.getAnchoCalle()/2);
		this.puntaje = 0;
		this.muertes = 0;
	}
	
	private void colisionSakuraFloreria() {
		if(this.ikebana == null && Rectangulo.colision(this.sakura.getRect(), this.floreria.getRect())) { // si no existe un ikebana y sakura colisiona con la floreria
			this.ikebana = new Ikebana(this.sakura.getX(), this.sakura.getY()); // se crea un ikebana con las x e y de sakura
		}
	}

	private void movimientoIkebana(){
		if (this.ikebana!=null){
			this.ikebana.setX(this.sakura.getX());
			this.ikebana.setY(this.sakura.getY()+5);
		}
	}
	
	private void cambiarVelocidadNinjas(){
		if (this.puntaje==35) {
			for (int i = 0; i < ninjas.length; i++) {
				if (this.ninjas[i]!=null) {
					this.ninjas[i].setVelocidad(2);
				}
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
