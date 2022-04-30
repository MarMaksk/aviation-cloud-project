package com.proj.flight.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDTOMapper<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);

    default List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
