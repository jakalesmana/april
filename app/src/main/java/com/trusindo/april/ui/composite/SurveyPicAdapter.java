package com.trusindo.april.ui.composite;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trusindo.april.R;
import com.trusindo.april.model.SurveyPicture;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakalesmana on 10/14/17.
 */

public class SurveyPicAdapter extends BaseAdapter {

    private Context context;
    private List<SurveyPicture> mListPicture;

    public SurveyPicAdapter(Context context) {
        this.context = context;
        mListPicture = new ArrayList<>();
    }

    public void addPics(List<SurveyPicture> list) {
        mListPicture.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListPicture.size();
    }

    @Override
    public SurveyPicture getItem(int i) {
        return mListPicture.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class ViewHolder {
        ImageView imgSurvey;
        View layoutDescription,layoutDelete;
        TextView txtDescription;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_survey_pic, null);

            holder = new ViewHolder();
            holder.imgSurvey = convertView.findViewById(R.id.imgSurvey);
            holder.layoutDescription = convertView.findViewById(R.id.layoutDescription);
            holder.layoutDelete = convertView.findViewById(R.id.layoutDelete);
            holder.txtDescription = convertView.findViewById(R.id.txtPicDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(mListPicture.get(pos).getImageUri()).fit().into(holder.imgSurvey);

        if (mListPicture.get(pos).getImageDescription().equals("")) {
            holder.layoutDescription.setVisibility(View.GONE);
        } else {
            holder.layoutDescription.setVisibility(View.VISIBLE);
            holder.txtDescription.setText(mListPicture.get(pos).getImageDescription());
        }

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDeleteConfirmation(pos);
            }
        });

        return convertView;
    }

    private void showDeleteConfirmation(final int pos) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(context.getString(R.string.delete_pic))
                .setMessage(context.getString(R.string.delete_pic_confirmation))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mListPicture.remove(pos);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
