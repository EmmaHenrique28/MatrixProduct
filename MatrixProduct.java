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
        

    }
}