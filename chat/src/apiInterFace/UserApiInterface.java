package apiInterFace;

import java.util.HashMap;

import com.zoho.model.User;


public interface UserApiInterface  {
	public User getUser(String userId);

	public int getSpam(String userId);
	
	public long updateProfile(String userId,HashMap<String, Object> map);

}
