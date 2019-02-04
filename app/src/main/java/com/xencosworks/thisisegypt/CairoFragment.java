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
public class CairoFragment extends Fragment {

    private static final String TAG = "CairoFragment";

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;

    public CairoFragment() {
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
        mPlaces.add(new Place(getString(R.string.cairo_title_giza), 4.5, R.drawable.cover_cairo_pyramids,
                getString(R.string.cairo_subtitle_giza),
                new MapsLocation("29.9771752","31.1315695"),
                "https://en.wikipedia.org/wiki/Giza_pyramid_complex",
                "https://www.google.com/search?tbm=isch&q=Giza Necropolis"));

        mPlaces.add(new Place(getString(R.string.cairo_title_museum), 4.5, R.drawable.cover_cairo_museum,
                getString(R.string.cairo_subtitle_museum),
                new MapsLocation("30.0482666","31.2318007"),
                "https://en.wikipedia.org/wiki/Egyptian_Museum",
                "https://www.google.com/search?tbm=isch&q=Egyptian Museum"));

        mPlaces.add(new Place(getString(R.string.cairo_title_tower), 4.2, R.drawable.cover_cairo_tower,
                getString(R.string.cairo_subtitle_tower),
                new MapsLocation("30.0481201","31.2120477"),
                "https://en.wikipedia.org/wiki/Cairo_Tower",
                "https://www.google.com/search?tbm=isch&q=Cairo Tower"));

        mPlaces.add(new Place(getString(R.string.cairo_title_church), 4.6, R.drawable.cover_cairo_church,
                getString(R.string.cairo_subtitle_church),
                new MapsLocation("30.0101831","31.220698"),
                "https://en.wikipedia.org/wiki/The_Hanging_Church",
                "https://www.google.com/search?tbm=isch&q=The Hanging Church"));
    }

    private void initRecyclerView(View rootView){
        final RecyclerView recyclerView = rootView.findViewById(R.id.recycler);
        final PlaceRecyclerAdapter placeRecyclerAdapter = new PlaceRecyclerAdapter(getActivity(), mPlaces);
        recyclerView.setAdapter(placeRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        placeRecyclerAdapter.setOnClickListener(new PlaceRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.v(TAG, "TAPPED cairo at pos: "+position);
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
