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

The second image were generated with the same code provided in the link above.

## Before
![alt tag](https://github.com/omaflak/Image-Kernel-Convolution/blob/master/image.jpg?raw=true)
## After
![alt tag](https://github.com/omaflak/Image-Kernel-Convolution/blob/master/image2.jpg?raw=true)
![alt tag](https://github.com/omaflak/Image-Kernel-Convolution/blob/master/image3.jpg?raw=true)
