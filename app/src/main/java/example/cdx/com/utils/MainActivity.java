package example.cdx.com.utils;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import example.cdx.com.utilslibrary.utils.LocationServiceUtils;
import example.cdx.com.utilslibrary.utils.SHA1Utils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1();

        //获取SHA1值
        test2();
    }

    private void test1() {
        Log.e(TAG,LocationServiceUtils.isOpenLocService(this)+"");
    }

    private void test2() {
        Log.e(TAG,SHA1Utils.getSHA1(this));
    }
}
