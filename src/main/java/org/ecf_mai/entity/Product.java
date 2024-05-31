package org.ecf_mai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecf_mai.constant.Category;
import org.ecf_mai.constant.Size;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private Category category;
    private Size size;

    private double price;
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<InvoiceProduct> invoiceProducts;

    @Override
    public String toString() {
        return "[" + id + "] - " + description + " - "
                + category + " - taille " + size + " - "
                + price + "€ - " + quantity + " en stock";
    }
}
