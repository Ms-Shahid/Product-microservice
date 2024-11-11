package com.productservice.repo;

import com.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

    //custom query
    @Query("SELECT p.title AS title, p.description as description FROM Product p WHERE p.di = ")
    Product getProductByTitleAndDesc(@Param("id") Long id);

    @Query(value = "SELECT title, description FROM Product WHERE id = :id", nativeQuery = true)
    Product getProductByTitleAndDescSQL(@Param("id") Long id);
}

//JpaRepository<Class, id>
