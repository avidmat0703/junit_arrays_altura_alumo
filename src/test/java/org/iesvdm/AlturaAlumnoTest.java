package org.iesvdm;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AlturaAlumnoTest {
    @Test
    void verdadero() {
        assertTrue(1==1);
    }


    @Test
    void aniadeNombreTest1() {


        final String[] array = new String[2];

        array[0]="Jose";
        array[1]="Paco";

        String nombre = "María";
        String[] arrayActual = AlturaAlumno
                .añadeNombre(array, nombre);

        assertTrue(arrayActual[arrayActual.length-1]
                    .equals(nombre) );

        for (int i = 0; i< array.length;i++)
            assertEquals(array[i], arrayActual[i]);

        String[] arrayExpected = Arrays.copyOf(array, array.length+1);
        arrayExpected[arrayExpected.length-1]=nombre;

        assertArrayEquals(arrayExpected, arrayActual);

    }

    @Test
    void aniadeNombreTest2() {


        final String[] array = new String[0];
        int longInicial = array.length;

        String nombre = "María";
        String[] arrayActual = AlturaAlumno
                .añadeNombre(array, nombre);

        assertTrue(arrayActual[arrayActual.length-1]
                .equals(nombre) );
        assertEquals(longInicial+1, arrayActual.length);
        assertEquals(nombre, arrayActual[longInicial+1]);

    }

    @Test
    void modificaAlturaPosicionEnElArray() {

        //When (Cuando)
        double[] array = {1.6, 1.8, 1.7};
        double[] array2 = Arrays.copyOf(array,array.length);
        int posicion = 1;
        double altura = 1.9;


        //Do (Hacer)
        AlturaAlumno.modificaAltura(array, posicion, altura);


        //Then (Entonces)

        //altura esta en la posicion
        assertTrue( altura == array[posicion]);

        //Todos los demas elementos del array no cambian
        for (int i = 0; i < array.length; i++) {
            if (i != posicion) {
                assertEquals(array[i], array2[i]);
            }
        }

    }

    @Test
    void modificaAlturaPosicionFueraDeRangoArray() {

        //When (Cuando)
        double[] array = {1.6, 1.8, 1.7};
        double[] array2 = Arrays.copyOf(array,array.length);
        int posicion = array.length+2;
        double altura = 1.9;


        //Do (Hacer)
        AlturaAlumno.modificaAltura(array, posicion, altura);


        //Then (Entonces)

        //altura esta en la posicion
        assertTrue( altura == array[posicion]);

        //Todos los demas elementos del array no cambian
        for (int i = 0; i < array.length; i++) {
                assertEquals(array[i], array2[i]);
        }
        assertArrayEquals(array2, array);

    }
}