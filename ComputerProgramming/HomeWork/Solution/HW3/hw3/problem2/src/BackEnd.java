import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.FileReader;

public class BackEnd extends ServerResourceAccessible {
    // Use getServerStorageDir() as a default directory
    // TODO sub-program 1 ~ 4 :
    // Create helper functions to support FrontEnd class

    public boolean auth(String authInfo) {
        String[] authArray = authInfo.split("\\n");
        File file = new File(getServerStorageDir() + authArray[0] + "/password.txt");
        String pw = "";

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                pw = line;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (authArray[1].equals(pw)) return true;
        else return false;
    }
}
