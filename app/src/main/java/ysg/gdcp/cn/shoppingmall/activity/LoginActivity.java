package ysg.gdcp.cn.shoppingmall.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.Utils.BmobUtils;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;
import ysg.gdcp.cn.shoppingmall.entity.MyUser;
import ysg.gdcp.cn.shoppingmall.event.MessageEvent;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobListener;
import ysg.gdcp.cn.shoppingmall.listener.MyTextWatcher;


public class LoginActivity extends AppCompatActivity implements MyBmobListener {

    @Bind(R.id.tv_quick_register)
    TextView mTvQuickRegister;
    @Bind(R.id.tv_count_register)
    TextView mTvCountRegister;

    @Bind(R.id.view_line_left)
    View mViewLineLeft;
    @Bind(R.id.view_line_right)
    View mViewLineRight;
    //帐号登录
    @Bind(R.id.ll_login)
    LinearLayout mLlLogin;
    //快速登录按钮
    @Bind(R.id.ll_quick_login)
    LinearLayout mLlQuickLogin;
    //快速登录手机号编辑框
    @Bind(R.id.et_quick_phone)
    EditText mEtQuickPhone;
    //快速登录验证码编辑框
    @Bind(R.id.btn_get_code)
    Button mBtnGetCode;
    @Bind(R.id.et_quick_code)
    EditText mEtQuickCode;
    //帐号登录用户名
    @Bind(R.id.username)
    EditText mUsername;
    //帐号登录密码
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.cb_show_pwd)
    CheckBox mCbShowPwd;
    //快速登录按钮
    @Bind(R.id.quick_login_btn)
    Button mQuickLoginBtn;
    //账号登录按钮
    @Bind(R.id.login_btn)
    Button mLoginBtn;
    //底部线条往右移
    private Animation mAnimationLeft;
    //底部线条往左移
    private Animation mAnimationRight;
    //登录文本颜色
    private int mOrange;
    private int mGray;

    //手机号验证码是否为空
    private boolean isPhoneNull = false;
    private boolean isCodeNull = false;
    //帐号密码是否为空
    private boolean isCountNull = false;
    private boolean ispwdNull = false;
    //获取验证码按钮秒数
    private int mTime;
    private BmobUtils mBmobUtils;
    private String mPhoneNumber;
    private String mCode;

    private boolean isShowPwd = false;
    private String mLoginUserName;
    private String mLoginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
        initAnimation();
    }


    private void initData() {
        mBmobUtils = new BmobUtils(this);
        mOrange = getResources().getColor(R.color.orange);
        mGray = getResources().getColor(R.color.gray);
        //监听快速登录文本框
        quuickLoginListener();
        //监听账号登录文本框
        countLoginListener();

    }


    private void initAnimation() {
        //底部线条往右移
        mAnimationLeft = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.view_line_move_right);
        //底部线条往左移
        mAnimationRight = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.view_line_move_left);
        ////底部线条动画监听调用的方法
        setAnimationListener();
    }


    @OnClick({R.id.tv_quick_register, R.id.tv_count_register, R.id.tv_register, R.id.cb_show_pwd
            , R.id.quick_login_btn, R.id.btn_get_code, R.id.login_btn, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_quick_register:
                //快速登录文本动画
                mViewLineRight.startAnimation(mAnimationRight);
                mLoginBtn.setVisibility(View.GONE);
                mQuickLoginBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_count_register:
                //帐号登录文本动画
                mViewLineLeft.startAnimation(mAnimationLeft);
                mLoginBtn.setVisibility(View.VISIBLE);
                mQuickLoginBtn.setVisibility(View.GONE);
                break;
            case R.id.tv_register:
                //跳转到注册页面
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
            case R.id.quick_login_btn:
                //快速登录按钮
                //获取手机号码 验证码
                mPhoneNumber = mEtQuickPhone.getText().toString().trim();
                mCode = mEtQuickCode.getText().toString().trim();
                mBmobUtils.login(mPhoneNumber, mCode, LoginActivity.this);
                break;
            case R.id.login_btn:
                //账号登录按钮
                mLoginUserName = mUsername.getText().toString().trim();
                mLoginPwd = mPassword.getText().toString().trim();
                mBmobUtils.countLogn(mLoginUserName, mLoginPwd, this);
                break;
            case R.id.btn_get_code:
                //获取验证码
                if (TextUtils.isEmpty(mPhoneNumber)) {
                    Toast.makeText(this, "手机号码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBmobUtils.getCode(mPhoneNumber, LoginActivity.this);
                break;

            case R.id.cb_show_pwd:
                //显示密码
                //显示密码按钮切换
                isShowPwd = isShowPwd ? false : true;
                toggle();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //显示密码按钮切换
    private void toggle() {
        if (isShowPwd) {
            mPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    //底部线条动画调用的方法
    private void setAnimationListener() {
        mAnimationLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTvQuickRegister.setTextColor(mGray);
                mTvCountRegister.setTextColor(mOrange);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlLogin.setVisibility(View.VISIBLE);
                mLlQuickLogin.setVisibility(View.GONE);
                mViewLineRight.setVisibility(View.VISIBLE);
                mViewLineLeft.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        //设置左移动画监听
        mAnimationRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTvQuickRegister.setTextColor(mOrange);
                mTvCountRegister.setTextColor(mGray);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlLogin.setVisibility(View.GONE);
                mLlQuickLogin.setVisibility(View.VISIBLE);
                mViewLineRight.setVisibility(View.INVISIBLE);
                mViewLineLeft.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //快速登录文本框的监听事件方法
    private void quuickLoginListener() {
        mEtQuickPhone.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                isPhoneNull = TextUtils.isEmpty(s.toString()) ? false : true;
                mQuickLoginBtn.setEnabled(isCodeNull && isPhoneNull);
            }
        });
        mEtQuickCode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                isCodeNull = TextUtils.isEmpty(s.toString()) ? false : true;
                mQuickLoginBtn.setEnabled(isCodeNull && isPhoneNull);
            }
        });
    }

    //监听账号登录文本框
    private void countLoginListener() {
        mUsername.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                isCodeNull = TextUtils.isEmpty(s.toString()) ? false : true;
                mLoginBtn.setEnabled(isCountNull && ispwdNull);

            }
        });
        mPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                ispwdNull = TextUtils.isEmpty(s.toString()) ? false : true;
                mLoginBtn.setEnabled(ispwdNull && ispwdNull);
            }
        });
    }

    //读秒的方法
    public void countDownTime() {
        mBtnGetCode.setEnabled(false);
        mTime = 60;
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTime--;
                        mBtnGetCode.setText(mTime + "");
                        if (mTime <= 0) {
                            mBtnGetCode.setText("重新发送");
                            mBtnGetCode.setEnabled(true);
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    @Override
    public void getCodeSucess() {
        countDownTime();
    }

    @Override
    public void loginSucess(MyUser user) {

        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        sp.edit().putBoolean("isLogin", true).putString("userName", user.getUsername())
                .putString("blance", user.getBlance()).putLong("time",System.currentTimeMillis()).commit();
        EventBus.getDefault().post(new MessageEvent(user.getUsername(), user.getImageUri(), user.getBlance()));
        finish();
    }

    @Override
    public void querySucess(FavorInfo favorInfo) {

    }

    @Override
    public void queryFalure(BmobException e) {

    }

    @Override
    public void queryAllFalure(BmobException e) {

    }

    @Override
    public void queryAllSucess(List<FavorInfo> list) {

    }

    @Override
    public void queryGIdSUcess(FavorInfo favorInfo) {

    }


}
