/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;


import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Prueba {

    /*
    
     */
    public static void main(String[] args) {

       

        boolean bandera = true;
        while (bandera) {
            System.out.println("\n\n\t\tOpciones:\n\n");
            System.out.println("\t1. Es positivo\n");
            System.out.println("\t2. Conversion B a Decimal \n");
            System.out.println("\t3. Conversion de Decimal a B\n");
            System.out.println("\t4. Serie para N terminos\n");
            System.out.println("\t5. Suma De digitos\n");
            System.out.println("\t6. Salir\n");
            System.out.println("Ingrese una Opcion: ");
            Scanner entrada = new Scanner(System.in);
            int op = entrada.nextByte();
          
            switch (op) {
                case 1:
                    System.out.println("\t\tIngrese un numero:");
                    int x = entrada.nextInt();
                    if (esPositivo(x)) {
                        System.out.println("\tEl numero: " + x + " es positivo.");
                    } else {
                        System.out.println("\tEl numero: " + x + " es negativo.");
                    }
                    break;
                case 2:
                    System.out.println("\t\tIngrese un numero :");
                    int bin = entrada.nextInt();
                    System.out.println("\t\tIngrese la base:");
                    int bas = entrada.nextInt();
                    System.out.println("\tEl numero " + bin + " es equivalente Dec ==> " + baseNADecimalR(bin, bas));
                    break;
                case 3:
                    System.out.println("\t\tIngrese un numero decimal:");
                    int dec = entrada.nextInt();
                    System.out.println("\t\tIngrese un base:");
                    int base = entrada.nextInt();
                    System.out.println("\tEl numero " + dec + " es equivalente==> " + convertBase(dec, base));
                    break;
                case 4:
                    System.out.println("\t\tIngrese un numero:");
                    System.out.println("la sumatoria de la serie es: " + sumaSerie(entrada.nextInt()));
                    break;
                case 5:
                    System.out.println("\t\tIngrese un numero:");
                    int xx = entrada.nextInt();
                    System.out.println("\t\tla suma es: " + sumaDigitos(xx));
                    break;
                case 6:
                    bandera = false;
                    break;
                default:
                    System.out.println("Error al seleccionar una opcion");
            }
          
        }
//        System.out.println(sumaDigitos(x));
//        System.out.println(sumaSerie(3));
//        System.err.println(binarioADecimalR(1010, 2));
//        System.err.println(decimalABinarioR(10, 2));

    }

    //FUNCIONES RECURSIVAS
    //DETERMINAR SI UN NUMERO ES POSITIVO
    public static boolean esPositivo(int n) {
        return n >= 0 ? true : false;
    }

    public static int binarioADecimal(int binario) {
        int dec = 0;
        int pot = 0;
        // Ciclo infinito hasta que binario sea 0

        while (true) {
            if (binario == 0) {
                break;
            } else {
                int temp = binario % 10;
                dec += temp * Math.pow(2, pot);
                binario = binario / 10;
                pot++;
            }
        }
        return dec;
    }

    public static int baseNADecimalR(int num, int base) {
        return baseNADecimalR(num, base, 0);
    }

    public static int baseNADecimalR(int num, int base, int pot) {
        if (num == 0) {
            return 0;
        }
        int dig = num % 10;
        int dec = baseNADecimalR(num / 10, base, pot + 1) + (int) (dig * Math.pow(base, pot));
        return dec;
    }
/// BASE DEC A N

    public static int convertBase(int num, int x) {
        if (num == 0) {
            return 0;
        }
        return (convertBase(num / x, x) * 10 + num % x);
    }

    public static String decimalABinario(int decimal) {
        String binario = "";
        while (decimal > 0) {
            binario = decimal % 2 + binario;
            decimal = decimal / 2;
        }
        return binario;
    }

    public static String decimalABinarioR(int num, int base) {

        return decimalABinarioAux(num, base);
    }

    public static String decimalABinarioAux(int num, int base) {
        if (num == 0) {
            return "";
        }

        int dig = num % base;
        String binario = decimalABinarioAux(num / base, base) + dig;
        return binario;

    }

    public static float sumaSerie(int n) {
        if (n == 0) {
            return 0;
        }
        return sumaSerie(n, 1, 2);
    }

    public static float sumaSerie(int n, float num, int den) {
        if (n == 0) {
            return 0;
        }
        return num / den + sumaSerie(n - 1, num + 2, den + 2);
    }

    public static int sumaDigitos(int nro) {
        if (nro == 0) {
            return 0;
        }
        int dig = nro % 10;

        int sum = sumaDigitos(nro / 10) + dig;

        return sum;

    }
}
