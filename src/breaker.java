package Manu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class breaker {

	public static String largeImg;
	public static int imageEWMain;
	public static int imageNSMain;
	public static int smallCount;
	public static String[] EWWE = {"EW", "WE"};
	public static String[] NSSN = {"NS", "SN"};

	public static void main(String[] args) throws IOException{
		cycleTables();
	}

	public static void cycleTables() throws IOException {
		for(int k = 0; k < EWWE.length; k++){
	    	for(int i = 1; i < 5; i++){
				for(int j = 1; j < 5; j++){
					largeImg = EWWE[k] + "_" + i + "_" + j;
//					System.out.println(largeImg);
					smallCount = 001;
					gridBuilder_EWWE32(EWWE[k], largeImg);
				}
			}
	    }
		for(int k = 0; k < NSSN.length; k++){
	    	for(int i = 1; i < 5; i++){
				for(int j = 1; j < 5; j++){
					largeImg = NSSN[k] + "_" + i + "_" + j;
					System.out.println(largeImg);
					smallCount = 001;
					gridBuilder_NSSN32(NSSN[k], largeImg);
				}
			}
	    }
	}

	public static void gridBuilder_EWWE32(String orient, String imgNo) throws IOException {		//pass rotation and large img no
		BufferedImage img = ImageIO.read(new File("C:\\Users\\Tk\\Pictures\\PennyCrack\\" + orient + "\\Original\\" + imgNo + ".png"));

		for (int b = 0; b < 472; b++) {
			for (int a = 0; a < 537; a++) {
				/*
				 * Loop through table using a + b as start points for each
				 * individual grid
				 */
				BufferedImage smallNew = new BufferedImage(31, 32,BufferedImage.TYPE_INT_RGB);
				File outputfile = new File("C:\\Users\\Tk\\Pictures\\PennyCrack\\" + orient + "\\32\\" + imgNo + "_" + String.format("%03d", smallCount) + ".png");

				for (int smallIMGy = 0; smallIMGy < 32; smallIMGy++) {
					for (int smallIMGx = 0; smallIMGx < 31; smallIMGx++) {
						smallNew.setRGB(smallIMGx,smallIMGy,
						img.getRGB(smallIMGx + a,
						smallIMGy + b));
					}
				}
				ImageIO.write(smallNew, "png", outputfile);
				smallCount++;
				a = a + 45;	//move across to next grid's starting x
			}
			b = b + 39;		//move across to next grid's starting y
		}
	}

	public static void gridBuilder_NSSN32(String orient, String imgNo) throws IOException {		//pass rotation and large img no
		BufferedImage img = ImageIO.read(new File("C:\\Users\\Tk\\Pictures\\PennyCrack\\" + orient + "\\Original\\" + imgNo + ".png"));

		for (int b = 0; b < 537; b++) { 
			for (int a = 0; a < 472; a++) {
				/*
				 * Loop through table using a + b as start points for each
				 * individual grid
				 */
				BufferedImage smallNew = new BufferedImage(32, 31,BufferedImage.TYPE_INT_RGB);
				File outputfile = new File("C:\\Users\\Tk\\Pictures\\PennyCrack\\" + orient + "\\32\\" + imgNo + "_" + String.format("%03d", smallCount) + ".png");

				for (int smallIMGy = 0; smallIMGy < 31; smallIMGy++) {
					for (int smallIMGx = 0; smallIMGx < 32; smallIMGx++) {
						smallNew.setRGB(smallIMGx,smallIMGy,
						img.getRGB(smallIMGx + a,
						smallIMGy + b));
					}
				}
				ImageIO.write(smallNew, "png", outputfile);
				smallCount++;
				a = a + 39;	//move across to next grid's starting x
			}
			b = b + 45;		//move across to next grid's starting y
		}
	}

	public static void gridBreak8_EWWE() {

	}

	public static void gridBreak8_NSSN() {

	}
}