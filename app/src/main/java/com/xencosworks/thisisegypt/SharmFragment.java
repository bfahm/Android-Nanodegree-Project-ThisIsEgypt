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
public class SharmFragment extends Fragment {

    private static final String TAG = "AlexFragment";

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;


    public SharmFragment() {
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
        mPlaces.add(new Place(getString(R.string.shram_title_bay), 4.4, R.drawable.cover_sharm_bay,
                getString(R.string.shram_subtitle_bay),
                new MapsLocation("27.9103183","34.3288344"),
                "https://en.wikipedia.org/wiki/Naama_Bay",
                "https://www.google.com/search?tbm=isch&q=Naama Bay"));

        mPlaces.add(new Place(getString(R.string.shram_title_rasm), 4.7, R.drawable.cover_sharm_rasm,
                getString(R.string.shram_subtitle_rasm),
                new MapsLocation("27.7420043","34.2325856"),
                "https://en.wikipedia.org/wiki/Ras_Muhammad_National_Park",
                "https://www.google.com/search?tbm=isch&q=Ras Muhammad National Park"));

        mPlaces.add(new Place(getString(R.string.shram_title_tiran), 4.2, R.drawable.cover_sharm_tiran,
                getString(R.string.shram_subtitle_tiran),
                new MapsLocation("27.962853","34.4889022"),
                "https://en.wikipedia.org/wiki/Tiran_Island",
                "https://www.google.com/search?tbm=isch&q=Tiran Island"));

        mPlaces.add(new Place(getString(R.string.shram_title_bhole), 4.6, R.drawable.cover_sharm_bhole,
                getString(R.string.shram_subtitle_bhole),
                new MapsLocation("28.5722676","34.535122"),
                "https://en.wikipedia.org/wiki/Blue_Hole_(Red_Sea)",
                "https://www.google.com/search?tbm=isch&q=Blue Hole Dahab"));
    }

    private void initRecyclerView(View rootView){
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler);
        final PlaceRecyclerAdapter placeRecyclerAdapter = new PlaceRecyclerAdapter(getActivity(), mPlaces);
        recyclerView.setAdapter(placeRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        placeRecyclerAdapter.setOnClickListener(new PlaceRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.v(TAG, "TAPPED luxasw at pos: "+position);
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
