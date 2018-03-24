package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by oluwapelumi.olaoye on 3/24/18.
 */

public class BodyPartFragment extends Fragment {

    public BodyPartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, parent, false);

        //Reference to the image view in fragment layout.
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image);

        //Set image resource
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        //Return root view
        return rootView;
    }
}
