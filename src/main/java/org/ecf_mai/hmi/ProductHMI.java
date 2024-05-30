package org.ecf_mai.hmi;

import org.ecf_mai.constant.CategoryType;
import org.ecf_mai.constant.SizeType;
import org.ecf_mai.entity.Product;
import org.ecf_mai.repository.impl.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductHMI implements HMIInterface{
    private static ProductHMI INSTANCE;

    private ProductHMI(){}

    public static synchronized ProductHMI getInstance() {
        if (INSTANCE == null) INSTANCE = new ProductHMI();
        return INSTANCE;
    }

    private final ProductRepository pr = new ProductRepository();

    public void start() {
        while (true) {
            System.out.println("""
                    - - - - Gestion des produits - - - -
                    1. Afficher les produits
                    2. Ajouter un produit
                    3. Modifier un produit [en cours]
                    4. Supprimer un produit
                    [0]. Quit
                    """);
            try {
                switch (promptInteger()) {
                    case 1 -> pr.getAll().forEach(System.out::println);
                    case 2 -> createProduct();
                    case 3 -> {}
                    case 4 -> deleteProduct();
                    case 0 -> {
                        return;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Product selectProduct() throws RuntimeException {
        while (true) {
            List<Product> productList = pr.getAll();
            if (productList.isEmpty())
                throw new RuntimeException("Il n'y a aucun produit enregistré");
            productList.forEach(System.out::println);
            System.out.println("Sélectionnez l'identifiant produit");
            Product product = pr.get(promptInteger());
            if (product != null) return product;
            System.out.println("Impossible de sélectionner ce produit");
        }
    }

    public void createProduct() {
        Product p = new Product();
        System.out.println("- - - - Ajouter un produit - - - -");
        System.out.println("Description :");
        p.setDescription(promptStringNotEmpty());
        while (true) {
            try {
                System.out.println("Catégorie: \n[1] Enfant | [2] Homme | [3] Femme");
                p.setCategory(CategoryType.values()[promptInteger()-1]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Saisie invalide");
            }
        }
        while (true) {
            try {
                System.out.println("Taille: \n[1] XS | [2] S | [3] M | [4] L | [5] XL | [6] XXL ");
                p.setSize(SizeType.values()[promptInteger()-1]);
                System.out.println(p.getSize());
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Saisie invalide");
            }
        }
        System.out.println("Prix : ");
        p.setPrice(promptDouble());
        System.out.println("Quantité : ");
        p.setQuantity(promptInteger());
        pr.create(p);
        System.out.println(p);
    }

    public void deleteProduct() throws RuntimeException {
        System.out.println("- - - - Supprimer un produit - - - -");
        Product p = selectProduct();
        System.out.println("Voulez supprimer ce produit?\n" + p +"\n[y/n]");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equalsIgnoreCase("y"))
            pr.delete(p);
    }
}
