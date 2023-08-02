package apiInterFace;

import java.util.ArrayList;

import com.zoho.annotation.cacheAnnotaion;
import com.zoho.model.Email;
import com.zoho.model.ObjectFactory;
@cacheAnnotaion
public interface EmailApiInterface{ 
	
	public ArrayList<Email> getAllEmail(String userId);
	
	public Email getPrimaryEmail(String userId);
	
	public ArrayList<Email> getVerifedEmails(String userId,boolean status);
	
	public String getUserId(String EmailId);
	
	public Email getEmailObject(String userId);

	
	
}
