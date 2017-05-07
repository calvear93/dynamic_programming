package DynamicAlgorithms;

/** Clase ChainedMatrixMultiplication (Multiplicacion encadenada de Matrices) donde se implementan los algoritmos recursivo y dinamico que solucionan el problema.
 * El problema consiste en encontrar el numero de multiplicaciones minimo en el caso optimo que se debe realizar al multiplicar una cadena de matrices en distinto orden.
 * @author Cristopher Alvear - Victor Inostroza.
 * @version 2.1
 */
class ChainedMatrixMultiplication {
	// CONSTRUCTOR.
	/** Constructor privado. */
	private ChainedMatrixMultiplication() {}
	
	// METODOS ENVOLTORIO.
	/** Metodo envoltorio del algoritmo recursivo de calculo de cantidad optima de multiplicacion de matrices.
	 * Precondicion : pqr <> null.
	 * @param pqr : int[] - Vector de los coeficientes de longitud de las matrices ordenados de la forma {p,q,r,...,n,m,s} en Mpxq x Mqxr x ... x Mnxm x Mmxs.
	 * @return long - Cantidad de multiplicaiones optimas.
	 */
	static long recursiveMultiplicationCounter( int[] pqr ) {
		return recursiveMultiplicationCounter( pqr, 0, pqr.length - 1 );
	}
	
	/** Metodo envoltorio del algoritmo recursivo de calculo de cantidad optima de multiplicacion de matrices.
	 * Precondicion : pqr <> null.
	 * @param pqr : int[] - Vector de los coeficientes de longitud de las matrices ordenados de la forma {p,q,r,...,n,m,s} en Mpxq x Mqxr x ... x Mnxm x Mmxs.
	 * @return long - Cantidad de multiplicaiones optimas.
	 */
	static long dynamicMultiplicationCounter( int[] pqr ) {
		return dynamicMultiplicationCounter( pqr, pqr.length - 1 );
	}
	
	// METODOS INTERNOS.
	/** Metodo recursivo que entrega el numero minimo de multiplicaciones que se deben realizar en una cadena de matrices. 
	 * Precondicion : pqr <> null, i = 0, ( j = pqr.length - 1 ) > 2.
	 * @param pqr : int[] - Vector de los coeficientes de longitud de las matrices ordenados de la forma {p,q,r,...} en Mpxq x Mqxr.
	 * @param i : int - 0, argumento muerto. Inicio del vector.
	 * @param j : int - pqr.length - 1, ultimo indice del vector.
	 * @return long - Cantidad de multiplicaiones optimas.
	 */
	private static long recursiveMultiplicationCounter( int[] pqr, int i, int j ) {
		// Casos base : f(i, j) = Mi,i+1 x Mi+1,j; si j - i == 2. 
		if ( j - i == 2 ) return pqr[i] * pqr[i + 1] * pqr[j];
		// Caso reductivo : f(i, j) = Min( f(i + 1, j) * Mi,i+1 x Mi+1,j, f(i, j - 1) + Mi,j-1 x Mj-1,j ).
		return min( pqr[i] * pqr[i + 1] * pqr[j] + recursiveMultiplicationCounter( pqr, i + 1, j ), recursiveMultiplicationCounter( pqr, i, j - 1 ) + pqr[i] * pqr[j - 1] * pqr[j] );
	}
		
	/** Metodo dinamico usando memoizacion que entrega el numero minimo de multiplicaciones que se deben realizar en una cadena de matrices. 
	 * Precondicion : pqr <> null, i = 0, ( j = pqr.length - 1 ) > 2.
	 * @param pqr : int[] - Vector de los coeficientes de longitud de las matrices ordenados de la forma {p,q,r,...,n,m,s} en Mpxq x Mqxr x ... x Mnxm x Mmxs.
	 * @param l : int - pqr.length - 1, ultimo indice del vector.
	 * @return long - Cantidad de multiplicaiones optimas.
	 */
	private static long dynamicMultiplicationCounter( int[] pqr, int l ) {
		// Matriz de resultados.
		long [][] matrix = new long[l][l];
		// Llamada al metodo que calcula los resultados en la matriz.
		fillMatrix( pqr, matrix, 0, l - 1 );
		// Se retorna el optimo, situado en la casilla superior derecha de la matriz.
		return matrix[0][l - 1];
	}
	
	/** Metodo recursivo que llena la matriz con los resultados del algoritmo dinamico. 
	 * Precondicion : pqr <> null, i= 0, ( j = pqr.length - 1 ) > 2.
	 * @param pqr : int[] - Vector de los coeficientes de longitud de las matrices ordenados de la forma {p,q,r,...,n,m,s} en Mpxq x Mqxr x ... x Mnxm x Mmxs.
	 * @param i : int - 0, argumento muerto. Primera fila de la matriz.
	 * @param j : int - matrix[0].length, argumento muerto. Longitud del primer vector o primera fila de la matriz.
	 */
	private static void fillMatrix( int[] pqr, long[][] matrix, int i, int j ) {
		if ( matrix[i][j] != 0 ) return;
		// Casos base : multiplicacion de matrices con su adyacente -> en una cadena de AxBxC, AxB y BxC serian casos base; A,B,C matrices.
		if ( i + 1 == j ) { matrix[i][j] = pqr[i] * pqr[i + 1] * pqr[i + 2]; return; }
		// Verifica si existen los valores requeridos para calcular la casilla, si no, los calcula recursivamente.
		fillMatrix( pqr, matrix, i, j - 1 );
		fillMatrix( pqr, matrix, i + 1, j );
		// Luego de calcular las casillas requeridas, setea el valor minimo a la casilla de la solucion.
		matrix[i][j] = min( matrix[i][j - 1] + pqr[i] * pqr[j] * pqr[j + 1], matrix[i + 1][j] + pqr[i] * pqr[i + 1] * pqr[j + 1] );
	}
	
	/** Metodo que retorna el minimo de entre sus 2 argumentos.
	 * @param a : int
	 * @param b : int
	 * @return int - Minimo entre a y b.
	 */
	private static long min( long a, long b ) {
		return a < b ? a : b;
	}
}