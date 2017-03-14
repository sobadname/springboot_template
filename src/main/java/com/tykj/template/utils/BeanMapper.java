package com.tykj.template.utils;

import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BeanMapper {

	private static MapperFacade mapper = null;

	static {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapper = mapperFactory.getMapperFacade();

	}

	public static <S, D> D map(S source, Class<D> destinationClass) {
		return mapper.map(source, destinationClass);
	}

	public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
		return mapper.mapAsList(sourceList, destinationClass);
	}

	public static <S, D> D[] mapAsArray(D[] paramArrayOfD, S[] paramArrayOfS, Class<D> paramClass) {
		return mapper.mapAsArray(paramArrayOfD, paramArrayOfS, paramClass);
	}

}
