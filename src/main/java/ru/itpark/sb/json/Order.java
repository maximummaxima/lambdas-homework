package ru.itpark.sb.json;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int userId;
    private double sum;
    private List<OrderItem> items;
}
