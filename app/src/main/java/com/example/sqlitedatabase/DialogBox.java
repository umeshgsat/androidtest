package com.example.sqlitedatabase;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogBox {

    public static void showDialog(Context context, String alertText, String posText, String negText, final OnDialogClickListener listener) {
        @SuppressLint("InflateParams") View alertLayout = LayoutInflater.from(context).inflate(R.layout.fragment_alert_dialog, null);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(alertLayout);
        dialog.setCancelable(true);
        TextView text = alertLayout.findViewById(R.id.alert_text);
        text.setText(alertText);
        final EditText note = alertLayout.findViewById(R.id.enter_note);
        Button posButton = alertLayout.findViewById(R.id.button1);
        posButton.setText(posText);
        Button negButton = alertLayout.findViewById(R.id.button2);
        negButton.setText(negText);
        posButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPosButtonClick(false, note.getText().toString(), dialog);
            }
        });
        negButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNegButtonClick(dialog);
            }
        });
       /* builder.setPositiveButton(posText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPosButtonClick(true, "");
            }
        });
        builder.setNegativeButton(negText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegButtonClick();
            }
        });*/
        dialog.show();
    }


    interface OnDialogClickListener {
        void onPosButtonClick(boolean update, String note, Dialog dialog);
        void onNegButtonClick(Dialog dialog);
    }
}
