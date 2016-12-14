import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Omar on 13/12/2016.
 */
public class Tools {
    public static Matrix[] loadImage(String path){
        Matrix[] imageMatrix = new Matrix[3];
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
            for (int i=0 ; i<imageMatrix.length ; i++){
                imageMatrix[i] = new Matrix(image.getHeight(), image.getWidth());
            }

            int color, red, green, blue;

            for (int i=0 ; i<image.getHeight() ; i++){
                for (int j=0 ; j<image.getWidth() ; j++){
                    color = image.getRGB(j, i);

                    red = (color & 0x00ff0000) >> 16;
                    green = (color & 0x0000ff00) >> 8;
                    blue = color & 0x000000ff;

                    imageMatrix[0].put(i,j, red);
                    imageMatrix[1].put(i,j, green);
                    imageMatrix[2].put(i,j, blue);
                }
            }

            return imageMatrix;
        } catch (IOException e) {
            return null;
        }
    }

    public static Matrix[] applyFilter(Matrix[] image, Matrix filter){
        int height = image[0].getHeight();
        int width = image[0].getWidth();
        int filterHeight = filter.getHeight();
        int filterWidth = filter.getWidth();
        int newImageHeight = height-filterHeight+1;
        int newImageWidth = width-filterWidth+1;

        Matrix[] newImage = new Matrix[3];
        for (int i=0 ; i<3 ; i++){
            newImage[i] = new Matrix(newImageHeight, newImageWidth);
        }


        for (int d=0 ; d<3 ; d++) {
            for (int i = 0; i < newImageHeight; i++) {
                for (int j = 0; j < newImageWidth; j++) {
                    newImage[d].put(i,j, image[d].subMatrix(i,j, filterHeight, filterWidth).multiply(filter).sum());
                }
            }
        }

        return newImage;
    }

    public static boolean saveImage(Matrix[] image, String name){
        int height = image[0].getHeight();
        int width = image[0].getWidth();
        int rgb;

        BufferedImage imageToSave = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y=0 ; y<height ; y++) {
            for (int x=0 ; x<width ; x++) {
                rgb = (int) image[0].get(y,x);
                rgb = (rgb << 8) + (int) image[1].get(y,x);
                rgb = (rgb << 8) + (int) image[2].get(y,x);

                imageToSave.setRGB(x, y, rgb);
            }
        }

        String extension = name.substring(name.lastIndexOf(".")+1);
        try {
            ImageIO.write(imageToSave, extension, new File(name));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
