package com.ecommerce.ecommercedemo.repository;

import com.ecommerce.ecommercedemo.domain.entity.ProductEntity;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String>,
        PagingAndSortingRepository<ProductEntity,String> {

    @Query("select p from ProductEntity p where p.uuid = ?1")
    Optional<ProductEntity> findByUuid (String uuid);

    void deleteByUuid(String uuid);

//   @Transactional
//    @Query("delete from ProductEntity p WHERE p.uuid = :uuid")
//    void deleteByUuid(String uuid);
   

}
