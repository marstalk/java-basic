package flinkdemo.a_wordcount;

public class WordCount {
    private String word;
    private long count;

    public WordCount(String word, long count) {
        this.word = word;
        this.count = count;
    }

    public WordCount() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WordCount{" +
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }
}
