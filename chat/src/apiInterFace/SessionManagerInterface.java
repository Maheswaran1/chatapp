package apiInterFace;


import com.zoho.model.SessionManager;


public interface SessionManagerInterface{
	
	public String getUserId(String sessionId);
	
	public SessionManager getSessionObject(String sessionId);
	
}
