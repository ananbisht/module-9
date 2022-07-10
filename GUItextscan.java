import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**GUItextscan implements EventHandler so that a stage may be set and actions performed on that stage can be handled. FOr this purpose the application module is also called.
* @author Anant 
*/
public class GUItextscan extends Application implements EventHandler<ActionEvent> {
	/** GUItextscan implements EventHandler so that a stage may be set and actions performed on that stage can be handled. FOr this purpose the application module is also called.

	    Two buttons are declared to be used by the functions that follow
	*/
	
	Button button;
	Button button2;
	
	public static void main(String[] args) throws Exception {
		/**
		 * This function is only meant to launch the arguments of the functions that follow and throw an Exception if it occurs.		
		 */
		
		launch(args);
		
	}
	

	@Override
	public void start (Stage primaryStage) throws Exception{
		/* The start function :
		 * It sets the stage by defining the buttons to display, the size of the buttons and the string which they display. It also has states which function to call when a specified action is performed
		 */
		
		
		primaryStage.setTitle("Word Counter Application");
		button = new Button("Print top 20 most used words");
		button2 = new Button("Print the count for random words");
		GridPane layout = new GridPane();

		
		
		button.setOnAction(this);
		button2.setOnAction(this);
		
		Scene scene = new Scene(layout, 500, 250);
		layout.addRow(0, button , button2);
		primaryStage.setScene(scene); 
		primaryStage.show();
		
	}


	
	@Override
	public void handle (ActionEvent event) {
		/* The handle function:
		 *  It opens and reads the text file containing the poem. It then separates the poem into its constituent words and places them into a list. 
		 *  Another list is made to hold the count for the word appearances in the first list. A third list is made to store the counts in ascending order.
		 *  The first button is meant to display the 20 most used words. This is done by getting the last 20 elements of the ordered list and getting their corresponding words by referencing the second list as they words would be in that index in the first list.
		 *  The second button is meant to display the count for 20 random words which is done by choosing a random word from the first list and displaying its count from the second list. 
		 */
		
		
		FileReader file = null;
		try {
			file = new FileReader("/Users/anantsinghbisht/Desktop/The Raven.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader read = new BufferedReader(file);    
		ArrayList<String> word = new ArrayList<String>();
		
		String str;
		try {
			while ((str=read.readLine())!=null) {
				str = str.replaceAll("\\p{IsPunctuation}","");
				String[] string = str.toLowerCase().split(" " ,100);
				for (String a : string) {
					word.add(a);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Integer> count = new ArrayList<Integer>();
		for (int i=0; i<word.size(); i++) {
			int counts=1;
			for(int j=i+1; j<word.size(); j++){    
				if(word.get(i).equals(word.get(j))){    
                    counts++;    
                }
			}
			count.add(counts);
		}
		
		ArrayList<Integer> ordered_count = new ArrayList<Integer>();
		ordered_count = count;
		
		
		
		int temp;
		for (int i = 0; i < count.size(); i++) {     
            for (int j = i+1; j < count.size(); j++) {     
               if(ordered_count.get(i) > ordered_count.get(j)) {    
                   temp = ordered_count.get(i);    
                   ordered_count.set(i, ordered_count.get(j)); 
                   ordered_count.set(j, temp);    
               }     
            }     
        }    
		
		
		if (event.getSource()==button) {
			int q=20;
			int y;
			int b=ordered_count.size();
			while (b>0) {
				y = count.indexOf(ordered_count.get(b-1));
				System.out.println(word.get(y) + " : " + count.get(y));
				q--;
				b--;
				if (q==0) {
					break;
				}
			}
			
		}
		if (event.getSource()==button2) {
			String y;
			int x;
			for (int i=0; i<20; i++) {
				Random rand = new Random();
				y=word.get(rand.nextInt(word.size()));
				x=word.indexOf(y);
				System.out.println(y+ " : " + count.get(x));
			}
			
			
		}
		
	}
	
	
	
}
