package apiInterFace;

import com.zoho.annotation.CacheClearAnnotation;

@CacheClearAnnotation
public interface CacheClearInterface {
	
	public int insert(String key,String field);
	
	public int delete(String key,String field);
	
	public int update(String key,String field);
}
