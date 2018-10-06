package ChatRoom;

import java.io.Closeable;

public class Util {
	@SafeVarargs
	public static<T extends Closeable> void closeAll(T ... io) {
		for(Closeable t:io) {
			try {
				t.close();
			} catch (Exception e) {
			}
		}
	}
}
