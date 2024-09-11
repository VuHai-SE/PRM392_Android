package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.api.adapter.MyArrayAdapter;
import com.example.api.api.TraineeRepository;
import com.example.api.api.TraineeService;
import com.example.api.model.Trainee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListuserActivity extends AppCompatActivity {
    TraineeService traineeService;
    ArrayList<Trainee> list;
    ListView listViewUser;
    MyArrayAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listuser);

        listViewUser = findViewById(R.id.listViewUser);
        list = new ArrayList<>();
        traineeService = TraineeRepository.getTraineeService();
        view();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void view() {
        try {
            Call<Trainee[]> call = traineeService.getAllTrainees();
            call.enqueue(new Callback<Trainee[]>() {
                @Override
                public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                    Trainee[] trainees = response.body();
                    if (trainees == null) {
                        return;
                    }

                    for (Trainee trainee : trainees) {
                        list.add(new Trainee(trainee.getId(), trainee.getName(), trainee.getEmail(), trainee.getPhone(), trainee.getGender()));
                    }

                    myadapter = new MyArrayAdapter(ListuserActivity.this, R.layout.layout_user, list);
                    listViewUser.setAdapter(myadapter);
                    listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Trainee selectedTrainee = list.get(i);
                            Intent myIntent = new Intent(ListuserActivity.this, SubActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("trainee", selectedTrainee);
                            myIntent.putExtras(bundle);
                            startActivityForResult(myIntent, 1); // requestCode = 1
                        }
                    });
                }

                @Override
                public void onFailure(Call<Trainee[]> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            list.clear();
            view();
        }
    }
}