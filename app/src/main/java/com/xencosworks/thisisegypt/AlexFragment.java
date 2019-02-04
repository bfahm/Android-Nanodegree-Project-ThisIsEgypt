package com.xencosworks.thisisegypt;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlexFragment extends Fragment {

    private static final String TAG = "AlexFragment";

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;


    public AlexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_container, container, false);

        initPlaces();
        initRecyclerView(rootView);

        return rootView;
    }

    private void initPlaces(){
        mPlaces.add(new Place(getString(R.string.alex_title_lighth), 4, R.drawable.cover_alex_lighthouse,
                getString(R.string.alex_subtitle_lighth),
                new MapsLocation("31.2123823","29.883176"),
                "https://en.wikipedia.org/wiki/Lighthouse_of_Alexandria",
                "https://www.google.com/search?tbm=isch&q=Lighthouse of Alexandria"));

        mPlaces.add(new Place(getString(R.string.alex_title_citadel), 4.4, R.drawable.cover_alex_citadel,
                getString(R.string.alex_subtitle_citadel),
                new MapsLocation("31.2139341","29.8851622"),
                "https://en.wikipedia.org/wiki/Citadel_of_Qaitbay",
                "https://www.google.com/search?tbm=isch&q=Citadel of Qaitbay"));

        mPlaces.add(new Place(getString(R.string.alex_title_library), 4.7, R.drawable.cover_alex_library,
                getString(R.string.alex_subtitle_library),
                new MapsLocation("31.2088705","29.9070125"),
                "https://en.wikipedia.org/wiki/Library_of_Alexandria",
                "https://www.google.com/search?tbm=isch&q=Bibliotheca Alexandrina"));

        mPlaces.add(new Place(getString(R.string.alex_title_montaza), 4.5, R.drawable.cover_alex_montaza,
                getString(R.string.alex_subtitle_montaza),
                new MapsLocation("31.2885102","30.0154439"),
                "https://en.wikipedia.org/wiki/Montaza_Palace",
                "https://www.google.com/search?tbm=isch&q=Montaza Palace"));
    }

    private void initRecyclerView(View rootView){
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler);
        final PlaceRecyclerAdapter placeRecyclerAdapter = new PlaceRecyclerAdapter(getActivity(), mPlaces);
        recyclerView.setAdapter(placeRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        placeRecyclerAdapter.setOnClickListener(new PlaceRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.v(TAG, "TAPPED alex at pos: "+position);
            }

            @Override
            public void onLikeClick(int position) {
                Log.v(TAG, "liked at pos: "+position);
                boolean currentStatus = mPlaces.get(position).isLiked();
                if(currentStatus){
                    mPlaces.get(position).setLiked(false);
                }else {
                    mPlaces.get(position).setLiked(true);
                }
                placeRecyclerAdapter.notifyItemChanged(position);
            }

            @Override
            public void onLocationClick(int position) {
                Log.v(TAG, "location at pos: "+position);
                String uri = String.format(Locale.ENGLISH, "geo:%s,%s", mPlaces.get(position).getLatitude(), mPlaces.get(position).getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getContext().startActivity(intent);
            }

            @Override
            public void onMoreInfoClick(int position) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlaces.get(position).getLinkWiki()));
                startActivity(browserIntent);
            }
            @Override
            public void onMoreImgsClick(int position) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlaces.get(position).getLinkGImgs()));
                startActivity(browserIntent);
            }
        });
    }

}
