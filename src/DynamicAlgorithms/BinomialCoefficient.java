package DynamicAlgorithms;

/** Clase BinomialCoefficient (Coeficiente Binomial) donde se implementan los algoritmos recursivo y dinamico que solucionan el problema.
 * El coeficiente binomial son todas las posibles formas en que se puede extraer un subconjunto de k elementos de entre un conjunto de n elementos.
 * @author Cristopher Alvear - Victor Inostroza.
 * @version 2.0
 */
class BinomialCoefficient {
	// CONSTRUCTOR.
	/** Constructor privado. */
	private BinomialCoefficient() {}

	// METODOS.
	/** Metodo recursivo que entrega la cantidad de formas posibles de un subconjunto de k elementos sobre n elementos. 
	 * Precondicion : n, k >= 0, n >= k.
	 * @param n : int - Total elementos.
	 * @param k : int - Longitud del subconjunto.
	 * @return long - Cantidad de formas posibles.
	 */
	static long recursiveBinomialCoefficient( int n, int k ) {
		// Casos base : f(n, k) = 1; si n = k o k = 0.
		// Caso reductivo : f(n, k) = f(n - 1, k) + f(n - 1, k - 1).
		return n == k || k == 0 ? 1 : recursiveBinomialCoefficient( n - 1, k - 1 ) + recursiveBinomialCoefficient( n - 1, k );
	}
		
	/** Metodo dinamico usando memoizacion que entrega la cantidad de formas posibles de un subconjunto de k elementos sobre n elementos. 
	 * Precondicion : n, k >= 0, n >= k.
	 * @param n : int - Total elementos.
	 * @param k : int - Longitud del subconjunto.
	 * @return long - Cantidad de formas posibles.
	 */
	static long dynamicBinomialCoefficient( int n, int k ) {
		// Matriz de resultados.
		long[][] matrix = new long[n + 1][k + 1];
		for ( int i = 1; i <= n; i++ ) // Se llena la matriz con los resultados, con limite la diagonal principal.
			for ( int j = 0; j <= k && j <= i ; j ++ )
				if ( i == j || j == 0 ) matrix[i][j] = 1;
				else matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
		return matrix[n][k];
	}
}