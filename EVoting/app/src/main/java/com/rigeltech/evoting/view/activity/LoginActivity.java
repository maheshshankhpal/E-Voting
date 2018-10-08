package com.rigeltech.evoting.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.rigeltech.evoting.BaseAppCompatActivity;
import com.rigeltech.evoting.MainActivity;
import com.rigeltech.evoting.R;
import com.rigeltech.evoting.contract.ILoginContract;
import com.rigeltech.evoting.model.login.UserDetail;
import com.rigeltech.evoting.presenter.LoginPresenter;
import com.rigeltech.evoting.utility.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompatActivity implements ILoginContract.ILoginView {

    @BindView(R.id.etLoginUsername)
    EditText etUsername;

    @BindView(R.id.etLoginPassword)
    EditText etPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    ILoginContract.ILoginPresenter iLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iLoginPresenter = new LoginPresenter(this);

        String userName = "";
        try{
            userName = SessionManager.getString(getApplicationContext(), getString(R.string.user_name));
        }
        catch (Exception e){}
        etUsername.setText(userName);
        //etUsername.setText("mahesh");
        //etPassword.setText("mahesh");

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnLoginClicked();
                }
                return false;
            }
        });
    }


    @OnClick(R.id.btnLogin)
    public void btnLoginClicked() {
        showProgressDialog();
        iLoginPresenter.ValidateUser(etUsername.getText().toString(), etPassword.getText().toString());
        // attemptLogin();
    }

    @Override
    public void setUserNameError() {
        hideProgressDialog();
        clearErrorMessage();
        etUsername.setFocusable(true);
        etUsername.setFocusableInTouchMode(true);
        etUsername.setError(getString(R.string.Mandatory));
        etUsername.requestFocus();
    }

    @Override
    public void setPasswordError() {
        hideProgressDialog();
        clearErrorMessage();
        etPassword.setFocusable(true);
        etPassword.setFocusableInTouchMode(true);
        etPassword.setError(getString(R.string.Mandatory));
        etPassword.requestFocus();
    }

    @Override
    public void onLoginSuccess(UserDetail userDetail) {

        hideProgressDialog();

        SessionManager.putString(getApplicationContext(), getString(R.string.user_name), etUsername.getText().toString());
        SessionManager.putString(getApplicationContext(), getString(R.string.profile_name), userDetail.getProfileOwner());
        SessionManager.putString(getApplicationContext(), getString(R.string.profile_role), userDetail.getUserrole());



       Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);

        showMessage("Login Successfully");
    }

    @Override
    public void onLoginFailure(String message) {
        hideProgressDialog();
        showMessage(message);
    }

    void clearErrorMessage() {
        etUsername.setError(null);
        etPassword.setError(null);
    }
}

