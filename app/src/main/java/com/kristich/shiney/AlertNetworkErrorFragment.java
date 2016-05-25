package com.kristich.shiney;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Kristich on 5/24/16.
 */
public class AlertNetworkErrorFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(context.getString(R.string.error_title));
        builder.setMessage(context.getString(R.string.network_error_message));
        builder.setPositiveButton(context.getString(R.string.error_button), null);

        AlertDialog dialog = builder.create();

        return dialog;


    }

}
