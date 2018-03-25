package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oluwapelumi.olaoye on 3/24/18.
 */

public class BodyPartFragment extends Fragment {

    private List<Integer> mImageIds;
    private int mListIndex;
    private String TAG = "ImageId lists is null";
    private String IMAGE_ID_LIST = "imageList";
    private String IMAGE_ID = "image";

    public BodyPartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        if(savedInstanceState != null) {
            mListIndex = savedInstanceState.getInt(IMAGE_ID);
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, parent, false);

        //Reference to the image view in fragment layout.
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image);

        //Set image resource
        if(mImageIds != null) {
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListIndex < mImageIds.size() - 1) {
                        mListIndex++;
                    } else {
                        mListIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else {
            Log.v(TAG, "This fragment has a null list of image id's");
        }

        //Return root view
        return rootView;
    }

    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setImageIndex(int imageIndex) {
        mListIndex = imageIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(IMAGE_ID, mListIndex);
    }
}
