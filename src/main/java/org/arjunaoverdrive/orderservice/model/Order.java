package org.arjunaoverdrive.orderservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {
    private String product;
    private Integer quantity;
}
