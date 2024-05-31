package org.ecf_mai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "invoice_product")
public class InvoiceProduct implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn(name = "product_id")
    private Product product;

    int quantity;

    @Override
    public String toString() {
        return product + " [Quantité : " + quantity + "]";
    }
}
