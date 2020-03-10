package com.shindorim.activitycontroller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    // View의 주소 값을 담을 참조 변수
    Button btn;
    EditText edit1, edit2;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Fragment가 관리할 View를 생성
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        // 생성한 View 내부에 있는 View 객체의 주소값을 추출
        btn = view.findViewById(R.id.button);
        edit1 = view.findViewById(R.id.editText);
        edit2 = view.findViewById(R.id.editText2);

        // Listener Setting
        BtnListener listener = new BtnListener();
        btn.setOnClickListener(listener);

        return view;
    }

    // Button을 누르면 반응할 Listener
    class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 현재 Fragment를 관리하는 Activity 객체를 추출
            MainActivity mainActivity = (MainActivity) getActivity();

            // 입력한 값을 Activity의 변수에 담음
            mainActivity.value1 = edit1.getText().toString();
            mainActivity.value2 = edit2.getText().toString();

            // ResultFragment가 나타나도록 Method를 호출
            mainActivity.setFragment("result");
        }
    }
}
