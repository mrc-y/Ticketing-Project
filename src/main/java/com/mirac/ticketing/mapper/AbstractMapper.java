package com.mirac.ticketing.mapper;

import com.mirac.ticketing.model.dto.TransferObject;
import com.mirac.ticketing.model.entity.EntityObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to handle the methods that are needed for Mapstruct
 *
 * @author miracy
 */
public abstract class AbstractMapper<T extends TransferObject, E extends EntityObject> {

    public List<E> mapToEntityList(final List<T> dtoList) {
        final List<E> entityList = new ArrayList<>();
        if (dtoList == null) {
            return entityList;
        }
        for (final T dto : dtoList) {
            entityList.add(convert(dto));
        }
        return entityList;
    }

    public List<T> mapToDTOList(final List<E> entityList) {
        final List<T> dtoList = new ArrayList<>();
        if (entityList == null) {
            return dtoList;
        }

        entityList.forEach(entity -> dtoList.add(convert(entity)));

        return dtoList;
    }

    public abstract T convert(E entityObject);

    public abstract E convert(T transferObject);
}
