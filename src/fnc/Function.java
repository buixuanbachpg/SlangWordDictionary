package fnc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import entity.SlangWord;

public class Function {
	
	
	public ArrayList<SlangWord> fileReader (String url){
		ArrayList<SlangWord> slangWords = new ArrayList<>();
		
		try {
			File file = new File(url);
			Scanner mySc = new Scanner(file);
			while (mySc.hasNextLine()) {
				SlangWord slangWord = new SlangWord();
		        String data = mySc.nextLine();
		        String[] dataArr = data.split("`");
		        slangWord.setKeyCode(dataArr[0]);
			    slangWord.setKeyDefination(dataArr[1]);
			    slangWords.add(slangWord);
		      }
			mySc.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("An error has occurred when reading file ~~ :(((");
		}
		return slangWords;
	}
	
	//save to another text file line by line
	public void saveHistory(String data) {
		try {
		      FileWriter myWriter = new FileWriter("history.txt",true);
		      myWriter.write(data+"\n");
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred when saving history ~~ :(((");
		      e.printStackTrace();
		    }
	}
	
	//for update or remove, clear all data and write again
	public void clearAllFileData(String url) {
		try {
		      FileWriter myWriter = new FileWriter(url);
		      myWriter.write("");
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred when saving history ~~ :(((");
		      e.printStackTrace();
		    }
	}
	
	//find by code
	public void findSlangWord (ArrayList<SlangWord> arr, String input) {
		boolean rs = false;
		for (SlangWord slangWord : arr) {
			if (input.equals(slangWord.getKeyCode())) {
				System.out.println("Your word [" +input + "] mean [" + slangWord.getKeyDefination() + "]");
				rs = true;
			}
		}
		if (!rs)
			System.out.println("Slang word is not found");
	}
	
	//find by definition (contains search)
	public void findDefinition (ArrayList<SlangWord> arr, String input) {
		boolean rs = false;
		for (SlangWord slangWord : arr) {
			if (slangWord.getKeyDefination().contains(input)) {
				System.out.println("Your word [" +input + "] is include in: [" 
						+ slangWord.getKeyCode() +"]: ["+ slangWord.getKeyDefination() +"]");
				rs = true;
			}
		}
		if (!rs)
			System.out.println("Slang word is not found");
	}
	
	//read data from history.txt
	public void showHistory () {
		try {
			File file = new File("history.txt");
			Scanner mySc = new Scanner(file);
			while (mySc.hasNextLine()) {
				System.out.println(mySc.nextLine());
		    }
			mySc.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("An error has occurred when reading file ~~ :(((");
		}
	}
	
	//add new word, write into file directly
	public void addNewSlangWordToFile(String data) {
		try {
		      FileWriter myWriter = new FileWriter("slang.txt", true);
		      myWriter.write(data);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred when saving history ~~ :(((");
		      e.printStackTrace();
		    }
	}
	
	//after update slang word, write all again
	public void writeAfterModify(String data) {
		try {
		      FileWriter myWriter = new FileWriter("slang.txt");
		      myWriter.write(data);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred when saving history ~~ :(((");
		      e.printStackTrace();
		    }
	}
}
