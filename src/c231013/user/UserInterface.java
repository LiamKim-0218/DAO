package c231013.user;

public interface UserInterface {
	/**
	 * use only select in DB
	 * */
	public void setId(int id);
	public int getId();
	public void setName(String name);
	public String getName();
	public void setUserId(String userId);
	public String getUserId();
	public void setPassword(String password);
	public String getPassword();
}