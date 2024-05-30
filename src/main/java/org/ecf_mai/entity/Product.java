package org.ecf_mai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecf_mai.constant.CategoryType;
import org.ecf_mai.constant.SizeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private CategoryType category;
    private SizeType size;

    private double price;
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<InvoiceProduct> invoiceProducts;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", size=" + size +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
