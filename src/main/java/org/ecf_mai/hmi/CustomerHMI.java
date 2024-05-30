package org.ecf_mai.hmi;

import org.ecf_mai.entity.Customer;
import org.ecf_mai.entity.Invoice;
import org.ecf_mai.entity.InvoiceProduct;
import org.ecf_mai.entity.Product;
import org.ecf_mai.repository.impl.CustomerRepository;
import org.ecf_mai.repository.impl.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerHMI implements HMIInterface {

    private static CustomerHMI INSTANCE;
    private CustomerHMI() {}

    public static synchronized CustomerHMI getInstance() {
        if (INSTANCE == null) INSTANCE = new CustomerHMI();
        return INSTANCE;
    }

    private final CustomerRepository cr = new CustomerRepository();
    private final InvoiceRepository ir = new InvoiceRepository();

    @Override
    public void start() {
        while (true) {
            System.out.println("""
                    - - - - Gestion des Clients - - - -
                    1. Consulter l'historique des clients [en cours]
                    2. Ajouter un client
                    3. Modifier les informations d'un client
                    4. Supprimer un client
                    -----------------------------------
                    5. Enregistrer le produit d'un client
                    [0]. Quit
                    """);
            try {
                switch (promptInteger()) {
                    case 1 -> showCustomerHistory();
                    case 2 -> createCustomer();
                    case 3 -> updateCustomer();
                    case 4 -> deleteCustomer();
                    case 5 -> clientBuyProduct();
                    case 0 -> {
                        return;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showCustomerHistory() {
        Customer c = selectCustomer();
        List<Invoice> invoices = ir.getInvoiceByCustomer(c);
        invoices.forEach(i -> System.out.println(i + " -- TOTAL : " + ir.getTotalPrice(i)));
    }

    public void clientBuyProduct() {
        List<InvoiceProduct> invoiceProducts = new ArrayList<>();
        Customer c = selectCustomer();
        System.out.println(c + "\n");
        while (true) {
            Product p = ProductHMI.getInstance().selectProduct();
            int quantity;
            System.out.println(p + "\n");
            System.out.println("Quantité : (max " + p.getQuantity() + " )");
            if ((quantity = promptInteger()) >= p.getQuantity()) {
                System.out.println("Stock insuffisant");
                continue;
            }
            invoiceProducts.add(InvoiceProduct.builder()
                    .product(p)
                    .quantity(quantity)
                    .build());
            System.out.println("Ajouter un autre produit ? [y/n]");
            if (promptStringNotEmpty().equalsIgnoreCase("n"))
                break;
        }
        if (ir.create(invoiceProducts, c)) {
            System.out.println("Commande effectuée");
        }


    }

    public Customer selectCustomer() throws RuntimeException {
        while (true) {
            List<Customer> customerList = cr.getAll();
            if (customerList.isEmpty())
                throw new RuntimeException("Il n'y a aucun client enregistré");
            customerList.forEach(System.out::println);
            System.out.println("Sélectionnez l'identifiant client:");
            Customer customer = cr.get(promptInteger());
            if (customer != null)
                return customer;
            System.out.println("Impossible de trouver ce client");
        }
    }

    public void createCustomer() {
        System.out.println( "- - - - Nouveau Client - - - -");
        Customer c = new Customer();
        System.out.println("Prénom du client : ");
        c.setFirstname(promptStringNotEmpty());
        System.out.println("Nom du client : ");
        c.setLastname(promptStringNotEmpty());
        System.out.println("Adresse email : ");
        c.setEmail(promptStringNotEmpty());
        System.out.println(cr.create(c));
    }

    public void updateCustomer() throws RuntimeException {
        String firstname, lastname, email;
        System.out.println( "- - - - Mettre à jour les informations client - - - -");
        Scanner scanner = new Scanner(System.in);
        Customer c = selectCustomer();
        System.out.println("Prénom du client : (actuel: "+c.getFirstname()+") [[Entrée] pour ne pas modifier] ");
        if (!(firstname = scanner.nextLine()).isEmpty())
            c.setFirstname(firstname);
        System.out.println("Nom du client : (actuel: "+c.getLastname()+") [[Entrée] pour ne pas modifier]");
        if (!(lastname = scanner.nextLine()).isEmpty())
            c.setLastname(lastname);
        System.out.println("Email du client : (actuel: "+c.getEmail()+") [[Entrée] pour ne pas modifier]");
        if (!(email = scanner.nextLine()).isEmpty())
            c.setEmail(email);
        System.out.println("Voulez enregistrer ces modifications?\n" + c +"\n[y/n]");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            cr.update(c);
        }
    }

    public void deleteCustomer() throws RuntimeException {
        System.out.println( "- - - - Supprimer un client - - - -");
        Customer c = selectCustomer();
        System.out.println("Voulez supprimer ce client?\n" + c +"\n[y/n]");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equalsIgnoreCase("y"))
            cr.delete(c);
    }
}
