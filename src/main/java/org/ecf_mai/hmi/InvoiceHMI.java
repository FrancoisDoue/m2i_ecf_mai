package org.ecf_mai.hmi;

import org.ecf_mai.constant.Status;
import org.ecf_mai.entity.Invoice;
import org.ecf_mai.repository.impl.InvoiceRepository;

import java.util.List;

public class InvoiceHMI implements HMIInterface {

    private final InvoiceRepository invoiceRepository = new InvoiceRepository();

    private static InvoiceHMI INSTANCE;

    private InvoiceHMI() {}

    public static synchronized InvoiceHMI getInstance() {
        if (INSTANCE == null) INSTANCE = new InvoiceHMI();
        return INSTANCE;
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("""
                    - - - - Gestion des commandes - - - -
                    1. Afficher les commandes
                    2. Valider une commande
                    3. Annuler une commande
                    -------------------------------------
                    [0] Menu précédent
                    """);
            try {
                switch (promptInteger()) {
                    case 1 -> showInvoices();
                    case 2 -> confirmInvoice();
                    case 3 -> cancelInvoice();
                    case 0 -> {
                        return;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showInvoices() throws RuntimeException {
        while (true) {
            System.out.println("""
                    - - - - Afficher les commandes - - - -
                    1. Afficher toutes les commandes
                    2. Afficher les commandes en cours
                    3. Afficher les commandes confirmées
                    4. Afficher les commandes annulées
                    [0] Menu précédent
                    """);
            (switch (promptInteger()) {
                case 1 -> invoiceRepository.getAll();
                case 2 -> invoiceRepository.getInvoiceByStatus(Status.IN_PROGRESS);
                case 3 -> invoiceRepository.getInvoiceByStatus(Status.CONFIRMED);
                case 4 -> invoiceRepository.getInvoiceByStatus(Status.CANCELED);
                default -> throw new RuntimeException("");
            }).forEach(System.out::println);
        }
    }

    public void confirmInvoice() throws RuntimeException {
        Invoice i = selectInvoiceWhereStatus(Status.IN_PROGRESS);
        i.setStatus(Status.CONFIRMED);
        System.out.println(invoiceRepository.update(i));
    }

    public void cancelInvoice() throws RuntimeException {
        Invoice i = selectInvoiceWhereStatus(Status.IN_PROGRESS);
        invoiceRepository.cancel(i);
    }

    public Invoice selectInvoiceWhereStatus(Status status) throws RuntimeException {
        while (true) {
            List<Invoice> invoiceList;
            if (status == null) {
                invoiceList = invoiceRepository.getAll();
            } else {
                invoiceList = invoiceRepository.getInvoiceByStatus(status);
            }
            if (invoiceList.isEmpty())
                throw new RuntimeException("Il n'y a aucune commande enregistrée");
            invoiceList.forEach(System.out::println);
            System.out.println("Sélectionnez l'identifiant de la commande :");
            Invoice invoice = invoiceRepository.get(promptInteger());
            if (invoice != null ) {
                return invoice;
            }
            System.out.println("Impossible de sélectionner cette commande");
        }
    }



}
