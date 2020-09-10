package com.gaad.GADSLeaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gaad.GADSLeaderboard.util.ApiBuilder;
import com.gaad.GADSLeaderboard.util.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity {

    private EditText fname, lname, email, project_link;
    String fName, lName, eMail, gitlink;

    private  Button submitBtn;
    private Dialog popUp, popUpSuccess, popUpFailed;
    private ProgressBar PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_submit);

        fname = findViewById(R.id.fName_EDT);
        lname = findViewById(R.id.lName_EDt);
        email = findViewById(R.id.email_EDT);
        project_link = findViewById(R.id.git_link_EDT);
        PD = findViewById(R.id.progressBar_circular);
        submitBtn = findViewById(R.id.subMitBTN);






//        initPopup();
        editTxtConditions();
        disableButton();

        initPopups();


    }



    private void editTxtConditions() {


        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checklName();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                chckfName();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEmail();
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        project_link.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                chckGitLink();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void chckGitLink() {

        eMail = email.getText().toString().trim();
        fName = fname.getText().toString().trim();
        lName = lname.getText().toString().trim();
        gitlink = project_link.getText().toString().trim();
        String emailPattern = getString(R.string.pattern);

        if (!TextUtils.isEmpty(gitlink))
        {
            if (!TextUtils.isEmpty(lName) &&  Patterns.WEB_URL.matcher(gitlink).find() && !TextUtils.isEmpty(fName) && eMail.matches(emailPattern))
            {
                enableButton();
            }
            else {
                disableButton();
            }
        }
        else
        {
            disableButton();
            Toast.makeText(this, "Please enter a valid github address", Toast.LENGTH_LONG).show();
        }

    }

    private void chckfName() {
        eMail = email.getText().toString().trim();
        fName = fname.getText().toString().trim();
        lName = lname.getText().toString().trim();
        gitlink = project_link.getText().toString().trim();
        String emailPattern = getString(R.string.pattern);

        if (!TextUtils.isEmpty(fName) )
        {
            if (!TextUtils.isEmpty(lName) && !TextUtils.isEmpty(eMail) && Patterns.WEB_URL.matcher(gitlink).find() && eMail.matches(emailPattern)){
                enableButton();
            }
            else {
                disableButton();
            }

        }
        else
        {
            disableButton();
            Toast.makeText(this, "Please enter first name", Toast.LENGTH_LONG).show();
        }
    }

    private void checklName() {

        eMail = email.getText().toString().trim();
        fName = fname.getText().toString().trim();
        lName = lname.getText().toString().trim();
        gitlink = project_link.getText().toString().trim();
        String emailPattern = getString(R.string.pattern);

        if (!TextUtils.isEmpty(lName))
        {
            if (!TextUtils.isEmpty(fName) && !TextUtils.isEmpty(eMail) && !TextUtils.isEmpty(gitlink) && eMail.matches(emailPattern))
            {
                enableButton();
            }
            else {
                disableButton();
            }
        }
        else
        {
            disableButton();
            Toast.makeText(this, "Please enter last name", Toast.LENGTH_LONG).show();
        }

    }

    private void checkEmail()  {
        eMail = email.getText().toString().trim();
        fName = fname.getText().toString().trim();
        lName = lname.getText().toString().trim();
        gitlink = project_link.getText().toString().trim();
        String emailPattern = getString(R.string.pattern);

        if (!TextUtils.isEmpty(eMail))
        {
            if (!TextUtils.isEmpty(fName) && !TextUtils.isEmpty(lName) && Patterns.WEB_URL.matcher(gitlink).find() && eMail.matches(emailPattern)){
                enableButton();
            } else {
                disableButton();
            }

        } else {
            disableButton();
            Toast.makeText(getApplicationContext(), "Please enter valid email address. \n eg; john@example.com", Toast.LENGTH_LONG).show();
        }
    }

    private void initPopups() {

        popUp = new Dialog(this);
        popUp.setContentView(R.layout.popup_confirm);
        Objects.requireNonNull(popUp.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popUp.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        popUp.getWindow().getAttributes().gravity = Gravity.CENTER_VERTICAL;

        popUpSuccess = new Dialog(this);
        popUpSuccess.setContentView(R.layout.popup_success);
        Objects.requireNonNull(popUpSuccess.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popUpSuccess.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        popUpSuccess.getWindow().getAttributes().gravity = Gravity.CENTER_VERTICAL;
        popUpSuccess.setCanceledOnTouchOutside(true);
        popUpSuccess.setCancelable(true);

        popUpFailed = new Dialog(this);
        popUpFailed.setContentView(R.layout.popup_failed);
        Objects.requireNonNull(popUpFailed.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popUpFailed.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        popUpFailed.getWindow().getAttributes().gravity = Gravity.CENTER_VERTICAL;
        popUpFailed.setCanceledOnTouchOutside(true);
        popUpFailed.setCancelable(true);

        Button conFirm;
        ImageView closePop;

        closePop = popUp.findViewById(R.id.hide_Confirm_popUp);
        conFirm = popUp.findViewById(R.id.confirmClick);

        closePop.setOnClickListener(view -> popUp.hide());

        conFirm.setOnClickListener(view -> {

            PD.setVisibility(View.VISIBLE);
            popUp.hide();
            submitData();

        });

    }

    private void submitData() {

        ApiService service = ApiBuilder.form_buildService(ApiService.class);
        Call<Void> request = service.postValues( eMail , fName , lName , gitlink);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 200) {
                    //Display Success dialog
                    popUpSuccess.show();
                    PD.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(), "Successfully Submitted: " , Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Response Code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {

                PD.setVisibility(View.INVISIBLE);
                popUpFailed.show();

                Toast.makeText(getApplicationContext(), "Failed to Submit project.", Toast.LENGTH_SHORT).show();;

            }
        });



    }


    // Back to Parent Activity
    public void backClick(View view){
        NavUtils.navigateUpFromSameTask(this);    }

    public void submit_projClick(View view) {
        popUp.show();

    }

    private void disableButton() {
        submitBtn.setEnabled(false);
        submitBtn.setTextColor(Color.argb(50, 255, 255, 255));
    }

    private void enableButton() {
        submitBtn.setEnabled(true);
        submitBtn.setTextColor(Color.rgb( 255, 255, 255));
    }

    public void focusParent(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View v = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}