package hcmute.edu.vn.foody_17;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterSearch extends RecyclerView.Adapter<RecyclerViewAdapterSearch.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Food> mData;
    private List<Food> mDataFilter;

    public RecyclerViewAdapterSearch(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFilter = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Bitmap img = null;
        AssetManagers assetManagers = new AssetManagers();
        try {
            img = assetManagers.getBitmap(mData.get(position).getThumbnail(),mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }


        holder.food_title.setText(mDataFilter.get(position).getTitle());
        holder.address.setText(mDataFilter.get(position).getAddress());
        holder.type_Store.setText(mDataFilter.get(position).getTypeStore());
        holder.food_thumb.setImageBitmap(img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("IdFood",mData.get(position).getIdFood());
                intent.putExtra("Title",mDataFilter.get(position).getTitle());
                intent.putExtra("Address",mDataFilter.get(position).getAddress());
                intent.putExtra("Province",mDataFilter.get(position).getProvince());
                intent.putExtra("Thumb",mDataFilter.get(position).getThumbnail());
                intent.putExtra("Address",mData.get(position).getAddress());
                intent.putExtra("Price",mData.get(position).getPrice());
                intent.putExtra("Lat",mData.get(position).getLatiTude());
                intent.putExtra("Long",mData.get(position).getLongiTude());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return mData.size();
        return mDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if(Key.isEmpty()){
                    mDataFilter = mData;
                }
                else{
                    List<Food> lstFiltered = new ArrayList<>();
                    for(Food row : mData){
                        if(row.getTitle().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }
                    }

                    mDataFilter = lstFiltered;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFilter;
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFilter = (List<Food>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView food_title, address, type_Store;
        ImageView food_thumb;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_title = (TextView) itemView.findViewById(R.id.txt_Name);
            address = (TextView) itemView.findViewById(R.id.txt_address);
            type_Store = (TextView) itemView.findViewById(R.id.txt_typeStore);
            food_thumb = (ImageView) itemView.findViewById(R.id.imgv_food);
            cardView = (CardView) itemView.findViewById(R.id.cardview_idss);

        }
    }
}
