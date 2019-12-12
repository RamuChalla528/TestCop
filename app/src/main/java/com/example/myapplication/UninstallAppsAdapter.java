package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UninstallAppsAdapter extends RecyclerView.Adapter<UninstallAppsAdapter.ViewHolder>{
    private Context mContext;
    private List<String> mDataSet;

    public UninstallAppsAdapter(Context context, List<String> list){
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextViewLabel;
        public TextView mTextViewPackage;
        public ImageView mImageViewIcon;
        public ImageButton mImageButtonUninstall;

        public ViewHolder (View v){
            super(v);
            // Get the widgets reference from custom layout
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextViewLabel = (TextView) v.findViewById(R.id.app_label);
            mTextViewPackage = (TextView) v.findViewById(R.id.app_package);
            mImageViewIcon = (ImageView) v.findViewById(R.id.iv_icon);
            mImageButtonUninstall = (ImageButton) v.findViewById(R.id.ib_uninstall);
        }
    }

    @Override
    public UninstallAppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.uninstalapp,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        // Initialize a new instance of AppManager class
        AppsManager appsManager = new AppsManager(mContext);

        // Get the current package name
        final String packageName = (String) mDataSet.get(position);

        // Get the current app icon
        Drawable icon = appsManager.getAppIconByPackageName(packageName);

        // Get the current app label
        String label = appsManager.getApplicationLabelByPackageName(packageName);

        // Set the current app label
        holder.mTextViewLabel.setText(label);

        // Set the current app package name
        holder.mTextViewPackage.setText(packageName);

        // Set the current app icon
        holder.mImageViewIcon.setImageDrawable(icon);

        // Set a click listener for ImageButton
        holder.mImageButtonUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new Intent to uninstall an app/package
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+packageName));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        // Count the installed apps
        return mDataSet.size();
    }}
