public class PostSearch implements Comparable<PostSearch> {
        Post post;
        int occurrence;
        int numOfKeys;

        PostSearch(Post post, int occur, int numKeys){
        this.post = post;
        this.occurrence = occur;
        this.numOfKeys = numKeys;
    }

    @Override
    public int compareTo(PostSearch p) {
            if(this.occurrence>p.occurrence) return 1;
            else if (this.occurrence==p.occurrence) {
                if(this.numOfKeys>p.numOfKeys) return 1;
                else return -1;
            } else return -1;
    }
}
