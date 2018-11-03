package ChatRoom.ServerPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.sqlite.*;

public class ConnectSQL {
	    
	  //  private static final String Class_Name = "org.sqlite.JDBC";
	    private static final String DB_URL = "jdbc:sqlite:/home/cedricxs/ChatRoom.db";

	    public static void main(String args[]) {
	        // load the sqlite-JDBC driver using the current class loader
	        Connection connection = null;
	        try {
	            connection = createConnection();
	            System.out.println("Connect to DB Success!");
	        }  catch (SQLException e) {
	            System.err.println(e.getMessage());
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally{
	            try {
	                if (connection != null)
	                    connection.close();
	            } catch (SQLException e) {
	                // connection close failed.
	                System.err.println(e);
	            }
	        }
	    }
	    
	    // 创建Sqlite数据库连接
	    public static Connection createConnection() throws SQLException, ClassNotFoundException {
		    return  JDBC.createConnection(DB_URL, new Properties() );
		    //Class.forName(Class_Name);显式加载JDBC类
		    //JDBC j =new JDBC()当调用new时，隐式加载JDBC类
	        //return DriverManager.getConnection(DB_URL);
	    }

	    public static boolean getLogin(Connection connection,QueryInfo<String, String> info) {
			Object name = info.getKey();
			Object pwd = info.getValue();	
			PreparedStatement statement = null;
			ResultSet rs = null;
			try {
				statement = connection.prepareStatement("select * from Users where username= ? and password=?");
				statement.setQueryTimeout(30);
				statement.setObject(1, name);
				statement.setObject(2, pwd);
				rs = statement.executeQuery();
				 return rs.next();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			 return false;
	    }
	    public static String getAllUsers(Connection connection,String thisname) {
		  String res = "";
		    try { 
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			 ResultSet rs = statement.executeQuery("select username,loginstatus from Users");
			 while(rs.next()) {
				 if(!thisname.equals(rs.getString("username"))){
					 res+=rs.getString("username");
					 res+=":";
					 res+=rs.getBoolean("loginstatus");
					 res+=" ";
				 }
			 }
		     } catch (SQLException e) {
				e.printStackTrace();
		     }
		    return res;
	    }
	    public static String getFriends(Connection connection,String thisname) {
			  String res = "";
			    try { 
				PreparedStatement statement = connection.prepareStatement("select u2.username,u2.loginstatus from Users u1,Users u2,FriendList where u1.ID=FriendList.userID and u2.ID=FriendList.hasFriend and u1.username=?");
				statement.setQueryTimeout(30);
				statement.setString(1, thisname);
				 ResultSet rs = statement.executeQuery();
				 while(rs.next()) {
					 if(!thisname.equals(rs.getString("username"))){
						 res+=rs.getString("username");
						 res+=":";
						 res+=rs.getBoolean("loginstatus");
						 res+=" ";
					 }
				 }
			     } catch (SQLException e) {
					e.printStackTrace();
			     }
			    return res;
		    }
	    public static int updateLoginStatus(Connection connection,boolean loginstatus,String username) {
		    	PreparedStatement statement = null;
			int rs;
			try {
				statement = connection.prepareStatement("update Users set loginstatus=?  where username=?");
				statement.setQueryTimeout(30);
				statement.setObject(1, loginstatus);
				statement.setObject(2, username);
				rs = statement.executeUpdate();
				 return rs;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			 return 0;
				
	    }
	    public static int updateLoginIP(Connection connection,String IP,String PORT,String username) {
		
		    	PreparedStatement statement = null;
			int rs;
			try {
				statement = connection.prepareStatement( "update UserIP set IP=?,PORT=? where UserID=( select ID from Users where username=?)");
				statement.setQueryTimeout(30);
				statement.setObject(1, IP);
				statement.setObject(2, PORT);
				statement.setObject(3, username);
				rs = statement.executeUpdate();
				 return rs;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			 return 0;
	    }
	    public static String getUserIP(Connection connection,String username) {
		    String res = "";
		    try {
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery("select IP,PORT from UserIP,Users where UserIP.UserID=Users.ID and Users.username='"+username+"'");
				while(rs.next()&&res.equals("")) {
					res+=username;
					res+=":";
					res+=rs.getString("IP");
					res+=":";
					res+=rs.getString("PORT");
				}
		    }
		    catch (SQLException e) {	
			    e.printStackTrace();
		    }
		    return res;
	    }
	    public static int addBufferMsg(Connection connection,String msg,String recusername,String sendusername) {
		    	PreparedStatement statement = null;
			int rs;
			try {
				statement = connection.prepareStatement( "insert into BufferMsg values(?,?,?)");
				statement.setQueryTimeout(30);
				statement.setObject(1, recusername);
				statement.setObject(2, sendusername);
				statement.setObject(3, msg);
				rs = statement.executeUpdate();
				 return rs;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			 return 0;
	    }
	    
}