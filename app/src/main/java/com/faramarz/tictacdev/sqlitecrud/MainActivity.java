package com.faramarz.tictacdev.sqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edtID, edtName, edtFamily, edtAge;
    Button btnDelete, btnUpdate, btnShowData, btnSaveData;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);
        bind();
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnShowData.setOnClickListener(this);
        btnSaveData.setOnClickListener(this);

    }

    private void bind() {
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtFamily = findViewById(R.id.edtFamily);
        edtAge = findViewById(R.id.edtAge);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnShowData = findViewById(R.id.btnShowData);
        btnSaveData = findViewById(R.id.btnSaveData);
    }

    public void viewAllData() {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Empty", "Noting found");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (res.moveToNext()) {
            stringBuffer.append("ID: " + res.getString(0) + "\n");
            stringBuffer.append("Name: " + res.getString(1) + "\n");
            stringBuffer.append("Family: " + res.getString(2) + "\n");
            stringBuffer.append("Age: " + res.getString(3) + "\n");
            stringBuffer.append("-------------------\n");
        }
        showMessage("list data", stringBuffer.toString());
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void DataUpdate() {
        String id = edtID.getText().toString();
        String name = edtName.getText().toString();
        String family = edtFamily.getText().toString();
        String age = edtAge.getText().toString();
        boolean isUpdate = myDb.updateData(id, name, family, age);
        if (isUpdate == true) {
            Toast.makeText(this, "student with id " + id + " updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "update failed", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnSaveData:
                boolean isInsert = myDb.insertData(edtName.getText().toString(),
                        edtFamily.getText().toString(),
                        edtAge.getText().toString());
                if (isInsert == true) {
                    Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                    edtAge.setText("");
                    edtFamily.setText("");
                    edtName.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Saving data failed :(", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnShowData:
                viewAllData();
                break;

            case R.id.btnUpdate:
                DataUpdate();
                break;

            case R.id.btnDelete:
                Integer deleted_row = myDb.deleteData(edtID.getText().toString());

                if (deleted_row > 0) {
                    Toast.makeText(MainActivity.this, "row deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "delete failed", Toast.LENGTH_SHORT).show();

                }
                break;

            default:
                break;
        }

    }

}





