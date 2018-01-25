package com.project.touristguide;

import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.touristguide.gps.GPS;
import com.project.touristguide.apidata.Place_list_Activity;

import java.util.ArrayList;

/**
 * Created by nbpat on 10/30/2016.
 */

public class CategoryFragment extends Fragment {
    ListView list;
    ArrayList<Item> item = new ArrayList<Item>();
    CategoryAdaptor ca;


    public static String CATEGORY = "category";
    public static String CITY = "city";
    public static String STATE = "state";
    public static String LOCATION = "location";
    public static String LONGITUDE = "longitude";
    static String LATITUDE = "latitude";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        item.add(new Item("Restaurant", "Find best restaurant near to you...", R.drawable.restaurant));
        item.add(new Item("Parks", "Find parks near to you...", R.drawable.camping));
        item.add(new Item("Museum", "Find Museum near to you...", R.drawable.museum));
        item.add(new Item("Tourist Attraction", "Find Tourist Attraction near to you...", R.drawable.tourist));
        item.add(new Item("Night Life", "Find Night Life  near to you...", R.drawable.nightlife));
        item.add(new Item("Hotels", "Find Hotels  near to you...", R.drawable.hotel));
        item.add(new Item("Shopping", "Find Shopping places near to you...", R.drawable.shopping));


        View view = inflater.inflate(R.layout.category_list, container, false);
        list = (ListView) view.findViewById(R.id.categoryList);
        ca = new CategoryAdaptor(getActivity().getApplicationContext(), R.layout.category_list_item, item);
        list.setAdapter(ca);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                GPS gps = new GPS(getActivity().getApplication().getApplicationContext());
                Location gpsLocation = null;
                gpsLocation = gps.getMyLocation();


                if (gpsLocation == null) {
                    Log.d("weatehr", "LOC:" + false);
                    gpsLocation.setLongitude(-87.623177);
                    gpsLocation.setLatitude(41.881832);
                }

                Intent intent = new Intent(getActivity().getApplicationContext(), Place_list_Activity.class);
                intent.putExtra(LOCATION, gpsLocation);
                intent.putExtra(LONGITUDE, gpsLocation.getLongitude());
                intent.putExtra(LATITUDE, gpsLocation.getLatitude());
                switch (position) {
                    case 0:
                        intent.putExtra(CATEGORY, "Restaurant");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;
                    case 1:
                        intent.putExtra(CATEGORY, "Parks");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;
                    case 2:
                        intent.putExtra(CATEGORY, "Museum");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;
                    case 3:
                        intent.putExtra(CATEGORY, "Tourist Attraction");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;
                    case 4:
                        intent.putExtra(CATEGORY, "Night Life");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;
                    case 5:
                        intent.putExtra(CATEGORY, "Hotel");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;

                    case 6:
                        intent.putExtra(CATEGORY, "Shopping");
                        intent.putExtra(CITY, CurrentLocationActivity.myCity);

                        break;
                }
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.zoom_in);


            }
        });

        return view;
    }


    public class Item {
        String title;
        String tagLine;
        int img;

        public Item(String title, String tagLine, int img) {
            this.title = title;
            this.tagLine = tagLine;
            this.img = img;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }


        public String getTagLine() {
            return tagLine;
        }

        public void setTagLine(String tagLine) {
            this.tagLine = tagLine;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
