package com.ukefu.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.TemplateLoader;

public class TempletLoader implements TemplateLoader {
	private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";
	private Map<String, String> templates = new HashMap<String, String>();

	public TempletLoader(String defaultTemplate) {
		if (defaultTemplate != null && !defaultTemplate.equals("")) {
			templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
		}
	}

	public void AddTemplate(String name, String template) {
		if (name == null || template == null || name.equals("")
				|| template.equals("")) {
			return;
		}
		if (!templates.containsKey(name)) {
			templates.put(name, template);
		}
	}

	public void closeTemplateSource(Object templateSource) throws IOException {
	}

	public Object findTemplateSource(String name) throws IOException {
		if (name == null || name.equals("")) {
			name = DEFAULT_TEMPLATE_KEY;
		}
		return templates.get(name);
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return new StringReader((String) templateSource);
	}
}