import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class empDAO {
	private Statement stmt;
	private Connection conn;

	public ArrayList<empVO> listEmployees() {
		ArrayList<empVO> list = new ArrayList<empVO>(); // DTO
		try {
			connDB(); // DB 연결
			String query = "select a.employee_id,a.emp_name,b.emp_name manager_name,c.department_name "
					+ "from employees a,employees b,departments c "
					+ "where a.manager_id=b.employee_id and a.department_id=c.department_id"; // query문
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query); // ResultSet class는 가져온 테이블(2차원 배열)을 저장하고있다.

			while (rs.next()) {
				empVO eVO = new empVO();
				eVO.setEmp_id(rs.getInt("employee_id"));
				eVO.setEmp_name(rs.getString("emp_name"));
				eVO.setDepartment_name(rs.getString("department_name"));
				eVO.setManager_name(rs.getString("manager_name"));
				list.add(eVO);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private void connDB() {
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
