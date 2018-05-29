package fg.badoapp.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fg.badoapp.feature.dal.BadiDao;
import fg.badoapp.feature.model.Bath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Overwatch");
        addBadisToList();
    }


    private void addBadisToList()
    {
        ListView badis = findViewById(R.id.badiliste);
        ArrayAdapter<Bath> badiAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        badiAdapter.addAll(BadiDao.getAll());
        badis.setAdapter(badiAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new
                AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView parent, View v, int position, long id)
                    {
                        Intent intent = new Intent(getApplicationContext(), BadiDetailsActivity.class);
                        Bath selected = (Bath)parent.getItemAtPosition(position);
                        intent.putExtra("badiId", selected.getId());
                        intent.putExtra("badiName", selected.getName());
                        startActivity(intent);
                    }
                };
        badis.setOnItemClickListener(mListClickedHandler);

    }

}
