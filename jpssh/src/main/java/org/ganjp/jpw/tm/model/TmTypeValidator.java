package org.ganjp.jpw.tm.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TmTypeValidator implements Validator {
	private static String[] reservedUserNames = { "aaaa", "bbbb" };
	private static List<String> reservedUserNameList = Arrays.asList(reservedUserNames);

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public void validate(Object target, Errors errors) {
		if (target instanceof TmType) {
			TmType tmType = (TmType) target;
			if (reservedUserNameList.contains(tmType.getStrType())) {
				errors.rejectValue("userName", "reserved");
			}
		}
	}
}
