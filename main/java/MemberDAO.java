import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MemberDAO {
	private Statement stmt;
	private Connection conn;

	public ArrayList<MemberVO> listMember() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			connDB();
			String query = "select * from t_member";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query); // ResultSet class는 가져온 테이블(2차원 배열)을 저장하고있다.
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String joinDate = rs.getString("joindate");
				MemberVO mvo = new MemberVO();
				mvo.setId(id);
				mvo.setPwd(pwd);
				mvo.setName(name);
				mvo.setMobile(mobile);
				mvo.setJoinDate(joinDate);
				list.add(mvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void connDB() {
		String driver = "orcle.jdbc.driver.OracleDriver";
		String userid = "ora_user";
		String passcode = "human123";

		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(driver, userid, passcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
