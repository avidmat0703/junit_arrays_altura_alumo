package org.iesvdm;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlturaAlumnoTest {
    //Atributos
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void AñadeNombre() {
        //when
        String[] array = new String[0];
        String nombre = "";
        
        //do
        String[] newArray = AlturaAlumno.añadeNombre(array, nombre);
        
        //then
        assertTrue(newArray[newArray.length - 1] == nombre);
        assertTrue(newArray.length == array.length + 1);
        assertArrayEquals(array, Arrays.copyOfRange(newArray, 0, array.length));
    }

    @Test
    void añadeAltura() {
        //when
        double[] array = new double[0];
        double AlturaXDefecto = 1.6;
        
        //do
        double[] NewArray = AlturaAlumno.añadeAltura(array);
        
        //then
        assertTrue(NewArray.length == array.length + 1);
        assertTrue(NewArray[NewArray.length - 1] == AlturaXDefecto);
        assertArrayEquals(array,Arrays.copyOfRange(NewArray,0, array.length));
    }

    @Test
    void modificaAltura() {
        //when
        double[]array = new double[0];
        double[] ArrayNuevo = AlturaAlumno.añadeAltura(array);
        int posicion = 0;
        
        //do
        AlturaAlumno.modificaAltura(ArrayNuevo, posicion, 1.7);
        
        //then
        assertTrue(ArrayNuevo.length == array.length + 1);
        assertTrue(ArrayNuevo[0] == 1.7);
        assertArrayEquals(array, Arrays.copyOfRange(ArrayNuevo, 0, ArrayNuevo.length - 1));
    }

    @Test
    void modificaAlturaOutOfRange() {
        //when
        double[] array = new double[0];
        double[] ArrayNuevo = Arrays.copyOf(array, array.length);
        int posicion = 1;
        
        //do
        AlturaAlumno.añadeAltura(array);
        AlturaAlumno.modificaAltura(ArrayNuevo, posicion, 1.7);
        
        //then
        assertArrayEquals(array, ArrayNuevo);
    }

    @Test
    void givenNamesAndHeightsWhenMostrarThenPrint() {

        System.setOut(new PrintStream(outputStreamCaptor));
        // when
        String[] arrayNombre = {"Alex"};
        double[] arrayAltura = {1.8};
        
        //do
        AlturaAlumno.mostrar(arrayNombre, arrayAltura);
        
        //then
        assertEquals("Alex" + "\t|   " + "1.8\n", outputStreamCaptor.toString());
        assertTrue(outputStreamCaptor.toString().contains("Alex"));
    }

    @Test
    void buscarNombreYEsta() {
        //when
        String [] array = {"Alex"};
        
        //then
        assertTrue(AlturaAlumno.buscaNombre(array, "Alex") == 0);
    }

    @Test
    void buscaNombreYNoEsta() {
        //when
        String [] array = new String[1];
        array[0] = "Alex";
        String NombreNoEsta = "Pepito";
        
        //do
        int resultado = AlturaAlumno.buscaNombre(array, NombreNoEsta);
        
        //then
        assertTrue(resultado == - 1);
    }

    @Test
    void CalcularMaximo() {
        //when
        double [] array = {1.6, 1.87, 1.71};
        
        //then
        assertTrue(AlturaAlumno.calculaMaximo(array)[0] == 1);
        assertTrue(AlturaAlumno.calculaMaximo(array)[1] == 1.87);
    }

    @Test
    void CalculaMaximoWhenArrayVacio() {
        //when
        double[]array = new double[0];
        double [] resultadoExpected = new double [2];
        
        //do
        double [] maximo = AlturaAlumno.calculaMaximo(array);
        
        //then
        assertArrayEquals(maximo, resultadoExpected);
    }

    @Test
    void CalcularMedia() {
        //when
        double[] array = {1.75, 1.77, 1.80, 1.82, 1.85};
        
        //do
        double mediaExpected = AlturaAlumno.calculaMedia(array);
        
        //then
        assertTrue(mediaExpected == 1.798);
    }

    @Test
    void CalculaMediaWhenArrayVacio() {
        //when
        double[] array = new double[0];
        
        //do
        double mediaExpected = AlturaAlumno.calculaMedia(array);
        
        //then
        assertTrue(mediaExpected == 0);
    }
}