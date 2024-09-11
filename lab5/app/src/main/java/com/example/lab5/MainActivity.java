package com.example.lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        userList.add(new User("haivv", "Vu Hai", "haivv@gmail.com"));
        userList.add(new User("VanHai", "Vu Van Hai", "hai@gmail.com"));
        userList.add(new User("bichngan", "Bích Ngân", "bichngan@gmail.com"));
        userList.add(new User("vuvan", "Vu Van Hai", "vuvan@gmail.com"));
        userList.add(new User("thanhphap", "Thanh Phap", "thanhphap@gmail.com"));
        userList.add(new User("hungdue", "Hung Due", "hungdue@gmail.com"));

        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
}
