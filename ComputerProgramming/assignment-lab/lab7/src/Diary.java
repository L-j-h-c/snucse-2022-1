import java.util.*;

public class Diary {
    public List<DiaryEntry> diaryEntries = new LinkedList();
    public Map<Integer, Set<String>> searchMap = new HashMap<>();

	public Diary() {
    	//TODO
	}

    public void createEntry() {
    	//TODO
        // Practice 1 - Create a diary entry
        String title = DiaryUI.input("title: ");
        String content = DiaryUI.input("content: ");
        DiaryEntry entry = new DiaryEntry(title, content);
        diaryEntries.add(entry);

        addSearchKeywords(entry);

        DiaryUI.print("The entry is saved.");
    }

    public DiaryEntry findEntry(int id) {
        for (DiaryEntry entry : diaryEntries){
            if (entry.getID() == id) return entry;
        }
        return null;
    }

    public void readEntry(int id) {
    	//TODO
        // Practice 1 - Read the entry of given id
        DiaryEntry diaryEntry = findEntry(id);

        if (diaryEntry == null) {
            DiaryUI.print("There is no entry with id " + id);
            return;
        }
        DiaryUI.print(diaryEntry.getFullString());
    }

    public void deleteEntry(int id) {
    	//TODO
        // Practice 1 - Delete the entry of given id
        DiaryEntry diaryEntry = findEntry(id);
        if ( diaryEntry == null) {
            DiaryUI.print("There is no entry with id " + id);
            return;
        }
        diaryEntries.remove(diaryEntry);
        DiaryUI.print("Entry "+ id + " is removed.");
    }

    public void searchEntry(String keyword) {
        //TODO
        // Practice 1 - Search and print all the entries containing given keyword
        List<DiaryEntry> searchResult = new ArrayList<DiaryEntry>();

        for(int id: searchMap.keySet())
            if(searchMap.get(id).contains(keyword))
                searchResult.add(findEntry(id));

        if(searchResult.isEmpty()) {
            DiaryUI.print("There is no entry that contains \"" + keyword + "\".");
            return;
        }

        for(DiaryEntry entry : searchResult)
            DiaryUI.print(entry.getFullString() + "\n");
    }

    private void addSearchKeywords(DiaryEntry entry) {
        Set<String> keywords = new HashSet<>();

        for(String keyword : entry.getTitle().split("\\s"))
            keywords.add(keyword);

        for(String keyword : entry.getContent().split("\\s"))
            keywords.add(keyword);

        searchMap.put(entry.getID(), keywords);
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
