package com.talan.rsa.entity.other;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessageBuilder {
	
	public String buildErrorMessage(String entityName, List<Long> ids){
		StringBuilder message = new StringBuilder();
		message.append(entityName).append(" ").append("with id").append(" ").append(ids.toString()).append(" not found");
		
		
		if (ids.size() > 1 ){
			message.insert(message.indexOf(entityName) + entityName.length(), "s");
			message.insert(message.indexOf("id") + 2, "s");
		}
			
		
		return message.toString();
	}

}
