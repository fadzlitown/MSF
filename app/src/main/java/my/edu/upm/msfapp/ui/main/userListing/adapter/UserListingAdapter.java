package my.edu.upm.msfapp.ui.main.userListing.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.model.User;
import my.edu.upm.msfapp.util.Constant;

public class UserListingAdapter extends ArrayAdapter<User> {
    public ViewHolder holder;
    public User item;

    public UserListingAdapter(Context context, ArrayList<User> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_listing_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        item = getItem(position);

        if(item!=null){
            if(!TextUtils.isEmpty(item.getFullName())){
                holder.name.setText(item.getFullName());
            }

            if(item.getRole()== Constant.RoleType.LECTURER){
                holder.role.setText("Lecturer");
                holder.status.setImageDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.user));
            } else if(item.getRole()== Constant.RoleType.STAFF){
                holder.role.setText("Staff");
                holder.status.setImageDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.user));
            }else if(item.getRole()== Constant.RoleType.PATIENT){
                holder.role.setText("Patient");
            } else if(item.getRole()== Constant.RoleType.STUDENT){
                holder.role.setText("Student");
                if(item.isEvaluated()){
                    holder.status.setImageDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.right));
                } else {
                    holder.status.setImageDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.wrong_a));
                }
            }


        }

        return convertView;
    }




    private class ViewHolder {
        TextView name;
        TextView role;
        ImageView status;

        ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.user_name);
            role = (TextView) view.findViewById(R.id.user_role);
            status = (ImageView) view.findViewById(R.id.status);

        }
    }
}