package ge.taxistgela.ram.bean;

/**
 * Created by GIO on 6/30/2015.
 */
public class BOOLEAN {
    private boolean b = false;

    public BOOLEAN(boolean b) {
        this.b = b;
    }

    public BOOLEAN(BOOLEAN b) {
        this.b = b.get();
    }

    public boolean get() {
        return b;
    }

    public void set(boolean b) {
        this.b = b;
    }
}
