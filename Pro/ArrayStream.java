package Pro;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayStream {

	/*
	 * the object is to use the stream of array, which has a lot of useful 
	 * functions such as count average min max map reduce... 
	 * and for string stream of array, it's a little likes stream spark and RDD
	 * which also use stream function such as reduce map flatmap
	 */
	public ArrayStream() {
		
	}
	/*
	 * test array of int 
	 */
	public void IntArrayStream() {
		int[] a = {1,2,3,4};
		IntStream stream = Arrays.stream(a);
		IntSummaryStatistics summary = stream.summaryStatistics();
		System.out.println(summary);
	}
	
	/*
	 * test array of object, here use string
	 * 
	 * 
	 * Both map and flatMap can be applied to a Stream<T> and they both return a Stream<R>. 
	 * The difference is that the map operation produces one output value for each input value, 
	 * whereas the flatMap operation produces an arbitrary number (zero or more) values for each input value.
		This is reflected in the arguments to each operation.
		The map operation takes a Function, which is called for each value in the input stream and produces one result value, 
		which is sent to the output stream.
		
		The flatMap operation takes a function that conceptually wants to consume one value and produce an arbitrary number of values. 
		However, in Java, it's cumbersome for a method to return an arbitrary number of values, since methods can return only zero or one value. 
		One could imagine an API where the mapper function for flatMap takes a value and returns an array or a List of values, 
		which are then sent to the output. Given that this is the streams library, 
		a particularly apt way to represent an arbitrary number of return values is for the mapper function itself to return a stream! 
		The values from the stream returned by the mapper are drained from the stream and are passed to the output stream. 
		The "clumps" of values returned by each call to the mapper function are not distinguished at all in the output stream, 
		thus the output is said to have been "flattened."
		
		Typical use is for the mapper function of flatMap to return Stream.empty() if it wants to send zero values, 
		or something like Stream.of(a, b, c) if it wants to return several values. But of course any stream can be returned.
	 */
	public void ObjectArrayStream() {
		String[] s = {"abc","def","ghi"};
		Stream<String> stream = Arrays.stream(s);
		Optional<String> max = stream.max(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length()!=o2.length())
					return o1.length()-o2.length();
				if(o1.length() == o2.length()) {
					for(int i=0;i<o1.length();i++)
						if(o1.charAt(i) != o2.charAt(i))
							return o1.charAt(i) - o2.charAt(i);
				}
				return 0;
			}
			
		});
		System.out.println(max.get());
	}
	public static void main(String[] args) {
		ArrayStream test = new ArrayStream();
		test.IntArrayStream();
		test.ObjectArrayStream();
	}

}
