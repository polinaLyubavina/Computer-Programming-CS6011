public class HelloThreads {

    public class myRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello from thread " + Thread.currentThread().getId());
        } 
        
    }

    public static int answer = 0; 

    public static void sayHello() throws InterruptedException{
        Thread firstThreads[] = new Thread[10];
        for(int t = 0; t < firstThreads.length; t++) {
            firstThreads[t] = new Thread(() -> {
                for(int i = 0; i < 100; i++) {
                    System.out.println("Hello number " + i + "from thread number " + Thread.currentThread().getId());
                }
            });
            firstThreads[t].start();
            firstThreads[t].join(); 
        }
        // threads are named randomly & output randomly, but there are 10 of them.
    }

    // the same thread doesn't always print out hello

    public static void badSum() throws InterruptedException {
        answer = 0; 
        int maxValue = 40000; 
        Thread secondThreads[] = new Thread[5]; 
        System.out.println(secondThreads);
        for(int i = 0; i < secondThreads.length; i++) {
            final int finalI = i;
            secondThreads[i] = new Thread(() -> {
                for(int val = finalI * maxValue / secondThreads.length; val < Math.min((finalI+1)*maxValue/secondThreads.length, maxValue); val++) {
                    answer += val;
                }
            });
            secondThreads[i].start();
        }

        for (int i = 0; i < secondThreads.length; i++) {
            secondThreads[i].join(); 
        }

        System.out.println(answer);
        System.out.println((maxValue * (maxValue - 1) / 2));
    }

    // the results seem random because they didn't wait for the thread before them to finish the calculation. 
    // You fixed this by telling them to wait. 

    public static void main(String[] args) throws InterruptedException {
        
        HelloThreads.sayHello();
        HelloThreads.badSum();

    }
    
}
