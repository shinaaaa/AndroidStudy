package com.shindorim.activitycontroller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    Button btn;
    TextView text1, text2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        // View의 주소 값을 추출
        btn = view.findViewById(R.id.button2);
        text1 = view.findViewById(R.id.textView);
        text2 = view.findViewById(R.id.textView2);

        // MainActivity 객체를 추출
        MainActivity mainActivity = (MainActivity) getActivity();
        text1.setText(mainActivity.value1);
        text2.setText(mainActivity.value2);

        BtnListener listener = new BtnListener();
        btn.setOnClickListener(listener);

        return view;
    }

    class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Activity 객체를 추출
            MainActivity mainActivity = (MainActivity) getActivity();

            // FragmentManager를 추출
            FragmentManager manager = mainActivity.getSupportFragmentManager();

            // 이전 Fragment로 이동
            manager.popBackStack();
        }
    }
}
