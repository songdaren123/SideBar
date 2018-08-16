package demo.song.com.sidebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final  String TAG=MainActivity.class.getSimpleName();
    private SideBar mSideBar;
    private List<CityInfo> citys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mSideBar=findViewById(R.id.sidebar);
        mSideBar.setItemSelectedListener(new SideBar.SideBarItemSelectedListener() {
            @Override
            public void itemSelected(String str) {
                Log.i(TAG, "itemSelected: "+str);
            }
        });
    }
    private void initData() {
        citys = new ArrayList<>();
        citys.add(new CityInfo("北京", 1));
        citys.add(new CityInfo("上海", 2));
        citys.add(new CityInfo("天津", 3));
        citys.add(new CityInfo("重庆", 4));
        citys.add(new CityInfo("山东", 5));
        citys.add(new CityInfo("山西", 6));
        citys.add(new CityInfo("河南", 7));
        citys.add(new CityInfo("河北", 8));
        citys.add(new CityInfo("广东", 9));
        citys.add(new CityInfo("广西", 10));
        for (CityInfo city : citys) {
            String pinyin = CharacterParser.getInstance().getSelling(city.getName());
            String letter=pinyin.substring(0,1);
            city.setLetter(letter.toUpperCase());
        }
        Collections.sort(citys,new PinyinComparable());


    }

    public class PinyinComparable implements Comparator<CityInfo> {




        @Override
        public int compare(CityInfo cityInfo, CityInfo t1) {
            return cityInfo.getLetter().compareTo(t1.getLetter());
        }
    }
}
