import java.util.*;

public class Diary {
	public Diary() {
    	//TODO
	}
	
    public void createEntry() {
    	//TODO
        // Practice 1 - Create a diary entry
    }
    public void readEntry(int id) {
    	//TODO
        // Practice 1 - Read the entry of given id
    }

    public void deleteEntry(int id) {
    	//TODO
        // Practice 1 - Delete the entry of given id
    }

    public void searchEntry(String keyword) {
        //TODO
        // Practice 1 - Search and print all the entries containing given keyword
    }

    public void listEntries() {
        //TODO
        // Practice 2 - List all the entries - sorted in ascending order of the ID
    }
    public void listEntries(String condition1) {
        //TODO
        // Practice 2 - List all the entries - sorted in ascending order of the title
    }
    public void listEntries(String condition1, String condition2) {
        //TODO
        // Practice 2 - List all the entries - sorted in ascending order of the title
        //                                      then in descending order of the content word count
        //                                      then in ascending order of the ID

    }

    class IDSort implements Comparator<DiaryEntry>{
        @Override
        public int compare(DiaryEntry entry1, DiaryEntry entry2){
            //TODO
            // Practice 2 - List all the entries - sorted in ascending order of the ID
            return -1;
        }
    }
    class titleSort implements Comparator<DiaryEntry>{
        @Override
        public int compare(DiaryEntry entry1, DiaryEntry entry2){
            //TODO
            // Practice 2 - List all the entries - sorted in ascending order of the title
            return -1;
        }
    }

    class lengthSort implements Comparator<DiaryEntry>{
        @Override
        public int compare(DiaryEntry entry1, DiaryEntry entry2){
            //TODO
            // Practice 2 - List all the entries - sorted in descending order of the content word count
            return -1;
        }
    }
}
