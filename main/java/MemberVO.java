// database table과 같은 구조(schema) + getter/setter
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String mobile;
	private String joinDate;
	
	public MemberVO() {
		
	}
	

	public MemberVO(String id, String pwd, String name, String mobile, String joinDate) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.mobile = mobile;
		this.joinDate = joinDate;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
}
