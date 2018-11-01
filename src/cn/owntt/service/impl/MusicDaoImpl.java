package cn.owntt.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.owntt.entity.Music;
import cn.owntt.service.IMusicDao;
import cn.owntt.util.DBUtil;

/**
 * 经纪人持久层实现类
 * 
 * @author LYC
 * 
 */
@Service
public class MusicDaoImpl implements IMusicDao {

	DBUtil db = DBUtil.getInstance();

	private MusicDaoImpl dao = null;

	public IMusicDao getInstance() {
		if (dao == null) {
			dao = new MusicDaoImpl();
		}
		return dao;
	}

	public List<Music> selectMusics(int page, String name) {
		if (page <= 0) {
			page = 1;
		}
		List<Music> musics = new ArrayList<Music>();
		Music m;
		try {
			String sql = "select * from music ";
			if (!"".equals(name) && name != null && !"null".equals(name)) {
				sql += "where name like ?";
			}
			sql += " order by mTop , id , createTime desc ";
			sql +=" limit " + (page - 1) * 10 + ", 10";
			// if(!b_ischeck.equals("null"))
			// sql+=" where b_ischeck="+b_ischeck;
			PreparedStatement pstatement = db.getPrepareStatement(sql);
			if (!"".equals(name) && name != null && !"null".equals(name)) {
				pstatement.setString(1, "%" + name + "%");
			}
			System.out.println("sql : "+ sql );
			System.out.println("param: page = " + page + ", name = %" + name + "%");
			ResultSet rs = db.executeQuery(pstatement);
			while (rs.next()) {
				m = new Music();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setAuthor(rs.getString("author"));
				m.setUrl(rs.getString("url"));
				musics.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeRs();
			db.closePs();
			db.closeConn();
		}
		return musics;
	}

	public Music selectMusicById(int id) {
		 
		Music m = null;;
		try {
			String sql = "select * from music where id = ?";
			// if(!b_ischeck.equals("null"))
			// sql+=" where b_ischeck="+b_ischeck;
			PreparedStatement pstatement = db.getPrepareStatement(sql);
			pstatement.setInt(1, id);
			ResultSet rs = db.executeQuery(pstatement);
			while (rs.next()) {
				m = new Music();;
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setAuthor(rs.getString("author"));
				m.setUrl(rs.getString("url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeRs();
			db.closePs();
			db.closeConn();
		}
		return m;
	}

	
	public int count(String name) {
		int num = 0;
		try {
			String sql = "select count(1) from music";
			if (!"".equals(name) && name.trim().length() > 0) {
				sql += " where name like ?";
			}
			// if(!b_ischeck.equals("null"))
			// sql+=" where b_ischeck="+b_ischeck;
			PreparedStatement pstatement = db.getPrepareStatement(sql);
			if (!"".equals(name) && name.trim().length() > 0) {
				pstatement.setString(1, "%" + name + "%");
			}
			ResultSet rs = db.executeQuery(pstatement);
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeRs();
			db.closePs();
			db.closeConn();
		}
		return num;
	}

	public boolean insert(Music m) {
		try {
			String addString = "insert into music(name,author,mSize,url) values(?,?,?,?)";
			PreparedStatement pstatement = db.getPrepareStatement(addString);

			pstatement.setString(1, m.getName());
			pstatement.setString(2, m.getAuthor());
			pstatement.setString(3, m.getmSize());
			pstatement.setString(4, m.getUrl());

			db.executeUpdate(pstatement);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closePs();
			db.closeConn();
		}
		return false;
	}

	// public int deleteBrokers(int id) {
	// int count = 0;
	// try {
	// String deleteString = "delete from brokers where b_id=?";
	// PreparedStatement pstatement = db.getPrepareStatement(deleteString);
	// pstatement.setInt(1, id);
	// count = db.executeUpdate(pstatement);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closePs();
	// db.closeConn();
	// }
	// return count;
	// }

	//
	// public List<Brokers> queryAllBrokers(String b_ischeck) {
	// List<Brokers> brokers = new ArrayList<Brokers>();
	// Brokers broker;
	// try {
	// String sql = "select * from brokers";
	// if(!b_ischeck.equals("null"))
	// sql+=" where b_ischeck="+b_ischeck;
	// PreparedStatement pstatement = db.getPrepareStatement(sql);
	// ResultSet rs = db.executeQuery(pstatement);
	// while(rs.next()){
	// broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_company(rs.getString("b_company"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// brokers.add(broker);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return brokers;
	// }
	//
	// public Brokers queryBrokersById(int cId,String b_ischeck) {
	// Brokers broker = null;
	// try {
	// String sql = "select * from brokers where b_id=?";
	// if(b_ischeck!=null)
	// sql+="  and b_ischeck="+b_ischeck;
	// PreparedStatement pstatement = db.getPrepareStatement(sql);
	// pstatement.setInt(1, cId);
	// ResultSet rs = db.executeQuery(pstatement);
	// if(rs.next()){
	// broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_company(rs.getString("b_company"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));;
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return broker;
	// }
	//
	// public int updateBrokers(Brokers brokers) {
	// int count = 0;
	// try {
	// String updString =
	// "update brokers set b_name=?,b_pwd=?,b_realname=?,b_email=?,b_card=?,b_head_img=?,b_card_img=?,b_business_img=?,b_tel=?,b_h_ids=?,b_company=?,b_infos=?,b_grade=?,b_level=?,b_ischeck=?,c_id=?,b_isonline=? where b_id=?";
	// PreparedStatement pstatement = db.getPrepareStatement(updString);
	// pstatement.setString(1, brokers.getB_name());
	// pstatement.setString(2, brokers.getB_pwd());
	// pstatement.setString(3, brokers.getB_realname());
	// pstatement.setString(4, brokers.getB_email());
	// pstatement.setString(5, brokers.getB_card());
	// pstatement.setString(6, brokers.getB_head_img());
	// pstatement.setString(7, brokers.getB_card_img());
	// pstatement.setString(8, brokers.getB_business_img());
	// pstatement.setString(9, brokers.getB_tel());
	// pstatement.setString(10, brokers.getB_h_ids());
	// pstatement.setString(11, brokers.getB_company());
	// pstatement.setString(12, brokers.getB_infos());
	// pstatement.setString(13, brokers.getB_grade());
	// pstatement.setString(14, brokers.getB_level());
	// pstatement.setString(15, brokers.getB_ischeck());
	// pstatement.setInt(16, brokers.getC_id());
	// pstatement.setInt(17, brokers.getB_isonline());
	// pstatement.setInt(18, brokers.getB_id());
	// count = db.executeUpdate(pstatement);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closePs();
	// db.closeConn();
	// }
	// return count;
	// }
	//
	// public Brokers queryBrokersByName(String bName) {
	// try {
	// String sql = "select * from brokers where b_name=? and b_ischeck=?";
	// PreparedStatement pstatement = db.getPrepareStatement(sql);
	// pstatement.setString(1, bName);
	// pstatement.setString(2,"1");
	// ResultSet rs =pstatement.executeQuery();
	// if(rs.next()){
	// Brokers broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_company(rs.getString("b_company"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));;
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// return broker;
	// }
	// return null;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return null;
	// }
	//
	// public Brokers queryBrokersByEmail(String bEmail) {
	// try {
	// String sql = "select * from brokers where b_email=? ";
	// PreparedStatement pstatement = db.getPrepareStatement(sql);
	// pstatement.setString(1, bEmail);
	// ResultSet rs = pstatement.executeQuery();
	// Brokers broker=null;
	// if(rs.next()){
	// broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// }
	// return broker;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return null;
	// }
	//
	// public List<Brokers> queryByBrokerAndCompany(DoPage doPage,String
	// ischeck) {
	// String sql="select * from brokers b,companys c where b.c_id=c.c_id  ";
	// if(!ischeck.equals("null"))
	// sql+=" and b_ischeck="+ischeck;
	// sql+="  order by b_id desc limit ?,?";
	// PreparedStatement ps=db.getPrepareStatement(sql);
	// List<Brokers> list=new ArrayList<Brokers>();
	// try {
	// ps.setInt(1, (doPage.getCurrentPage()-1)*doPage.getPageSize());
	// ps.setInt(2, doPage.getPageSize());
	// ResultSet rs=ps.executeQuery();
	// while(rs.next()){
	// Brokers broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_company(rs.getString("c_name"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// list.add(broker);
	// }
	// return list;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return null;
	// }
	//
	// public List<Brokers> queryByBrokerAndCompany(String ischeck) {
	// String sql="select * from brokers b,companys c where b.c_id=c.c_id  ";
	// if(!ischeck.equals("null"))
	// sql+=" and b_ischeck="+ischeck;
	// PreparedStatement ps=db.getPrepareStatement(sql);
	// List<Brokers> list=new ArrayList<Brokers>();
	// try {
	// ResultSet rs=ps.executeQuery();
	// while(rs.next()){
	// Brokers broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_company(rs.getString("c_name"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// list.add(broker);
	// }
	// return list;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return null;
	// }
	//
	// public int setBrokersIsonline() {
	// String sql="update brokers set b_isonline=0 where b_id>0";
	// PreparedStatement ps=db.getPrepareStatement(sql);
	// try {
	// int result=ps.executeUpdate();
	// return result;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closePs();
	// db.closeConn();
	// }
	// return 0;
	// }
	//
	// public List<Brokers> queryBrokersByC_id(int c_id,String b_ischeck) {
	// List<Brokers> brokers = new ArrayList<Brokers>();
	// Brokers broker;
	// try {
	// String sql = "select * from brokers where c_id=?";
	// if(!b_ischeck.equals("null"))
	// sql+=" where b_ischeck="+b_ischeck;
	// PreparedStatement pstatement = db.getPrepareStatement(sql);
	// pstatement.setInt(1, c_id);
	// ResultSet rs = db.executeQuery(pstatement);
	// while(rs.next()){
	// broker = new Brokers();
	// broker.setB_id(rs.getInt("b_id"));
	// broker.setB_name(rs.getString("b_name"));
	// broker.setB_pwd(rs.getString("b_pwd"));
	// broker.setB_realname(rs.getString("b_realname"));
	// broker.setB_email(rs.getString("b_email"));
	// broker.setB_card(rs.getString("b_card"));
	// broker.setB_head_img(rs.getString("b_head_img"));
	// broker.setB_card_img(rs.getString("b_card_img"));
	// broker.setB_business_img(rs.getString("b_business_img"));
	// broker.setB_tel(rs.getString("b_tel"));
	// broker.setB_h_ids(rs.getString("b_h_ids"));
	// broker.setB_company(rs.getString("b_company"));
	// broker.setB_infos(rs.getString("b_infos"));
	// broker.setB_grade(rs.getString("b_grade"));
	// broker.setB_level(rs.getString("b_level"));
	// broker.setB_ischeck(rs.getString("b_ischeck"));
	// broker.setC_id(rs.getInt("c_id"));
	// broker.setB_isonline(rs.getInt("b_isonline"));
	// brokers.add(broker);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }finally{
	// db.closeRs();
	// db.closePs();
	// db.closeConn();
	// }
	// return brokers;
	// }
}
