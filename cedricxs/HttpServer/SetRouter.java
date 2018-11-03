package cedricxs.HttpServer;

public class SetRouter {
	//设置路由
	public static void setRouter() {
		//添加'/'路由
		ServerLet.use("/", new ServerLet() {
			void handleFunction(request req,response res) {
				res.send("index.html");
			}
		});
		ServerLet.use("/class.html", new ServerLet() {
			void handleFunction(request req,response res) {
				res.send("课表.html");
			}
		});
		
		
		//采用静态路由
		ServerLet.use("/js/Print.js", new ServerLet() {
			void handleFunction(request req,response res) {
				res.setContentType("application/javascript");
				res.send("Print.js");
			}
		});
		ServerLet.use("/css/Print.css", new ServerLet() {
			void handleFunction(request req,response res) {
				res.setContentType("text/css");
				res.send("Print.css");
			}
		});
	}
	
	
}
