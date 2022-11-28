package com.mitocode.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection = "clients")
public class Client {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private LocalDate birthDate;

    @Field
    private String urlPhoto;
}
