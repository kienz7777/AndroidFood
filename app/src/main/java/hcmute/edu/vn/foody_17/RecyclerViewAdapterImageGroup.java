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

public class RecyclerViewAdapterImageGroup extends RecyclerView.Adapter<RecyclerViewAdapterImageGroup.MyViewHolder> {


    private Context mContext;
    private List<MenuGroup> mData;


    public RecyclerViewAdapterImageGroup(Context mContext, List<MenuGroup> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_image,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Bitmap img = null;
        AssetManagers assetManagers = new AssetManagers();
        try {
            img = assetManagers.getBitmap(mData.get(position).getImage(),mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.title_id.setText(mData.get(position).getNameGroup());
        holder.img_id.setImageBitmap(img);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,DetailActivity.class);
//                intent.putExtra("IdFood",mData.get(position).getIdFood());
//                intent.putExtra("Title",mData.get(position).getTitle());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Category",mData.get(position).getCategory());
//                intent.putExtra("Thumb",mData.get(position).getThumbnail());
//                intent.putExtra("Address",mData.get(position).getAddress());
//                intent.putExtra("Price",mData.get(position).getPrice());
//                intent.putExtra("Lat",mData.get(position).getLatiTude());
//                intent.putExtra("Long",mData.get(position).getLongiTude());
//                mContext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title_id;
        ImageView img_id;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_id = (TextView) itemView.findViewById(R.id.title_id);
            img_id = (ImageView) itemView.findViewById(R.id.img_id);



        }
    }
}