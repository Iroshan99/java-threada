public class SummationWithoutThreads {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        long sum = 0;
        for (int i = 1; i <= 10_000_000; i++) {
            sum += i;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Sum without threads: " + sum);
        System.out.println("Execution time without threads: " + (endTime - startTime) + " milliseconds");
    }
}
