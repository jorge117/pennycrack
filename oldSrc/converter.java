package Manu;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class converter {

	/*
	 * 15 * 11 165 31 * 12 372 537
	 * 
	 * 32*12 384 8 * 11 88 472
	 * 
	 * Take y1,y1 to x1,x1, save as file move on to y2,2 to x2,x2, repeat 11
	 * times until at end of line of symbols Bump line down, repeat 12 time
	 */

	public static String imageLoc = new String(
			"C:\\Users\\Tk\\Pictures\\Penet\\Clean\\44.png");
	public static int keeper = 1;
	public static int[] across = { 0, 9, 19, 29 };	//x coords on grid
	public static int[] down = { 0, 5, 8, 13, 16, 21, 24, 29 };	//y coords on grid
	public static ArrayList<manuTuple> list = new ArrayList<>();
	public static manuTuple details;

	public static void main(String args[]) {

		// add image block
		// for loop ii
		// for loop jj
		// loop imgBlock + numbers

		// new image 12 times, moving across first row
		// make first small symbol, save as.
		// move on to next on x axis
		// repeat to end of line, bump down for next x coord.
		// continue till end of file, 12 x 12

		/*
		 * Loops - master grid for start coords of each symbol, x+y, until x/y
		 * +symbol size inner - loop through making individual symbols from
		 * large
		 */

		// breakAndMove(); // Destroy main grid

		// imageColourTest();

		uniqueTest();

	}

	public static void breakAndMove() {
		for (int b = 0; b < 472; b++) {
			for (int a = 0; a < 537; a++) {
				/*
				 * Loop through grid using a + b as start points for each
				 * individual grid
				 */
				imageBreaker(a, b);
				a = a + 45;
			}
			b = b + 39;
		}
	}

	public static void imageBreaker(int q, int p) {
		try {
			// the line that reads the image file
			BufferedImage image = ImageIO.read(new File(imageLoc));

			BufferedImage newSmallBlock = new BufferedImage(31, 32,
					BufferedImage.TYPE_INT_RGB); // typeint to copy original?

			for (int v = 0; v < 32; v++) { // y coord
				for (int w = 0; w < 31; w++) { // x coord
					newSmallBlock.setRGB(w, v, image.getRGB((q + w), (p + v)));
					// System.out.println(keeper);
				}
			}

			File outputfile = new File(
					"C:\\Users\\Tk\\Pictures\\Penet\\Clean\\image16_" + keeper
							+ ".png");
			ImageIO.write(newSmallBlock, "png", outputfile);
			keeper++;
			// 31x by 32y
			// 15x gap
			// 8 y gap
			// System.out.println(image.getRGB(i, j));

		} catch (IOException e) {
			// log the exception
			// TODO throw error message?
		}
	}

	public static void imageColourTest() {

		/**
		 * create binary string of image Try to add to hashset, record string,
		 * instance number and increment 3rd member of tuple to represent times
		 * it occurs in a grid do this for all 144 * 16 in one hit? Must do to
		 * compare all...or compare 16 hashsets?
		 */

		try {
			// the line that reads the image file
			BufferedImage image = ImageIO.read(new File(
					"C:\\Users\\Tk\\Pictures\\Penet\\Clean\\image1_1.png"));
			String imageString = "";
			int k = 0; // variable to keep track of how many passes the scan
						// does on the image
			for (int j = 0; j < down.length; j++) {
				for (int i = 0; i < across.length; i++) {
					// System.out.println("I = " + across[i] + ", J = " +
					// down[j]);
					Color mycolor = new Color(image.getRGB(across[i], down[j]));
					int red = mycolor.getRed();
					int green = mycolor.getGreen();
					int blue = mycolor.getBlue();

					if (green + red + blue == 0) {
						// pixel is black
						// System.out.println("1");
						imageString = imageString.concat("1");
					} else if (green + red + blue == 765) {
						// pixel is white
						// System.out.println("0");
						imageString = imageString.concat("0");
					} else if (red == 255 && blue + green == 0) {
						// pixel is red
						// System.out.println("1 RED");
						imageString = imageString.concat("1");
					}

					// System.out.println(image.getRGB(i, j));
					k++;
				}
			}
			// work with the image here ...
			// System.out.println("K = " + k);
			System.out.println(imageString);
		} catch (IOException e) {
			// log the exception
			// re-throw if desired
		}
	}

	public static void uniqueTest() {

		int k = 0; // variable to keep track of how many passes the scan does on the image

		/**
		 * create binary string of image Try to add to list, record string,
		 * instance number and increment 3rd member of tuple to represent times
		 * it occurs in a grid
		 */
		// TODO add arff config 

		try {
			writer("@RELATION PENCODE\n\n");
			
			writer("@ATTRIBUTE 32BITSTRING STRING\n");
			writer("@ATTRIBUTE IMAGENO STRING\n");
			writer("@ATTRIBUTE ISRED NUMERIC\n");
			writer("@ATTRIBUTE FREQUENCY NUMERIC\n\n");
			
			writer("@DATA \n\n");
			
			writerPostProcess("@RELATION PENCODE\n\n");
			
			writerPostProcess("@ATTRIBUTE 32BITSTRING STRING\n");
			writerPostProcess("@ATTRIBUTE IMAGENO STRING\n");
			writerPostProcess("@ATTRIBUTE ISRED NUMERIC\n");
			writerPostProcess("@ATTRIBUTE FREQUENCY NUMERIC\n\n");
			
			writerPostProcess("@DATA \n\n");
			
			writerLong("@RELATION PENCODE\n\n");
			
			writerLong("@ATTRIBUTE 32BITSTRING NUMERIC\n");
			writerLong("@ATTRIBUTE IMAGENO STRING\n");
			writerLong("@ATTRIBUTE ISRED NUMERIC\n");
			writerLong("@ATTRIBUTE FREQUENCY NUMERIC\n\n");
			
			writerLong("@DATA \n\n");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// TODO for loop to check through each image
		for (int main = 1; main < 17; main++) {
			for (int sub = 1; sub < 145; sub++) {
				String imagePointer = main + "_" + sub;

				try {
					// the line that reads the image file
					BufferedImage image = ImageIO.read(new File(
							"C:\\Users\\Tk\\Pictures\\Penet\\Clean\\image"
									+ imagePointer + ".png"));

					details = new manuTuple("", imagePointer, 0, 1);
					String imageString = "";
					
					for (int j = 0; j < down.length; j++) {
						for (int i = 0; i < across.length; i++) {
							Color mycolor = new Color(image.getRGB(across[i],
									down[j]));
							int red = mycolor.getRed();
							int green = mycolor.getGreen();
							int blue = mycolor.getBlue();

							if (green + red + blue == 0) {
								// pixel is black
								imageString = imageString.concat("1");
							} else if (green + red + blue == 765) {
								// pixel is white
								imageString = imageString.concat("0");
							} else if (red == 255 && blue + green == 0) {
								// pixel is red
								details.red = 1;
								imageString = imageString.concat("1");
							}
						}
					}
					details.string = imageString;
//					System.out.println(details.imageNo);
//					System.out.println(details.occurences);
//					System.out.println(details.red);
//					System.out.println(details.string);
					writer(details.string );
					k++;
					recurse(0);
				
				} catch (IOException e) {
					// log the exception
					// re-throw if desired
					System.out.println("FAIL");
				}
			}
		}
		for(int mn = 0; mn <list.size(); mn++){
			System.out.println("manu = " + list.get(mn).imageNo + ", " + list.get(mn).occurences + " times, " + list.get(mn).red);
			try {
				writerPostProcess((list.get(mn).string + ", " + list.get(mn).imageNo + ", " + list.get(mn).red + ", " + list.get(mn).occurences));
				writerLong((binaryToInt(list.get(mn).string) + ", " + list.get(mn).imageNo + ", " + list.get(mn).red + ", " + list.get(mn).occurences));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int nm = 0;
		int nmo = 0;
		for(int mn = 0; mn <list.size(); mn++){	//count through list
			if(list.get(mn).red == 1){			//reds count
				nm++;
			} else if (list.get(mn).red == 0){ // blacks count
				nmo++;
			}
		}
		System.out.println("LIST LENGTH " + list.size());
		System.out.println("BLACKS " + nmo);
		System.out.println("REDS " + nm);		
	}
	
	public static void recurse(int length){

		if(length < list.size()){
			if(list.get(length).red == details.red && list.get(length).string.equals(details.string) && length != 0){
				list.get(length).occurences = list.get(length).occurences + 1; //if same string and not length 0, increase count for said string
			} else {
				recurse(length + 1); //may be new string or held further in list
			}
		} else {
			list.add(details); //new string, add to list
		}
	}
	
	public static void writer(String text) throws FileNotFoundException{
		try (PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\Tk\\Pictures\\Penet\\Clean\\pencode.txt", true))) {
		    out.append(text);
		    out.append(System.getProperty("line.separator")); 

		}
	}

	public static void writerPostProcess(String text) throws FileNotFoundException{
		try (PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\Tk\\Pictures\\Penet\\Clean\\pencodeSifted.arff", true))) {
		    out.append(text);
		    out.append(System.getProperty("line.separator")); 
		}
	}

	public static void writerLong(String text) throws FileNotFoundException{
		try (PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\Tk\\Pictures\\Penet\\Clean\\pencodeSiftedLong.arff", true))) {
		    out.append(text);
		    out.append(System.getProperty("line.separator")); 
		}
	}

	public static Long binaryToInt(String binary)
	{
	    return Long.parseLong(binary, 2);
	    
	}
	
}