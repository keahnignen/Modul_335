package kenabis.myaudio.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
