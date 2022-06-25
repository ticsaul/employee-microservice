package com.microservices.employee.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggingUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private LoggingUtil() {
	}

	public static <T> String getJsonFormato(T object, boolean beautified) {
		String result = null;
		try {
			if (beautified) {
				result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			} else {
				result = mapper.writeValueAsString(object);
			}
		} catch (JsonProcessingException jpe) {
			LOGGER.error(jpe.getMessage(), jpe);
		}
		return result;
	}
}
