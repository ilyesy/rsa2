package com.talan.rsa.entity.other;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ImplementationEnum {
	
	RecommendedImplementation("A"),
	NotRecommendedImplementation("B");
	
	 private String text;

	 ImplementationEnum(String text){this.text = text;}
	 
	 @Override public String toString() { return text; }
	 
	 public String getText(){return this.text;}
	 
	 @JsonCreator
	 public static ImplementationEnum fromText(String text){
	        for(ImplementationEnum r : ImplementationEnum.values()){
	            if(r.getText().equals(text)){
	                return r;
	            }
	        }
	        throw new IllegalArgumentException();
	    }

}
