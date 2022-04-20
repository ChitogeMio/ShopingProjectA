package com.example.shopingprojecta.tagcast;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.view.CheckinPage;
import com.example.shopingprojecta.view.workwork.WorkPage;

public class ErrorDialogWorkPage extends DialogFragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE 	= "message";
    public static final String KEY_TYPE = "type";

    public static final int TYPE_NO = -1;
    public static final int TYPE_OK	 = 1;
    public static final int TYPE_RETRY = 2;
    public static final int TYPE_UPDATE = 3;

    public static void showDialogFragmentWorkPage (FragmentManager fragmentManager, String tag, Fragment fragment){

        if(fragmentManager==null){
            return;
        }


        FragmentTransaction t = fragmentManager.beginTransaction();
        Fragment prve = fragmentManager.findFragmentByTag(tag);
        if (prve!=null){

            t.remove(prve);

        }
        t.add(fragment,tag);
        t.commitAllowingStateLoss();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int dialogFlag = getArguments().getInt(KEY_TYPE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString(KEY_TITLE));
        builder.setMessage(getArguments().getString(KEY_MESSAGE));

        switch (dialogFlag){

            case TYPE_OK:

                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDismiss(dialog);
                    }
                });
                break;
            case TYPE_RETRY:

                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Activity activity = getActivity();
                        if (activity== null){return;}
                        WorkPage workPage = (WorkPage) activity;
                        workPage.mErrorDialogType=TYPE_NO;
                        // bat dau quet

                        workPage.tgcAdapterWkPage.startScan();
                        onDismiss(dialog);

                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity==null){
                            return;
                        }
                        WorkPage workPage = (WorkPage) activity;
                        workPage.mErrorDialogType=TYPE_NO;
                        onDismiss(dialog);
                    }
                });
                break;
            case TYPE_UPDATE:
                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Activity activity = getActivity();
                        if (activity==null){return;}
                        WorkPage workPage = (WorkPage) activity;
                        workPage.mErrorDialogType=TYPE_NO;
                        //thu thap du lieu thu quan ly tagcast
                        workPage.tgcAdapterWkPage.prepare();
                        onDismiss(dialog);
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Activity activity = getActivity();
                        if (activity==null){return;}
                        WorkPage workPage = (WorkPage) activity;
                        workPage.mErrorDialogType=TYPE_NO;
                        onDismiss(dialog);

                    }
                });
                break;
            default:
                break;
        }

        return builder.create();


    }
}
