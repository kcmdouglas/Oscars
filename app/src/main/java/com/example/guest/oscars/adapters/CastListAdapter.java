package com.example.guest.oscars.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.oscars.R;
import com.example.guest.oscars.activity.MovieDetailActivity;
import com.example.guest.oscars.models.Cast;
import com.example.guest.oscars.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/24/16.
 */
public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.CastViewHolder>{
    private ArrayList<Cast> mCasts = new ArrayList<>();
    private Context mContext;

    public CastListAdapter(Context context, ArrayList<Cast> casts) {
        mContext = context;
        mCasts = casts;
    }

    @Override
    public CastListAdapter.CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        CastViewHolder viewHolder = new CastViewHolder (view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (CastListAdapter.CastViewHolder holder, int position) {
        holder.bindMovie(mCasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.castNameTextView) TextView mCastName;
        @Bind(R.id.castImageView) ImageView mCastImage;
        private Context mContext;

        public CastViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindMovie(Cast cast) {
            final Cast mCast = cast;


            if(cast.getmProfile().equals("https://image.tmdb.org/t/p/originalnull")) {
                Picasso.with(mContext).load(R.drawable.placeholder_poster).fit().into(mCastImage);
            } else {
                Picasso.with(mContext).load(cast.getmProfile()).fit().into(mCastImage);
            }
            mCastName.setText(cast.getmName());

        }

    }

}
