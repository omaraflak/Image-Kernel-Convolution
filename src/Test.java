/**
 * Created by Omar on 15/09/2016.
 */
public class Test {
    public static void main (String [] arg) {
        Matrix[] image = Tools.loadImage("image.jpg");
        Matrix[] image2 = Tools.applyFilter(image, testFilter());
        Matrix[] image3 = Tools.applyFilter(image, negativeFilter());

        Tools.saveImage(image2, "image2.jpg");
        Tools.saveImage(image3, "image3.jpg");

        System.out.println("Done!");
    }


    static Matrix identityFilter(){
        double[][] filter = {{0,0,0},
                             {0,1,0},
                             {0,0,0}};

        return new Matrix(filter);
    }

    static Matrix blurFilter(){
        Matrix m = new Matrix(3,3);
        m.reset(1.0/9.0);
        return m;
    }

    static Matrix negativeFilter(){
        double[][] filter = {{-0.5,0,0},
                             {0,-0.5,0},
                             {0,0,0}};

        return new Matrix(filter);
    }

    static Matrix testFilter(){
        double[][] filter = {{0,0,0},
                             {0,2,0},
                             {0,0,0}};

        return new Matrix(filter);
    }
}