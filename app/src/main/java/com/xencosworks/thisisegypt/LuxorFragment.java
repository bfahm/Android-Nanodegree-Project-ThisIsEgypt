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
public class LuxorFragment extends Fragment {

    private static final String TAG = "AlexFragment";

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;


    public LuxorFragment() {
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
        mPlaces.add(new Place(getString(R.string.luxor_title_kings), 4.7, R.drawable.cover_luxor_kings,
                getString(R.string.luxor_subtitle_kings),
                new MapsLocation("25.7399321","32.5995552"),
                "https://en.wikipedia.org/wiki/Valley_of_the_Kings",
                "https://www.google.com/search?tbm=isch&q=Valley of the Kings"));

        mPlaces.add(new Place(getString(R.string.luxor_title_karnak), 4.7, R.drawable.cover_luxor_karnak,
                getString(R.string.luxor_subtitle_karnak),
                new MapsLocation("25.7184963,","32.6564764"),
                "https://en.wikipedia.org/wiki/Karnak",
                "https://www.google.com/search?tbm=isch&q=Karnak"));

        mPlaces.add(new Place(getString(R.string.luxor_title_temple), 4.7, R.drawable.cover_luxor_temple,
                getString(R.string.luxor_subtitle_temple),
                new MapsLocation("25.6990476","32.6382892"),
                "https://en.wikipedia.org/wiki/Luxor_Temple",
                "https://www.google.com/search?tbm=isch&q=Luxor Temple"));

        mPlaces.add(new Place(getString(R.string.luxor_title_queens), 4.5, R.drawable.cover_luxor_queens,
                getString(R.string.luxor_subtitle_queens),
                new MapsLocation("25.7284386","32.5908941"),
                "https://en.wikipedia.org/wiki/Valley_of_the_Queens",
                "https://www.google.com/search?tbm=isch&q=Valley of the Queens"));
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
