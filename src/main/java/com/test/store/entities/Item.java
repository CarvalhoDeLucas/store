package com.test.store.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "items")
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name_item", length = 100, nullable = false)
    private String nameItem;

    @Column(name = "description_item", length = 200, nullable = false)
    private String descriptionItem;

    @Column(name = "price", precision = 20, scale = 2, nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "catefory_id")
    private Category categories;

}
