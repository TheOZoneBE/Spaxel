package code.math;

public class LineSegment {
    private VectorD start;
    private VectorD end;

    public LineSegment(VectorD start, VectorD end) {
        this.start = start;
        this.end = end;
    }

    public VectorD intersection(LineSegment other) {
        VectorD a = start;
        VectorD b = end;
        VectorD c = other.getStart();
        VectorD d = other.getEnd();

        VectorD r = b.diff(a);
        VectorD s = d.diff(c);
        double cross = r.crossProduct(s);

        VectorD den = c.diff(a);
        double t = den.crossProduct(s) / cross;
        double u = den.crossProduct(r) / cross;
        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            return a.sum(r.multiplicate(t));
        } else {
            return null;
        }
    }

    /**
     * @return the start
     */
    public VectorD getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(VectorD start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public VectorD getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(VectorD end) {
        this.end = end;
    }
}