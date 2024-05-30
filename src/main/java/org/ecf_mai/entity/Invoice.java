package org.ecf_mai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecf_mai.constant.StatusType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Invoice implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private StatusType status;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceProduct> invoiceProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = StatusType.IN_PROGRESS;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
