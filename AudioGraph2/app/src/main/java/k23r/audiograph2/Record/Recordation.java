package k23r.audiograph2.Record;

import java.util.HashMap;
import java.util.Map;

public class Recordation {
    private String name;

    public Map<String, Float> swag = new HashMap<String, Float>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
