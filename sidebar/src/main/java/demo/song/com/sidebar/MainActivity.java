package demo.song.com.sidebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final  String TAG=MainActivity.class.getSimpleName();
    private SideBar mSideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSideBar=findViewById(R.id.sidebar);
        mSideBar.setItemSelectedListener(new SideBar.SideBarItemSelectedListener() {
            @Override
            public void itemSelected(String str) {
                Log.i(TAG, "itemSelected: "+str);
            }
        });
    }
}
