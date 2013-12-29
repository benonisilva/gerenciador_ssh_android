package com.example.monografiassh2013.utils;
public class ConfirmAlert {

    DialogReturn dialogReturn;

    public interface DialogReturn {

        void onDialogCompleted(boolean answer);
        void onDialogCompleted2(boolean answer);
    }

    public void setListener(DialogReturn dialogReturn) {
        this.dialogReturn = dialogReturn;
    }

    public DialogReturn getListener() {
        return dialogReturn;

    }
}