package org.example.comicon.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String plot;
    private BigDecimal price;
    private LocalDateTime uploadTime;

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    // Explicit getter for title
    public String getTitle() {
        return this.title;
    }

    // Explicit getter for price
    public BigDecimal getPrice() {
        return this.price;
    }

}
