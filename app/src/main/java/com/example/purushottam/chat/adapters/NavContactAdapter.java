package com.example.purushottam.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.purushottam.chat.R;
import com.example.purushottam.chat.modals.NavContact;

import java.util.List;

/**
 * Created by purushottam on 27/4/17.
 */

public class NavContactAdapter extends RecyclerView.Adapter<NavContactAdapter.NavContactBaseViewHolder> {

    private Context mContext;
    private List<NavContact> mContacts;

    private OnNavContactActionListener mListener;

    private final int TYPE_HEADER = 0;
    private final int TYPE_CONTACT = 1;


    public NavContactAdapter(Context context, List<NavContact> contacts) {
        this.mContext = context;
        this.mContacts = contacts;

        if(context instanceof OnNavContactActionListener) {
            mListener = (OnNavContactActionListener) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() +
                    " : must implement " + OnNavContactActionListener.class.getSimpleName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_CONTACT;
    }

    @Override
    public NavContactBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER :
                return new NavContactHeaderViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.nav_contact_header_dashboard, parent, false));

            case TYPE_CONTACT :
                return new NavContactViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_nav_contact, parent, false));

            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(final NavContactBaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER :
                final NavContactHeaderViewHolder headerHolder = (NavContactHeaderViewHolder) holder;
                break;

            case TYPE_CONTACT :
                final NavContactViewHolder contactHolder = (NavContactViewHolder) holder;
                final NavContact contact = mContacts.get(position-1);

                contactHolder.tvName.setText(contact.name);
                contactHolder.tvAbout.setText(contact.about);
                contactHolder.cbVisibility.setChecked(contact.isOnline);

                contactHolder.ivProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mListener != null) {
                            mListener.onShowContactDetails(contact);
                        }
                    }
                });
                contactHolder.lyContainerVisibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mListener != null) {
                            contact.isOnline = !contactHolder.cbVisibility.isChecked();
                            contactHolder.cbVisibility.setChecked(contact.isOnline);
                            mListener.onVisibilityChecked(contact, contact.isOnline, true);
                        }
                    }
                });
                contactHolder.cbVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(mListener != null) {
                            contact.isOnline = isChecked;
                            mListener.onVisibilityChecked(contact, isChecked, true);
                        }
                    }
                });
                if(mListener != null) {
                    mListener.onVisibilityChecked(contact, contact.isOnline, false);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return getAtualItemCount() + 1;
    }

    private int getAtualItemCount() {
        return mContacts == null ? 0 : mContacts.size();
    }

    public class NavContactBaseViewHolder extends RecyclerView.ViewHolder {
        public NavContactBaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NavContactViewHolder extends NavContactBaseViewHolder {

        TextView tvName, tvAbout;

        ImageView ivProfile;
        CheckBox cbVisibility;
        RelativeLayout lyContainerVisibility;

        public NavContactViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAbout = (TextView) itemView.findViewById(R.id.tv_about);

            ivProfile = (ImageView) itemView.findViewById(R.id.iv_profile);
            cbVisibility = (CheckBox) itemView.findViewById(R.id.iv_indicator);
            lyContainerVisibility = (RelativeLayout) itemView.findViewById(R.id.container_indicator);
        }
    }

    public class NavContactHeaderViewHolder extends NavContactBaseViewHolder {

        TextView tvName, tvAbout;

        ImageView ivProfile;
        CheckBox cbVisibility;
        RelativeLayout lyContainerVisibility;

        public NavContactHeaderViewHolder(View itemView) {
            super(itemView);

//            tvName = (TextView) itemView.findViewById(R.id.tv_name);
//            tvAbout = (TextView) itemView.findViewById(R.id.tv_about);
//
//            ivProfile = (ImageView) itemView.findViewById(R.id.iv_profile);
//            cbVisibility = (CheckBox) itemView.findViewById(R.id.iv_indicator);
//            lyContainerVisibility = (RelativeLayout) itemView.findViewById(R.id.container_indicator);
        }
    }

    public interface OnNavContactActionListener {
        void onVisibilityChecked(NavContact contact, boolean visible, boolean enableZoom);
        void onShowContactDetails(NavContact contact);
    }
}
