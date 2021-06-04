package ru.gb.april.market.april_market.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.models.ProductSOAP;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findAllBy(Pageable pageable);

    Optional<ProductSOAP> findByName(String name);
}
