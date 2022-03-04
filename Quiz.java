import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Quiz {
    private String name;
    private ArrayList<Question> questions = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    Test test = new Test();
    Fillin fillin = new Fillin();
    public Quiz() throws FileNotFoundException {
    }

    public void setName(String s){
         this.name = s;
    }
    public String getName(){
        return name;
    }
    public void addQuestions(Question ss) {
        this.questions.add(ss);
    }
    public Quiz loadFromFile(String s) throws FileNotFoundException {
        Quiz q = new Quiz();
        File file = new File(s);
        Scanner inFile = new Scanner(file);
        String left = "";
        /*String incorrect = "";*/
        int correct_Ans = 0;
        int All_Ans = 0;
        while (inFile.hasNextLine()) {
            /*test.setDescription(inFile.nextLine());*/
            String sss = inFile.nextLine();
            All_Ans++;
            if (sss.contains("{blank}")) {
                fillin.setDescription(sss);
                System.out.println("------------------------");
                System.out.println(fillin.getDescription().replace("{blank}", "_____"));
                fillin.setAnswer(inFile.nextLine());
                System.out.print("\nWrite the right answer: ");
                String ss = in.nextLine();
                if (fillin.getAnswer().equals(ss)) {
                    System.out.println("\nCorrect");
                    correct_Ans++;
                } else {
                    System.out.println("\nincorrect");
                    left = fillin.getAnswer();
                    System.out.println("\nThe correct answer was -> "+ left);
                    /*incorrect += fillin.getAnswer();*/
                }
            }else {
                test.setDescription(sss);
                System.out.println("-------------------------");
                System.out.println(test.getDescription());

                /*System.out.println(test.getAnswer());*/
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = inFile.nextLine();
                }
                test.setAnswer(options[0]);
                test.setOptions(options);
                for (int i = 0; i < 4; i++) {
                    System.out.println(test.getOptionAt(i) + " " + options[i]);
                }
                System.out.print("\nEnter only one letter as answer: ");
                while (true) {
                    try {
                        char symbols = in.nextLine().charAt(0);
                        int indexAnswer = (symbols - 'A');
                        if (options[indexAnswer].equals(test.getAnswer())) {
                            correct_Ans++;
                            System.out.println("\nCorrect");
                            break;
                        } else {
                            System.out.println("\nIncorrect");
                            left = test.getAnswer();
                            System.out.println("\nCorrect answer was -> " + left);
                            /*incorrect += fillin.getAnswer();*/
                            break;
                        }


                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.print("Invalid quiz format. Try again (Ex A, B ... ) ");
                    }
                }
            }
        }
        String a = Integer.toString(correct_Ans);
        String b = Integer.toString(All_Ans);
        int percent = (int) Math.round(correct_Ans * 100)/All_Ans;

        String[] arr = {"Your mind level so low", "It is still low result", "You need study hard",
                "Still a few low", "It may be a random", "You are smart and logical person", "Great, Clever person", "Very high level education maybe", "You are genius", "Perfect"};
        int[] mass = {10, 20, 30, 40, 50, 60,  70, 80, 90, 100};
        String s1 = "";
        for (int i = 0; i < 10; i++) {
            if (percent >= mass[i]){
                 s1 = arr[i];
            }
        }
        System.out.println("\n"+s1+"\n");
        System.out.println("Correct Answers: " + correct_Ans + "/" + All_Ans + " " + "(" + percent  + "%)");
        return q;
    }

    @Override
    public String toString(){
        return "============================================\n" +
                "Welcome to my \"" + getName() + "\" QUIZ! Good Luck!\n";
    }
   /* public void start(){

    }*/

}
