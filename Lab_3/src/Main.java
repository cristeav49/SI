import java.io.IOException;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:/Univer/Anul IV/SI/Git/Lab_3/lista-ro.txt").toAbsolutePath();
        List<String> words = Files.lines(path).collect(Collectors.toList());
        String code = getInput();
        cipher(code, words);
    }

    public static String getInput(){
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the code    : ");
        String code = kb.nextLine();
        kb.close();
        System.out.print("Decrypted text is : ");
        return code;
    }

    public static boolean isWord(String search, List<String> words) throws IOException{
        return words.stream().anyMatch(p->p.equalsIgnoreCase(search));
    }

    public static int cipher(String code, List<String> database) throws IOException{
        Queue<Integer> indexHolder;
        String index = "AĂÂBCDEFGHIÎJKLMNOPQRSȘTȚUVWXYZ";
        char[] indexArray = index.toCharArray();
        int keyChange = 0, mostCommonCounter = 0, keyRemembered = -1;
        String answer = code.toUpperCase();

        while(keyChange != 31) {

            indexHolder = new LinkedList<Integer>();
            String holder = "";
            int preInd = 0, commonCounter = 0;

            for (int i = 0; i < code.length(); i++) {
                char inQuestion = answer.charAt(i);

                for (int j = 0; j < 31; j++) {
                    if(inQuestion ==  ' '){
                        holder += ' ';
                        indexHolder.add(i);
                        break;
                    }
                    else if (inQuestion == indexArray[j]) {
                        holder += indexArray[(j + keyChange) % 31];
                        break;
                    }
                }
            }

            indexHolder.add(holder.length());
         // using frequency analysis
            for (int i = 0; i < indexHolder.size() + 1; i++) {
                if( isWord( holder.substring(preInd, indexHolder.peek()) , database) ){
                    commonCounter++;
                }
                preInd = indexHolder.remove() + 1;
            }

            if(mostCommonCounter < commonCounter){
                mostCommonCounter = commonCounter;
                keyRemembered = keyChange;
            }

            keyChange++;
        }

        if(keyRemembered == -1){
            System.out.println("Caesar Cipher Did Not Work.");
        }
        else
            cipher(answer,keyRemembered);
        return keyChange;
    }

    public static void cipher(String code, int ind){
        String index = "aăâbcdefghiîjklmnopqrsștțuvwxyz";
        index = index.toUpperCase();
        String holder = "";
        char[] indexArray = index.toCharArray();
        String answer = code.toUpperCase();

        for(int i = 0; i < code.length(); i++){
            char inQuestion = answer.charAt(i);

            for(int j = 0; j < 31; j++){
                if(inQuestion ==  ' '){
                    holder += ' ';
                    break;
                }
                if(inQuestion == indexArray[j]){
                    holder += indexArray[(j+ind)%31];
                    break;
                }
            }
        }
        System.out.println(holder);
        System.out.print("Used Key is       : ");
        System.out.print(ind);
    }

}


