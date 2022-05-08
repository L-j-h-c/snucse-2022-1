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

    public void storePost(List contentList, User user) {
        String fileName = "";
        File[] files = directoryFiles(getServerStorageDir()+user.id+"/post");

        int largeFileName = 0;
        for(File file : files) {
            int idx = file.getName().lastIndexOf(".");
            if(Integer.parseInt(file.getName().substring(0, idx)) > largeFileName) {
                largeFileName = Integer.parseInt(file.getName().substring(0, idx));
                break;
            }
        }
        largeFileName++;

        fileName = getServerStorageDir()+user.id+"/post"+largeFileName;

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (Object string : contentList) {
                fileWriter.write(string + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.print("error");
        }
    }

    private File[] directoryFiles(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }
}
