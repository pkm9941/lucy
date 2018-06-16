package collection;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class GenericMapper {

	public static void main(String[] args) {
		GenericMapper mapper = new GenericMapper();
		Map<String, String> fromObj = new HashMap<>();
		fromObj.put("one", "원");
		TestObj toObj = new TestObj();
		mapper.copyObject(fromObj, toObj);
		System.out.println(toObj.getOne());
		
		toObj.setOne("");
		System.out.println(toObj.getOne());
		
		System.out.println(toObj);
		mapper.copyObject2(fromObj, toObj);
		System.out.println(toObj.getOne());
		
		
		
	}
	
	public <F, R> void copyObject(F fromObj, R toObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(fromObj, toObj);
	}
	
	public <F, R> R copyObject(F fromObj, Class<R> toClass) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(fromObj, toClass);
	}
	
	public <F, R> void copyObject2(F fromObj, R toObj) {
		System.out.println(toObj);
		toObj = (R) copyObject(fromObj, toObj.getClass());
		System.out.println(toObj);
	}
}
