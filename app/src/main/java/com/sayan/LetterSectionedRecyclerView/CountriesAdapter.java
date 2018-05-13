package com.sayan.LetterSectionedRecyclerView;

/**
 * Created by Sayan Manna on 13-07-2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class CountriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SECTION_VIEW = 0;
    public static final int CONTENT_VIEW = 1;

    ArrayList<CountriesModel> mCountriesModelList;
    WeakReference<Context> mContextWeakReference;

    public CountriesAdapter(ArrayList<CountriesModel> mCountriesModelList, Context context) {

        this.mCountriesModelList = mCountriesModelList;
        this.mContextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = mContextWeakReference.get();
        if (viewType == SECTION_VIEW) {
            return new SectionHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_title, parent, false));
        }
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name, parent, false), context);
    }

    @Override
    public int getItemViewType(int position) {
        if (mCountriesModelList.get(position).isSection) {
            return SECTION_VIEW;
        } else {
            return CONTENT_VIEW;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final Context context = mContextWeakReference.get();
        if (context == null) {
            return;
        }
        if (SECTION_VIEW == getItemViewType(position)) {

            SectionHeaderViewHolder sectionHeaderViewHolder = (SectionHeaderViewHolder) holder;
            CountriesModel sectionItem = mCountriesModelList.get(position);
            sectionHeaderViewHolder.headerTitleTextview.setText(sectionItem.name);
            return;
        }

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final CountriesModel currentUser = ((CountriesModel) mCountriesModelList.get(position));
        itemViewHolder.nameTextview.setText(currentUser.name);
        itemViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+currentUser.name, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return mCountriesModelList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextview;
public LinearLayout linearLayout;
        public ItemViewHolder(View itemView, final Context context) {
            super(itemView);
            nameTextview = (TextView) itemView.findViewById(R.id.nameTextview);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.ll_country);
        }
    }

    public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitleTextview;

        public SectionHeaderViewHolder(View itemView) {
            super(itemView);
            headerTitleTextview = (TextView) itemView.findViewById(R.id.headerTitleTextview);
        }
    }
}