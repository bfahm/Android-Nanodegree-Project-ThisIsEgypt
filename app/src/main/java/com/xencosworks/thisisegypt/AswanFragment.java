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
public class AswanFragment extends Fragment {

    private static final String TAG = "AlexFragment";

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;


    public AswanFragment() {
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
        mPlaces.add(new Place(getString(R.string.aswan_title_museum), 4.5, R.drawable.cover_aswan_museum,
                getString(R.string.aswan_subtitle_museum),
                new MapsLocation("24.0794249","32.8869862"),
                "https://en.wikipedia.org/wiki/Nubian_Museum",
                "https://www.google.com/search?tbm=isch&q=Nubian Museums"));

        mPlaces.add(new Place(getString(R.string.aswan_title_eleph), 4.6, R.drawable.cover_aswan_eleph,
                getString(R.string.aswan_subtitle_eleph),
                new MapsLocation("24.0833324","32.8745782"),
                "https://en.wikipedia.org/wiki/Elephantine",
                "https://www.google.com/search?tbm=isch&q=Elephantine"));
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
