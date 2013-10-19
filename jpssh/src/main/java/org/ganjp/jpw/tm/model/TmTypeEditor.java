package org.ganjp.jpw.tm.model;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

public class TmTypeEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TmType tmType = new TmType();
		if (text != null) {
			String[] items = text.split(":");
			tmType.setStrType(items[0]+"by propertyeEditor");
			tmType.setNumberType(new BigDecimal(items[1]));
		}
		setValue(tmType);
	}
}
