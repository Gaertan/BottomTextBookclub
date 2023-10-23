package com.example.bottomtextbookclub.ui.main.fragments.dialogScrollableFragment;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomtextbookclub.R;

public class DialogActivity extends FragmentActivity {
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_principal);
        showEditDialog();

    }



    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogScrollable editNameDialogFragment = DialogScrollable.newInstance("about");
        editNameDialogFragment.show(fm, "fragment_scroll_dialog");

    }


}


