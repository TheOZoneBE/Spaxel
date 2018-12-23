package code.logger;

/**
 * Created by theo on 24/06/17.
 */
public class LogResult {
    private long start;
    private long end;

    public LogResult(long start){
        this.start = start;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getDifference(){
        return end - start;
    }
}
