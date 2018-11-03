package ChatRoom.ServerPack;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
/**
 * 对于Mongoose来说,DataBase就是一个数据库
 * 			，Collection is a table
 * 			 ,  Document is a record of table
 * @author cedricxs
 *
 */
public class DataBase{
	//get user table
	MongoCollection<Document> userCollection = null;
	public void startDataBase() {
	         // 连接到 mongodb 服务
	         @SuppressWarnings("resource")
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         // 连接到数据库
	         MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");  
	         System.out.println("Connect to database successfully");
	         //get the table of user
	         userCollection = mongoDatabase.getCollection("user");
	}
	public void addBufferMsg(String msg,String username) {
		UserSchema query = new UserSchema(username);
		MongoCursor<Document> it = userCollection.find(new Document(query)).iterator();
		if(it.hasNext()) {
			it.next().append("BufferMsg", msg);
		}
	}
	public String getAllUsers(String thisname) {
		String res = "";
		MongoCursor<Document> it = userCollection.find().iterator();
		while(it.hasNext()) {
			Document user =  it.next();
			String name = (String)user.get("name");
			res+=name.equals(thisname)? "":name+"&";
		}
		return res;
	}
	public void updateLoginStatus(boolean loginstatus,String username) {
		UserSchema query = new UserSchema(username);
		MongoCursor<Document> it = userCollection.find(new Document(query)).iterator();
		if(it.hasNext()) {
			System.out.println("更新登录状态");
			it.next().append("loginstatus",loginstatus);
		}
	}
	public void insertItem(Map<String,Object> user) {
	            userCollection.insertOne(new Document(user));
	}
	public boolean areadyHas(Entry<String,Object> username) {
		UserSchema query = new UserSchema(username.getValue());
		return userCollection.find(new Document(query)).iterator().hasNext();
	}
	public boolean getLogin(Entry<Object,Object> Loginfo) {
		Object name = Loginfo.getKey();
		Object pwd = Loginfo.getValue();
		UserSchema query = new UserSchema(name,pwd);
		return userCollection.find(new Document(query)).iterator().hasNext();
	}
}

class QueryInfo<K,V> implements Entry<K,V>{
	K name;
	V pwd;
	public QueryInfo(K name,V pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	@Override
	public K getKey() {
		return name;
	}
	@Override
	public V getValue() {
		return pwd;
	}
	@Override
	public V setValue(V value) {
		V old = pwd;
		pwd = value;
		return old;
	}
}
class UserSchema extends HashMap<String,Object>{
	private static final long serialVersionUID = 1L;
	public UserSchema(Object name,Object pwd) {
		this.put("name", name);
		this.put("pwd", pwd);
	}
	public UserSchema(Object name) {
		this.put("name", name);
	}
}