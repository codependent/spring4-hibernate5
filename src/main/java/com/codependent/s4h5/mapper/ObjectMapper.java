package com.codependent.s4h5.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapeador de objetos mediante Dozer
 * 
 * @author JINGA4X
 * 
 */
@Component("objectMapper")
public class ObjectMapper {

	@Autowired
	private Mapper mapper;

	public <D> D convert(Object o, Class<D> clazz) {
		return (o == null) ? null : mapper.map(o, clazz);
	}

	public <D> Collection<D> convert(Collection<?> oList, Class<D> clazzDestino) {
		if (oList == null) {
			return null;
		} else {
			List<D> convertedList = new ArrayList<D>();
			for (Object or : oList) {
				convertedList.add(convert(or, clazzDestino));
			}
			return convertedList;
		}
	}

	public <D> List<D> convert(List<?> oList, Class<D> clazzDestino) {
		if (oList == null) {
			return null;
		} else {
			List<D> convertedList = new ArrayList<D>();
			for (Object or : oList) {
				convertedList.add(convert(or, clazzDestino));
			}
			return convertedList;
		}
	}
}
