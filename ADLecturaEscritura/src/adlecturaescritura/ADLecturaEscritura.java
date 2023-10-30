package adlecturaescritura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ADLecturaEscritura {

    public static void main(String[] args) {
        String archivoEntrada = "alumnosNotas.txt";
        String alumno, nombre;
        int nota1, nota2, nota3;
        double notaMedia;
        String[] vectorAlumnos;
        
        try {
            BufferedReader bR = new BufferedReader(new FileReader(archivoEntrada));
            while ((alumno = bR.readLine()) != null) {                
                vectorAlumnos = alumno.split(":");
                nombre = vectorAlumnos[0];
                nota1 = Integer.parseInt(vectorAlumnos[1]);
                nota2 = Integer.parseInt(vectorAlumnos[2]);
                nota3 = Integer.parseInt(vectorAlumnos[3]);
                
                notaMedia = (nota1 + nota2 + nota3) / 3;
                System.out.println(nombre + ":" + notaMedia);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
