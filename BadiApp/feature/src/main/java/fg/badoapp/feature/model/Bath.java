package fg.badoapp.feature.model;

import java.util.List;

public class Bath {

    private int id;
    private String name;
    private List<Pool> pools;
    private int kanton;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

    public int getKanton() {
        return kanton;
    }

    public void setKanton(int kanton) {
        this.kanton = kanton;
    }




}
