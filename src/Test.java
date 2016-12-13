/**
 * Created by Omar on 15/09/2016.
 */
public class Test {
    public static void main (String [] arg) {
        String path = "image.jpg";
        String path2 = "image2.jpg";

        Matrix[] image = Tools.loadImage(path);
        Matrix[] newImage = Tools.applyFilter(image, getTestFilter());

        boolean success = Tools.saveImage(newImage, path2, Tools.JPG);

        if(success){
            System.out.println("Done!");
        }
        else{
            System.out.println("Error happened...");
        }
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

    static Matrix getTestFilter(){
        double[][] filter = {{0,0,0},
                             {0,2,0},
                             {0,0,0}};

        return new Matrix(filter);
    }
}