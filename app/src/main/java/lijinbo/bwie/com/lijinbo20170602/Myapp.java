package lijinbo.bwie.com.lijinbo20170602;

import android.app.Application;

import org.xutils.x;

/**初始化xutils
 * Created by lijinbo on 2017/6/2.
 */


public class Myapp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
