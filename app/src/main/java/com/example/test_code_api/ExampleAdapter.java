package com.example.test_code_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.exampleViewHolder> {
    private ArrayList<ExampleItem> mExamplelist;

    private Context mContext ;
    private OnItemClickListener mListener;

    public ExampleAdapter(ArrayList<ExampleItem> mExamplelist){ this.mExamplelist = mExamplelist; }

    public void setFilteredList(ArrayList<ExampleItem> filteredList){
        this.mExamplelist = filteredList;
        notifyDataSetChanged();
    }


   public static class ExampleViewHolder extends RecyclerView.ViewHolder{
       public ImageView mImageView;

       public ExampleViewHolder(@NonNull View itemView) {
           super(itemView);
       }
   }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener ;
    }
    public ExampleAdapter(Context context, ArrayList<ExampleItem> examplelist){
        mContext =context;
        mExamplelist = examplelist;
    }


    @NonNull
    @Override
    public exampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new exampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull exampleViewHolder holder, int position) {
        ExampleItem currentItem = mExamplelist.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        double likecount = currentItem.getLikeCount();
        String date = currentItem.getDate();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("notes:"+ likecount);
        holder.mTextViewDate.setText("date:"+date);

        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

     public class exampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView ;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes ;
        public TextView mTextViewDate ;


        public exampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
            mTextViewDate = itemView.findViewById(R.id.text_view_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);

                        }
                    }
                }
            });


        }
    }

}
