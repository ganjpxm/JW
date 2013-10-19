package org.ganjp.jpw.tm.model;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

public class TmTypeConverter implements Converter<String,TmType>{

	public TmType convert(String source) {
		TmType tmType = new TmType();
		if(source != null){
			String[] items = source.split(":");
			tmType.setStrType(items[0]);
			tmType.setNumberType(new BigDecimal(items[1]));
		}
		return tmType;
	}
}
