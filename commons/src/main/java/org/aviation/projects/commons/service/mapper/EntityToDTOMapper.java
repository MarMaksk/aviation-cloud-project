package org.aviation.projects.commons.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDTOMapper<E, D> {
    E toEntity(D dto) throws Exception;

    D toDTO(E entity);

    default List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> dtos) {
        return dtos.stream().map(dto -> {
            try {
                return this.toEntity(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
