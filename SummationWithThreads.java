import java.util.ArrayList;
import java.util.List;

public class SummationWithThreads {
        private static final int NUM_THREADS = 10; // Number of threads to use
        private static final int RANGE = 10_000_000 / NUM_THREADS; // Range for each thread

        static class SumTask implements Runnable {
            private final int start;
            private final int end;
            private long sum;

            public SumTask(int start, int end) {
                this.start = start;
                this.end = end;
                this.sum = 0;
            }

            @Override
            public void run() {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            }

            public long getSum() {
                return sum;
            }
        }

        public static void main(String[] args) throws InterruptedException {
            long startTime = System.currentTimeMillis();

            List<Thread> threads = new ArrayList<>();
            List<SumTask> tasks = new ArrayList<>();

            for (int i = 0; i < NUM_THREADS; i++) {
                int start = i * RANGE + 1;
                int end = (i + 1) * RANGE;
                SumTask task = new SumTask(start, end);
                tasks.add(task);
                Thread thread = new Thread(task);
                threads.add(thread);
                thread.start();
            }

            long sum = 0;
            for (int i = 0; i < NUM_THREADS; i++) {
                threads.get(i).join();
                sum += tasks.get(i).getSum();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Sum with threads: " + sum);
            System.out.println("Execution time with threads: " + (endTime - startTime) + " milliseconds");
        }

}
