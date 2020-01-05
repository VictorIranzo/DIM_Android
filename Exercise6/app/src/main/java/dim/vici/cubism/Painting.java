package dim.vici.cubism;

import androidx.annotation.NonNull;

public class Painting {
    public final int id;
    public final String name;

    public Painting(int resourcePainting, String name)
    {
        this.id = resourcePainting;
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
