package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator comp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    public T max() {
        int max = 0;
        if (super.size() == 0) {
            return null;
        }
        for (int i = 1; i < super.size(); i++) {
            if (comp.compare(super.get(i), super.get(max)) > 0) {
                max = i;
            }
        }
        return super.get(max);
    }

    public T max(Comparator<T> c) {
        int max = 0;
        if (super.size() == 0) {
            return null;
        }
        for (int i = 1; i < super.size(); i++) {
            if (c.compare(super.get(i), super.get(max)) > 0) {
                max = i;
            }
        }
        return super.get(max);
    }




}
