import java.text.DecimalFormat;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        String answer;
        char ans = ' ';

        Scanner input = new Scanner(System.in);

        FileOps trainEnglish = new FileOps();
        String textLang1 = trainEnglish.getFileText(args[0]);
        String englishLangName = "English";
        FileText english = new FileText();
        english.train(textLang1);

        FileOps trainCzech = new FileOps();
        String textLang2 = trainCzech.getFileText(args[1]);
        String czechLangName = "Czech";
        FileText czech = new FileText();
        czech.train(textLang2);

        do {
            System.out.print("Please insert new text file to test:  ");
            String testFile = input.next();
            FileOps test = new FileOps();
            String testingText = test.getFileText(testFile);

            if (testingText != null) {

                english.test(testingText);
                czech.test(testingText);

                double englishProb = english.getProbability();
                double czechProb = czech.getProbability();

                DecimalFormat df = new DecimalFormat("#%");

                if (englishProb > czechProb){
                    System.out.println("\nThe most probability that the text file is in \""
                            + englishLangName + "\"");
                } else {
                    System.out.println("\nThe most probability that the text file is in \""
                            + czechLangName + "\"");
                }

                System.out.println("\nProbability of " + englishLangName.toUpperCase()
                        + " text: " + (df.format(englishProb)));

                System.out.println("Probability of " + czechLangName.toUpperCase()
                        + " text: " + (df.format(czechProb)));

                System.out.print("\n\nWould you like to test another text file? yes/no ");
                answer = input.next().toLowerCase();
                ans = answer.charAt(0);

            }
        } while (ans == 'y');

        System.out.println("\nThe application is terminated.");

    }

}


