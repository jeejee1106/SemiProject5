package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import data.dto.AdminApplyDTO;
import mysql.db.DBConnect;

public class AdminApplyDAO {
	DBConnect db = new DBConnect();

	// 전체 회원 출력
	public ArrayList<AdminApplyDTO> getAllMembers() {
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member order by idx desc";

		ArrayList<AdminApplyDTO> list = new ArrayList<AdminApplyDTO>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminApplyDTO dto = new AdminApplyDTO();

				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setNick(rs.getString("nick"));
				dto.setHp(rs.getString("hp"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAuth1(rs.getString("auth1"));
				dto.setAuth2(rs.getString("auth2"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}

		return list;
	}
	
	
	
	
	
	// 대회별 회원 출력
		public ArrayList<AdminApplyDTO> getSelectMembers(String sel) {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "select * from apply where marathon=? order by idx desc";

			ArrayList<AdminApplyDTO> list = new ArrayList<AdminApplyDTO>();

			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, sel);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					AdminApplyDTO dto = new AdminApplyDTO();

					dto.setAidx(rs.getString("idx"));
					dto.setAmarathon(rs.getString("marathon"));
					dto.setAid(rs.getString("id"));
					dto.setAname(rs.getString("name"));
					dto.setAaddr1(rs.getString("addr1"));
					dto.setAaddr2(rs.getString("addr2"));
					dto.setAhp(rs.getString("hp"));
					dto.setAcourse(rs.getString("course"));
					dto.setAtime(rs.getString("time"));
					dto.setAperson(rs.getString("person"));

					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.dbClose(rs, pstmt, conn);
			}

			return list;
		}
	
	
	
	
	
	
	
	// 전체 대회 버튼 출력
		public ArrayList<AdminApplyDTO> getContestName() {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "select * from contest order by contest_start desc";

			ArrayList<AdminApplyDTO> list = new ArrayList<AdminApplyDTO>();

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					AdminApplyDTO dto = new AdminApplyDTO();

					dto.setContestName(rs.getString("name"));
					dto.setContestStart(rs.getString("contest_start"));
					dto.setContestEnd(rs.getString("contest_end"));
					dto.setApplyStart(rs.getString("apply_start"));
					dto.setApplyEnd(rs.getString("apply_end"));
					dto.setMoney(rs.getString("money"));

					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.dbClose(rs, pstmt, conn);
			}

			return list;
		}
	

	
	
		// 기념품 발송정보 입력
		public void delivery(AdminApplyDTO dto) {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = null;

			String sql = "update apply set delivery=? where id=?";

			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, dto.getDelivernum());
				pstmt.setString(2, dto.getCheckid());

				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.dbClose(pstmt, conn);
			}
		}
		
		
		
		
		
		// 완주시간 입력
		public void recordtime(AdminApplyDTO dto) {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = null;

			String sql = "update apply set finishcourse=?, finishhour=?, finishminute=? where id=?";

			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, dto.getFinishcourse());
				pstmt.setInt(2, dto.getFinishhour());
				pstmt.setInt(3, dto.getFinishminute());
				pstmt.setString(4, dto.getRecordid());

				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.dbClose(pstmt, conn);

			}
		}
		
		
		// 페이스 계산
		public double getPace(String id) {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			double pace = 0;

			String sql = "select finishhour,finishminute,finishcourse from apply where id=?";

			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					AdminApplyDTO dto = new AdminApplyDTO();
					
					dto.setFinishcourse(rs.getInt("finishcourse"));
					dto.setFinishhour(rs.getInt("finishhour"));
					dto.setFinishminute(rs.getInt("finishminute"));
					
					pace = ((dto.getFinishhour()*60)+dto.getFinishminute())/dto.getFinishcourse();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.dbClose(rs, pstmt, conn);
			}
			
			return pace;
		}
	
		
		
		
	// 삭제
	public void deleteMember(String id) {
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from member where id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	
	
	

	
	  // 수정 
	  public void updateMember(AdminApplyDTO dto) { 
	  Connection conn = db.getConnection(); 
	  PreparedStatement pstmt = null;
	  
	  String sql = "update member set name=?,nick=?,hp=?,pass=?,addr1=?,addr2=?,auth1=?,auth2=? where id=?";
	  
	  try { pstmt = conn.prepareStatement(sql);
	  
	  pstmt.setString(1, dto.getName()); 
	  pstmt.setString(2, dto.getNick());
	  pstmt.setString(3, dto.getHp()); 
	  pstmt.setString(4, dto.getPass());
	  pstmt.setString(5, dto.getAddr1());
	  pstmt.setString(6, dto.getAddr2());
	  pstmt.setString(7, dto.getAuth1());
	  pstmt.setString(8, dto.getAuth2());
	  pstmt.setString(9, dto.getId());
	  
	  pstmt.execute();
	  } catch (SQLException e) { 
		  // TODO Auto-generated catch
		  e.printStackTrace(); 
	  } finally { 
		  db.dbClose(pstmt, conn);
	  } 
	}

}
