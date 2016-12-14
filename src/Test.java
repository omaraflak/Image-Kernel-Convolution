/**
 * Created by Omar on 15/09/2016.
 */
public class Test {
    public static void main (String [] arg) {
        String path = "image.jpg";
        String path2 = "image2.jpg";
        String path3 = "image3.jpg";

        Matrix[] image = Tools.loadImage(path);
        Matrix[] image2 = Tools.applyFilter(image, getTestFilter());
        Matrix[] image3 = Tools.applyFilter(image, getNegativeFilter());

        Tools.saveImage(image2, path2);
        Tools.saveImage(image3, path3);

        System.out.println("Done!");
    }


    static Matrix getIdentityFilter(){
        double[][] filter = {{0,0,0},
                             {0,1,0},
                             {0,0,0}};

        return new Matrix(filter);
    }

    static Matrix getBlurFilter(){
        Matrix m = new Matrix(3,3);
        m.reset(1.0/9.0);
        return m;
    }

    static Matrix getNegativeFilter(){
        double[][] filter = {{-0.5,0,0},
                             {0,-0.5,0},
                             {0,0,0}};

        return new Matrix(filter);
    }

    static Matrix getTestFilter(){
        double[][] filter = {{0,0,0},
                             {0,2,0},
                             {0,0,0}};

        return new Matrix(filter);
    }
}