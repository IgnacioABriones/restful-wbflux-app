package com.mitocode.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

//maestro detalles cabecera detalles
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "invoices")
public class Invoice {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Field
    private String description;

    @Field
    private Client client;

    private List<InvoiceDetail> items;
}
