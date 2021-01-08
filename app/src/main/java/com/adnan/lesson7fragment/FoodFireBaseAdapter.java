package com.adnan.lesson7fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

public class FoodFireBaseAdapter extends FirestoreRecyclerAdapter<Food, FoodFireBaseAdapter.FoodHolder> {
    private static final String TAG = "FoodFireBaseAdapter";
    private  OnItemClickListner listner;
    FirestoreRecyclerOptions<Food> options;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public FoodFireBaseAdapter(@NonNull FirestoreRecyclerOptions<Food> options) {
        super(options);
       this.options=options;
       // this.options=options;
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodHolder holder, int position, @NonNull Food model) {

        Food f=new Food();
     //  f= options.getSnapshots().getSnapshot(position).getData().get("first").toString();
//        Log.i(TAG, "setUpRecyclerView  constructor: "+ options.getSnapshots().getSnapshot(position).getData().get("first"));
//        Log.i(TAG, "setUpRecyclerView  position: "+ position);
//        Log.i(TAG, "setUpRecyclerView  position: "+ model.getFoodDescription());
//        Log.i(TAG, "setUpRecyclerView  position: "+ model.getFoodName());
//        Log.i(TAG, "setUpRecyclerView  position: "+ model.getFoodImage());

        holder.textView.setText(model.getFoodName());
      // holder.textView1.setText(model.getFoodDescription());
      // holder.textView1.setText(String.valueOf(model.getPrice()));
       // holder.imageView.getContext().

        Picasso.get().load(model.getFoodImage()).into(holder.imageView);


    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem,parent,false);
        return new FoodHolder(v);
    }

   public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    class FoodHolder extends RecyclerView.ViewHolder{
TextView textView,textView1,textView2;
ImageView imageView;
        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.fooditem_tv_foodName);
          //  textView1=itemView.findViewById(R.id.fooditem_tv_fooddescription);
           // textView2=itemView.findViewById(R.id.fooditem_tv_foodPrice);
           imageView=itemView.findViewById(R.id.fooditem_im_foodImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(position !=RecyclerView.NO_POSITION && listner !=null){
                        listner.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListner{

        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
    this.listner=listner;

    }
}
