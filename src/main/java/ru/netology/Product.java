package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    protected int id;
    protected String title;
    protected int price;

    public boolean matches(String searchInput) {
        return getTitle().toUpperCase().contains(searchInput.toUpperCase());
    }
}