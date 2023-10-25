//Duncan Barnes
//Katie El Fattal
//Nadia Martinez
//George Ghandour

import java.util.Arrays;

public class Matrix<T> {
    private T[][] data;
    private int rows;
    private int cols;

    public Matrix(T[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    public void print() {
        for (T[] row : data) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Matrix Addition
    public Matrix<T> add(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }

        T[][] resultData = (T[][]) new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultData[i][j] = addValues(this.data[i][j], other.data[i][j]);
            }
        }
        return new Matrix<>(resultData);
    }

    // Matrix Multiplication
    public Matrix<T> multiply(Matrix<T> other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Matrix width must match the height for multiplication.");
        }

        T[][] resultData = (T[][]) new Object[this.rows][other.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                resultData[i][j] = multiplyValues(this.data[i], other, j);
            }
        }
        return new Matrix<>(resultData);
    }

    // Matrix Asterate (defined as a custom operation)
    public Matrix<T> asterate() {
        T[][] resultData = (T[][]) new Object[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultData[i][j] = asterateValue(this.data[i][j]);
            }
        }
        return new Matrix<>(resultData);
    }

    // Helper methods for generic operations
    private T addValues(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(((Integer) a) + (Integer) b);
        } else if (a instanceof Double) {
            return (T) Double.valueOf(((Double) a) + (Double) b);
        } else if (a instanceof String) {
            return (T) ((String) a + (String) b);
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }
    }

    private T multiplyValues(T[] a, Matrix<T> b, int col) {
        if (a[0] instanceof Integer) {
            int sum = 0;
            for (int i = 0; i < this.cols; i++) {
                sum += (Integer) a[i] * (Integer) b.data[i][col];
            }
            return (T) Integer.valueOf(sum);
        } else if (a[0] instanceof Double) {
            double sum = 0.0;
            for (int i = 0; i < this.cols; i++) {
                sum += (Double) a[i] * (Double) b.data[i][col];
            }
            return (T) Double.valueOf(sum);
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }
    }

    private T asterateValue(T a) {
        if (a instanceof Integer) {
            int value = (Integer) a;
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < value; i++) {
                result.append(value);
            }
            return (T) Integer.valueOf(Integer.parseInt(result.toString()));
        } else if (a instanceof Double) {
            throw new IllegalArgumentException("Asteration not supported for decimals");
        } else if (a instanceof String) {
            String value = (String) a;
            return (T) scrambleWords(value);
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }
    }

    private String scrambleWords(String input) {
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 1) {
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length / 2; j++) {
                    char temp = chars[j];
                    chars[j] = chars[chars.length - 1 - j];
                    chars[chars.length - 1 - j] = temp;
                }
                words[i] = new String(chars);
            }
        }
        return String.join(" ", words);
    }

    public static void main(String[] args) {
        // Example usage for different data types
        Integer[][] intData1 = {{1, 2}, {3, 4}};
        Integer[][] intData2 = {{5, 6}, {7, 8}};
        Matrix<Integer> intMatrix1 = new Matrix<>(intData1);
        Matrix<Integer> intMatrix2 = new Matrix<>(intData2);
        System.out.println("Integer Matrix Addition:");
        intMatrix1.add(intMatrix2).print();

        Double[][] doubleData1 = {{5.2, 4.1}, {3.3, 2.2}};
        Double[][] doubleData2 = {{1.1, 2.2}, {3.3, 4.4}};
        Matrix<Double> doubleMatrix1 = new Matrix<>(doubleData1);
        Matrix<Double> doubleMatrix2 = new Matrix<>(doubleData2);
        System.out.println("Double Matrix Multiplication:");
        doubleMatrix1.multiply(doubleMatrix2).print();

        String[][] stringData = {{"my", "bonnie", "lies"}, {"over", "the", "mountain"}};
        Matrix<String> stringMatrix = new Matrix<>(stringData);
        System.out.println("String Matrix Asteration:");
        stringMatrix.asterate().print();
    }
}
