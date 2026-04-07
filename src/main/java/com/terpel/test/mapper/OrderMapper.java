package com.terpel.test.mapper;

import com.terpel.test.dto.request.CreateOrderRequest;
import com.terpel.test.dto.response.CreateOrderResponse;
import com.terpel.test.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Orders toEntity(CreateOrderRequest request);

    CreateOrderResponse toResponse(Orders entity);
}