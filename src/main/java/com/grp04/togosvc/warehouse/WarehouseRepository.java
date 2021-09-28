package com.grp04.togosvc.warehouse;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WarehouseRepository extends PagingAndSortingRepository<Warehouse, Long>{
    Warehouse findByProductId(Long productId);
}
