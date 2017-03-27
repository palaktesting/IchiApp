package com.ichi.inspection.app.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ichi.inspection.app.R;
import com.ichi.inspection.app.fragments.ForgetPasswordFragment;
import com.ichi.inspection.app.fragments.InspectionListFragment;
import com.ichi.inspection.app.fragments.LoginFragment;
import com.ichi.inspection.app.fragments.SplashFragment;
import com.ichi.inspection.app.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = StartActivity.class.getSimpleName();

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigateToScreen(Constants.INSPECTIONLIST, null, false);
    }
    /**
     * replace new fragment on current fragment
     *
     * @param containerId     containerId
     * @param fragment        fragment
     * @param keepOnBackState keepOnBackState
     */
    public void replaceFragment(int containerId, Fragment fragment, boolean keepOnBackState) {
        try {
            FragmentManager manager = getSupportFragmentManager();
            //manager.enableDebugLogging(true);
            FragmentTransaction transaction = manager.beginTransaction();

            //based on the keepOnBackState flag manager will manage the back stack fragment
            if (!keepOnBackState) {
                int count = manager.getBackStackEntryCount();
                for (int i = 0; i < count; ++i) {
                    manager.popBackStack();
                }
            }
            //transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.replace(containerId, fragment, fragment.getClass().getName());
            if (keepOnBackState) {
                transaction.addToBackStack(fragment.getClass().getName());
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    *  Handles back stack for the fragment manager
    * */
    @Override
    public void onBackPressed(){

        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            Log.v(TAG,"onBackPressed : " + getSupportFragmentManager().getBackStackEntryCount() );
            Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fl_home_container);
            if(fragment instanceof InspectionListFragment){
                finish();  // To prevent back from the activity
            }else{
                navigateToScreen(0, null, true);
            }
        }
        else {
            Log.v(TAG,"super onBackPressed : ");
            super.onBackPressed();
        }
    }
    /**
     * Navigate to different screen
     * @param position
     */
    public void navigateToScreen(int position, Bundle bundle, boolean keepOnBackState) {

        Fragment fragment = null;

        switch (position){
            case Constants.INSPECTIONLIST:
                fragment = new InspectionListFragment();
                break;
            case Constants.INSPECTION_NAVIGATION:

                break;
            case Constants.INSPECTION_ORDER:

                break;
            case Constants.INSPECTION_DETAIL:
                break;
        }

        if(fragment == null){
            fragment = new SplashFragment();
        }
        if(bundle != null) fragment.setArguments(bundle);

        replaceFragment(R.id.fl_home_container, fragment, keepOnBackState);

    }
}
