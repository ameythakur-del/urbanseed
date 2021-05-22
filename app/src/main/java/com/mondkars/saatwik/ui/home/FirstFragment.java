package com.mondkars.saatwik.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mondkars.saatwik.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import model.Image;
import model.Item;
import ui.ItemRecyclerAdapter;
import ui.SliderAdapterExample;

public class FirstFragment extends Fragment {
    public List<Item> itemList;
    public RecyclerView recyclerView;
    public ItemRecyclerAdapter itemRecyclerAdapter;
    public SliderView horizontal;
    public List<Image> snacksList;
    private ProgressBar progressBar;
    DatabaseReference reference, reference2, reference3;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("Stop");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first_fragment, container, false);
            itemList = new ArrayList<>();
            snacksList = new ArrayList<>();

            progressBar = view.findViewById(R.id.progressBar3);
            progressBar.setVisibility(View.VISIBLE);

            horizontal = view.findViewById(R.id.horizontal_view);
            recyclerView = view.findViewById(R.id.view1);

            reference2 = FirebaseDatabase.getInstance().getReference().child("Snacks");
            reference = FirebaseDatabase.getInstance().getReference().child("items");
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        reference3 = FirebaseDatabase.getInstance().getReference().child("Off Tag");
            final LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue().toString().equals("True")) {
                        {
                            {
                                {
                                    reference3.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            snacksList = new ArrayList<Image>();
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                {
                                                    final Image image = dataSnapshot1.getValue(Image.class);
                                                    {
                                                        snacksList.add(image);
                                                    }
                                                }
                                            }
                                            SliderAdapterExample sliderAdapterExample = new SliderAdapterExample(getActivity(), snacksList);
                                            horizontal.setSliderAdapter(sliderAdapterExample);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                }
                            }
                        }
                    } else {
                        {
                            {
                                {
                                    reference2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            snacksList = new ArrayList<Image>();
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                {
                                                    final Image image = dataSnapshot1.getValue(Image.class);
                                                    {
                                                        snacksList.add(image);
                                                    }
                                                }
                                            }
                                            SliderAdapterExample sliderAdapterExample = new SliderAdapterExample(getActivity(), snacksList);
                                            horizontal.setSliderAdapter(sliderAdapterExample);
                                            sliderAdapterExample.notifyDataSetChanged();
                                            horizontal.startAutoCycle();
                                            horizontal.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                                            horizontal.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
                                            horizontal.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                }
                            }
                        }
                    }
                        {
                            {
                                {
                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            itemList = new ArrayList<Item>();
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                {
                                                    Item ameya = dataSnapshot1.getValue(Item.class);
                                                    if (dataSnapshot1.child("category").exists()) {
                                                        if (ameya.getCategory().equals("Snacks") && ameya.getVisibility()) {
                                                            itemList.add(ameya);
                                                        }
                                                    }
                                                }
                                            }
                                            itemRecyclerAdapter = new ItemRecyclerAdapter(getActivity(), itemList);
                                            progressBar.setVisibility(View.INVISIBLE);
                                            recyclerView.setAdapter(itemRecyclerAdapter);
                                            itemRecyclerAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return view;
        }
}