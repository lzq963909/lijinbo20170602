package lijinbo.bwie.com.lijinbo20170602;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import lijinbo.bwie.com.lijinbo20170602.fragment.Fragment01;
/**替换 fragment
 * Created by lijinbo on 2017/6/2.
 */

public class MainActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开启一个事务  替换fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment01()).commit();
    }
}
