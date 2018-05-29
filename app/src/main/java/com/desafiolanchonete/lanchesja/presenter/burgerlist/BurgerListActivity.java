package com.desafiolanchonete.lanchesja.presenter.burgerlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;

import com.desafiolanchonete.lanchesja.BaseActivity;
import com.desafiolanchonete.lanchesja.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BurgerListActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);
        ButterKnife.bind(this, this);

        BurgerListFragment burgerListFragment = (BurgerListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_drawer_layout);

        if (burgerListFragment == null) {
            burgerListFragment = BurgerListFragment.newInstance();
            startNewFragment(burgerListFragment, getSupportFragmentManager(), R.id.frame_layout_container_drawer_layout);
        }

        if (getSupportActionBar() != null) {
            mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
            mActionBarDrawerToggle.syncState();

            mNavigationView.setNavigationItemSelectedListener(this);

            getSupportActionBar().setTitle(getString(R.string.title_burger_list));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof BurgerListContract.View) {
            BurgerListContract.View view = (BurgerListContract.View) fragment;
            view.setPresenter(new BurgerListPresenter(view));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_promotion:
                closeDrawerMenu();
                getCurrentFragment().callPromotionListActivity();
                break;
            case R.id.nav_item_shopping_cart:
                closeDrawerMenu();
                getCurrentFragment().callShoppingCartActivity();
                break;
            default:
                /* Do nothing */
        }

        return true;
    }

    private void closeDrawerMenu() {
        mDrawerLayout.closeDrawer(Gravity.START);
    }

    private BurgerListContract.View getCurrentFragment() {
        BurgerListContract.View currentView = null;
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_drawer_layout);
        if (fragment!= null && fragment instanceof BurgerListContract.View) {
            currentView = (BurgerListContract.View) fragment;
        }
        return currentView;
    }
}
