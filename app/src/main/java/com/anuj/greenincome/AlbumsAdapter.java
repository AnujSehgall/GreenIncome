package com.anuj.greenincome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anuj.greenincome.activity.DeviceControlActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());

        switch (position){

            case 0: Glide.with(mContext).load("http://1.bp.blogspot.com/-OqDKgSvTx1w/VYsEuNy-YmI/AAAAAAAATto/kVtv_szgrZQ/s1600/MoneyWallet%2B2.0.png").into(holder.thumbnail);
                break;
            case 1: Glide.with(mContext).load("https://www.issaquahhighlands.com/wp-content/uploads/2017/03/car-icon.png").into(holder.thumbnail);
                break;
            case 2: Glide.with(mContext).load("http://typren.co.uk/wp-content/uploads/2015/10/Carbon-footprint-icon.png").into(holder.thumbnail);
                break;
            case 3: Glide.with(mContext).load("https://lh5.ggpht.com/RnhUVhf6TNKNq2I0nfGTivYo29tscz2ibXgrnSxikNcV0oE9T9W9PoRcvjRfidx24s-Q=w300").into(holder.thumbnail);
                break;
            case 4: Glide.with(mContext).load("http://nutritionsmart.com/wp-content/uploads/2016/03/bigstock-recycle-green-flat-icon-recycl-86555183-1.jpg").into(holder.thumbnail);
                break;
            case 5: Glide.with(mContext).load("http://www.iconarchive.com/download/i86033/graphicloads/100-flat-2/bulb.ico").into(holder.thumbnail);
                break;

        }
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {
                    Intent i = new Intent(mContext, RecycleBank.class);
                    mContext.startActivity(i);

                }
                if (position == 1) {
                    Intent i = new Intent(mContext, Carpool.class);
                    mContext.startActivity(i);

                }
                if (position == 2) {
                    Intent i = new Intent(mContext, DeviceControlActivity.class);
                    mContext.startActivity(i);

                }

                if (position == 5){
                    Intent i = new Intent(mContext,Tips.class);
                    mContext.startActivity(i);
                }

                if (position == 3){
                    Intent i = new Intent(mContext,Echocharge.class);
                    mContext.startActivity(i);
                }



                if (position==4){
                    Intent i = new Intent(mContext, Recycler.class);
                    mContext.startActivity(i);

                }
            }
        });

  /*      holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    /**
     * Showing popup menu when tapping on 3 dots

     private void showPopupMenu(View view) {
     // inflate menu
     PopupMenu popup = new PopupMenu(mContext, view);
     MenuInflater inflater = popup.getMenuInflater();
     inflater.inflate(R.menu.menu_album, popup.getMenu());
     popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
     popup.show();
     }

     /**
     * Click listener for popup menu items

     class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

     public MyMenuItemClickListener() {
     }

     @Override
     public boolean onMenuItemClick(MenuItem menuItem) {
     switch (menuItem.getItemId()) {
     case R.id.action_add_favourite:
     Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
     return true;
     case R.id.action_play_next:
     Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
     return true;
     default:
     }
     return false;
     }
     }*/

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
