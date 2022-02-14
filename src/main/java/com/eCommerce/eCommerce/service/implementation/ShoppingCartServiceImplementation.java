package com.eCommerce.eCommerce.service.implementation;

import com.eCommerce.eCommerce.dao.ProductRepository;
import com.eCommerce.eCommerce.exception.NotEnoughProductsInStockException;
import com.eCommerce.eCommerce.model.Orders;
import com.eCommerce.eCommerce.model.Product;
import com.eCommerce.eCommerce.model.ProductOrder;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.service.JdbcOrderService;
import com.eCommerce.eCommerce.service.ProductOrderService;
import com.eCommerce.eCommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    int amount;
    int id;
    List<ProductOrder> productOrderList = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JdbcOrderService jdbcOrdersService;
    @Autowired
    private ProductOrderService productOrderService;
    private Map<Product, Integer> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public void checkout(User user) throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            product = productRepository.getById(entry.getKey().getIdproduct());
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
            if (id != entry.getKey().getIdproduct()) {
                ProductOrder productOrder = new ProductOrder();
                productOrder.setQuantity(entry.getValue());
                productOrder.setProductId(product);
                BigDecimal price = new BigDecimal(entry.getValue());
                productOrder.setTotal(price.multiply(product.getPrice()));
                productOrderList.add(productOrder);
            }
            id = entry.getKey().getIdproduct();
            amount++;
        }
        Set<Product> product1 = products.keySet();
        ArrayList<Product> productArrayList = new ArrayList<>(product1);
        for (Product product2 : productArrayList) {
            productRepository.save(product2);
            productRepository.flush();
            products.clear();
        }
        long millis = System.currentTimeMillis();
        java.sql.Date now = new Date(millis);
        Orders orders = new Orders(amount, user.getAddressId().getStreet(), now, user);
        jdbcOrdersService.save(orders);
        Orders order = jdbcOrdersService.findLast();
        order.setUserId(user);
        for (ProductOrder productOrder : productOrderList) {
            productOrder.setOrderId(order);
            productOrderService.saveOrUpdate(productOrder);
        }


    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
