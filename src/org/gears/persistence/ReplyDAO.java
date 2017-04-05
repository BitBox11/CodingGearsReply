package org.gears.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.gears.domain.BoardVO;

public class ReplyDAO {

	private static BasicDataSource dataSource;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dataSource = new BasicDataSource();
			dataSource.setUrl("jdbc:oracle:thin:@192.168.0.8:1521:XE");
			dataSource.setUsername("leo");
			dataSource.setPassword("leo");
			dataSource.setInitialSize(10); // 사실 10을 주면 안됨. why?? WAS쪽에 150개
											// 사용하면
			dataSource.setMinIdle(5);
			dataSource.setMaxIdle(10);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<BoardVO> detail(int bno) {

		List<BoardVO> list = new ArrayList<>();

		String sql = "SELECT * FROM TBL_BOARD2 where bno=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = dataSource.getConnection().prepareStatement(sql);

			pstmt.setInt(1, bno);

			rs = pstmt.executeQuery();

			rs.next();

			BoardVO vo = new BoardVO();

			vo.setBno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setContent(rs.getString(3));
			vo.setWriter(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setUpdatedate(rs.getDate(6));
			vo.setGno(rs.getString(7));
			vo.setGord(rs.getInt(8));
			vo.setParent(rs.getInt(9));

			list.add(vo);

		} catch (Exception e) {
			e.getMessage();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
		return list;

	}

	public void register(BoardVO vo){
		//답글
		String sql = "INSERT INTO TBL_BOARD2 (bno, title, content, writer, gno,gord, parent) "
				+ "VALUES (seq_board2.nextval, ?,?,?,?,?,?)";
		
	
		//수정문
		String sqlUpdate = "update tbl_board2 set gord = gord+1 where gno = ? and gord >= ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			dataSource.getConnection().setAutoCommit(false);
		//답글	
		if(vo.getParent() !=null){
		
		pstmt = dataSource.getConnection().prepareStatement(sqlUpdate);
		pstmt.setString(1, vo.getGno());
		pstmt.setInt(2, vo.getGord());
		rs = pstmt.executeQuery();
		
		pstmt.close();


		}
//		else{		
//		pstmt = dataSource.getConnection().prepareStatement(sql2);
//		pstmt.setString(1, vo.getTitle());
//		pstmt.setString(2, vo.getContent());
//		pstmt.setString(3, vo.getWriter());
//		rs = pstmt.executeQuery();
//		
//		pstmt.close();
		
		
		
		pstmt = dataSource.getConnection().prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriter());
		
		String result =vo.getParent() == null?"seq_board2.currval":vo.getGno();
		
		pstmt.setString(4, result);
		pstmt.setInt(5,vo.getGord());
		pstmt.setInt(6, vo.getParent());
		
		rs = pstmt.executeQuery();
		
		pstmt.close();
		
		
		}catch(Exception e){
			try {
				dataSource.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.getMessage();
			
		}finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
		

		}
	
	public List<BoardVO> readList(int page) {

		String sql = "select bno, gno, gord,  title, content, writer ,updatedate, regdate from"
				+ "( SELECT rownum rn, bno, gno, gord,  title, content, writer ,updatedate, regdate FROM"
				+ "(SELECT bno, gno, gord,  title, content, writer ,updatedate, regdate FROM tbl_board2 order "
				+ "by gno desc,gord asc)where rownum <=?*10) "
				+ "where rn>(?-1)*10";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();

		try {
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, page);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();

				vo.setBno(rs.getInt(1));
				vo.setGno(rs.getString(2));
				vo.setGord(rs.getInt(3));
				vo.setTitle(rs.getString(4));
				vo.setContent(rs.getString(5));
				vo.setWriter(rs.getString(6));
				vo.setUpdatedate(rs.getDate(7));
				vo.setRegdate(rs.getDate(8));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}

		}
		return list;

	}

	public int getListCount() throws Exception {
		String sql = "select count(bno) from tbl_board2 ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int total = -1;

		try {

			pstmt = dataSource.getConnection().prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				total = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}

		}

		return total;

	}

}
