package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {

    ListView lvDrink;
    Button btnDatMon;
    ArrayList<Drink> drinkList;
    DrinkAdapter drinkAdapter;
    int selectedDrinkIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        lvDrink = findViewById(R.id.lvDrink);
        btnDatMon = findViewById(R.id.btnDatMon);

        drinkList = new ArrayList<>();
        drinkList.add(new Drink("Coffee", "Đồ uống phổ biến", R.drawable.coffee, 25000));
        drinkList.add(new Drink("Trà sữa", "Đồ uống yêu thích của giới trẻ", R.drawable.milk_tea, 30000));
        drinkList.add(new Drink("Pepsi", "Nước ngọt có gas", R.drawable.pepsi, 15000));
        drinkList.add(new Drink("Schweppes", "Nước uống có gas", R.drawable.schweppes, 20000));
        drinkList.add(new Drink("Soda", "Nước ngọt có gas", R.drawable.soda, 18000));

        drinkAdapter = new DrinkAdapter(this, R.layout.item_drink, drinkList);
        lvDrink.setAdapter(drinkAdapter);

        lvDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDrinkIndex = position;
                Toast.makeText(DrinkActivity.this, "Bạn đã chọn: " + drinkList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDrinkIndex != -1) {
                    Drink selectedDrink = drinkList.get(selectedDrinkIndex);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedDrink", selectedDrink.getName());
                    resultIntent.putExtra("drinkPrice", selectedDrink.getPrice());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(DrinkActivity.this, "Vui lòng chọn đồ uống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
