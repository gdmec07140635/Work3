package com.example.administrator.work4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button calculatorButton;
    private EditText weightEditText;
    private CheckBox manCheckBox;
    private CheckBox womanCheckBox;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButton= (Button) findViewById(R.id.calculator);
        weightEditText= (EditText) findViewById(R.id.weight);
        manCheckBox= (CheckBox) findViewById(R.id.man);
        womanCheckBox= (CheckBox) findViewById(R.id.woman);
        resultTextView= (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        calculatorButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")) {
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("评估结果\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男生标准身高：");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "厘米");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女生标准身高：");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "厘米");
                        }
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("性别！");
                    }
                } else {
                    showMessage("请输入体重！");
                }
            }
        });
    }
            private double evaluateHeight(Double weight, String sex) {
                double height;
                if (sex=="男"){
                    height=170-(62-weight)/0.6;
                }else{
                    height=158-(52-weight)/0.6;
                }
                return height;
            }
    private void showMessage(String message ) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(message);
        alert.setButton("确定",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int whichButton){

            }
        });
        alert.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       switch (item.getItemId()){
           case 1://退出
               finish();
               break;
       }
        return super.onOptionsItemSelected(item);
    }
}
