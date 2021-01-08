package com.adnan.lesson7fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SweetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SweetFragment extends Fragment {
    private RecyclerView  recyclerView1;
    //ArrayList<Sweet> sweetList=new ArrayList<Sweet>();
    private static final String TAG = "SweetFragment";
    FoodFireBaseAdapter foodAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SweetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SweetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SweetFragment newInstance(String param1, String param2) {
        SweetFragment fragment = new SweetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sweet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView=view.findViewById(R.id.fragment_sweet_rv_sweet_list);
//       sweetAdapter=new SweetAdapter(sweetList,getContext(), recyclerViewClick);
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//        recyclerView.setAdapter(sweetAdapter);


        setUpRecyclerView(view);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.fragment_food_floatbtn_addsweet);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(),NewFoodActivity.class));
            }
        });
    }

    private void setUpRecyclerView(View view) {
        Query query = FirebaseFirestore.getInstance()
                .collection("foodMenu");
        Query query1= query.whereEqualTo("foodType","Sweet");

        FirestoreRecyclerOptions<Food> options = new FirestoreRecyclerOptions.Builder<Food>()
                .setQuery(query1, Food.class)
                .build();
        foodAdapter=new FoodFireBaseAdapter(options);

        recyclerView1= view.findViewById(R.id.fragment_sweet_rv_sweet_list);


        recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView1.setAdapter(foodAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                foodAdapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView1);

        foodAdapter.setOnItemClickListner(new FoodFireBaseAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Food food=documentSnapshot.toObject(Food.class);

                String id=documentSnapshot.getId();
                Toast.makeText(requireContext(),"position: " +position +"Id:" +id , Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(),"position: " +food.getFoodName()+"Id:" +food.getFoodDescription(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(requireContext(),FoodActivity.class);
                intent.putExtra("food", food);
                requireContext().startActivity(intent);

            }
        });

    }


    RecyclerViewClick recyclerViewClick= new RecyclerViewClick() {
        @Override
        public void onItemClick(int position) {
            Intent intent = new Intent(requireContext(), SweetActivity.class);
           //  intent.putExtra("sweet",
                  //  sweetList.get(position));
          //  requireContext().startActivity(intent);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        foodAdapter.startListening();
        Log.i(TAG, "onStart: hello " );


    }

    @Override
    public void onStop() {
        super.onStop();
        foodAdapter.stopListening();
    }
}