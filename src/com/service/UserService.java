package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.domain.Users;
import com.utils.SqlHelper;

public class UserService {
	// ��֤�û��Ϸ���
	public boolean checkUser(Users user) {
		boolean b = false;
		String sql = "select * from users where id=? and passwd=?";
		String para[] = { user.getId() + "", user.getPassword() };
		ResultSet rs = SqlHelper.excuteQuery(sql, para);
		try {
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return b;
	}

	// ��ȡ��ҳҳ��
	public int getPageCount(int pagesize) {
		String sql = "select count(*) from users";
		ResultSet rs = SqlHelper.excuteQuery(sql, null);
		int rowcount = 0;
		try {
			rs.next();
			rowcount = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}

		return (rowcount - 1) / pagesize + 1;
	}

	// ��ҳ�㷨
	public ArrayList getUserByPage(int pagenow, int pagesize) {
		ArrayList<Users> al = new ArrayList<Users>();
		String sql = "select * from users limit ?,?";
		int para[] = { (pagenow * pagesize - 3), pagesize };
		ResultSet rs = SqlHelper.intExcuteQuery(sql, para);
		try {
			while (rs.next()) {

				Users user = new Users();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setGrade(rs.getInt(4));
				al.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return al;
	}

	// ɾ���û�
	public boolean delUser(String id) {
		boolean b = true;
		String sql = "delete from users where id=?";
		String para[] = { id };
		try {
			SqlHelper.ExcuteUpdate(sql, para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(),
					SqlHelper.getCt());
		}
		return b;
	}

	// ͨ���û�id��ȡ�û���Ϣ
	public Users getUserById(String id) {
		Users users = new Users();
		String sql = "select * from users where id=?";
		String para[] = { id };
		try {
			ResultSet rs = SqlHelper.excuteQuery(sql, para);
			if (rs.next()) {
				users.setId(rs.getInt(1));
				users.setUsername(rs.getString(2));
				users.setEmail(rs.getString(3));
				users.setGrade(rs.getInt(4));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(),
					SqlHelper.getCt());
		}
		return users;
	}

	// �޸��û�
	public boolean UpdateUser(Users users) {
		boolean b = true;
		String sql = "update users set username=?,email=?,grade=? where id=?";
		String para[] = { users.getUsername(), users.getEmail(),
				users.getGrade() + "", users.getId() + "" };
		try {
			SqlHelper.ExcuteUpdate(sql, para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(),
					SqlHelper.getCt());
		}
		return b;
	}

	// �����û�
	public boolean addUser(Users users) {
		boolean b = true;
		String sql = "insert into users values(?,?,?,?,?)";
		String para[] = { users.getId() + "", users.getUsername(),
				users.getEmail(), users.getGrade() + "", users.getPassword() };
		try {
			SqlHelper.ExcuteUpdate(sql, para);
		} catch (Exception e) {
			b = false;
			// TODO: handle exception
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(),
					SqlHelper.getCt());
		}
		return b;
	}

	// ��ѯ�û�
	public Users getUserByName(String username) {
		Users users = new Users();
		String sql = "select * from users where username=?";
		String para[] = { username };
		try {
			ResultSet rs = SqlHelper.excuteQuery(sql, para);
			if (rs.next()) {
				users.setId(rs.getInt(1));
				users.setUsername(rs.getString(2));
				users.setEmail(rs.getString(3));
				users.setGrade(rs.getInt(4));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(),
					SqlHelper.getCt());
		}
		return users;
	}
}