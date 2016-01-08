package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    TextView textView;
    String details;
    public DetailActivityFragment() {
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail2, container, false);
        textView= (TextView) rootView.findViewById(R.id.textView);
        Intent intent= getActivity().getIntent();
        details= intent.getStringExtra("forecast");
        textView.setText(details);



    return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.detail_fragment,menu);

    }

    private Intent createShareForecastIntent(){

        //TODO fix intent to share
        Intent shareIntent= new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,details);
        Log.d("Details",details);

        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == R.id.share){


            android.support.v7.widget.ShareActionProvider shareActionProvider= (android.support.v7.widget.ShareActionProvider) MenuItemCompat
                    .getActionProvider(item);

            if (shareActionProvider != null){
                shareActionProvider.setShareIntent(createShareForecastIntent());
            } else{
                Log.e("Invalid Intent","Cannot Share Forecast");
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
