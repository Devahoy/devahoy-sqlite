package com.devahoy.sample.ahoysqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.devahoy.sample.ahoysqlite.model.Friend;
import com.devahoy.sample.ahoysqlite.utils.DBHelper;

import java.util.List;


public class MainActivity extends ListActivity {

    DBHelper mHelper;
    List<String> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHelper = new DBHelper(this);
        friends = mHelper.getFriendList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friends);

        setListAdapter(adapter);

        adapter.notifyDataSetChanged();


        queryFriendList();


    }

    private void queryFriendList() {
        friends = mHelper.getFriendList();

        if (friends.size() == 0) {
            Friend friend = new Friend(1, "John", "Doe", "000-12345678",
                    "john@doe.com", "blah blah");
            mHelper.addFriend(friend);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friends);

        setListAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryFriendList();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent detail = new Intent(this, DetailActivity.class);
        String listName = friends.get(position);
        int index = listName.indexOf(" ");
        String columnId = listName.substring(0, index);

        detail.putExtra(Friend.Column.ID, columnId);
        startActivity(detail);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent addFriend = new Intent(this, AddFriendActivity.class);

            startActivity(addFriend);
            overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
        }
        return super.onOptionsItemSelected(item);
    }

}
