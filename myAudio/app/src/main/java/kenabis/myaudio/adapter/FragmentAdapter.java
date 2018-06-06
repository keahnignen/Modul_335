package kenabis.myaudio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter
{
    private List<Fragment> pages = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public FragmentAdapter(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public Fragment getItem(int index)
    {
        return(this.pages.get(index));
    }

    @Override
    public int getCount()
    {
        return(this.pages.size());
    }

    public void addFragment(Fragment fragment)
    {
        this.pages.add(fragment);
    }

    public void addTitle(String title)
    {
        this.titles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int index)
    {
        return(this.titles.get(index));
    }
}
