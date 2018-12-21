import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class FileOps {

    private String text = null;

    public String getFileText(String fileText) {

        readFile(fileText);

        if (text != null) {
            convertText();
        }

        return text;
    }

    private void readFile(String fileText) {

        String line;
        BufferedReader br;

        try {
            FileReader fr = new FileReader(fileText);
            br = new BufferedReader(fr);

            line = br.readLine();
            StringBuffer stb = new StringBuffer();

            while (line != null) {
                stb.append(" " + line);
                line = br.readLine();
            }
            text = stb.toString();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void convertText() {

        text = text.toLowerCase();

        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        text = pattern.matcher(nfdNormalizedString).replaceAll("");

        text = text.replaceAll("<.*?>", "");
        text = text.replaceAll("[^a-z ]", "");
        text = text.replaceAll("\\s", " ").trim();
    }

}

