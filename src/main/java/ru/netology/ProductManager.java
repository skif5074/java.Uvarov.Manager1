package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductManager {
    private ProductRepository repository = new ProductRepository();

    //методы, зависимые от репозитория
    //добавление продукта в конец массива
    public void add(Product productAdd) {
        repository.addProduct(productAdd);
    }

    //получить все сохранненные продукты в порядке добавления
    public Product[] findAll() {
        return repository.findAll();
    }

    //удалить по id
    public void removeId(int id) {
        repository.removeId(id);
    }

    //логика менеджера
    //поиск по названию продукта
    public Product[] searchByTitle(String searchInput) {
        Product[] results = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, searchInput)) {
                Product[] tmp = new Product[results.length + 1];
                int i = 0;
                for (Product result : results) {
                    tmp[i] = result;
                    i++;
                }
                tmp[results.length] = product;
                results = tmp;
            }
        }
        return results;
    }

    //определение соотвествия товара запросу (без учета регистра)
    public boolean matches(Product product, String searchInput) {
        return product.getTitle().toUpperCase().contains(searchInput.toUpperCase());
    }

    //поиск через rich model
    public Product[] searchByTitleRichModel(String searchInput) {
        Product[] results = new Product[0];
        for (Product product : repository.findAll()) {
            if (matchesRichModel(product, searchInput)) {
                Product[] tmp = new Product[results.length + 1];
                System.arraycopy(results, 0, tmp, 0, results.length);
                tmp[results.length] = product;
                results = tmp;
            }
        }
        return results;
    }

    public boolean matchesRichModel(Product product, String searchInput) {
        return product.matches(searchInput);
    }
}