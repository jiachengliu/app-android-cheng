package com.dameiren.app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dameiren.app.R;
import com.dameiren.app.callback.NetCallBack;
import com.dameiren.app.core.MyConstants;
import com.dameiren.app.net.AccountNet;
import com.dameiren.app.ui.base.BaseActivity;
import com.dameiren.app.ui.login.presenter.LoginPresenter;
import com.dameiren.app.ui.login.view.LoginView;
import com.dameiren.app.ui.main.MainActivity;
import com.litesuits.utils.JCStringUtils;
import com.litesuits.utils.JCToastUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpParams;

/**
 * 作者:liujiacheng.
 * 日期: 2016/5/31 0031 17:06.
 * 描述：
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView,NetCallBack {
    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String BUNDLE_LOGIN_TYPE = TAG + "bundle_login_type";
    public static final String BUNDLE_LOGIN_FLAG = TAG + "bundle_login_flag";
    public static final int TYPE_LOGIN_NOW = 1;
    public static final int TYPE_LOGIN_ACTIVITY = 2;
    private Button btn_login;
    private EditText editText_nickname,editText_password;
    private LoginPresenter loginPresenter;
    public static final int WHAT_LOGIN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initIble(this);
        loginPresenter = new LoginPresenter(this,mActivity);
    }

    private void initViews() {
        editText_nickname = (EditText)findViewById(R.id.editText_nickname);
        editText_password = (EditText)findViewById(R.id.editText_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            case R.id.btn_login:
                startTask(MyConstants.Action.ACTION_ACCOUNT_LOGIN,WHAT_LOGIN,NET_METHOD_POST,false);
                break;
            default:
                break;
        }
    }

    @Override
    public void moveToIndex() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return editText_nickname.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return editText_password.getText().toString().trim();
    }

    public HttpParams onStart(int what){
        return AccountNet.getInstance().login(mActivity, getName(), getPassword());
    }
    /**网络请求成功回调方法*/
    public void onSuccess(int what, String result, boolean hashCache){
        if (JCStringUtils.getInstance().isEmpty(result)) {
            JCToastUtil.showToast(mActivity, R.string.content_tip_request_result_empty);
            return;
        }
        loginPresenter.onSuccess(what, result, hashCache);
    }
    /**网络请求失败回调方法*/
    public void onError(int what, int code, String message){
        Toast.makeText(mActivity,message,Toast.LENGTH_SHORT).show();
        loginPresenter.onError(what, code, message);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(mActivity);//Activity销毁时，取消网络请求
    }
}
