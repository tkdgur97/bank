package money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BankDAO {
	Connection conn;

	public BankDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/bank";
		String user = "root";
		String password = "1234";
		conn = DriverManager.getConnection(url, user, password);
	}

	// 해당 아이디가 있는지 확인
	public boolean idCheck(String id) throws Exception {
		// 중복 아이디가 있는지 확인
		String sql = "select * from member where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	// 신규 고객 정보 입력
	public void insertMember(BankDTO dto) throws Exception {

		String sql = "insert into member values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getName());
		ps.setInt(3, dto.getAge());
		ps.setString(4, dto.getTel());
		ps.executeUpdate();
	}

	// 고객 전화번호 수정
	public void updateMember(String id, String tel) throws Exception {
		String sql = "update member set tel = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, tel);
		ps.setString(2, id);
		ps.executeUpdate();

	}

	// 고객 탈퇴
	public void memberDelete(String id) throws Exception {
		String sql = "delete from member where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
	}

	// 전체 고객 리스트 조회
	public ArrayList<BankDTO> allMemberInfo() throws Exception {

		String sql = "select * from member";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<BankDTO> list = new ArrayList<>();
		while (rs.next()) {
			BankDTO dto = new BankDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setAge(rs.getInt("age"));
			dto.setTel(rs.getString("tel"));
			list.add(dto);
		}

		return list;

	}

}
