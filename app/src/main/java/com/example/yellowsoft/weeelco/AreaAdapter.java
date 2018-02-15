package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yellowsoft on 9/2/18.
 */

public class AreaAdapter extends BaseAdapter implements Filterable {
    Context context;
    LayoutInflater inflater;
    ArrayList<Areas> areas;
    ArrayList<Areas> areas_all;
    CheckoutActivity activity;
    PlanetFilter planetFilter;
    public AreaAdapter(Context context,ArrayList<Areas> areas,CheckoutActivity activity){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.areas = areas;
        this.activity = activity;
        this.areas_all = areas;



    }
    @Override
    public int getCount() {
        return areas.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.area_items,null);
        LinearLayout line = (LinearLayout) item_view.findViewById(R.id.line);
        final LinearLayout area_ll = (LinearLayout) item_view.findViewById(R.id.area_ll);
        final TextView area_title = (TextView) item_view.findViewById(R.id.area_title);
        Log.e("areassize", String.valueOf(areas.size()));

        area_title.setText(areas.get(i).title);
        Log.e("area_title",area_title.getText().toString());



        area_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areas.get(i).type.equals("0")) {

                } else {
                    Log.e("title", area_title.getText().toString());
                    Log.e("id", areas.get(i).id);
                    activity.popup_view.setVisibility(View.GONE);
                    activity.drop_popup_view.setVisibility(View.GONE);
                    activity.area.setText(area_title.getText().toString());
                    activity.drop_area.setText(area_title.getText().toString());
                    activity.area_id = areas.get(i).id;
                    activity.drop_area_id = areas.get(i).id;
                    Log.e("drop_areaid", activity.drop_area_id);

                }
            }
        });

        if(areas.get(i).type.equals("0")) {
            area_title.setBackgroundColor(context.getResources().getColor(R.color.line));
            area_title.setHeight((int) context.getResources().getDimension(R.dimen.activity_horizontal_margin));
            area_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            area_title.setTextColor(context.getResources().getColor(R.color.background));
            line.setVisibility(View.GONE);
        }else{
            area_title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }



        return item_view;
    }

    @Override
    public Filter getFilter() {
        if(planetFilter==null)
            planetFilter=new PlanetFilter();
        return planetFilter;
    }

    private class PlanetFilter extends Filter {
        Boolean clear_all=false;
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
// We implement here the filter logic
            clear_all=false;
            if (constraint == null || constraint.length() == 0) {
                clear_all=true;
// No filter implemented we return all the list
                results.values = areas;
                results.count = areas.size();
            }
            else {
// We perform filtering operation
                List<Areas> nPlanetList = new ArrayList<>();

                for (Areas p : areas_all) {

//Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();

                    if (Pattern.compile(Pattern.quote(constraint.toString()), Pattern.CASE_INSENSITIVE).matcher(p.title).find())
                        nPlanetList.add(p);
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count == 0) {
//                restaurants = (ArrayList<Restaurants>) results.values;
                notifyDataSetChanged();
            }
            else if(clear_all){

                areas = areas_all;
                notifyDataSetChanged();
            }


            else {
                areas = (ArrayList<Areas>) results.values;
                notifyDataSetChanged();
            }



        }

    }
}

