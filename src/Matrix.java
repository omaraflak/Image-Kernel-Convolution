import java.util.Arrays;

/**
 * Created by Omar on 15/09/2016.
 */
public class Matrix {
    public final static int COLUMN=0;
    public final static int ROW=1;

    private int height;
    private int width;
    private double[][] array;

    public Matrix(int height, int width){
        this.width = width;
        this.height = height;
        this.array = new double[height][width];

        reset(0.0);
    }

    public Matrix(double[][] array){
        this.array = array;
        this.height = this.array.length;
        this.width = this.array[0].length;
    }

    public Matrix(double[] array, int type){
        if(type==COLUMN){
            this.width = 1;
            this.height = array.length;
            this.array = new double[height][width];
            for (int i=0 ; i<array.length ; i++){
                this.array[i][0] = array[i];
            }
        }
        else if(type==ROW){
            this.width = array.length;
            this.height = 1;
            this.array = new double[height][width];
            for (int i=0 ; i<array.length ; i++){
                this.array[0][i] = array[i];
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void reset(double value) {
        for (int i = 0; i < height; i++) {
            Arrays.fill(array[i], value);
        }
    }

    public double get(int h, int w){
        return array[h][w];
    }

    public void put(int h, int w, double value){
        array[h][w] = value;
    }

    public void add(int h, int w, double value){
        array[h][w] += value;
    }

    public void multiply(int h, int w, double value){
        array[h][w] *= value;
    }

    public void divide(int h, int w, double value){
        array[h][w] /= value;
    }

    public Matrix add(double value){
        for (int i=0 ; i<height ; i++){
            for (int j=0 ; j<width ; j++){
                array[i][j] += value;
            }
        }
        return this;
    }

    public Matrix multiply(double value){
        for (int i=0 ; i<height ; i++){
            for (int j=0 ; j<width ; j++){
                array[i][j] *= value;
            }
        }
        return this;
    }

    public Matrix divide(double value){
        for (int i=0 ; i<height ; i++){
            for (int j=0 ; j<width ; j++){
                array[i][j] /= value;
            }
        }
        return this;
    }

    public Matrix add(Matrix matrix){
        if(matrix.getHeight()==height && matrix.getWidth()==width){
            Matrix result = this.copy();
            for (int i=0 ; i<height ; i++){
                for (int j=0 ; j<width ; j++){
                    result.add(i,j, matrix.get(i,j));
                }
            }
            return result;
        }
        return null;
    }

    public Matrix subtract(Matrix matrix){
        if(matrix.getHeight()==height && matrix.getWidth()==width){
            Matrix result = this.copy();
            for (int i=0 ; i<height ; i++){
                for (int j=0 ; j<width ; j++){
                    result.add(i,j, -matrix.get(i,j));
                }
            }
            return result;
        }
        return null;
    }

    public Matrix multiply(Matrix matrix){
        if(matrix.getHeight()==height && matrix.getWidth()==width){
            Matrix result = this.copy();
            for (int i=0 ; i<height ; i++){
                for (int j=0 ; j<width ; j++){
                    result.multiply(i,j, matrix.get(i,j));
                }
            }
            return result;
        }
        return null;
    }

    public Matrix divide(Matrix matrix){
        if(matrix.getHeight()==height && matrix.getWidth()==width){
            Matrix result = this.copy();
            for (int i=0 ; i<height ; i++){
                for (int j=0 ; j<width ; j++){
                    result.divide(i,j, matrix.get(i,j));
                }
            }
            return result;
        }
        return null;
    }

    public double sum(){
        double sum=0.0;
        for (int i=0 ; i<height ; i++){
            for (int j=0 ; j<width ; j++){
                sum += array[i][j];
            }
        }
        return sum;
    }

    public Matrix dot(Matrix matrix){
        if(width==matrix.getHeight()){
            Matrix result = new Matrix(height, matrix.getWidth());
            int w=0;

            for (int i=0 ; i<matrix.getWidth() ; i++){
                for (int j=0 ; j<matrix.getHeight() ; j++){
                    for (int h=0 ; h<height ; h++){
                        result.add(h, i, array[h][w]*matrix.get(j,i));
                    }
                    w++;
                }
                w=0;
            }

            return result;
        }
        return null;
    }

    public Matrix subMatrix(int hStart, int wStart, int h, int w){
        if(hStart+h<=height && wStart+w<=width){
            Matrix output = new Matrix(h, w);

            for (int i=hStart ; i<hStart+h ; i++){
                for (int j=wStart ; j<wStart+w ; j++){
                    output.put(i-hStart, j-wStart, array[i][j]);
                }
            }

            return output;
        }
        else{
            return null;
        }
    }

    public double max(){
        double m=0.0;
        for (int i=0 ; i<height ; i++){
            for (int j=0 ; j<width ; j++){
                if(m<array[i][j])
                    m=array[i][j];
            }
        }
        return m;
    }

    public Matrix transpose(){
        Matrix result = new Matrix(width, height);
        for (int i=0 ; i<result.getHeight() ; i++){
            for (int j=0 ; j<result.getWidth() ; j++){
                result.put(i,j, array[j][i]);
            }
        }
        return result;
    }

    public Matrix applyFunction(Function function){
        Matrix result = this.copy();
        for (int i=0 ; i<height ; i++){
            for (int j=0 ; j<width ; j++){
                result.put(i,j, function.execute(result.get(i,j)));
            }
        }
        return result;
    }

    public void print(){
        for (int i=0 ; i<height ; i++){
            System.out.println(Arrays.toString(array[i]));
        }
    }

    private Matrix copy(){
        double[][] a = new double[array.length][];
        for (int i=0 ; i<array.length ; i++) {
            a[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return new Matrix(a);
    }

    public interface Function
    {
        double execute(double x);
    }
}
