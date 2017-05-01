package mx.itesm.tiroparabolico;

/**
 * Created by jorgeemiliorubiobarboza on 30/04/17.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;



public class AdapterProfessorListView extends ArrayAdapter<String> implements SectionIndexer {
    HashMap<String, Integer> mapIndex;
    String[] sections;
    List<String> students;

    public AdapterProfessorListView(Context context, List<String> studentsList){
        super(context, R.layout.list_itemstudent, studentsList);

        this.students=studentsList;
        mapIndex = new LinkedHashMap<String, Integer>();

        for (int x = 0; x < students.size(); x++) {
            String fruit = students.get(x);
            String ch = fruit.substring(0, 1);
            ch = ch.toUpperCase(Locale.US);

            // HashMap va a preveenir que se dupliquen valores
            mapIndex.put(ch, x);
        }

        Set<String> sectionLetters = mapIndex.keySet();

        // crear lista a partir del conjunto a ordenar
        ArrayList<String> sectionList = new ArrayList<String>(sectionLetters);

        Log.d("sectionList", sectionList.toString());
        Collections.sort(sectionList);

        sections = new String[sectionList.size()];

        sectionList.toArray(sections);
    }

    public int getPositionForSection(int section) {
        Log.d("section", "" + section);
        return mapIndex.get(sections[section]);
    }

    public int getSectionForPosition(int position) {
        Log.d("position", "" + position);
        return 0;
    }

    public Object[] getSections() {
        return sections;
    }
}
