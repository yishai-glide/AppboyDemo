package me.glide.appboydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appboy.Appboy;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;


public class MainActivity extends ActionBarActivity implements IEventSubscriber<FeedUpdatedEvent> {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Appboy.getInstance(this).openSession(this)) {
            Appboy.getInstance(this).requestFeedRefresh();
        }
    }

    @Override
    protected void onStart() {
        MyApplication.isVisible = true;
        super.onStart();
    }

    @Override
    protected void onStop() {
        MyApplication.isVisible = false;
        Appboy.getInstance(this).closeSession(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void trigger(FeedUpdatedEvent feedUpdatedEvent) {
        Log.d(TAG, "got a feedback event: " + feedUpdatedEvent.toString());
        Toast.makeText(this, "YAY GOT AN EVET!", Toast.LENGTH_LONG).show();
    }
}
