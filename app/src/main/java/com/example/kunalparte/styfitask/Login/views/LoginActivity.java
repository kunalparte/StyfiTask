package com.example.kunalparte.styfitask.Login.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kunalparte.styfitask.Utils.AppPreferences;
import com.example.kunalparte.styfitask.Utils.BaseActivity;
import com.example.kunalparte.styfitask.Utils.Consts;
import com.example.kunalparte.styfitask.DataList.views.DataListActivity;
import com.example.kunalparte.styfitask.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends BaseActivity implements View.OnClickListener , GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signInButton;
    private Button googleCustombtn;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Consts.isNetworkAvailable(this)) {
            if (AppPreferences.getInstance(this).getLoginToken().isEmpty()) {
                init();
            } else {
                startActivity(new Intent(this, DataListActivity.class));
                finish();
            }
        }else {
            Toast.makeText(this,"Please Connect to Internet", Toast.LENGTH_SHORT).show();
        }

    }

    public void init(){
        getSupportActionBar().hide();
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        googleCustombtn = (Button) findViewById(R.id.googleCustomButton);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();


        signInButton.setOnClickListener(this);
        googleCustombtn.setOnClickListener(this);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleSignInClient);
        startActivityForResult(signInIntent, 1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.googleCustomButton:
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void handleSignInResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            AppPreferences.getInstance(this).setLoginToken(account.getId());
            Intent intent = new Intent(this,DataListActivity.class);
            intent.putExtra(Consts.NAME_KEY, account.getDisplayName());
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if (requestCode == 1){
            handleSignInResult(intent);

            }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        String connResult = connectionResult.getErrorMessage();
    }
}
