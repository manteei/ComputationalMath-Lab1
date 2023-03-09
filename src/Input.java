import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Input {
    private double[][] matrixA;
    private double[] columnB;
    private double eps;
    private int n;

    private int getInputType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("������� ���� ������� ������-�������");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("��� �������� ��������� �������, ��� ������ ������� ��������������� �����:\n"
                + "� ���������������� ���� (1)\n" +
                "� ���� ������ �� ����� (2)\n" +
                "� ��������� ��������� ������ (3)");
        String str = scanner.nextLine();
        System.out.println("------------------------------------------------------------------------------");
        while (true) {
            if (str.trim().equalsIgnoreCase("1")) {
                System.out.println("�� ������� ���������������� ����");
                return 1;
            } else if (str.trim().equalsIgnoreCase("2")) {
                System.out.println("�� ������� ���� ������ �� �����");
                return 2;
            }
            else if (str.trim().equalsIgnoreCase("3")) {
                System.out.println("�� ������� ��������� ��������� ������");
                return 3;
            }
            System.out.println("������! ������� 1, 2 ��� 3 � ����������� �� ����, ����� �������� ������ ������ ������.");
            str = scanner.nextLine();
        }
    }
    public void fillMatrix() throws FileNotFoundException {
        int type = getInputType();
        System.out.println("------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� �������� �����������");
        while (true) {
            String s = scanner.nextLine();
            try {
                eps = Double.parseDouble(s);
                if (eps > 0 && eps <=1){
                    break;
                }else {
                    System.out.println("������! �������� ������ ���������� ������ ������ 0 � ������ 1");
                }
            }catch (NumberFormatException e){
                System.out.println("������! �������� ������ ���������� ������");
            }

        }
        if (type==1){
            System.out.println("������� ����������� ������� (����� ����� �� ����� 20)");
            while (true){
                String s = scanner.nextLine();
                try {
                    n = Integer.parseInt(s);
                    if (n > 0 && n <= 20){
                        System.out.println("������� �������");
                        break;
                    }else {
                        System.out.println("������! ����������� ������ ���������� ������ �� 0 �� 20 ������������");
                    }
                }catch (NumberFormatException e){
                    System.out.println("������! ����������� ������ ���������� ����� ������");
                }
            }
            readMatrix(scanner, n);
        }else if (type == 2 ){
            System.out.println("������� ���� � �����");
            while(true) {
                try {
                    File file = new File(scanner.nextLine());
                    scanner = new Scanner(file);
                    n = scanner.nextInt();
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("������! ������������ �������� �����");
                }
            }

            readMatrix(scanner, n);
        }else if (type == 3) {
            System.out.println("������� ����������� ������� (����� ����� �� ����� 20)");
            while (true){
                String s = scanner.nextLine();
                try {
                    n = Integer.parseInt(s);
                    if (n > 0 && n <= 20){
                        break;
                    }else {
                        System.out.println("������! ����������� ������ ���������� ������ �� 0 �� 20 ������������");
                    }
                }catch (NumberFormatException e){
                    System.out.println("������! ����������� ������ ���������� ����� ������");
                }
            }
            generateRandomMatrix(n);
        }
    }

    private void generateRandomMatrix(int n){
        Random rand = new Random();
        matrixA = new double[n][n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrixA[i][j] = rand.nextDouble() * 10;
                    sum += Math.abs(matrixA[i][j]);
                }
            }
            matrixA[i][i] = sum + rand.nextDouble() * 10;
        }

        columnB = new double[n];
        for (int i = 0; i < n; i++) {
            columnB[i] = rand.nextDouble() * 10;
        }
        System.out.println("�������������");
        System.out.println("������� A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.2f", matrixA[i][j]);
            }
            System.out.println();
        }
        System.out.println("������� B:");
        for (int i = 0; i < n; i++) {
            System.out.printf("%8.2f", columnB[i]);
        }
        System.out.println();

    }
    private void readMatrix(Scanner scanner, int n){
        if (scanner.hasNextDouble()) {
            matrixA = new double[n][n];
            columnB = new double[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n + 1; j++) {
                    if (j == n) {
                            try {
                                columnB[i] = scanner.nextDouble();
                            } catch (InputMismatchException e) {
                                System.out.println("������! ������� ������� ������ ���������� ������");
                            }

                    } else {
                            try {
                                matrixA[i][j] = scanner.nextDouble();
                            } catch (InputMismatchException e) {
                                System.out.println("������! ������� ������� ������ ���������� ������");
                            }
                    }
                }
            }
        }
        scanner.close();
    }

    public double[][] getMatrixA() {
        return matrixA;
    }

    public double[] getColumnB() {
        return columnB;
    }

    public double getEps() {
        return eps;
    }
}
