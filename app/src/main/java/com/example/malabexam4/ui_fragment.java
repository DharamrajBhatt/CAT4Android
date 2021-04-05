package com.example.malabexam4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ui_fragment  extends Fragment {

    RadioButton ans1,ans2,ans3;
    Button ml_submit;
    int Score = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference databasescores;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_activity, container, false);

        ans1 = v.findViewById(R.id.answer1);
        ans2 = v.findViewById(R.id.answer4);
        ans3 = v.findViewById(R.id.answer6);
        ml_submit = v.findViewById(R.id.mlbutton);

        scores Scores = new scores();

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();


        databasescores = FirebaseDatabase.getInstance().getReference("Scores");

        ml_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ans1.isChecked()){
                    Score = Score+1;
                }
                if(ans2.isChecked()){
                    Score = Score+1;
                }
                if(ans3.isChecked()){
                    Score = Score+1;
                }


                String id = databasescores.push().getKey();
                String id_add = id;

                String savecurrentdate,savecurrenttime;
                Calendar date = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
                savecurrentdate = currentDate.format(date.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                savecurrenttime = currentTime.format(date.getTime());

                Scores.setCust_id(currentUser.getEmail());
                Scores.setDate(savecurrentdate);
                Scores.setTime(savecurrenttime);
                Scores.setSubject("UI");
                Scores.setMarks(Integer.toString(Score));
                databasescores.child(currentUser.getUid()).child(id).setValue(Scores);

                Toast.makeText(getActivity(), "Your marks has been submitted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),Subject_Page.class);
                startActivity(intent);

            }
        });

        return v;
    }

}
