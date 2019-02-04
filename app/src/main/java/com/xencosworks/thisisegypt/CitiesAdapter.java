package com.xencosworks.thisisegypt;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bola on 2/3/2019.
 */

public class CitiesAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public CitiesAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CairoFragment();
            case 1:
                return new AlexFragment();
            case 2:
                return new LuxorFragment();
            case 3:
                return new AswanFragment();
            default:
                return new SharmFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.city_cairo);
            case 1:
                return mContext.getString(R.string.city_alex);
            case 2:
                return mContext.getString(R.string.city_luxor_aswan);
            case 3:
                return mContext.getString(R.string.city_aswan);
            default:
                return mContext.getString(R.string.city_sharm);

        }
    }
}
