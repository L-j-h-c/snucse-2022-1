public class HashableString implements Comparable<HashableString> {

    private String content;

    public HashableString(String content){
        this.content = content;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for(char c : content.toCharArray()) {
            sum += c;
        }
        return sum % HashAVLTable.tableSize;
    }

    @Override
    public int compareTo(HashableString op) {
        return content.compareTo(op.content);
    }

    @Override
    public String toString() {
        return this.content;
    }
}
