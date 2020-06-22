package hcmute.edu.vn.foody_17;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Food> mData;

    private AssetManager assetManager;

    public RecyclerViewAdapter(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_food,parent,false);
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

        holder.food_title.setText(mData.get(position).getTitle());
        holder.food_des.setText(mData.get(position).getDescription());
        holder.food_thumb.setImageBitmap(img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("IdFood",mData.get(position).getIdFood());
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Category",mData.get(position).getCategory());
                intent.putExtra("Thumb",mData.get(position).getThumbnail());
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
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView food_title, food_des;
        ImageView food_thumb;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_title = (TextView) itemView.findViewById(R.id.food_title_id);
            food_thumb = (ImageView) itemView.findViewById(R.id.food_img_id);
            food_des = (TextView) itemView.findViewById(R.id.food_des_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}