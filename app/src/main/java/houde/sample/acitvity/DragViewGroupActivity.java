package houde.sample.acitvity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import houde.sample.R;

/**
 * Created by Administrator on 2016/1/30.
 */
public class DragViewGroupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_viewgroup);
    }
}
