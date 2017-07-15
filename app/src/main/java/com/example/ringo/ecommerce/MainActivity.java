package com.example.ringo.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText EuserName;
    private EditText EpassWord;
    private TextView Login;
    private TextView Register;
    private ImageView Iimage;
    private Button BOK;
    private int modal = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Iimage = (ImageView) findViewById(R.id.image_bar);
        Login = (TextView) findViewById(R.id.textView1);
        Register = (TextView) findViewById(R.id.textView2);
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modal = 0;
                AnimationSet set = new AnimationSet(true);
                TranslateAnimation translate = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1f,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                set.addAnimation(translate);
                set.setFillAfter(true);
                set.setDuration(300);
                Iimage.startAnimation(set);
                SpannableString s = new SpannableString("请输入新的用户名");//这里输入自己想要的提示文字
                EuserName.setHint(s);
                SpannableString r = new SpannableString("请输入密码");//这里输入自己想要的提示文字
                EpassWord.setHint(r);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modal = 1;
                AnimationSet set = new AnimationSet(true);
                TranslateAnimation translate = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                set.addAnimation(translate);
                set.setFillAfter(true);
                set.setDuration(300);
                Iimage.startAnimation(set);
                SpannableString s = new SpannableString("用户名");//这里输入自己想要的提示文字
                EuserName.setHint(s);
                SpannableString r = new SpannableString("密码");//这里输入自己想要的提示文字
                EpassWord.setHint(r);
            }
        });
        EuserName = (EditText) findViewById(R.id.userName);
        EuserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
            }
        });
        EpassWord = (EditText) findViewById(R.id.passWord);
        EpassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    EpassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        BOK = (Button) findViewById(R.id.OK);
        BOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (modal == 1 && check() == 1) {
//                    final User_authentication user_authentication = new User_authentication(EuserName.getText().toString(), EpassWord.getText().toString());
//                    user_authentication.sendRequestWithOkHttp_Json();
//                    Handler handle = new Handler();
//                    handle.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            judge(user_authentication.hh);
//                        }
//                    }, 1000);

                    User_login.setState(EuserName.getText().toString());
                    Intent i = new Intent(MainActivity.this, FragmentActivity.class);
                    MainActivity.this.startActivity(i);

                }
                if (modal == 0 && check() == 1) {
                    final User_register user_register = new User_register(EuserName.getText().toString(), EpassWord.getText().toString());
                    user_register.sendRequestWithOkHttp_Json();
                    Handler handle = new Handler();
                    handle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            judge(user_register.hh);
                        }
                    }, 1000);
                }

            }
        });
    }

    private void judge(String a) {
        if (a.equals("wrong"))
            Toast.makeText(MainActivity.this,
                    R.string.Fail_tip,
                    Toast.LENGTH_SHORT).show();
        if (a.equals("right")) {
            User_login.setState(EuserName.getText().toString());
            Intent i = new Intent(MainActivity.this, FragmentActivity.class);
            MainActivity.this.startActivity(i);
        }
        if(a.equals("OK"))
            Toast.makeText(MainActivity.this,
                    R.string.register_success,
                    Toast.LENGTH_SHORT).show();
        if(a.equals("ERROR"))
            Toast.makeText(MainActivity.this,
                    R.string.error,
                    Toast.LENGTH_SHORT).show();
        if(a.equals("exist"))
            Toast.makeText(MainActivity.this,
                    R.string.user_exist,
                    Toast.LENGTH_SHORT).show();
    }

    private int check() {
        int i = 1;
        if (EuserName.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this,
                    R.string.empty_name,
                    Toast.LENGTH_SHORT).show();
            i = 0;
        } else if (EpassWord.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this,
                    R.string.empty_pw,
                    Toast.LENGTH_SHORT).show();
            i = 0;
        }
        return i;
    }
}
