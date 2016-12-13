# Image Kernel Convolution

## How to

    String path = "image.jpg";
    String path2 = "image2.jpg";

    Matrix[] image = Tools.loadImage(path);
    Matrix[] newImage = Tools.applyFilter(image, getTestFilter());

    Tools.saveImage(newImage, path2, Tools.JPG);
    
    ...
    
    Matrix getTestFilter(){
        double[][] filter = {{0,0,0},
                             {0,2,0},
                             {0,0,0}};

        return new Matrix(filter);
    }
    
# Sample

Try for yourself and see!

https://github.com/omaflak/Image-Kernel-Convolution/blob/master/src/Test.java

# Example

[[https://github.com/omaflak/Image-Kernel-Convolution/image.jpg]]
