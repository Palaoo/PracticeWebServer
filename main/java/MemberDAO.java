import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	private Statement stmt;
	private Connection conn;

	public boolean searchMember(String id, String passwd) {
		boolean isExist = false;
		try {
			connDB();
			String query = "select id,pwd from t_member where id='" + id + "' and pwd='" + passwd + "'";
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int n = 0;
			while (rs.next())
				n++;

			if (n == 1)
				isExist = true;
			else
				isExist = false;
			
			stmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExist;
	}

	public void updateMember(MemberVO mvo) {
		try {
			connDB();
			String query = "update t_member set pwd=?, name=?, mobile=?, joindate=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mvo.getPwd());
			pstmt.setString(2, mvo.getName());
			pstmt.setString(3, mvo.getMobile());
			pstmt.setString(4, mvo.getJoinDate());
			pstmt.setString(5, mvo.getId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMember(String id) {
		try {
			connDB();
			String query = "delete from t_member where id=(?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberVO getMember(String id) {
		MemberVO mvo = new MemberVO();
		try {
			connDB();
			String query = "select * from t_member where id='" + id + "'";
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			mvo.setId(id);
			mvo.setPwd(rs.getString("pwd"));
			mvo.setName(rs.getString("name"));
			mvo.setMobile(rs.getString("mobile"));
			mvo.setJoinDate(rs.getString("joindate"));
			this.stmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mvo;
	}

	public void addnewMember(MemberVO mvo) throws SQLException {
		try {
			connDB();
			String query = "insert into t_member values(?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, mvo.getId());
			psmt.setString(2, mvo.getPwd());
			psmt.setString(3, mvo.getName());
			psmt.setString(4, mvo.getMobile());
			psmt.setString(5, mvo.getJoinDate());

			psmt.executeUpdate();
			psmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MemberVO> listMember() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>(); // DTO
		try {
			connDB(); // DB 연결
			String query = "select * from t_member"; // query문
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
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void connDB() { // DB와 연결
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userid = "ora_user";
		String passcode = "human123";

		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, userid, passcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
