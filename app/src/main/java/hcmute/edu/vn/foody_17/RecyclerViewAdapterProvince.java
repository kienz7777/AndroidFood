package hcmute.edu.vn.foody_17;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterProvince extends RecyclerView.Adapter<RecyclerViewAdapterProvince.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Province> mData;
    private List<Province> mDataFilter;



    public RecyclerViewAdapterProvince(Context mContext, List<Province> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFilter = mData;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_province,parent,false);
        return new MyViewHolder(view);
    }


    final Boolean[] flag = {false};
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.txt_provinceItem.setText(mDataFilter.get(position).getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean value = holder.simpleCheckedTextView.isChecked();

                if (value == true && flag[0] == true) {
                    // set check mark drawable and set checked property to false

                    holder.simpleCheckedTextView.setCheckMarkDrawable(null);
                    holder.txt_provinceItem.setTextColor(Color.parseColor("#000000"));
                    holder.simpleCheckedTextView.setChecked(false);
                    flag[0] = false;
                    //Toast.makeText(mContext, "un-Checked", Toast.LENGTH_LONG).show();
                } else if(value == false && flag[0] == false) {
                    // set check mark drawable and set checked property to true

                    holder.simpleCheckedTextView.setCheckMarkDrawable(R.drawable.tick);
                    holder.txt_provinceItem.setTextColor(Color.parseColor("#3498db"));
                    holder.simpleCheckedTextView.setChecked(true);
                    flag[0] = true;

                    //Lưu giá trị province
                    SharedPreferences sharedPref = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("data", mDataFilter.get(position).getName());
                    editor.commit();
                }


            }
        });

    }
    @Override
    public int getItemCount() {
//        return mData.size();
        return mDataFilter.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                List<Province> lstFiltered = new ArrayList<>();

                if(Key.isEmpty()){
                    mDataFilter = mData;
                }
                else{

                    String key = constraint.toString().toLowerCase().trim();

                    for(Province row : mData){

                        if(row.getName().toLowerCase().contains(key)){

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

                mDataFilter = ((List<Province>) results.values);
                notifyDataSetChanged();
            }
        };



//        return new Filter(){
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint){
//                List<Province> lstFiltered = new ArrayList<>();
//
//                if (constraint == null || constraint.length() == 0){
//
//                    lstFiltered.addAll(mData);
//                }else {
//                    String key = constraint.toString().toLowerCase().trim();
//
//                    for(Province row : mData){
//                        if(row.getName().toLowerCase().contains(key)){
//                            lstFiltered.add(row);
//                        }
//                    }
//
//                }
//                FilterResults results = new FilterResults();
//                results.values = lstFiltered;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results){
//                mDataFilter.clear();
//                mDataFilter.addAll((List<Province>) results.values);
//                notifyDataSetChanged();
//            }
//        };
    }


    //implements View.OnClickListener
    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView txt_provinceItem;
        CheckedTextView simpleCheckedTextView;
        CardView cardView;
        public boolean flag = true;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_provinceItem = (TextView) itemView.findViewById(R.id.txt_provinceItem);
            simpleCheckedTextView = (CheckedTextView) itemView.findViewById(R.id.checked_Tick);
            cardView = (CardView) itemView.findViewById(R.id.cardview);

        }


    }


}
