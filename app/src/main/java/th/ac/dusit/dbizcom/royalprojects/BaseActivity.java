package th.ac.dusit.dbizcom.royalprojects;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {

    enum FragmentTransitionType {
        NONE,
        SLIDE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void loadFragment(Fragment fragment, String tag, boolean addToBackStack,
                                FragmentTransitionType transitionType) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (transitionType == FragmentTransitionType.SLIDE) {
            transaction.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
            );
        }
        transaction.replace(
                R.id.fragment_container,
                fragment,
                tag
        );
        if (addToBackStack) {
            transaction.addToBackStack(null).commit();
        } else {
            transaction.commit();
        }
    }

    protected void popAllBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    protected void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }
}
