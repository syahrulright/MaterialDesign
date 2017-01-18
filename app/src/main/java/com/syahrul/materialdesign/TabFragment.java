package com.syahrul.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout dan setup Views.
         */
            View x =  inflater.inflate(R.layout.tab_layout,null);
            tabLayout = (TabLayout) x.findViewById(R.id.tabs);
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set Apater untuk View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Solusi untuk bug,
         * setupWithViewPager tidak dapat bekerja tanpa runnable.
         * Mungkin bug dari support library-nya.
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                   }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Mengembalikan posisi dari fragment.
         */

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 : return new PrimaryFragment();
              case 1 : return new SocialFragment();
              case 2 : return new UpdatesFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * Method ini mengembalikan judul tab sesuai dengan posisi.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Primary";
                case 1 :
                    return "Social";
                case 2 :
                    return "Updates";
            }
                return null;
        }
    }

}
