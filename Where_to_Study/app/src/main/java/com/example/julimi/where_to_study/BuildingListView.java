package com.example.julimi.where_to_study;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.julimi.where_to_study.dummy.Model;
import android.provider.Settings.Secure;

public class BuildingListView extends AppCompatActivity  {



    public class BackgroundThread extends Thread {

        public BackgroundThread (){}
        private String android_id = Secure.getString(BuildingListView.this.getContentResolver(),
                Secure.ANDROID_ID);

        @Override
        public void run() {
            while(true){
                //System.out.println("send MAC");

                //System.out.println(android_id);
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {

                }
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryRefinementEnabled(true);
        searchView.setIconifiedByDefault(false);

        //Thread thread = new Thread(this);
        //thread.start();
        BackgroundThread bt=new BackgroundThread();
        bt.start();
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar0);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {

        //    public void onClick(View view) {
        //        Context context = view.getContext();
        //        Intent intent = new Intent(context, RoomListView.class);
                //intent.putExtra(itemDetailFragment.ARG_ITEM_ID, holder.mItem.id);

            //    context.startActivity(intent);
          //  }
        //});
        // Show the Up button in the action bar.


        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }



    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(Model.fakebuildinglist));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final String[] mValues;

        public SimpleItemRecyclerViewAdapter(String[] items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //holder.mItem = mValues[position];
            holder.mNameView.setText(mValues[position] + "                    ");


            //holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        //System.out.println("WAHAHA");
                        //Bundle arguments = new Bundle();
                       // arguments.putString(itemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        //itemDetailFragment fragment = new itemDetailFragment();
                        //fragment.setArguments(arguments);
                        //getSupportFragmentManager().beginTransaction()
                        //        .replace(R.id.item_detail_container, fragment)
                         //       .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, RoomListView.class);
                        System.out.println("WAHAHA");

                        RoomListView.setBN(mValues[position]);
                        //Model.helpToLoad();
                        //intent.putExtra(RoomListView.BUILDING_NAME, mValues[position]);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mNameView;
            //public Model.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mNameView = (TextView) view.findViewById(R.id.id);
            }


        }
    }
}

