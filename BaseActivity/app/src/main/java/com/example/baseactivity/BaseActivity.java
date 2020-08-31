package com.example.baseactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected LinearLayout toolbar;

    protected LinearLayout container;

    protected abstract int getContainer();  // BaseActivity를 상속받는 다른 Acivity의 Container

    protected abstract int getToolbar();    // BaseActivity를 상속받는 다른 Acivity의 Toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setContainer(getContainer());
        setToolbar(getToolbar());
    }

    /**
     * Container setting
     *
     * @param view Container layout id
     */
    private void setContainer(@LayoutRes int view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(view, container);
        setContentView(layout);
    }

    /**
     * Toolbar setting
     *
     * @param view Toolbar layout id
     */
    private void setToolbar(@LayoutRes int view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(view, toolbar);
        setContentView(layout);
    }
}