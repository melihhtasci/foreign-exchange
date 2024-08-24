package com.mtasci.foreign_exchange.mapper;

public abstract class BaseMapper<D, E> {

    public static <D, E> D toDto(E entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static <E, D> E toEntity(D dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
