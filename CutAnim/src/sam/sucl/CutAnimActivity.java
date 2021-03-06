package sam.sucl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/* 主要负责界面启动外框的初始工作 */
public class CutAnimActivity extends Activity
{
    Context context = null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        context=this;
        
        Button button1=(Button)findViewById(R.id.button1);
        
        /* 给Button注释一个回调函数，用于onClick事件触发时执行.
         * 注意！这里是一个语句，不是一个函数  */
        button1.setOnClickListener(new OnClickListener() 
	        {
				public void onClick(View v)
				{
					/*  创建和使用Cutact */
					Intent intent=new Intent(context, Cutact.class);
					startActivity(intent);
				}
			}
        );
        
    }
}