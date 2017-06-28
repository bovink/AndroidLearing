package com.bovink.androidlearing.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bovink.androidlearing.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bovink.androidlearing.popupwindow
 *
 * @author bovink
 * @since 2017/6/28
 */

public class ListPopupWindow extends PopupWindow {

    private RecyclerView recyclerView;
    private List<String> list;

    public ListPopupWindow(Context context, List<String> list) {
        this.list = list;

        View root = LayoutInflater.from(context).inflate(R.layout.popup_main, null);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ImageFolderAdapter(context));

        setContentView(root);
        setTouchable(true);
        setFocusable(true);

        setOutsideTouchable(true);
        ColorDrawable drawable = new ColorDrawable(0x323232);
        setBackgroundDrawable(drawable);
        setWidth(700);
        setHeight(600);

    }


    class ImageFolderAdapter extends RecyclerView.Adapter<ImageFolderAdapter.ImageFolderViewHolder> {

        private Context context;

        public ImageFolderAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ImageFolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageFolderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_popup_main, parent, false));
        }

        @Override
        public void onBindViewHolder(ImageFolderViewHolder holder, int position) {
            holder.folderNameTextView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ImageFolderViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.iv_folder_logo)
            ImageView folderLogoImageView;
            @BindView(R.id.tv_folder_name)
            TextView folderNameTextView;

            public ImageFolderViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
