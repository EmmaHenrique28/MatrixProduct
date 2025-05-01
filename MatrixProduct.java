import java.util.Random;

public class MatrixProduct {
    final static int SIZE = 100; // Size of the matrices

    private static class WorkerThread implements Runnable {
        int [] [] A, B, Result;
        int startRow, endRow;

        public WorkerThread(int [] [] A, int [] [] B, int [] [] Result, int startRow, int endRow) {
            this.A = A;
            this.B = B;
            this.Result = Result;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < SIZE; j++) {
                    Result[i][j] = 0;
                    for (int k = 0; k < SIZE; k++) {
                        Result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
    // Generate two random matrices of size SIZE x SIZE
    public static int [][] generateRandomMatrix() {
        Random rand = new Random();
        int[][] matrix = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = rand.nextInt(10); // Random integers between 0 and 9
            }
        }
        return matrix;    
    }

    public static void main(String[] args) {
        int [] [] A = generateRandomMatrix();
        int [] [] B = generateRandomMatrix();
        int [] [] Result = new int [SIZE] [SIZE];

        WorkerThread[] workers = new WorkerThread[10]; // Number of threads
        Thread[] threads = new Thread[10]; 

        //Assign 10 rows per thread
        for (int i = 0; i < 10; i++) {
            int startRow = i * (SIZE / 10);
            int endRow = (i + 1) * (SIZE / 10);
            workers[i] = new WorkerThread(A, B, Result, startRow, endRow);
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }


    }
}