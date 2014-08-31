package com.github.stephanenicolas.injectsexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.github.stephanenicolas.injectresource.InjectResource;
import com.github.stephanenicolas.injectview.ContentView;
import com.github.stephanenicolas.injectview.InjectFragment;
import com.github.stephanenicolas.injectview.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

  @InjectFragment(R.id.container)
  private PlaceholderFragment fragment;

  @InjectView(R.id.textView)
  private TextView textView;
  @InjectView(R.id.button)
  private Button button;

  @InjectResource(R.string.app_name)
  private String appName;

  @InjectResource(R.string.hello_world)
  private String helloWorld;

  public MainActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.button = (Button) findViewById(R.id.button);
    Log.d("DEBUG", "Before button");
    button.setOnClickListener( new View.OnClickListener() {
      @Override public void onClick(View view) {
        Fragment fragmentInner = getSupportFragmentManager().findFragmentById(R.id.containerInner);
        if (fragmentInner == null) {
          textView.setText(appName);
          getSupportFragmentManager().beginTransaction()
            .add(R.id.containerInner, new PlaceholderFragment())
            .commit();

        } else {
          textView.setText(helloWorld);
          getSupportFragmentManager().beginTransaction()
              .remove(fragmentInner)
              .commit();
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }
}
