import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<Double> precios = new ArrayList<>();
    static ArrayList<Integer> stock = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static final double ITBMS = 0.07;
    static final int STOCK_MINIMO = 5;

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n===== INVENTARIO =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver inventario");
            System.out.println("3. Registrar venta");
            System.out.println("4. Ver stock bajo");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    vender();
                    break;
                case 4:
                    stockBajo();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    static void agregar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        nombres.add(nombre);
        precios.add(precio);
        stock.add(cantidad);
        System.out.println("Producto agregado.");
    }

    static void listar() {
        if (nombres.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }
        for (int i = 0; i < nombres.size(); i++) {
            System.out.printf("%d. %-15s $%.2f  (%d unidades)%n",
                    i + 1, nombres.get(i), precios.get(i), stock.get(i));
        }
    }

    static void vender() {
        listar();
        if (nombres.isEmpty()) return;

        System.out.print("Numero de producto: ");
        int i = sc.nextInt() - 1;
        if (i < 0 || i >= nombres.size()) {
            System.out.println("Producto no existe.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        if (cantidad > stock.get(i)) {
            System.out.println("No hay suficiente stock.");
            return;
        }

        double subtotal = precios.get(i) * cantidad;
        double impuesto = subtotal * ITBMS;
        stock.set(i, stock.get(i) - cantidad);

        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("ITBMS 7%%: $%.2f%n", impuesto);
        System.out.printf("Total:    $%.2f%n", subtotal + impuesto);
    }

    static void stockBajo() {
        boolean hay = false;
        for (int i = 0; i < nombres.size(); i++) {
            if (stock.get(i) <= STOCK_MINIMO) {
                System.out.println("- " + nombres.get(i) + ": " + stock.get(i) + " unidades");
                hay = true;
            }
        }
        if (!hay) System.out.println("Todo el stock esta bien.");
    }
}
