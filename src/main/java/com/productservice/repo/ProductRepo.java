package com.productservice.repo;

import com.productservice.models.Product;
import com.productservice.projections.ProductTitleAndDescribtion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

    //custom query
    @Query("SELECT p.title AS title, p.description as description FROM Product p WHERE p.id = :id")
    ProductTitleAndDescribtion getProductByTitleAndDesc(@Param("id") Long id);

    @Query(value = "SELECT title, description FROM Product WHERE id = :id", nativeQuery = true)
    ProductTitleAndDescribtion getProductByTitleAndDescSQL(@Param("id") Long id);
}

//JpaRepository<Class, primary-key-type>
