package com.anuj.greenincome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

            case 0: Glide.with(mContext).load("https://s12.postimg.org/62l1c0s7x/ptv.jpg").into(holder.thumbnail);
                break;
            case 1: Glide.with(mContext).load("https://s21.postimg.org/ycwsdz96v/trpt.jpg").into(holder.thumbnail);
                break;
            case 2: Glide.with(mContext).load("https://s17.postimg.org/sd3cdsrin/hotels.jpg").into(holder.thumbnail);
                break;
            case 3: Glide.with(mContext).load("https://s13.postimg.org/9k2hfny1j/navig.jpg").into(holder.thumbnail);
                break;
            case 4: Glide.with(mContext).load("https://s14.postimg.org/ggg626ogx/exper.jpg").into(holder.thumbnail);
                break;
            case 5: Glide.with(mContext).load("https://s9.postimg.org/j5wu62d1r/pubtpt.jpg").into(holder.thumbnail);
                break;
            case 6: Glide.with(mContext).load("https://s13.postimg.org/8tz46rlh3/rest.jpg").into(holder.thumbnail);
                break;
            case 7:  Glide.with(mContext).load("http://elementarycarrental.com/wp-content/uploads/2014/12/holiday-car-rental.jpg").into(holder.thumbnail);
                break;
            case 8: Glide.with(mContext).load("https://s14.postimg.org/pjszs81fl/shopping.jpg").into(holder.thumbnail);
                break;
            case 9: Glide.with(mContext).load("https://s21.postimg.org/6ji8leg9j/medical.jpg").into(holder.thumbnail);
                break;
        }
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 1) {
                    Intent i = new Intent(mContext, Carpool.class);
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
