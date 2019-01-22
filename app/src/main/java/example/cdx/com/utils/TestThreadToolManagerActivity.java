package example.cdx.com.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import example.cdx.com.utilslibrary.utils.ThreadPoolManager;

public class TestThreadToolManagerActivity extends AppCompatActivity {

    private static final String TAG = TestThreadToolManagerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread_tool_manager);

        ThreadPoolManager
                .getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,"线程执行了.....");
                    }
                });
    }
}
