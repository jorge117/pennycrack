package Splitter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static String largeImg = "";
	
	@SuppressWarnings("null")
	public static void main(String args[]) {
		
		//TODO Take large image
		/*
		 * Take full image, split in to 144
		 */
		int i = 1;
		int j = 2;
		int imageNSMain = 23;
		int imageEWMain = 24;

//		for(int i = 1; i<5; i++){
//			for(int j = 1; j<5; j++){
				largeImg = i + "_" + j;

				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File("../../Images/" + largeImg + ".png"));
					System.out.println(img.getHeight());
					System.out.println(img.getWidth());
//					System.out.println(img.getRGB(26, 27));	//-16777216 = black, -65536 = red, -1 = white
//					System.out.println(img.getRGB(25, 25));
					
					
					for(int NS = 0; NS < 32 ; NS++){
						for(int EW = 0; EW < 31; EW++){
							System.out.println(img.getRGB(imageEWMain + EW, imageNSMain + NS) + "   £££");
							EW = EW + 9;
						}
						NS = NS + 5;	//This adds 3 + 2 to hop the carrier bar
						for(int EW2 = 0; EW2 < 31; EW2++){
							System.out.println(img.getRGB(imageEWMain + EW2, imageNSMain + NS));
							EW2 = EW2 + 9;
						}
						NS++;
						NS++;
					}
//
//				    
//				    for(int y = 0, y < 587; y++){		width 585/586
//				    	for(int x = 0; x < 518; x++){	height 516/517
//				    		
//				    	}
//				    }
					BufferedImage smallNew = new BufferedImage(31, 32, BufferedImage.TYPE_INT_RGB);
					int smallCount = 1;
				    File outputfile = new File(i + "_" + j + "_" + smallCount + ".jpg");

				    for(int smallIMGy = 0; smallIMGy < 32; smallIMGy++){
						for(int smallIMGx = 0; smallIMGx < 31; smallIMGx++){
							smallNew.setRGB(smallIMGx, smallIMGy, img.getRGB(smallIMGx + imageEWMain, smallIMGy + imageNSMain));
					
						}
					}
					try {
					    // retrieve image
					    ImageIO.write(smallNew, "jpg", outputfile);
					    
					} catch (IOException e) {
					    
					}
					smallCount++;

				    
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}			
//			}
//		}
		
	}
}
