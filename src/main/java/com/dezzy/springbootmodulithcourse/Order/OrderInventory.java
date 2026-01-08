package com.dezzy.springbootmodulithcourse.Order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(indexes = {
        @Index(name = "order_idx", columnList = "order_id"),
        @Index(name = "inv_idx", columnList = "inventory_id")
})
public class OrderInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long orderId;
    private long inventoryId;
    private int qty;
    private long totalQtyPrice;
}
