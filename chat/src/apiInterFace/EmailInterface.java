package apiInterFace;


public interface EmailInterface extends EmailApiInterface{
		
	public long removeprimaryEmail();
	
	public long  addEmail(String email, String userId,boolean primaryEmail,boolean verified);
	
	public long checkExistingEmail(String emailId, String string);

	public long delete(String emailId);

	public long setPrimaryEmail(String emailId,String userId);

}
