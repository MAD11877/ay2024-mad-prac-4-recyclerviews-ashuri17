package sg.edu.np.mad.madpractical4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView tvName = findViewById(R.id.textView2);     //'userTitle'
        TextView tvDescription = findViewById(R.id.textView3);  // 'user.description'
        Button btnFollow = findViewById(R.id.button1);  //toggle 'followed' boolean

        Intent receivingID = getIntent();
        String username = receivingID.getStringExtra("name");
        String userDescription = receivingID.getStringExtra("description");
        final Boolean[] userFollowed = {receivingID.getBooleanExtra("followed", true)};
        tvName.setText(username);
        tvDescription.setText(userDescription);

        btnFollow.setText(userFollowed[0] ? "Unfollow" : "Follow");
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userFollowed[0] = !userFollowed[0];
                if (!userFollowed[0]){
                    btnFollow.setText("Follow");
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                }
                else{
                    btnFollow.setText("Unfollow");
                    Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
