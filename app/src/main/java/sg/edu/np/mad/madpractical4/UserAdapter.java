package sg.edu.np.mad.madpractical4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Intent;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private final ListActivity activity;
    private final ArrayList<User> listObjects;
    public UserAdapter(ArrayList<User> listObjects, ListActivity activity){
        this.listObjects = listObjects;
        this.activity = activity;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(UserViewHolder holder, int position){
        User listItems = listObjects.get(position);
        holder.name.setText(listItems.getName());
        holder.description.setText(listItems.getDescription());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Profile");
        builder.setMessage(listItems.getName());
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent MainActivity = new Intent(activity,MainActivity.class);
                MainActivity.putExtra("name",listItems.getName());
                MainActivity.putExtra("description",listItems.getDescription());
                MainActivity.putExtra("followed",listItems.getFollowed());
                activity.startActivity(MainActivity);
            }
        });
        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        if (listItems.getName().endsWith("7")){
            holder.bigImage.setVisibility(View.VISIBLE);
        }
        else{
            holder.bigImage.setVisibility(View.GONE);
        }
    }
    public int getItemCount() { return listObjects.size();}
}
