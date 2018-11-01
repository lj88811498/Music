package cn.owntt.util;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库底层操作工具类
 * 
 */
public class DBUtil {
	static DBUtil instance=null;
	static Connection conn = null;// 连接对象
	PreparedStatement ps = null;// 预编译语句对象
	ResultSet rs = null;// 结果集
	CallableStatement cs=null;//调用存储过程的语句对象
	//Oracle连接数据库参数
	static String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	static String UserName="scott";
	static String PassWord="tiger";
	static String Driver="oracle.jdbc.driver.OracleDriver";
	//sql server2008
	static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String conStr = "jdbc:sqlserver://localhost:1433;DataBaseName=db45";
	static final String username = "sa";
	static final String pwd = "";
	//mysql
	static final String mdriver = "com.mysql.jdbc.Driver";
	static final String mconStr = "jdbc:mysql://localhost:3306/mp3?useSSL=false";
	static final String mconStr2 = "jdbc:mysql://47.105.52.119:3306/mp3?useSSL=false&useUnicode=true&characterEncoding=utf-8";
	static final String musername = "root";
	static final String mpwd = "0926";
	private DBUtil(){};
	public static synchronized DBUtil getInstance(){
		if(instance==null)
			instance=new DBUtil();
		return instance;
	}
	/**
	 * 获取连接对象
	 * 
	 * @return
	 */
	/*	public static Connection getConnection() {
		try {
			Class.forName("org.logicalcobwebs.proxool.admin.servlet.AdminServlet");
			//Class.forName(driver);
			//conn = DriverManager.getConnection(PropertyReader.getProperties("conStr"), PropertyReader.getProperties("username"), PropertyReader.getProperties("pwd"));
			conn=DriverManager.getConnection("proxool.myProxool");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
			
		public static Connection getConnection() {
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(PropertyReader.getProperties("url"), PropertyReader.getProperties("UserName"), PropertyReader.getProperties("PassWord"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	*/
		public static Connection getConnection() {
			try {
				Class.forName(mdriver);
				conn = DriverManager.getConnection(mconStr2,musername,mpwd);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}	
		
	/**
	 * 
	 * 测试oracle链接
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	/**
	 * 获取预编译语句对象
	 * @param sql
	 * @return 预编译语句对象
	 */
	public PreparedStatement getPrepareStatement(String sql) {
		try {
			ps=getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
 
	/**
	 * 执行预编译语句对象的查询操作
	 * 
	 * @param ps
	 * @return 结果集
	 */
	public ResultSet executeQuery(PreparedStatement ps) {
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行预编译语句对象的增删改操作
	 * 
	 * @param ps
	 * @return 影响行数
	 */
	public int executeUpdate(PreparedStatement ps) {
		int count = 0;
		try {
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 调用执行存储过程并传参
	 * @param proc
	 * @param os
	 * @return 结果集
	 */
   public ResultSet getRSWithProc(String proc,Object[] os){
	   try {
		cs=getConnection().prepareCall(proc);
		if(os!=null){
			for (int i = 0; i < os.length; i++) {
				if(os[i] instanceof String)
					cs.setString(i+1, (String) os[i]);
				if(os[i] instanceof Integer)
					cs.setInt(i+1, (Integer) os[i]);
			}
		}
		rs=cs.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return rs;
   }
	/**
	 * 关闭三个对象的方法，注意顺序
	 */
   /**
    * 关闭结果集
    */
	public void closeRs() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * 关闭预编译语句对象
 */
	public void closePs() {
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭调用存储过程的语句对象
	 */
	public void closeCs() {
		try {
			if (cs != null) {
				cs.close();
				cs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * 关闭连接对象
 */
	public void closeConn() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
