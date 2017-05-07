package DynamicAlgorithms;

/** Clase DistanceBetweenStrings (Distancia de Levenshtein o editada entre dos cadenas) donde se implementan los algoritmos recursivo y dinamico que solucionan el problema.
 * La distancia entre 2 cadenas, se utiliza para comparar y saber que tan parecidas son entre si, para esto, se definen 3 operaciones elementales:
 * 	- Eliminar un caracter.
 * 	- Agregar un caracter.
 * 	- Cambiar un caracter.
 * @author Cristopher Alvear - Victor Inostroza.
 * @version 2.7
 */
class DistanceBetweenStrings {
	// CONSTRUCTOR.
	/** Constructor privado. */
	private DistanceBetweenStrings() {}
	
	// METODOS ENVOLTORIO.
	/** Metodo envoltorio del algoritmo recursivo de calculo de distancia entre cadenas.
	 * Precondicion : u, v <> null.
	 * @param u : String
	 * @param v : String
	 * @return int - Cantidad de operaciones optima.
	 */
	static int recursiveDistance( String u, String v ) {
		char[] ux = u.toCharArray(), vx = v.toCharArray();
		return recursiveDistance( ux, ux.length - 1, vx, vx.length - 1 );
	}
	
	/** Metodo envoltorio del algoritmo dinamico de calculo de distancia entre cadenas.
	 * Precondicion : u, v <> null.
	 * @param u : String
	 * @param v : String
	 * @return int - Cantidad de operaciones optima.
	 */
	static int dynamicDistance( String u, String v ) {
		char[] ux = u.toCharArray(), vx = v.toCharArray();
		return dynamicDistance( ux, ux.length, vx, vx.length );
	}
	
	// METODOS INTERNOS.
	/** Metodo recursivo que entrega el numero minimo de operaciones para transformar la cadena u en v. 
	 * Precondicion : u, v <> null, m = u.length - 1, n = v.length - 1.
	 * @param u : char[] - Vector, cadena de caracteres.
	 * @param m : int - Ultimo indice accesible del vector u.
	 * @param v : char[] - Vector, cadena de caracteres.
	 * @param n : int - Ultimo indice accesible del vector v.
	 * @return int - Cantidad de operaciones optima.
	 */
	private static int recursiveDistance( char[] u, int m, char[] v, int n ) {
		// Casos base : f(u, v) = 0, si m, n = 0; f(u, v) = n + 1, si m = 0, f(u, v) = m + 1, si n = 0. 
		if ( m == -1 ) return n == -1 ? 0 : n + 1; 
		if ( n == -1 ) return m + 1;
		// Caso reductivo : Si los caracteres en las posiciones actuales son iguales, las posiciones retroceden 1 en cada cadena y se vuelven a ejecutar las instrucciones,
		// si no, se elije el minimo o mejor caso de acortar la primera cadea, acortar la segunda cadena, o las dos y se les suma 1 operacion de transformacion.
		return u[m] == v[n] ? recursiveDistance( u, m-1, v, n-1 ) : 1 + min( recursiveDistance( u, m-1, v, n ), recursiveDistance( u, m, v, n-1 ), recursiveDistance( u, m-1, v, n-1 ));
	}
	
	/** Metodo dinamico usando memoizacion que entrega la cantidad minima de operaciones para transformar la cadena u en v. 
	 * Precondicion : u, v <> null, m = u.length, n = v.length.
	 * @param u : char[] - Vector, cadena de caracteres.
	 * @param m : int - Longitud del vector u.
	 * @param v : char[] - Vector, cadena de caracteres.
	 * @param n : int - Longitud del vector v.
	 * @return int - Cantidad de operaciones optima.
	 */
	private static int dynamicDistance( char[] u, int m, char[] v, int n ) {
		int[][] matrix = new int[m + 1][n + 1];
		fillMatrix( matrix, u, m + 1, v, n + 1 );
		return matrix[m][n];
	}
	
	/** Metodo que llena la matriz con los resultados del algoritmo dinamico. 
	 * Precondicion : matrix, u, v <> null, matrix e Mmxn, m = matrix.length (filas), n = matrix[0].length (columnas).
	 * @param matrix : int[][] - Matriz de resultados.
	 * @param u : char[] - Vector, cadena de caracteres.
	 * @param m : int - Longitud del vector u + 1 o filas de la matriz.
	 * @param v : char[] - Vector, cadena de caracteres.
	 * @param n : int - Longitud del vector v + 1 o columnas de la matriz.
	 */
	private static void fillMatrix( int[][] matrix, char[] u, int m, char[] v, int n ) {
		int add, max = m > n ? m : n;
		for( int i = 0; i < max + 1; i++ ) {
			if ( i < n ) matrix[0][i] = i;
			if ( i < m ) matrix[i][0] = i;
		}
		for ( int i = 1; i < m; i++ )
			for ( int j = 1; j < n; j++ ) {
				add = u[i - 1] == v[j - 1] ? 0 : 1;
				matrix[i][j] = min( matrix[i - 1][j - 1] + add, matrix[i][j - 1] + 1, matrix[i - 1][j] + 1 );
			}
	}
	
	/** Metodo que retorna el minimo de entre sus 3 argumentos.
	 * @param a : int
	 * @param b : int
	 * @param c : int
	 * @return int - Minimo entre a, b y c.
	 */
	private static int min( int a, int b, int c ) {
		return a < b ? a < c ? a : c : b < c ? b : c;
	}
}