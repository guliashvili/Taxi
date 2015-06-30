package ge.taxistgela.ram.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by GIO on 6/30/2015.
 */
public class BOOLEAN {
    @Expose
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
