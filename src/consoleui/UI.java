package consoleui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import entity.SlangWord;
import fnc.Function;

public class UI {
	Scanner sc = new Scanner(System.in);
	Function fnc = new Function();

	public void mainUI() {
		System.out.println("==============SLANG WORD DICTIONARY================");
		System.out.println("1. Find by Slang word ");
		System.out.println("2. Find by definition ");
		System.out.println("3. Recently searched word (History) ");
		System.out.println("4. Add new Slang word ");
		System.out.println("5. Modify an existing Slang word ");
		System.out.println("6. Remove a Slang word ");
		System.out.println("7. Restore to origin ");
		System.out.println("8. Show a ramdom Slang word ");
		System.out.println("9. Random question (Find the right definition) ");
		System.out.println("10. Random question (Find the right Slang word) ");
		System.out.println("11. Exit program ");
		System.out.println("Input your action: ");
	}

	public void findBySlangWordUI(ArrayList<SlangWord> arr) {
		System.out.println("============1. FIND BY SLANG WORD===================");
		System.out.println("Please input your slang word: ");
		String slgwrd = sc.nextLine();

		fnc.findSlangWord(arr, slgwrd);
		fnc.saveHistory(slgwrd);
		System.out.println("\n");
		System.out.println("What're you going to do next? ");
		System.out.println("1. Find another Slang word ");
		System.out.println("2. Back to main Menu ");
		System.out.println("Input your action: ");
	}

	public void findByDefinitionUI(ArrayList<SlangWord> arr) {
		System.out.println("==============2. FIND BY DEFINITION==================");
		System.out.println("Please input your definition: ");
		String des = sc.nextLine();

		fnc.findDefinition(arr, des);
		fnc.saveHistory(des);

		System.out.println("\n");
		System.out.println("What're you going to do next? ");
		System.out.println("1. Find another ");
		System.out.println("2. Back to main Menu ");
		System.out.println("Input your action: ");
	}

	public void showHistoryUI() {
		System.out.println("==========3. RECENTLY SEARCHED WORD (HISTORY)==========");
		System.out.println("You've found these words bellow: ");

		fnc.showHistory();

		System.out.println("\n");
		System.out.println("What're you going to do next? ");
		System.out.println("1. Clear all ");
		System.out.println("2. Back to main Menu ");
		System.out.println("Input your action: ");
	}

	public SlangWord addNewUI(ArrayList<SlangWord> arr) {
		boolean overWrite=false;
		SlangWord slangWordNew = new SlangWord();
		System.out.println("===================4. ADD NEW SLANG WORD=============== ");
		System.out.println("Input Slang word: ");
		String slgWrd = sc.nextLine();
		System.out.println("Input definition: ");
		String des = sc.nextLine();
		slangWordNew.setKeyCode(slgWrd);
		slangWordNew.setKeyDefination(des);
		for (SlangWord slangWord : arr) {
			if (slangWord.getKeyCode().equals(slgWrd)) {
				System.out.println("===================Slang word has exists=============== ");
				System.out.println("1. Overwrite Slang word ");
				System.out.println("2. Duplicate Slang word ");
				System.out.println("Input your action: ");
				String act = sc.nextLine();
				if(act.equals("1"))
				{
					overWrite=true;
					String data="";
					arr.remove(arr.indexOf(slangWord));//					
				}				
				break;
			}
		}		
		if(!overWrite)
		{
			String data = slgWrd + "`" + des + "\n";
			fnc.addNewSlangWordToFile(data);
		}
		System.out.println("\n");
		System.out.println("What're you going to do next? ");
		System.out.println("1. Add another one ");
		System.out.println("2. Back to main Menu ");
		System.out.println("Input your action: ");

		return slangWordNew;
	}

	public ArrayList<SlangWord> removeASlangWord(ArrayList<SlangWord> arr) {
		System.out.println("===============5. REMOVE A SLANG WORD===============");
		System.out.println("Input the word which you wanna REMOVE ");

		String slgWrd = sc.nextLine();

		String data = ""; // new array after remove (in String) to write to file
		boolean rs = false;// remove result pass/fail. if pass then update file
		for (SlangWord slangWord : arr) {
			if (slangWord.getKeyCode().equals(slgWrd)) {
				arr.remove(arr.indexOf(slangWord));
				rs = true;
				System.out.println("Removed ~~" +slgWrd);
				break;
			}
		}
		if (rs) {// re-write to file
			for (SlangWord slangWord : arr) {// Array to String
				data = data + slangWord.getKeyCode() + "`" + slangWord.getKeyDefination() + "\n";
			}
			fnc.writeAfterModify(data);
		} else {
			System.out.println("Not found to remove ");
		}

		System.out.println("\n");
		System.out.println("What're you going to do next? ");
		System.out.println("1. Remove another ");
		System.out.println("2. Back to main Menu ");
		System.out.println("Input your action: ");

		return arr;
	}

	public ArrayList<SlangWord> updateSlangWord(ArrayList<SlangWord> arr) {
		System.out.println("===========6. MODIFY AN EXIST SLANG WORD===============");
		System.out.println("Input the word which you wanna MODIFY: ");

		String slgWrd = sc.nextLine();

		fnc.findSlangWord(arr, slgWrd);

		System.out.println("Input new Definition: ");
		String newDes = sc.nextLine();

		String data = "";
		boolean rs = false;

		// new Slang word to replace. Because of using set function of Array list
		SlangWord newSlgWrd = new SlangWord();
		newSlgWrd.setKeyCode(slgWrd);
		newSlgWrd.setKeyDefination(newDes);

		for (SlangWord slangWord : arr) {
			if (slangWord.getKeyCode().equals(slgWrd)) {
				arr.set(arr.indexOf(slangWord), newSlgWrd);
				rs = true;
				System.out.println("Updated ~~");

				break;
			}
		}
		if (rs) {
			for (SlangWord slangWord : arr) {
				data = data + slangWord.getKeyCode() + "`" + slangWord.getKeyDefination() + "\n";
			}
			fnc.writeAfterModify(data);
		} else {
			System.out.println("Not found to remove ");
		}

		System.out.println("\n");
		System.out.println("What're you going to do next? ");
		System.out.println("1. Modify another ");
		System.out.println("2. Back to main Menu ");
		System.out.println("Input your action: ");

		return arr;
	}

	public ArrayList<SlangWord> restoreAsDefaultUI() {
		ArrayList<SlangWord> arr = new ArrayList<>();
		System.out.println("3. RESTORE TO ORIGIN");
		System.out.println("Are you sure? ");
		System.out.println("1. Yes ");
		System.out.println("2. No ");
		int confirm = sc.nextInt();

		if (confirm == 1) {
			fnc.clearAllFileData("slang.txt");
			arr = fnc.fileReader("SlangBackUp.txt");
			String data = "";
			for (SlangWord slangWord : arr) {
				data = data + slangWord.getKeyCode() + "`" + slangWord.getKeyDefination() + "\n";
			}
			fnc.writeAfterModify(data);
		}

		return arr;
	}

	public void randomASlangWord(ArrayList<SlangWord> arr) {
		System.out.println("===============8. RAMDOM A SLANG WORD=====================");

		Random rd = new Random();
		int rs = rd.nextInt(arr.size());// random a number, this is index in array

		System.out.println(arr.get(rs).toString());

	}

	public void ramdomQuest(ArrayList<SlangWord> arr, String key) {
		if (key.equals("code")) {
			System.out.println("==============9. RAMDOM QUESTION (FILE DEFINITION)=============");

			Random rd = new Random();
			int ques = rd.nextInt(arr.size()); // 1 question and 3 answers (1 answer in question obj)
			int a = rd.nextInt(arr.size());
			int b = rd.nextInt(arr.size());
			int c = rd.nextInt(arr.size());

			// add to Array to shuffle them
			ArrayList<Integer> answ = new ArrayList<>();
			answ.add(a);
			answ.add(b);
			answ.add(c);
			answ.add(ques);
			Collections.shuffle(answ);

			System.out.println("Find the definition of [" + arr.get(ques).getKeyCode() + "]: ");
			System.out.println("A. " + arr.get(answ.get(0)).getKeyDefination());
			System.out.println("B. " + arr.get(answ.get(1)).getKeyDefination());
			System.out.println("C. " + arr.get(answ.get(2)).getKeyDefination());
			System.out.println("D. " + arr.get(answ.get(3)).getKeyDefination());
			System.out.println("Input your choice: ");
			String rs = sc.nextLine();
			switch (rs) {
			case "A":
				if (arr.get(answ.get(0)).getKeyDefination().equals(arr.get(ques).getKeyDefination()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;
			case "B":
				if (arr.get(answ.get(1)).getKeyDefination().equals(arr.get(ques).getKeyDefination()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;
			case "C":
				if (arr.get(answ.get(2)).getKeyDefination().equals(arr.get(ques).getKeyDefination()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;
			case "D":
				if (arr.get(answ.get(3)).getKeyDefination().equals(arr.get(ques).getKeyDefination()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;

			default:
				System.out.println("Incorrect ~~");
				break;
			}

			System.out.println("\n");
			System.out.println("What're you going to do next? ");
			System.out.println("1. Try again ");
			System.out.println("2. Back to main Menu ");
			System.out.println("Input your action: ");

		} else if (key.equals("definition")) {
			System.out.println("==================10. RAMDOM QUESTION (FIND SLANG WORD)====================");

			Random rd = new Random();
			int ques = rd.nextInt(arr.size());
			int a = rd.nextInt(arr.size());
			int b = rd.nextInt(arr.size());
			int c = rd.nextInt(arr.size());

			ArrayList<Integer> answ = new ArrayList<>();
			answ.add(a);
			answ.add(b);
			answ.add(c);
			answ.add(ques);
			Collections.shuffle(answ);

			System.out.println("Find the Slang Word of [" + arr.get(ques).getKeyDefination() + "]: ");
			System.out.println("A. " + arr.get(answ.get(0)).getKeyCode());
			System.out.println("B. " + arr.get(answ.get(1)).getKeyCode());
			System.out.println("C. " + arr.get(answ.get(2)).getKeyCode());
			System.out.println("D. " + arr.get(answ.get(3)).getKeyCode());
			System.out.println("Input your choice: ");
			String rs = sc.nextLine();
			switch (rs) {
			case "A":
				if (arr.get(answ.get(0)).getKeyCode().equals(arr.get(ques).getKeyCode()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;
			case "B":
				if (arr.get(answ.get(1)).getKeyCode().equals(arr.get(ques).getKeyCode()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;
			case "C":
				if (arr.get(answ.get(2)).getKeyCode().equals(arr.get(ques).getKeyCode()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;
			case "D":
				if (arr.get(answ.get(3)).getKeyCode().equals(arr.get(ques).getKeyCode()))
					System.out.println("Amazing, Good job !!!");
				else
					System.out.println("Incorrect ~~");
				break;

			default:
				System.out.println("Incorrect ~~");
				break;
			}

			System.out.println("\n");
			System.out.println("What're you going to do next? ");
			System.out.println("1. Try again ");
			System.out.println("2. Back to main Menu ");
			System.out.println("Input your action: ");
		}
	}

}
