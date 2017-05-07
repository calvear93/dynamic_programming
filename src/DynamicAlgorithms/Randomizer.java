package DynamicAlgorithms;

/** Clase Randomizer (Aleatorizador) que retorna variables y estructuras con valores aleatorios.
 * @author Cristopher Alvear - Victor Inostroza.
 * @version 1.1
 */
class Randomizer {
	// CONSTRUCTOR.
	/** Constructor privado (Clase util, estatica). */
	private Randomizer() {}
	
	// METODOS.
	/** Metodo que retorna un vector con longitud definida y con valores aleatorios dentro de un rango definido.
	 * @param length : int - Longitud del vector.
	 * @param a : int - Limite izquierdo/inferior inclusivo del rango.
	 * @param b : int - Limite derecho/superior inclusivo del rango.
	 * @return int[] - Vector de longitud length y valores aleatorios dentro del rango [a, b].
	 */
	static int[] randomizedVector( int length, int a, int b ) {
		int [] vector = new int[length];
		for ( int i = 0; i < length; i++ )
			vector[i] = randomInt( a, b );
		return vector;
	}
	
	/** Metodo que retorna un vector con longitud aleatoria entre [1, 999] y con valores aleatorios dentro de un rango definido.
	 * @param a : int - Limite izquierdo/inferior inclusivo del rango.
	 * @param b : int - Limite derecho/superior inclusivo del rango.
	 * @return int[] - Vector de longitud aleatoria y valores aleatorio dentro del rango [a, b].
	 */
	static int[] randomizedVector( int a, int b ) {
		return randomizedVector( randomInt( 1, 999 ), a, b );
	}
	
	/** Metodo que retorna un vector con longitud definida y valores aleatorios entre [1, 999].
	 * @param length : int - Longitud del vector.
	 * @return int[] - Vector con longitud length y valores aleatorios dentro del rango [0, 999].
	 */
	static int[] randomizedVector( int length ) {
		return randomizedVector( length, 0, 999 );
	}
	
	/** Metodo que retorna un vector con longitud y valores aleatorios entre [1, 999].
	 * @return int[] - Vector de longitud aleatoria entre [1, 999] y valores aleatorios dentro del rango [a, b].
	 */
	static int[] randomizedVector() {
		return randomizedVector( randomInt( 1, 999 ), 0, 999 );
	}
	
	/** Metodo que retorna cadena con caracteres alfabeticos aleatorios mayuscula o minuscula.
	 * @param length : int - Longitud de la cadena.
	 * @return String - Cadena de longitud length.
	 */
	static String randomString( int length ) {
		// Arreglo de caracteres,
		char[] string = new char[length];
		// Se llena el vector cadena.
		for( int i = 0; i < length; i++ )
			string[i] = randomChar();
		// Se hace cast a String y se retorna.
		return String.valueOf( string );
	}
	
	/** Metodo que retorna un caracter alfabetico aleatorio mayuscula o minuscula.
	 * @return char - Caracter ASCII comprendido entre [65, 90] U [97, 122].
	 */
	static char randomChar() {
		int value = randomInt( 65, 122 );
		char character =  value > 90 && value < 97 ? randomChar() : ( char ) value;
		return character;
	}
	
	/** Metodo que retorna un entero aleatorio dentro de un rango definido. 
	 * @param a : int - Limite izquierdo/inferior inclusivo del rango.
	 * @param b : int - Limite derecho/superior inclusivo del rango.
	 * @return int - Numero aleatorio dentro del rango [a,b].
	 */
	static int randomInt( int a, int b ) {
		 return ( int ) ( Math.random() * ( b - a + 1 ) + a );
	}
}