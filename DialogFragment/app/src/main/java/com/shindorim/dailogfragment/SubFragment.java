package com.shindorim.dailogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SubFragment extends DialogFragment {

    public SubFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("타이틀입니다.");
        builder.setMessage("메시지입니다.");

        DialogListener listener = new DialogListener();

        builder.setPositiveButton("Positive", listener);
        builder.setNeutralButton("Neutral", listener);
        builder.setNegativeButton("Negative", listener);

        AlertDialog alert = builder.create();


        return alert;
    }

    class DialogListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity activity = (MainActivity) getActivity();

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE :
                    activity.text1.setText("POSITIVE");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    activity.text1.setText("NEGATIVE");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    activity.text1.setText("NEUTRAL");
                    break;
            }
        }
    }
}
