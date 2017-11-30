package muhavani.com.project_exempt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import muhavani.com.project_exempt.Events;
import muhavani.com.project_exempt.R;


/**
 * Created by mikey on 12/10/2017.
 */

public class EventsAdapter extends BaseAdapter{

    LayoutInflater minflater;
    Context mcontext;
    ArrayList<Events> mDataSource;



    public EventsAdapter(Context context, ArrayList<Events> items){
        mcontext = context;
        mDataSource = items;
        minflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get view for row item
        View rowView = minflater.inflate(R.layout.list_item_event, parent, false);

        //Get the title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.event_list_title);

        //Get the date element
        TextView dateTextView = (TextView) rowView.findViewById(R.id.event_list_date);

        //Get the time element
        TextView timeTextView = (TextView) rowView.findViewById(R.id.event_list_time);

        //Get the time element
        TextView placeTextView = (TextView) rowView.findViewById(R.id.event_list_place);

        //Get the thumbnail element
        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.event_list_thumbnail);

        //1
        Events events = (Events) getItem(position);

        //2
        titleTextView.setText(events.title);
        dateTextView.setText(events.date);
        timeTextView.setText(events.time);
        placeTextView.setText(events.place);

        //3
        Picasso.with(mcontext).load(events.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);



        return rowView;
    }
}
