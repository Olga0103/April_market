package ru.gb.april.market.april_market.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.april.market.april_market.models.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {


}