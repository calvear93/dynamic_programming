package DynamicAlgorithms;

/** Clase Timer (Cronometro) para probar la velocidad de ejecucion de los programas.
 * @author Cristopher Alvear - Victor Inostroza.
 * @version 2.0
 */
class Timer {
	// ATRIBUTOS.
	/** Tiempo del sistema en nanosegundos en el que comenzo el timer. */
	private long startTime;
	/** Tiempo del sistema en nanosegundos en el que finalizo el timer. */
	private long endTime;
	
	// CONSTRUCTOR.
	/** Constructor vacio. */
	Timer() {}
	
	// METODOS.
	/** Metodo que incia el timer */
	void start() {
		startTime = System.nanoTime();
	}
	
	/** Metodo que finaliza el timer */
	void stop() {
		endTime = System.nanoTime();
	}
	
	/** Metodo que retorna el tiempo en nanosegundos */
	long getTimeInNanos() {
		return endTime - startTime;
	}
	
	/** Metodo que retorna el tiempo en ms (milisegundos) */
	double getTimeInMillis() {
		return ( double ) getTimeInNanos() / 1000000;
	}
	
	/** Metodo que retorna el tiempo en segundos */
	double getTimeInSeconds() {
		return ( double ) getTimeInMillis() / 1000;
	}
	
	/** Metodo que retorna el tiempo en minutos */
	double getTimeInMinutes() {
		return ( double ) getTimeInSeconds() / 60;
	}
}