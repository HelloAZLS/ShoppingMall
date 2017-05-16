package ysg.gdcp.cn.toolbar;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ysg.gdcp.cn.toolbar.Utils.ToolbarHelper;

public class ToolbarActivity extends AppCompatActivity {

    private ToolbarHelper mToolbarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbarHelper = new ToolbarHelper(ToolbarActivity.this, layoutResID);
        Toolbar toolbar = mToolbarHelper.getToolbar();
        setContentView(mToolbarHelper.getContentView());
        setSupportActionBar(toolbar);
        //自定义操作
        onCustomerOperation(toolbar);
    }

    public void onCustomerOperation(Toolbar toolbar) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
