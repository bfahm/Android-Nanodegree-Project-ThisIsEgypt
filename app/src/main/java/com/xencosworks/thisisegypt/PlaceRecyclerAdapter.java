package com.xencosworks.thisisegypt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

/**
 * Created by Bola on 2/3/2019.
 */

public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceRecyclerAdapter.ViewHolder> {
    private static final String TAG = "PlaceRecyclerAdapter";

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mListener; //create an instance of the interface created just below

    public interface OnItemClickListener{
        // an interface that has all custom listener functions you need
        // when created (in setOnClickListener) you should override those functions (in caller activity)
        void onItemClick(int position);

        void onLikeClick(int position);

        void onLocationClick(int position);
        void onMoreInfoClick(int position);
        void onMoreImgsClick(int position);
    }
    public void setOnClickListener(OnItemClickListener listener){
        // the main function that is called on the CairoActivity to initialize
        // the interface mListener
        mListener = listener;
    }

    public PlaceRecyclerAdapter(Context context, ArrayList<Place> places) {
        this.mPlaces = places;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // used for the job of view recycling
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // customize views in list_item here
        holder.placeImage.setImageResource(mPlaces.get(position).getCoverPic());
        holder.placeTitle.setText(mPlaces.get(position).getStrings()[0]);
        holder.placeRating.setRating((float)mPlaces.get(position).getRating());
        holder.placeDetails.setText(mPlaces.get(position).getStrings()[1]);

        boolean isLiked = mPlaces.get(position).isLiked();
        if(isLiked){
            holder.placeLikeStatus.setImageResource(R.mipmap.ic_item_fav_true);
        }else {
            holder.placeLikeStatus.setImageResource(R.mipmap.ic_item_fav_false);
        }

    }

    @Override
    public int getItemCount() {
        // mandatory for viewing all number items (if return 0 --> nothing will show)
        return mPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView parentLayout;

        ImageView placeImage;
        TextView placeTitle;
        SimpleRatingBar placeRating;
        ImageButton placeLikeStatus;
        TextView placeDetails;
        Button placeWikiInfo;
        ImageButton placeGImages;
        ImageButton placeLocation;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.root_layout);

            placeImage = itemView.findViewById(R.id.item_image);
            placeTitle = itemView.findViewById(R.id.item_title);
            placeRating = itemView.findViewById(R.id.item_rating);
            placeLikeStatus = itemView.findViewById(R.id.item_btn_liked);
            placeDetails = itemView.findViewById(R.id.item_details);
            placeWikiInfo = itemView.findViewById(R.id.item_btn_more_info);
            placeGImages = itemView.findViewById(R.id.item_btn_more_imgs);
            placeLocation = itemView.findViewById(R.id.item_btn_location);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            placeLikeStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onLikeClick(position);
                        }
                    }
                }
            });

            placeWikiInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onMoreInfoClick(position);
                        }
                    }
                }
            });

            placeGImages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onMoreImgsClick(position);
                        }
                    }
                }
            });

            placeLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onLocationClick(position);
                        }
                    }
                }
            });
        }
    }
}
