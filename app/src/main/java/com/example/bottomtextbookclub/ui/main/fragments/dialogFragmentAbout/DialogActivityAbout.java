package com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentAbout;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.bottomtextbookclub.R;

public class DialogActivityAbout extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_principal);
        showEditDialog();

    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogAbout dialogAbout = DialogAbout.newInstance("about");
        dialogAbout.show(fm, "fragment_about_dialog");

    }



}


