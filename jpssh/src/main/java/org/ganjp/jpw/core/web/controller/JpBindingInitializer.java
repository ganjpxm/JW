package org.ganjp.jpw.core.web.controller;

import org.ganjp.jpw.tm.model.TmType;
import org.ganjp.jpw.tm.model.TmTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class JpBindingInitializer implements WebBindingInitializer{
  public void initBinder(WebDataBinder binder, WebRequest request) {
	  binder.registerCustomEditor(TmType.class, new TmTypeEditor());
   }
}
