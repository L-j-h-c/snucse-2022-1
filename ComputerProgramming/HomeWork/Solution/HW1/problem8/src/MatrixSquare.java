public class MatrixSquare {
    public static void printSquaredMatrix(int[][] matrix) {
        int[][] multipliedMatrix = new int[10][10];

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                for(int k=0; k<matrix.length; k++)
                    multipliedMatrixpublic class MatrixSquare {
                    public static void printSquaredMatrix(int[][] matrix) {
                        int[][] multipliedMatrix = new int[10][10];

                        for(int i=0; i<matrix.length; i++) {
                            for(int j=0; j<matrix.length; j++) {
                                for(int k=0; k<matrix.length; k++)
                                    multipliedMatrix[i][j] += matrix[i][k]*matrix[k][j];
                                if(j!=matrix.length-1) {
                                    System.out.print(multipliedMatrix[i][j] + " ");
                                } else {
                                    System.out.println(multipliedMatrix[i][j]);
                                }
                            }
                        }
                    }
                }
[i][j] += matrix[i][k]*matrix[k][j];
                if(j!=matrix.length-1) {
                    System.out.print(multipliedMatrix[i][j] + " ");
                } else {
                    System.out.println(multipliedMatrix[i][j]);
                }
            }
        }
    }
}
