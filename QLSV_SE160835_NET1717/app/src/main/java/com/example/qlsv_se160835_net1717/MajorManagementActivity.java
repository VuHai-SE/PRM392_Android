package com.example.qlsv_se160835_net1717;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv_se160835_net1717.database.DatabaseHelper;
import com.example.qlsv_se160835_net1717.ui.MajorAdapter;

import java.util.List;

public class MajorManagementActivity extends AppCompatActivity implements MajorAdapter.MajorAdapterListener {

    private DatabaseHelper dbHelper;
    private ListView listViewMajors;
    private Button buttonAddMajor;
    private List<String> majorList;
    private MajorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_management);

        listViewMajors = findViewById(R.id.listViewMajors);
        buttonAddMajor = findViewById(R.id.buttonAddMajor);

        dbHelper = new DatabaseHelper(this);
        majorList = dbHelper.getAllMajorNames();

        adapter = new MajorAdapter(this, R.layout.list_item_major, majorList, this);
        listViewMajors.setAdapter(adapter);

        buttonAddMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddMajorDialog();
            }
        });
    }

    @Override
    public void onEditClicked(String major) {
        showEditMajorDialog(major);
    }

    @Override
    public void onDeleteClicked(String major) {
        dbHelper.deleteMajor(major);
        majorList.remove(major);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Major deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private void showAddMajorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_major, null);
        builder.setView(dialogView);

        final EditText editTextMajorName = dialogView.findViewById(R.id.editTextMajorName);
        final Button buttonAdd = dialogView.findViewById(R.id.buttonAddMajor);
        final AlertDialog dialog = builder.create();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String majorName = editTextMajorName.getText().toString().trim();

                if (majorName.isEmpty()) {
                    Toast.makeText(MajorManagementActivity.this, "Please enter a major name", Toast.LENGTH_SHORT).show();
                    return;
                }

                long result = dbHelper.addMajor(majorName);
                if (result == -1) {
                    Toast.makeText(MajorManagementActivity.this, "Failed to add major", Toast.LENGTH_SHORT).show();
                } else {
                    majorList.add(majorName);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MajorManagementActivity.this, "Major added successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    private void showEditMajorDialog(String selectedMajor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_major, null);
        builder.setView(dialogView);

        final EditText editTextMajorName = dialogView.findViewById(R.id.editTextMajorName);
        editTextMajorName.setText(selectedMajor);
        final Button buttonAdd = dialogView.findViewById(R.id.buttonAddMajor);
        buttonAdd.setText("Update");
        final AlertDialog dialog = builder.create();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String majorName = editTextMajorName.getText().toString().trim();

                if (majorName.isEmpty()) {
                    Toast.makeText(MajorManagementActivity.this, "Please enter a major name", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper.updateMajor(selectedMajor, majorName);
                int index = majorList.indexOf(selectedMajor);
                majorList.set(index, majorName);
                adapter.notifyDataSetChanged();
                Toast.makeText(MajorManagementActivity.this, "Major updated successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
