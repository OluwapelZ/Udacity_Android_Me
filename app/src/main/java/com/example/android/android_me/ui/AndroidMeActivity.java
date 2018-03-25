/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {
    public int headIndex, bodyIndex, legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        Bundle bundle = new Bundle();
        headIndex = bundle.getInt("headIndex");
        bodyIndex = bundle.getInt("bodyIndex");
        legIndex = bundle.getInt("legIndex");

        if(savedInstanceState == null) {

            BodyPartFragment headFragment = new BodyPartFragment();
            BodyFragment bodyFragment = new BodyFragment();
            LegFragment legFragment = new LegFragment();

            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setImageIndex(headIndex);

            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setImageIndex(bodyIndex);

            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setImageIndex(legIndex);

            //Use fragment manager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            //Fragment Transaction
            fragmentManager.beginTransaction()
                    .add(R.id.header_container, headFragment)
                    .commit();

            //Fragment Transaction
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //Fragment Transaction
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
