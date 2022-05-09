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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(dateTime + "\n");
            fileWriter.write(contentList.get(0) + "\n");
            fileWriter.write(contentList.get(1) + "\n\n");
            fileWriter.write(contentList.get(2) + "");
            fileWriter.close();
        } catch (IOException e) {
            System.out.print("error");
        }
    }

    private File[] directoryFiles(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }

    public List<Post> sendRecommend(int N, User user) {
        File file = new File(getServerStorageDir() + user.id + "/friend.txt");

        List<String> friends = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                friends.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Post> adPosts = new ArrayList<>();
        List<Post> noAddPosts = new ArrayList<>();

        for (String friend : friends) {
            File[] files = directoryFiles(getServerStorageDir()+friend+"/post");
            for ( File singleFile : files) {
                try {
                    Scanner scanner = new Scanner(singleFile);
                    String date = scanner.nextLine();
                    String title = scanner.nextLine();
                    String isAdd = scanner.nextLine();
                    scanner.nextLine();
                    String contents = scanner.nextLine()+"\n";
                    while (scanner.hasNext()) {
                        contents += scanner.nextLine()+"\n";
                    }
                    scanner.close();

                    LocalDateTime postDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

                    int idx = singleFile.getName().lastIndexOf(".");
                    int id = Integer.parseInt(singleFile.getName().substring(0, idx));
                    Post newPost = new Post(id, postDate, isAdd, title, contents);

                    if (isAdd.equals("yes")) {
                        adPosts.add(newPost);
                    } else noAddPosts.add(newPost);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Post> ret = new ArrayList<>();

        Collections.sort(adPosts, Collections.reverseOrder());
        Collections.sort(noAddPosts, Collections.reverseOrder());

        int returnCount = 0;

        for(Post p : adPosts) {
            if(returnCount<N) {
                ret.add(p);
                returnCount++;
            } else break;
        }

        for(Post p : noAddPosts) {
            if(returnCount<N) {
                ret.add(p);
                returnCount++;
            } else break;
        }

        return ret;
    }
//    private static File[] nameSortedDirectoryFiles(String directoryName) throws NoDataDirectoryException {
//        //TODO: Practice 3 - (2)
//        File[] directoryFiles = directoryFiles(directoryName);
//        try {
//            Arrays.sort(directoryFiles, Comparator.comparing(File::getName));
//        } catch (NullPointerException e) {
//            throw new NoDataDirectoryException();
//        }
//        return directoryFiles;
//    }
//
//    private static List<String> readLines(File file) {
//        List<String> strings = new LinkedList<>();
//        try {
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNext()) {
//                String line = scanner.nextLine();
//                strings.add(line);
//            }
//            scanner.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return strings;
//    }

//    public static List<List<String>> directoryChildrenLines(String directoryName) throws NoDataDirectoryException {
//        List<List<String>> childrenLines = new LinkedList<>();
//        for (File childFile : nameSortedDirectoryFiles(directoryName)) {
//            List<String> lines = readLines(childFile);
//            childrenLines.add(lines);
//        }
//        return childrenLines;
//    }
//
//    private static List<String> readLines(File file) {
//        List<String> strings = new LinkedList<>();
//        try {
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNext()) {
//                String line = scanner.nextLine();
//                strings.add(line);
//            }
//            scanner.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return strings;
//    }
}
