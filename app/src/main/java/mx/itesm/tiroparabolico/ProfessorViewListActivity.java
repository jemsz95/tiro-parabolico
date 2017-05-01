package mx.itesm.tiroparabolico;

/**
 * Created by jorgeemiliorubiobarboza on 30/04/17.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ProfessorViewListActivity extends ListActivity {

    ListView studentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_viewlist_activity);

        studentView = (ListView) findViewById(android.R.id.list);

        studentView.setFastScrollEnabled(true);
        //rellenar array con datos de la base de datos
        String[] students = getResources().getStringArray(R.array.fruits_array);

        List<String> studentList = Arrays.asList(students);
        Collections.sort(studentList);
        setListAdapter(new AdapterProfessorListView(this, studentList));

        studentView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View arg1,
                                    int position, long arg3) {
                Toast.makeText(getApplicationContext(),
                        "" + parent.getItemAtPosition(position),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
