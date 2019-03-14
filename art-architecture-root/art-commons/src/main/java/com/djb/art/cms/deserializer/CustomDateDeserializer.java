package com.djb.art.cms.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static com.djb.art.cms.utils.DateUtils.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException {
		
		try {
			return deserializeDate(jsonParser.getText());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}


	
}
