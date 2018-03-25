package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by oluwapelumi.olaoye on 3/24/18.
 */

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallback;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    //Override onAttach to make sure that the activity has implemented the callback interface
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //To make sure the host Activity has implemented OnImageClickListener interface
        //Else, throw an exception
        try{
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnImageClickListener");
        }
    }

    public MasterListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.image_grid_view);

        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //Set adapter on GridView
        gridView.setAdapter(masterListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallback.onImageSelected(position);
            }
        });

        return view;
    }
}
