import java.io.*;
import java.util.*;
import java.lang.*;

public class QuizMaker {
    public static void main(String[] args) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setName("JavaQuizFromKazakhstan");
        System.out.println(quiz.toString());
        quiz.loadFromFile(args[0]);
    }
}
