package cedricxs.HttpServer;

import java.io.IOException;

public class www {
	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			HttpServer server = HttpServer.createHttpServer(8080);
		} catch (IOException e) {
		}
	}
}
