package fg.badoapp.feature.dal;

import java.util.ArrayList;
import java.util.List;
import fg.badoapp.feature.model.Bath;

public class BadiDao {
    public static List<Bath> getAll() {
        List<Bath> availableBadis = new ArrayList<>();
        availableBadis.add(new Bath(71, "Schwimmbad", "Aarberg", "BE"));
        availableBadis.add(new Bath(27, "Schwimmbad Gruebi", "Adelboden", "BE"));
        availableBadis.add(new Bath(6, "Stadtberner Baeder", "Bern", "BE"));
        availableBadis.add(new Bath(55, "Zürichsee", "", "ZH"));
        return availableBadis;
    }
}