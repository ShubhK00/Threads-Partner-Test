import java.time.Instant;

public class Main{
	public static void main(String[] args){

		System.out.println("Time to Count to 1,000,000,000 \nWithout Threads: " + countNoThreads() + "ms");
	}

	public static int countNoThreads(){
		Instant time = Instant.now();

		int sum=0;
		for(int i=0; i<1_000_000_000; i++)
			sum++;

		return time.getNano()/1_000_000;
	}
}