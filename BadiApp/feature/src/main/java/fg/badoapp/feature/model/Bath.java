package fg.badoapp.feature.model;

import java.util.List;

public class Bath {

    private int id;
    private String name;
    private List<Pool> pools;
    private String kanton;
    private String ort;


    public Bath()
    {

    }

    public Bath(int id, String name, String kanton, String ort) {
        this.id = id;
        this.name = name;
        this.pools = pools;
        this.kanton = kanton;
        this.ort = ort;
    }

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

    public String getKanton() {
        return kanton;
    }

    public void setKanton(String kanton) {
        this.kanton = kanton;
    }


    public void addBecken(Pool becken) {
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getOrt() {
        return ort;
    }

}
