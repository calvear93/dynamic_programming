package DynamicAlgorithms;

/** Clase Sampling (Muestreo) que testea los algoitmos recursivos de implementacion directa V/S los de implementación dinamica.
 * @author Cristopher Alvear - Victor Inostroza.
 * @version 4.5
 */
class Sampling {
	/** Metodo de ejecucion principal.*/
	public static void main( String[] args ) {
		System.out.println( " Cargando...\n" );
		// Testeo de los algoritmos.
		binomialCoefficientSampling( 250, 12 );
		distanceBetweenStringsSampling( 250, 10 );
		chainedMatrixMultiplicationSampling( 250, 12 );
		// Fin del muestreo.
		System.out.println( " [i] FIN DEL PROGRAMA" );
	}
	
	/** Metodo que testea el calculo de coeficiente binomial.*/
	private static void binomialCoefficientSampling( int resamples, int samples ) {
		Timer timer = new Timer();
		long[] recursiveScores = new long[samples], dynamicScores = new long[samples];
		int n, k;
		
		for ( int r = 0; r < resamples; r++ ) for ( int i = 0; i < samples; i++ ) {
			n = ( i + 1 ) * 10;
			k = Randomizer.randomInt( 2, 4 );
			// Algoritmo recursivo de implementación directa.
			timer.start();
			BinomialCoefficient.recursiveBinomialCoefficient( n, k );
			timer.stop();
			recursiveScores[i] += timer.getTimeInNanos();
			// Algoritmo de implementación dinamica.
			timer.start();
			BinomialCoefficient.dynamicBinomialCoefficient( n, k );
			timer.stop();
			dynamicScores[i] += timer.getTimeInNanos();
		}
		
		System.out.println( "[ A1 ] : Calculo del Coeficiente Binomial - [" + samples + " samples, " + resamples + " resamples]" );
		System.out.println( " [>] TIEMPO DE EJECUCION (en nanosegundos)" );
		System.out.println( "\tDirecto\t\t\t\tDinamico" );
		// Se calculan los promedios.
		averageScores( recursiveScores, resamples );
		averageScores( dynamicScores, resamples );
		// Se imprimen los resultados.
		printScores( recursiveScores, dynamicScores, samples );
		System.out.println();
	}
	
	/** Metodo que testea la distancia entre cadenas.*/
	private static void distanceBetweenStringsSampling( int resamples, int samples ) {
		Timer timer = new Timer();
		long[] recursiveScores = new long[samples], dynamicScores = new long[samples];
		String u, v;
		
		for ( int r = 0; r < resamples; r++ ) for ( int i = 0; i < samples; i++ ) {
			u = Randomizer.randomString( i + 1 );
			v = Randomizer.randomString( i + 1 );
			// Algoritmo recursivo de implementación directa.
			timer.start();
			DistanceBetweenStrings.recursiveDistance( u, v );
			timer.stop();
			recursiveScores[i] += timer.getTimeInNanos();
			// Algoritmo de implementación dinamica.
			timer.start();
			DistanceBetweenStrings.dynamicDistance( u, v );
			timer.stop();
			dynamicScores[i] += timer.getTimeInNanos();
		}
		
		System.out.println( "[ A2 ] : Distancia entre Cadenas (de Levenshtein) - [" + samples + " samples, " + resamples + " resamples]" );
		System.out.println( " [>] TIEMPO DE EJECUCION (en nanosegundos)" );
		System.out.println( "\tDirecto\t\t\t\tDinamico" );
		// Se calculan los promedios.
		averageScores( recursiveScores, resamples );
		averageScores( dynamicScores, resamples );
		// Se imprimen los resultados.
		printScores( recursiveScores, dynamicScores, samples );
		System.out.println();
	}
	
	/** Metodo que testea la cuenta de multiplicaciones encadenadas optima entre matrices.*/
	private static void chainedMatrixMultiplicationSampling( int resamples, int samples ) {
		Timer timer = new Timer();
		long[] recursiveScores = new long[samples], dynamicScores = new long[samples];
		int[] pqr;
		
		for ( int r = 0; r < resamples; r++ ) for ( int i = 0; i < samples; i++ ) {
			pqr = Randomizer.randomizedVector( i + 3 );
			// Algoritmo recursivo de implementación directa.
			timer.start();
			ChainedMatrixMultiplication.recursiveMultiplicationCounter( pqr );
			timer.stop();
			recursiveScores[i] += timer.getTimeInNanos();
			// Algoritmo de implementación dinamica.
			timer.start();
			ChainedMatrixMultiplication.dynamicMultiplicationCounter( pqr );
			timer.stop();
			dynamicScores[i] += timer.getTimeInNanos();
		}
		
		System.out.println( "[ A3 ] : Calculo cantidad optima de Multiplicacion encadenada de Matrices - [" + samples + " samples, " + resamples + " resamples]" );
		System.out.println( " [>] TIEMPO DE EJECUCION (en nanosegundos)" );
		System.out.println( "\tDirecto\t\t\t\tDinamico" );
		// Se calculan los promedios.
		averageScores( recursiveScores, resamples );
		averageScores( dynamicScores, resamples );
		// Se imprimen los resultados.
		printScores( recursiveScores, dynamicScores, samples );
		System.out.println();
	}
	
	/** Metodo que calcula el promedio de scores en un vector.*/
	private static void averageScores( long[] scores, int resamples ) {
		for ( int i = 0; i < scores.length; i++ )
			scores[i] /= resamples;
	}
	
	/** Metodo que imprime en columnas los scores de los vectores de implementaciones directa y dinamica.*/
	private static void printScores( long[] recursiveScores, long[] dynamicScores, int length ) {
		for ( int i = 0; i < length; i++ )
			System.out.printf( "\t %-10d\t\t\t %-10d\n", recursiveScores[i], dynamicScores[i] );
	}
}