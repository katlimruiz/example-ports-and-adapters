package com.mycompany.app;

import java.util.Scanner;

import com.mycompany.core.*;

public class Main {
    static Scanner scanner;
    static Store store;

    static String readString(String question) {
        System.out.print(question + ": ");
        return scanner.nextLine().trim();
    }

    static void productMenu(String cmd) throws Exception {
        switch (cmd) {
            case "create":
                String name = readString("Enter product name");

                double price;
                try {
                    String sprice = readString("Enter product price");
                    price = Double.parseDouble(sprice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price.");
                    return;
                }

                var product = new Product();
                product.setName(name);
                product.setPrice(price);
                product = store.createProduct(product);
                break;
            case "list":
                var list = store.listProducts();
                System.out.println("-start-");
                for (Product p : list) {
                    System.out.println(
                            String.format("- Product id=%s,name=%s,price=%s", p.getId(), p.getName(), p.getPrice()));
                }
                System.out.println("-end-");
                break;
            default:
                System.out.println("Unknown product command");
        }

    }

    static void orderMenu(String cmd) throws Exception {
        switch (cmd) {
            case "create":
                String productId = readString("Enter product id");
                int quantity;
                try {
                    String sqty = readString("Enter quantity");
                    quantity = Integer.parseInt(sqty);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity.");
                    return;
                }
                var order = store.createOrder(productId, quantity);
                System.out.println("Order created with ID: " + order.getId());
                break;
            case "list":
                var list = store.listOrders();
                System.out.println("-start-");
                for (Order o : list) {
                    System.out.println(
                            String.format("- Order id=%s,number=%s,amount=%s", o.getId(), o.getOrderNumber(),
                                    o.getAmount()));
                }
                System.out.println("-end-");
                break;
            default:
                System.out.println("Unknown order command");
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        store = Configurator.start();

        System.out.println("Welcome! Type 'exit' to quit.");

        while (true) {
            System.out.print("\nMain Menu > ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit"))
                break;

            if (input == "") {
                System.out.println("Must enter <module> <command>");
                continue;
            }

            String[] words = input.split("\\s+");
            if (words.length < 2) {
                System.out.println("Must enter <module> <command>");
                continue;
            }

            String module = words[0];
            String command = words[1];

            try {

                switch (module) {
                    case "product":
                        productMenu(command);
                        break;
                    case "order":
                        orderMenu(command);
                        break;
                    default:
                        System.out.println("Unknown level. Use 'product' or 'order'");
                }
            } catch (Exception e) {
                System.out.println(String.format("ERROR: %s", e.getMessage()));
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
