package com.kimbactran.inventoryservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="inventory")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private int quantity;
}
