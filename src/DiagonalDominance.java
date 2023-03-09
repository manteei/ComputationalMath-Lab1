public class DiagonalDominance {
    private double[][] matrixA;
    private double[] columnB;
    int brokenColumn;
    boolean nb;

    public DiagonalDominance(double[][] matrixA, double[] columnB){
        this.matrixA = matrixA;
        this.columnB = columnB;
    }

    /*
    Проверяем диагональное преобладание строки
     */
    private boolean checkLineDominance(int i, int k){
        int n = matrixA.length;
        double diagonalElement = Math.abs(matrixA[i][k]);
        double sumOfNonDiagonalElements = 0.0;
        for (int j = 0; j < n; j++) {
            if (j != k) {
                sumOfNonDiagonalElements += Math.abs(matrixA[i][j]);
            }
        }
        if (diagonalElement > sumOfNonDiagonalElements){
            nb = true;
        }
        return (diagonalElement >= sumOfNonDiagonalElements);

    }
    /*
    Проверяем диагональное преобладание всех строк
     */
    private boolean dominanceChecker(){
        nb = false;
        int n = matrixA.length;
        boolean dominance;
            for (int i = 0; i < n; i++) {
                dominance = checkLineDominance(i, i);
                if (!dominance){
                    brokenColumn = i;
                    return false;
                }
            }
            if (nb){
                return true;
            }
            return false;


    }

    /*
    Метод, меняющий строки
     */
    public void changeLine(int i, int k){
       double[] tempA =  matrixA[i];
       matrixA[i] = matrixA[k];
       matrixA[k] = tempA;

       double tempB = columnB[i];
       columnB[i] = columnB[k];
       columnB[k] = tempB;
        System.out.println("Строки №" + Integer.toString(k+1) + " и №" + Integer.toString(i+1) + " переставлены");
    }
    /*
    Метод, меняющий столбцы
     */
    public void changeColumn(int m, int k){
        for (int i = 0; i < matrixA.length; i++) {
            double temp = matrixA[i][m];
            matrixA[i][m] = matrixA[i][k];
            matrixA[i][k] = temp;
            System.out.println("Столбцы №" + Integer.toString(m+1) + " и №" + Integer.toString(k+1) + " переставлены");
        }
    }

    public boolean repearColumn(){
        int n = matrixA.length;
        boolean flag = false;
        outerloop:
        for (int i = brokenColumn; i < n; i++ ){
            for (int j = brokenColumn + 1; j < n; j++){
                if (checkLineDominance(j, i)){
                    changeLine(j, i);
                    flag = true;
                    break outerloop;
                }
            }
            for (int j = brokenColumn; j < n; j++){
                if (checkLineDominance(i, j)){
                    changeColumn(j, i);
                    flag = true;
                    break outerloop;
                }
            }
        }
        return flag;
    }

    public void permutation() {
        int n = matrixA.length;
        if (!dominanceChecker()){
           if (!repearColumn()) {
               System.out.println("Диагонального преобладания достичь невозможно!");
               System.exit(1);
           }else {
               permutation();
           }
        }else {
            System.out.println("Диагональное преобладание достигнуто!");
            System.out.println("Матрица A:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%8.2f ", matrixA[i][j]);
                }
                System.out.println();
            }
            System.out.println("Столбец B:");
            for (int i = 0; i < n; i++) {
                System.out.printf("%8.2f ", columnB[i]);
            }
            System.out.println();
        }
    }



    public double[] getColumnB() {
        return columnB;
    }

    public double[][] getMatrixA() {
        return matrixA;
    }
}
