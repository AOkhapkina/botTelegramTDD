import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TextSaver {

    private static final String DEFAULT_FILENAME = "Storage.txt";

    private boolean saverCalled = false;

    public boolean isCalled() {
        return saverCalled;
    }

    public boolean saveText(String text) {
        saverCalled = true;

        try {
            Writer writer = new BufferedWriter(new FileWriter(DEFAULT_FILENAME, true));
            writer.write(text + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getFileName() {
        return DEFAULT_FILENAME;
    }
}
