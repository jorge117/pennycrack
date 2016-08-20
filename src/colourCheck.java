package Manu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class colourCheck {

	public static String imageLoc = new String(
			"C:\\Users\\Tk\\Pictures\\Penet\\Clean\\44.png");
	public static int keeper = 1;

	public static void main(String[] args) {
		imageReader();
	}

	public static void imageReader() {
		try {
			// the line that reads the image file
			BufferedImage image = ImageIO.read(new File(imageLoc));
			
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
			System.out.println("START");
			for(int i = 0; i < imageHeight; i++){	//cycle whole table
				for(int j = 0; j < imageHeight; j++){
					//if(image.getRGB(i, j) != )
					int colour = image.getRGB(i, j);
//					System.out.println(colour);
					int blue = colour & 0xff;					
					int green = (colour & 0xff00) >> 8;
					int red = (colour & 0xff0000) >> 16;
					int alpha = (colour & 0xff000000) >>> 24;	//transparency
					
					if(red != 255){
						if(red != 000){
							System.out.println("~~~");							
						}
					}
					if(green != 255){
						if(green != 000){
							System.out.println("~~~");							
						}
					}
					if(blue != 255){
						if(blue != 000){
							System.out.println("~~~");							
						}
					}
					if(alpha != 255){
						System.out.println("~~~");
					}
					
//					System.out.println(blue);
//					System.out.println(green);
//					System.out.println(red);
//					System.out.println(alpha);
				}				
			}
			System.out.println("end");

			
//			BufferedImage newSmallBlock = new BufferedImage(31, 32,
//					BufferedImage.TYPE_INT_RGB); // typeint to copy original?
//
//			for (int v = 0; v < 32; v++) { // y coord
//				for (int w = 0; w < 31; w++) { // x coord
//
//					newSmallBlock.setRGB(w, v, image.getRGB((q + w), (p + v)));
//					// System.out.println(keeper);
//				}
//			}
//
//			File outputfile = new File(
//					"C:\\Users\\Tk\\Pictures\\Penet\\Clean\\image16_" + keeper
//							+ ".png");
//			ImageIO.write(newSmallBlock, "png", outputfile);
//			keeper++;
//			// 31x by 32y
//			// 15x gap
//			// 8 y gap
//			// System.out.println(image.getRGB(i, j));

		} catch (IOException e) {
			// log the exception
			// TODO throw error message?
		}
	}

}
