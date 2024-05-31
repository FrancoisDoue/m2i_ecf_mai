package org.ecf_mai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecf_mai.constant.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Invoice implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Status status;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoiceProduct> invoiceProducts;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = Status.IN_PROGRESS;
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
        StringBuilder sb = new StringBuilder();
        invoiceProducts.forEach(ip -> sb.append("\t").append(ip.toString()).append("\n"));
        return "["+ id + "] Commande pour : " + customer.getFirstname() + " " + customer.getLastname() + " | " +
                " statut : " + status +
                " - Créée le " + createdAt.format(df) +"\n" + sb ;
    }
}
