package demo.song.com.sidebar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private SideBar mSideBar;
    private List<CityInfo> citys;
    private RecyclerView mRecyclerView;
    private CityAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mSideBar = findViewById(R.id.sidebar);
        mRecyclerView = findViewById(R.id.recycelview);
        mSideBar.setItemSelectedListener(new SideBar.SideBarItemSelectedListener() {
            @Override
            public void itemSelected(String str) {
                int index = mCityAdapter.getIndex(str);
                if (index != -1) {
                    mRecyclerView.scrollToPosition(index);
                }

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCityAdapter = new CityAdapter();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mCityAdapter);
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
        citys.add(new CityInfo("安徽", 11));
        citys.add(new CityInfo("江苏", 12));
        citys.add(new CityInfo("福建", 13));
        citys.add(new CityInfo("湖南", 14));
        citys.add(new CityInfo("湖北", 15));
        citys.add(new CityInfo("浙江", 16));
        citys.add(new CityInfo("四川", 17));
        citys.add(new CityInfo("云南", 18));
        citys.add(new CityInfo("台湾", 19));
        citys.add(new CityInfo("辽宁", 20));
        citys.add(new CityInfo("吉林", 21));
        citys.add(new CityInfo("黑龙江", 22));
        citys.add(new CityInfo("西藏", 23));
        citys.add(new CityInfo("青海", 24));
        citys.add(new CityInfo("新疆", 25));
        citys.add(new CityInfo("宁夏", 26));
        for (CityInfo city : citys) {
            String pinyin = CharacterParser.getInstance().getSelling(city.getName());
            String letter = pinyin.substring(0, 1);
            city.setLetter(letter.toUpperCase());
        }
        Collections.sort(citys, new PinyinComparable());


    }

    public class PinyinComparable implements Comparator<CityInfo> {


        @Override
        public int compare(CityInfo cityInfo, CityInfo t1) {
            return cityInfo.getLetter().compareTo(t1.getLetter());
        }
    }

    private class CityAdapter extends RecyclerView.Adapter<CityHolder> {
        @NonNull
        @Override
        public CityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = android.view.LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_item, viewGroup, false);
            CityHolder holder = new CityHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull CityHolder cityHolder, int i) {
            cityHolder.cityname.setText(citys.get(i).getName());
        }

        @Override
        public int getItemCount() {
            return citys.size();
        }

        public int getIndex(String letter) {
            for (CityInfo cityInfo : citys) {
                if (cityInfo.getLetter().equals(letter)) {
                    return citys.indexOf(cityInfo);
                }
            }
            return -1;
        }
    }

    private class CityHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView cityname;

        public CityHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.tv_city);
        }
    }

}
