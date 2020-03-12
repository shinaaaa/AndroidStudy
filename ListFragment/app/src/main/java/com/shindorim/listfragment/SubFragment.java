package com.shindorim.listfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment extends ListFragment {

    TextView text1;

    // ListView를 구성하기 위한 데이터
    String [] list = {"항목1","항목2","항목3","항목4","항목5","항목6"};

    public SubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub, container, false);

        // View의 주소 값을 받아옴
        text1 = view.findViewById(R.id.textView);

        // ListView에 적용할 adapter를 생성
        MainActivity activity = (MainActivity) getActivity();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, list);

        // adapter를 ListView에 적용
        setListAdapter(adapter);

        return view;
    }

    // ListView의 항목을 터치하면 호츨되는 메소드
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        text1.setText("선택한 항목 : " + list[position]);
    }
}
