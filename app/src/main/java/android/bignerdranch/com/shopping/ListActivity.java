package android.bignerdranch.com.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fm= getSupportFragmentManager();
        Fragment fragmentList= fm.findFragmentById(R.id.container_list);
        if (fragmentList == null) {
            fragmentList= new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_list, fragmentList)
                    .commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
