package com.test.store.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "situation")
    private String situation;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column(name = "total_value")
    private double totalValue;

}
