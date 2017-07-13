//package com.talan.rsa.entity.other;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import com.talan.rsa.entity.Chapter;
//import com.talan.rsa.entity.Rule;
//import com.talan.rsa.entity.Theme;
//
//@Component
//public class CustomListSerializr extends StdSerializer<List<Object>>{
//
//	protected CustomListSerializr(Class<List> t, boolean dummy) {
//		super(t, dummy);
//	}
//	
//	protected CustomListSerializr() {
//		this(null, false);
//	}
//
//
//	@Override
//	public void serialize(List<Object> list, JsonGenerator gen, SerializerProvider provider) throws IOException {
//		List<Long> chapsToShow = new ArrayList<>();
//		list.stream().forEach(element -> {
//			if(element.getClass() == Chapter.class){
//				Chapter chapter = (Chapter)element;
//				chapter.getTheme().getChapters().stream().forEach(chapt -> {
//					
//					chapsToShow.add(chapt.getId());
//				});
//				
//			}else if(element.getClass() == Rule.class){
//				
//			}else{
//				
//			}
//				try {
//					gen.writeObject(chapsToShow);
//					gen.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		});
//		
//	}
//
//}
