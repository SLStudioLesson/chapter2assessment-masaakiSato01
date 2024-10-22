package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        RecipeFileHandler rfh = new RecipeFileHandler();
        ArrayList<String> array = new ArrayList<>(rfh.readRecipes());
        //レシピが空なら実行しない
        if (array.isEmpty()) {
            System.out.println("No recipes available");
            return;
        }
        // レシピの出力
            for (String str : array) {
                System.out.println("Recipes:");
                String[] currentStr = str.split(",");
                System.out.println("Recipe Name: " + currentStr[0]);
                System.out.print("Main Ingredients");
                for (int i = 1; i < currentStr.length; i++) {
                    if (i > 1)
                        System.out.print(", ");
                    System.out.print(currentStr[i]);
                }
                System.out.println();
                System.out.println("-----------------------------------");
            }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        RecipeFileHandler rfh = new RecipeFileHandler();
        try {
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            System.out.print("Enter main ingredients (comma separated): ");
            String ingredients = reader.readLine();

            rfh.addRecipe(recipeName, ingredients);
            System.out.println("Recipe added successfully");
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 設問3: 検索機能
     *
     * @throws java.io.IOException 一致するレシピがない
     */

    private void searchRecipe() throws IOException {
        RecipeFileHandler rfh = new RecipeFileHandler();
        try {
            System.out.print("Enter search query (e.g., 'name=Tomato&ingredient=Garlic'): ");
            String inputStr = reader.readLine();
            String name = "";
            String ingredients = "";
            String[] splitStr = inputStr.split("&");

            for (String str : splitStr) {
                String[] keyValue = str.split("=");
                if (keyValue[0].contains("name") && keyValue.length == 2) {
                    name = keyValue[1];
                }
                if (keyValue[0].contains("ingredient") && keyValue.length == 2) {
                    ingredients = keyValue[1];
                }
            }

            rfh.searchRecipes(name, ingredients);

        } catch (IOException e) {
            throw e;
        }
    }

}
