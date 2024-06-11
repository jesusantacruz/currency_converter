package com.aluracursos.currencyconverter.main;

import com.aluracursos.currencyconverter.api.ExchangeApi;
import com.aluracursos.currencyconverter.models.Conversion;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    private ExchangeApi converterApi;

    public Menu() {
        this.converterApi = new ExchangeApi();
    }

    public void show() {
        int option;

        do {
            System.out.println("Seleccione una opción para la conversión de monedas:");
            System.out.println("1. Dólar a Peso Mexicano");
            System.out.println("2. Peso Mexicano a Dólar");
            System.out.println("3. Euro a Peso Mexicano");
            System.out.println("4. Peso Mexicano a Euro");
            System.out.println("5. Dólar a Euro");
            System.out.println("6. Euro a Dólar");
            System.out.println("7. Peso Colombiano a Peso Mexicano");
            System.out.println("8. Peso Mexicano a Peso Colombiano");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();

            manageOption(option);
            System.out.println();
        } while(option != 9);

        scanner.close();
    }

    private void manageOption(int option) {
        switch(option) {
            case 1:
                convert("USD", "MXN");
                break;
            case 2:
                convert("MXN", "USD");
                break;
            case 3:
                convert("EUR", "MXN");
                break;
            case 4:
                convert("MXN", "EUR");
                break;
            case 5:
                convert("USD", "EUR");
                break;
            case 6:
                convert("EUR", "USD");
                break;
            case 7:
                convert("COP", "MXN");
                break;
            case 8:
                convert("MXN", "COP");
                break;
            case 9:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida. Por favor, intente nuevamente.");
        }
    }

    private void convert(String from, String to) {
        System.out.print("Ingrese la cantidad en " + from + ": ");
        double amount = scanner.nextDouble();
        Conversion result;
        try {
            result = this.converterApi.convert(from, to, amount);
            System.out.println(amount + " " + result.base_code() + " son " + result.conversion_result() + " " + result.target_code() + ".");
            System.out.println("La tasa de conversion es de " + result.conversion_rate());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectarse al API. " + e.getMessage());
        }
    }
}