package com.test.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "categories")
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Category {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "type_item", length = 100, nullable = false, unique = true)
    private String typeItem;

}
