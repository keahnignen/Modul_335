package kenabis.myaudio.record.graph;

import java.util.ArrayList;
import java.util.List;

public class Recordation {

    public Recordation()
    {

    }

    private String name;

    public List<FrequencyPoint> swag = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
