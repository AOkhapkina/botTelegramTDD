import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextSaverTest {

    @Test
    public void whenSaveHelloThenFileSavedOk() {

        TextSaver saver = new TextSaver();

        boolean result = saver.saveText("Bye-bye");
        assertTrue(result);
        assertTrue(new File(saver.getFileName()).exists());
    }

    @Test
    public void whenSaveHelloThenFileHasHello() {

        TextSaver saver = new TextSaver();
        saver.saveText("Bye-bye");

        BufferedReader reader = getBufferedReader(saver.getFileName());
        String line = getLastLine(reader);

        assertEquals("Bye-bye", line);
    }

    private String getLastLine (BufferedReader reader){
        //read all lines and get last line
        // read line, while not null
        //take the line before null and write it
//        String line;
//        line = reader.readLine();


        return null;
    }

    private String getAllLines(BufferedReader reader) {
        String line;
        StringBuilder allLinesBuilder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                allLinesBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLinesBuilder.toString();
    }

    @Nullable
    private BufferedReader getBufferedReader(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }
}
