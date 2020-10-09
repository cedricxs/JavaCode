package BDD.hadoop.java;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//链式操作->生产线
//重写Key和group、partition、compare方法
public class NumMentionCount {
	public static void clearResult(String resDirectory) {
		File resDir = new File(resDirectory);
		 if(resDir.exists()) {
			 File[] files = resDir.listFiles();
			 for(File f:files)
				 f.delete();
			 resDir.delete();
		 }
	}
	public static class SortKeyComparator extends WritableComparator{
		public SortKeyComparator() {
			super(CompositeKey.class,true);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int compare(WritableComparable wc1, WritableComparable wc2) {
			System.out.println('a');
			CompositeKey key1 = (CompositeKey) wc1;
			CompositeKey key2 = (CompositeKey) wc2;
			int a = key1.num,b = key2.num;
			return a>b?1:(a == b)?0:-1;

		}
		@Override
	  	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			int res = super.compare(b1, s1, l1, b2, s2, l2);
			return res;
	 	}
	 }
	
	public static class GroupKeyComparator extends WritableComparator {

		public GroupKeyComparator() {
			super(CompositeKey.class, true);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int compare(WritableComparable wc1, WritableComparable wc2) {
			System.out.println("group");
			CompositeKey key1 = (CompositeKey) wc1;
			CompositeKey key2 = (CompositeKey) wc2;
			return key1.country.compareTo(key2.country);

		}

	}
	public static class KeyPartitioner extends Partitioner<CompositeKey, IntWritable> {

		@Override
		public int getPartition(CompositeKey key, IntWritable value, int numPartitions) {

			// Automatic n-partitioning using hash on the state name
			return Math.abs(key.country.hashCode() & Integer.MAX_VALUE) % numPartitions;
		}
		
	}
	public static class CompositeKey implements WritableComparable<CompositeKey>{
		public String country;
		public int num;
		CompositeKey(){
		}
		CompositeKey(String t,int n){
			super();
			this.set(t, n);
		}
		public void set(String t,int n) {
			this.country = t;
			this.num = n;
		}
		public int compareTo(CompositeKey o) {
			System.out.println("compare");
			return num>o.num?1:(num==o.num)?0:-1;
		}
		
		public void write(DataOutput out) throws IOException {
			out.writeUTF(country);
			out.write(num);
		}

		public void readFields(DataInput in) throws IOException {
			country = in.readUTF();
			num = in.readInt();
		}
		public String toString() {
			return country+" "+num;
		}
		
	}
	public static void main(String[] args) throws Exception {
		 clearResult(args[1]);
		 Configuration conf = new Configuration();
		 Job job = Job.getInstance(conf, "MentionCount");
		 
		 job.setJarByClass(NumMentionCount.class);
		 
		 job.setMapperClass(MyMapper1.class);
		 job.setInputFormatClass(TextInputFormat.class);
		 job.setMapOutputKeyClass(CompositeKey.class);
		 job.setMapOutputValueClass(IntWritable.class);
		 
		 job.setPartitionerClass(KeyPartitioner.class);
		 job.setSortComparatorClass(SortKeyComparator.class);
		 job.setGroupingComparatorClass(GroupKeyComparator.class);
		 
		 job.setReducerClass(MyReducer.class);
		 job.setOutputKeyClass(Text.class);
		 job.setOutputValueClass(IntWritable.class);
		 job.setNumReduceTasks(1);
		 job.setOutputFormatClass(TextOutputFormat.class);
		 
		 FileInputFormat.addInputPath(job, new Path(args[0]));
		 FileOutputFormat.setOutputPath(job, new Path(args[1]));
		 //ChainMapper.addMapper(job, MyMapper1.class, LongWritable.class, Text.class, CompositeKey.class, IntWritable.class, conf);
		 //ChainReducer.setReducer(job, MyReducer.class, CompositeKey.class, IntWritable.class, Text.class, IntWritable.class, conf);
		 //ChainReducer.addMapper(job, MyMapper2.class, IntWritable.class, Text.class, IntWritable.class, Text.class, conf);
		 job.waitForCompletion(true);
	}
	
	public static class MyMapper1
	extends Mapper<LongWritable, Text, CompositeKey, IntWritable> {
		
		public CompositeKey compositekey = new CompositeKey();
		public IntWritable num = new IntWritable(0);
		
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
			StringTokenizer lines = new StringTokenizer(value.toString(),"\n");
			while (lines.hasMoreTokens()) {
				String line = lines.nextToken();
				String[] columns = line.split("\t");
				compositekey.set(columns[7], Integer.valueOf(columns[31]));
				context.write(compositekey, num);
			}
		 }
	}
	public static class MyMapper2
	extends Mapper<IntWritable, Text, IntWritable, Text> {
		public void map(IntWritable key, Text value, Context context) throws IOException,InterruptedException {
			context.write(key, value);
		 }
	}
	public static class MyReducer
	extends Reducer<CompositeKey, IntWritable, Text, IntWritable> {
		IntWritable num = new IntWritable(0);
		Text t = new Text();
		public void reduce(CompositeKey key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			System.out.println(key);
			int sum = 0;
			for (IntWritable val : values) {
			 sum += val.get();
			}
			num.set(sum);
			t.set(key.country);
			context.write(t, num);
		}
	}
	
}