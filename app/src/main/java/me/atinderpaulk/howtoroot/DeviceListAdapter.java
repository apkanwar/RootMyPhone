package me.atinderpaulk.howtoroot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DevicesHolder> {

    private List<Device> devicesList;
    private Context context;

    class DevicesHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;
        ImageView imgViewIcon;

        DevicesHolder(View itemLayoutView) {
            super(itemLayoutView);

            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.phone_name);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.phone_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent();

                    if(position == 0){
                        intent =  new Intent(context, OPOActivity.class);
                    }
                    else if(position == 1) {
                        intent = new Intent(context, Zenfone2Activity.class);
                    }
                    else if(position == 2){
                        intent =  new Intent(context, AboutActivity.class);
                    }
                    else if(position == 3) {
                        intent = new Intent(context, AboutActivity.class);
                    }
                    context.startActivity(intent);
                }
            });
        }
    }

    DeviceListAdapter(Context context, List<Device> devicesList) {
        this.context = context;
        this.devicesList = devicesList;
    }

    public DevicesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_list, parent, false);

        return new DevicesHolder(itemView);

    }

    class SectionHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;
        ImageView imgViewIcon;

        SectionHolder(View itemLayoutView) {
            super(itemLayoutView);

            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.phone_name);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.phone_img);
        }
    }

    @Override
    public void onBindViewHolder(DevicesHolder holder, int position) {
        Device device = devicesList.get(position);
        holder.txtViewTitle.setText(device.getTitle());
        holder.imgViewIcon.setImageResource(device.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }

}
