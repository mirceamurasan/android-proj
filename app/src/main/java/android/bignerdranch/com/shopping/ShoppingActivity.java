package android.bignerdranch.com.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;

public class ShoppingActivity extends AppCompatActivity  implements UIFragment.OnFragmentInteractionListener {

    private static ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm= getSupportFragmentManager();
        Fragment fragmentUI= fm.findFragmentById(R.id.container_ui_portrait);
        if (fragmentUI == null) {
            fragmentUI= new UIFragment();
            fm.beginTransaction()
                    .add(R.id.container_ui_portrait, fragmentUI)
                    .commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
