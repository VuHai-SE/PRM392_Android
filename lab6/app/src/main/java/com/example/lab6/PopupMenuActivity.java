package com.example.lab6;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PopupMenuActivity extends AppCompatActivity {
    private Button popupMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        popupMenuButton = findViewById(R.id.popupMenuButton);
        popupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PopupMenuItems menuItem = PopupMenuItems.fromId(item.getItemId());
                switch (menuItem) {
                    case POPUP1:
                        Toast.makeText(PopupMenuActivity.this, "Them selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case POPUP2:
                        Toast.makeText(PopupMenuActivity.this, "Sua selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case POPUP3:
                        Toast.makeText(PopupMenuActivity.this, "Xoa selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }
}
