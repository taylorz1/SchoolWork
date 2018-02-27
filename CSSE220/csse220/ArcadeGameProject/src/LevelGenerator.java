import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 
 * I don't even know why you would need this class. It's mostly to save time.
 * All it does is generate a bounding box of walls.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class LevelGenerator {
	// Level format:
	// int :: Wall #
	// Double Double :: Wall left corner for int number of times
	// Double Double :: Hero center
	// int :: BrickWall #
	// Double Double :: Brick wall left corner for int number of times
	// int :: Monster #

	public static void main(String[] args) {
		// Let's create some bounding walls.
		int n = 3; // THIS IS THE LEVEL NUMBER:: YOU WILL OVERWRITE
		int x = 1050;
		int y = 840;
		int wall = 60;
		int counter = 0;

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("Level" + n + ".txt"), "utf-8"))) {
			//writer.write("\n");
			for (int i = 0; i*60 <= x; i++) {
				int xp = i*60;
				int yp = 0;
				writer.write(xp+" "+yp);
				((BufferedWriter) writer).newLine();
				counter++;
			}
			for (int i = 0; i*60 <= y; i++) {
				int xp = 0;
				int yp = i*60;
				writer.write(xp +" " +yp);
				((BufferedWriter) writer).newLine();
				counter++;
			}
			//Time to write the other half of walls.
			for (int i = 0; i*60 <= x; i++) {
				int xp = i*60;
				int yp = 800;
				writer.write(xp+" "+yp);
				((BufferedWriter) writer).newLine();
				counter++;
			}
			for (int i = 0; i*60 < y; i++) {
				int xp = 1000;
				int yp = i*60;
				writer.write(xp +" " +yp);
				((BufferedWriter) writer).newLine();
				counter++;
			}
			writer.write(String.valueOf(counter));
			writer.close();
		} catch (UnsupportedEncodingException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		} catch (FileNotFoundException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
	}
}
