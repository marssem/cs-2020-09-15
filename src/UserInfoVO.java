
public class UserInfoVO {

	private String uiId;
	private int uiAge;
	private String uiPwd;
	private String cmd;
	public String getUiId() {
		return uiId;
	}
	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	public int getUiAge() {
		return uiAge;
	}
	public void setUiAge(int uiAge) {
		this.uiAge = uiAge;
	}
	public String getUiPwd() {
		return uiPwd;
	}
	public void setUiPwd(String uiPwd) {
		this.uiPwd = uiPwd;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	@Override
	public String toString() {
		return "UserInfoVO [uiId=" + uiId + ", uiAge=" + uiAge + ", uiPwd=" + uiPwd + ", cmd=" + cmd + "]";
	}
	
	
}
