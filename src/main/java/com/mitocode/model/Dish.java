package com.mitocode.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection = "dishes")
public class Dish {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Field
    private String name;

    @Field//(name="price_dish")
    private Double price;

    @Field
    private Boolean status;

}
