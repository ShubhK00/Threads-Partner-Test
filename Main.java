import java.util.concurrent.*;
import java.util.ArrayList;
import java.time.Instant;

public class Main{
	public static void main(String[] args) throws Exception{

		System.out.println("Time to Count to 1,000,000,000 \nWith Threads: " + countWithThreads() + "ms");
	}

	public static int countNoThreads(){
		Instant time = Instant.now();

		int sum=0;
		for(int i=0; i<1_000_000_000; i++)
			sum++;

		return time.getNano()/1_000_000;
	}

	public static int countWithThreads() throws Exception{
		Instant time = Instant.now();
		int total = 0;

		ExecutorService executor = Executors.newFixedThreadPool(1000);
		ArrayList<Future<Integer>> futures = new ArrayList<>();

		for(int i=0; i<1000; i++){
			Callable<Integer> task = () ->{
				int sum = 0;
				for(int j=0; j<1000000; j++)
					sum++;
				return sum;
			};
			futures.add(executor.submit(task));
		}

		for(Future<Integer> f: futures) {
			total += f.get();
		}

		executor.shutdown();

		return time.getNano()/1000000;

	}
}