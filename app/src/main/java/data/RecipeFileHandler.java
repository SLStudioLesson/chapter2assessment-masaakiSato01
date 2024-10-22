package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br> 
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        ArrayList<String> arrayList = new ArrayList<>();
        String line = "";
        //読み込めなくなるまで実行
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));){
            while ((line = reader.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        //リストを返す
        return arrayList;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     // 
    public void addRecipe(String recipeName, String ingredients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))){
            //レシピ名と材料を書き込む
            writer.write(recipeName+", "+ingredients);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
    }

    /**
     * 設問3 検索機能
     * 
     * @param name
     * @param ingredients
     */

    public void searchRecipes(String name,String ingredients){
        for(String array:readRecipes()){
            if (array.contains(name) && array.contains(ingredients)) {
                System.out.println(array);
            }
        }
    }

}
