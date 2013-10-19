/**
 * $Id: BasePdfView.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.view;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public abstract class BasePdfView extends AbstractView {
	
	public BasePdfView() {
	   setContentType("application/pdf");
    }
	
	@Override
    protected boolean generatesDownloadContent() {
        return true;
    }
	
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, 
    		HttpServletResponse response) throws Exception {
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		this.renderOutput(model, request, response, baos);
        writeToResponse(response, baos);
    }
	
	protected abstract void renderOutput(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response, ByteArrayOutputStream baos) throws Exception;

}

