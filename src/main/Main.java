package main;

import java.util.ArrayList;
import java.util.Scanner;
import consoleui.UI;
import entity.SlangWord;
import fnc.Function;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function fnc = new Function();
		UI ui = new UI();
		boolean mainAct=true;
		Scanner sc = new Scanner(System.in);
		// get all data first run only
		ArrayList<SlangWord> slgWrdArr = new ArrayList<>();
		slgWrdArr = fnc.fileReader("slang.txt");
		SlangWord slangWord = new SlangWord();
		while (mainAct) {// always back to main Menu when finish a function

			ui.mainUI();

			String action = sc.nextLine();

			switch (action) {
			case "1":// find by code
				ui.findBySlangWordUI(slgWrdArr);
				String act = sc.nextLine();
				while (act.equals("1")) {
					ui.findBySlangWordUI(slgWrdArr);
					act = sc.nextLine();
				}
				break;
			case "2":// find by definition
				ui.findByDefinitionUI(slgWrdArr);
				String act2 = sc.nextLine();
				while (act2.equals("1")) {
					ui.findByDefinitionUI(slgWrdArr);
					act2 = sc.nextLine();
				}
				break;
			case "3":// show search history
				ui.showHistoryUI();
				String act3 = sc.nextLine();
				if (act3.equals("1"))
					fnc.clearAllFileData("history.txt");
				break;
			case "4":// add new
				slangWord = ui.addNewUI(slgWrdArr);
				slgWrdArr.add(slangWord);
				String act4 = sc.nextLine();
				while (act4.equals("1")) {
					ui.addNewUI(slgWrdArr);
					act4 = sc.nextLine();
				}
				break;
			case "5":// update (definition only)
				slgWrdArr = ui.updateSlangWord(slgWrdArr);
				String act5 = sc.nextLine();
				while (act5.equals("1")) {
					ui.updateSlangWord(slgWrdArr);
					act5 = sc.nextLine();
				}
				break;
			case "6":// remove by code
				slgWrdArr = ui.removeASlangWord(slgWrdArr);
				String act6 = sc.nextLine();
				while (act6.equals("1")) {
					ui.removeASlangWord(slgWrdArr);
					act6 = sc.nextLine();
				}
				break;
			case "7":// restore to origin data (clear all update/insert/delete)
				slgWrdArr = ui.restoreAsDefaultUI();
				break;
			case "8":// show a random Word
				ui.randomASlangWord(slgWrdArr);
				break;
			case "9":// random quest (select definition)
				ui.ramdomQuest(slgWrdArr, "code");
				String act9 = sc.nextLine();
				while (act9.equals("1")) {
					ui.ramdomQuest(slgWrdArr, "code");
					act9 = sc.nextLine();
				}
				break;
			case "10":// random quest (select code)
				ui.ramdomQuest(slgWrdArr, "definition");
				String act10 = sc.nextLine();
				while (act10.equals("1")) {
					ui.ramdomQuest(slgWrdArr, "definition");
					act10 = sc.nextLine();
				}
				break;
			case "11":
				mainAct=false;
				sc.close();				
				break;
			default:
				System.out.println("Acction is not found!");
				break;
			}
		}
		
	}
}
