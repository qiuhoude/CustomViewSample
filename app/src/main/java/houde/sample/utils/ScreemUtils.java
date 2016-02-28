package houde.sample.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/1/30.
 */
public class ScreemUtils {

    public static int getScreemHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}
