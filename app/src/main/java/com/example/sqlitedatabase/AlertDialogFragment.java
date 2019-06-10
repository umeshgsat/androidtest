package com.example.sqlitedatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlertDialogFragment extends DialogFragment implements View.OnClickListener {
    private OnPositiveClickListener positiveClickListener;
    private OnNegativeClickListener negativeClickListener;
    private TextView mAlertText;
    private Button mPosText;
    private Button mNegText;
    private EditText mNote;

    public static AlertDialogFragment newInstance(String alertText, String posText, String negText, OnPositiveClickListener onPositiveClickListener, OnNegativeClickListener onNegativeClickListener) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleConstants.ALERT_TEXT, alertText);
        bundle.putString(Constants.BundleConstants.POS_TEXT, posText);
        bundle.putString(Constants.BundleConstants.NEG_TEXT, negText);
        alertDialogFragment.setPositiveClcikListener(onPositiveClickListener);
        alertDialogFragment.setNegativeClickListener(onNegativeClickListener);
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    private void setNegativeClickListener(OnNegativeClickListener onNegativeClickListener) {
        this.negativeClickListener = onNegativeClickListener;
    }

    private void setPositiveClcikListener(OnPositiveClickListener onPositiveClickListener) {
        this.positiveClickListener = onPositiveClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert_dialog, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mAlertText = view.findViewById(R.id.alert_text);
        mPosText = view.findViewById(R.id.button1);
        mNegText = view.findViewById(R.id.button2);
        mNote = view.findViewById(R.id.enter_note);

        if (getArguments() != null)
            setData(getArguments());
        mPosText.setOnClickListener(this);
        mNegText.setOnClickListener(this);
    }

    private void setData(Bundle arguments) {
        mAlertText.setText(arguments.getString(Constants.BundleConstants.ALERT_TEXT));
        mPosText.setText(arguments.getString(Constants.BundleConstants.POS_TEXT));
        mNegText.setText(arguments.getString(Constants.BundleConstants.NEG_TEXT));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                positiveClickListener.onPosButtonClick(false, mNote.getText().toString());
                break;
            case R.id.button2:
                negativeClickListener.onNegButtonClick();
        }

    }

    interface OnPositiveClickListener {
        void onPosButtonClick(boolean update, String note);
    }

    interface OnNegativeClickListener {
        void onNegButtonClick();
    }
}
