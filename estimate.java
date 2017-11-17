
import java.util.concurrent.ThreadLocalRandom;

public class estimate {

	public static void main(String[] args) throws InterruptedException {
		
		long userStart = System.currentTimeMillis();
		double outside = 0;
		double inside = 0;
		long iterations = Long.parseLong(args[1]);
		long numThreads = Long.parseLong(args[0]);
		piCalc[] threads = new piCalc[(int) numThreads];
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for(int j=0;j<numThreads;j++) {
			threads[j] = new piCalc(iterations/numThreads);
			threads[j].start();
		}
		for (int i = 0; i < numThreads; i++) {   
			threads[i].join();
		}
		double pi = 0;
		for(int i = 0; i <threads.length;i++) {
			pi+=threads[i].getSum();
			inside+=threads[i].getInside();
			outside+=threads[i].getOutside();
		}
		long end = System.currentTimeMillis();
		long userEnd = System.currentTimeMillis();
		System.out.println("Pi: "+(pi/numThreads)*4);
		System.out.println("Total: "+(int)(inside+outside));
		System.out.println("Inside: "+(int)inside);
		System.out.println("Outside: "+(int)outside);
		System.out.println("Ratio: "+inside/outside);
		
		
		
		System.out.println("real: "+(userEnd-userStart));
		System.out.println("user: "+((userEnd+end)-(userStart+start)));
		System.out.println("sys: "+(end-start));
	}

	
	
}

class piCalc extends Thread {

    private final long N;
    double inside = 0;
    double outside = 0;
    public piCalc(long n) {
      N = n;
    }


    @Override
    public void run() {
        for (int i = 0; i <= N; i++) {
            	double x =ThreadLocalRandom.current().nextDouble(1);
        		double y = ThreadLocalRandom.current().nextDouble(1);
        		if(x*x+y*y<1) {
        			inside++;
        		}
        		else {
        			outside++;
        		}
        }
    }
    public double getInside() {
    	return inside;
    }
    public double getOutside() {
    	return outside;
    }
    public double getSum() {
    	//System.out.println(inside/outside);
        return inside/(inside+outside);
    }
}