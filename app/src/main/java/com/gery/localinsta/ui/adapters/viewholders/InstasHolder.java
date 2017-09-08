package com.gery.localinsta.ui.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gery.localinsta.LiApplication;
import com.gery.localinsta.R;
import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.ui.adapters.listener.ListItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by peterbekos on 5/16/17.
 */

public class InstasHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.insta_image)
    ImageView image;
    @BindView(R.id.caption)
    TextView caption;

    private Datum data;


    public InstasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.insta_large_view, parent, false);
    }

    public void onBind(Datum message, ListItemClickListener<Datum> listener) {
        this.data = message;

        caption.setText(data.getCaption().getText());
        itemView.setOnClickListener(view -> {
            listener.onItemClicked(data);
        });

        Glide.with(LiApplication.getContext())
                .load(data.getImages().getStandardResolution().getUrl())
                .into(image);
    }
}
