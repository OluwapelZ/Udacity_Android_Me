package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by oluwapelumi.olaoye on 3/24/18.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    public int headIndex, bodyIndex, legIndex;
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {

            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.image_grid_view);
            gridView.setNumColumns(2);

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
        } else {
            mTwoPane = false;
        }

    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;

        int indexList = position - (12 * bodyPartNumber);

        if (mTwoPane) {
            BodyPartFragment newFragment = new BodyPartFragment();
            BodyFragment bodyFragment = new BodyFragment();
            LegFragment legFragment = new LegFragment();

            switch (bodyPartNumber) {
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setImageIndex(indexList);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.header_container, newFragment)
                            .commit();
                    break;
                case 1:
                    bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyFragment.setImageIndex(indexList);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyFragment)
                            .commit();
                    break;
                case 2:
                    legFragment.setImageIds(AndroidImageAssets.getLegs());
                    legFragment.setImageIndex(indexList);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, legFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {

            switch (bodyPartNumber) {
                case 0:
                    headIndex = indexList;
                    break;
                case 1:
                    bodyIndex = indexList;
                    break;
                case 2:
                    legIndex = indexList;
                    break;
                default:
                    break;
            }

        }

        Bundle bundle = new Bundle();
        bundle.putInt("headIndex", headIndex);
        bundle.putInt("bodyIndex", bodyIndex);
        bundle.putInt("legIndex", legIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtra("bundle", bundle);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}