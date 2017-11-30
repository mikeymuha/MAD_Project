package muhavani.com.project_exempt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mikey on 30/11/2017.
 */

public class listviewActivity extends AppCompatActivity{
    public ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        mListView = (ListView) findViewById(android.R.id.list);

        final ArrayList<Events> EventList = Events.getEventsFromFile("events.json", this);

        String[] listItems= new String[EventList.size()];

        for(int i=0; i<EventList.size(); i++){
            Events events = EventList.get(i);
            listItems[i]= events.title;
        }

        EventsAdapter adapter = new EventsAdapter(this, EventList);
        mListView.setAdapter(adapter);

        final Context context=this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                //1
                Events selectedEvent =EventList.get(position);

                //2
                Intent detailIntent = new Intent(context, event_detail.class);

                //3
                detailIntent.putExtra("title", selectedEvent.title);
                detailIntent.putExtra("url", selectedEvent.url);

                //4
                startActivity(detailIntent);
            }

        });

    }
}


