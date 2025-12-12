package busqueda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MotorBusqueda {

    private static final int N_LIBROS = 2000000; 

    public static Libro buscarSecuencial(List<Libro> lista, String isbnBuscado) {
        for (Libro libro: lista) {
            if (libro.getIsbn().equals(isbnBuscado)) { 
                return libro;
            }
        }
        return null;
    }
    

    public static Libro buscarBinaria(List<Libro> lista, String isbnBuscado) {
        Libro claveBusqueda = new Libro(isbnBuscado, null, null);
        

        int indice = Collections.binarySearch(lista, claveBusqueda, Libro.ISBN_COMPARATOR);
        
        if (indice >= 0) {
            return lista.get(indice);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        
        System.out.println("=====================================================");
        System.out.println("            DUELO DE ALGORITMOS DE BUSQUEDA          ");
        System.out.println("             N = " + N_LIBROS + " libros              ");
        System.out.println("=====================================================");

        // --- 1. Generacion de Datos ---
        List<Libro> listaLibros = new ArrayList<>(); // Para Secuencial y Binaria
        Map<String, Libro> mapaLibros = new HashMap<>(); // Para Hash
        
        System.out.println("Generando " + N_LIBROS + " libros...");
        
        for (int i = 0; i < N_LIBROS; i++) {
            String isbn = "ISBN-" + i;
            Libro libro = new Libro (isbn, "Titulo-" + i, "Autor Genérico");
            listaLibros.add(libro);
            mapaLibros.put(isbn, libro);
        }

        String isbnABuscar = "ISBN-" + (N_LIBROS - 1); 
        System.out.println("Dato de prueba (Peor Caso, clave: " + isbnABuscar + ")");
        
       
        listaLibros.sort(Libro.ISBN_COMPARATOR);
        System.out.println("Lista ordenada. Iniciando pruebas de tiempo...");
        System.out.println("-----------------------------------------------------");


    
        long inicioSecuencial = System.nanoTime();
        Libro resultadoSecuencial = buscarSecuencial(listaLibros, isbnABuscar);
        long finSecuencial = System.nanoTime();
        long tiempoSecuencial = finSecuencial - inicioSecuencial;

        
        long inicioBinaria = System.nanoTime();
        Libro resultadoBinaria = buscarBinaria(listaLibros, isbnABuscar);
        long finBinaria = System.nanoTime();
        long tiempoBinaria = finBinaria - inicioBinaria;
        
        
        long inicioHash = System.nanoTime();
        Libro resultadoHash = mapaLibros.get(isbnABuscar);
        long finHash = System.nanoTime();
        long tiempoHash = finHash - inicioHash;

        
        System.out.println("--- RESULTADOS EN NANOSEGUNDOS (ns) ---");
        
        
        System.out.printf("1. Secuencial (O(N)): \t\t%,d ns\n", tiempoSecuencial);
        System.out.printf("2. Binaria (O(log N)): \t\t%,d ns\n", tiempoBinaria);
        System.out.printf("3. Hash (O(1)): \t\t\t%,d ns\n", tiempoHash);
        System.out.println("-----------------------------------------------------");

        
        long microSecuencial = TimeUnit.NANOSECONDS.toMicros(tiempoSecuencial);
        long microBinaria = TimeUnit.NANOSECONDS.toMicros(tiempoBinaria);
        long microHash = TimeUnit.NANOSECONDS.toMicros(tiempoHash);
        
        System.out.println("--- Resultados en Microsegundos (μs) ---");
        System.out.printf("1. Secuencial: \t\t%,d μs\n", microSecuencial);
        System.out.printf("2. Binaria: \t\t%,d μs\n", microBinaria);
        System.out.printf("3. Hash: \t\t%,d μs\n", microHash);
        System.out.println("=====================================================");

        if (resultadoHash != null) {
             System.out.println("Prueba exitosa. El libro buscado fue: " + resultadoHash.getIsbn());
        }
    }
}
