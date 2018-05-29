package fg.badoapp.feature.model;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;


public class WieWarmJsonParser {
    public static Bath createBadiFromJsonString(String badiJsonString) throws JSONException {
        Bath badi = new Bath();
        JSONObject jsonObj = new JSONObject(badiJsonString);
        badi.setId(Integer.parseInt(jsonObj.getString("badid")));
        badi.setName(jsonObj.getString("badname"));
        badi.setKanton(jsonObj.getString("kanton"));
        badi.setOrt(jsonObj.getString("ort"));
        JSONObject beckenJson = jsonObj.getJSONObject("becken");
        Iterator keys = beckenJson.keys();

        while (keys.hasNext())
        {
            Pool becken = new Pool();
            String key = (String) keys.next();
            JSONObject subObj = beckenJson.getJSONObject(key);
            becken.setName(subObj.getString("beckenname"));
            becken.setTemperature(Double.parseDouble(subObj.getString("temp")));
            badi.addBecken(becken);
        }

        return badi;
    }
}
