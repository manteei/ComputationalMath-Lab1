import java.io.FileNotFoundException;

public class Calculator {
    private Input input;
    public Calculator(Input input) throws FileNotFoundException {
        this.input = input;
    }
    public void go() throws FileNotFoundException {
        input.fillMatrix();
        DiagonalDominance diagonalDominance = new DiagonalDominance(input.getMatrixA(), input.getColumnB());
        diagonalDominance.permutation();
        count(diagonalDominance.getMatrixA(),  diagonalDominance.getColumnB(), input.getEps());
    }

    public static void count(double[][] matrixA, double[] columnB, double eps) {
        int n = matrixA.length;
        double[] x = new double[n];
        double[] error = new double[n];
        int iter = 0;
        boolean converged = false;
        while (!converged) {
            double maxError = 0;
            for (int i = 0; i < n; i++) {
                double oldX = x[i];
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum += matrixA[i][j] * x[j];
                    }
                }
                x[i] = (columnB[i] - sum) / matrixA[i][i];
                error[i] = Math.abs(x[i] - oldX);
                if (error[i] > maxError) {
                    maxError = error[i];
                }
            }
            iter++;
            if (maxError < eps) {
                converged = true;
            }
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Ответ:");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Столбец неизвестных:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.4f%n", i+1, x[i]);
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("Количество итераций: %d%n", iter);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Столбец погрешностей:");
        for (int i = 0; i < n; i++) {
            System.out.printf("e%d = %.4f%n", i+1, error[i]);
        }
        System.out.println("------------------------------------------------------------------------------");
    }
}
