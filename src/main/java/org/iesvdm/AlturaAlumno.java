package org.iesvdm;

import java.util.Scanner;

public class AlturaAlumno { // Recorrer dos arrays con la misma longitud

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        // VARIABLES:
        String[] alumnos = new String[0];
        double[] alturas = new double[0];

        boolean salir = false;
        int opcion = 0;


        // BUCLE MENU
        while (!salir)
        {
            // FUNCION MENU CON OPCIONES (ME DEVUELVE LA OPCION QUE HE PULSADO)
            opcion = menu();

            switch (opcion)
            {
                case 1: // Pide nombre, añade al alumno y por defecto le pone 1.5m de altura

                    System.out.println("Inserta el nombre del nuevo alumno:");
                    String nuevoNombre = sc.nextLine();

                    alumnos = añadeNombre(alumnos, nuevoNombre);
                    alturas = añadeAltura(alturas);
                    break;

                case 2: // Pide un nombre, lo busca y pide la altura para modificarla

                    System.out.println("Introduce el nombre del alumno a modificar:");
                    String nombreBuscado = sc.nextLine();

                    int posicion = buscaNombre(alumnos, nombreBuscado);

                    System.out.println("Introduce la nueva altura:");
                    double altura = sc.nextDouble();

                    modificaAltura(alturas, posicion, altura);
                    break;

                case 3: // Muestra la tabla de alumnos con sus alturas a un lado

                    mostrar(alumnos, alturas);
                    break;

                case 4: // Pide un incremento de altura y lo aplica a todas las alturas

                    System.out.println("Introduce el incremento de altura:");
                    double incremento = sc.nextDouble();

                    for (int i = 0; i < alturas.length; i++)
                    {
                        alturas[i] = alturas[i] + incremento;
                    }
                    break;

                case 5: // Muestra el nombre del alumno más alto junto a su altura

                    double[] posicionMaximo = calculaMaximo(alturas);

                    if(alumnos.length == 0)
                    {
                        System.out.println("Aun no hay registro de alumnos");
                    }
                    else
                    {
                        System.out.println("El alumno más alto es " + alumnos[(int)posicionMaximo[0]] + ", y mide " + posicionMaximo[1] + " metros");
                    }
                    break;

                case 6: // Muestra la media de las alturas de los alumnos

                    double media = calculaMedia(alturas);
                    System.out.println("La media de alturas es: " + media + " metros");
                    break;

                default:
                    salir = true;
                    System.out.println("Hasta pronto");

            }// Switch

        }// While salir

    }// Main


    // FUNCIONES:

    /**
     * Imprime el menu y devuelve la opción elegida
     * @return usuario
     */
    static int menu()
    {
        int usuario = 0;

        System.out.println("\nElige la opción que quieres realizar:");
        System.out.println("\t1 -> Añadir nuevo alumno"); // pide nombre y pone por defecto 1,5 de altura
        System.out.println("\t2 -> Modificar altura alumno"); // PIDE NOMBRE y luego pide nueva altura
        System.out.println("\t3 -> Mostrar todo"); // Muestra la tabla de nombres con sus alturas
        System.out.println("\t4 -> Incrementar altura"); // Pide el incremento
        System.out.println("\t5 -> Sacar el alumno más alto");
        System.out.println("\t6 -> Sacar la media de altura");
        System.out.println("Pulsa cualquier otro número para salir");
        usuario = sc.nextInt();
        // limpiar Buffer:
        sc.nextLine();

        return usuario;
    }

    /**
     * Añade un alumno al final del array
     * @param array,nombre
     * @return nuevo
     */
    static String[] añadeNombre(String[] array, String nombre)
    {
        String[] nuevo = new String[array.length+1];

        for (int i = 0; i < array.length; i++)
        {
            nuevo[i] = array[i];
        }

        nuevo[nuevo.length-1] = nombre;

        return nuevo;
    }

    /**
     * Añade una altura al final del array con un valor por defecto
     * @param array
     * @return nuevo
     */
    static double[] añadeAltura(double[] array)
    {
        double[] nuevo = new double[array.length+1];
        double alturaPorDefecto = 1.5;

        for (int i = 0; i < array.length; i++)
        {
            nuevo[i] = array[i];
        }

        modificaAltura(nuevo, nuevo.length-1, alturaPorDefecto);
        //nuevo[nuevo.length-1] = alturaPorDefecto; //lo comento porque paso a hacerlo con una funcion

        return nuevo;
    }

    /**
     * Modifica la altura de una posicion dada del array por un valor tambien dado
     * @param array,posicion,altura
     */
    static void modificaAltura(double[] array, int posicion, double altura)
    {
        if(posicion>=0 && posicion<array.length) // Controlo que la posicion no esté fuera del rango
        {
            array[posicion] = altura;
        }
    }

    /**
     * Busca la coincidencia de un nombre en el array y devuelve su posición
     * o devuelve -1 si no lo encuentra
     * @param array,nombre
     * @return posicionEncontrada
     */
    static int buscaNombre(String[] array, String nombre)
    {
        int posicionEncontrada = -1;
        int index = 0;

        // Mientras no llegue al final de array y el valor de posicón no haya cambiado
        while(index <= array.length-1 && posicionEncontrada == -1)
        {
            // Guardo la posición en el caso de encontrar un nombre igual
            if(array[index].equals(nombre))
            {
                posicionEncontrada = index;
            }
            index++;
        }

        return posicionEncontrada;
    }


    /**
     * Pinta la tabla de alturas
     * @param arrayNombre,arrayAltura
     */
    static void mostrar(String[] arrayNombre, double[] arrayAltura)
    {
        for (int i = 0; i < arrayNombre.length; i++)
        {
            System.out.println(arrayNombre[i] + "\t|   " + arrayAltura[i]);
        }
    }

    /**
     * Calcula el valor maximo de un array y su posicion
     * y devuelve ambos valores en un array
     * @param array
     * @return resultadoMaximo
     */
    static double[] calculaMaximo(double[] array)
    {
        double[] resultadoMaximo = new double[2];

        if(array.length>0) // Controlo que el array no esté vacío
        {
            double maximo = array[0];
            double posicion = 0;

            for (int i = 0; i < array.length; i++)
            {
                if(array[i]>maximo)
                {
                    maximo = array[i];
                    posicion = i;
                }
            }
            resultadoMaximo[0] = posicion;
            resultadoMaximo[1] = maximo;
        }
        return resultadoMaximo;
    }

    /**
     * Calcula el valor medio de un array
     * @param array
     * @return media
     */
    static double calculaMedia(double[] array)
    {
        double media = 0;

        for (int i = 0; i < array.length; i++)
        {
            media = media + array[i];
        }

        if(array.length > 0) // controlo la division por cero
        {
            media = media/(array.length);
        }

        return media;
    }
}