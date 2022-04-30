package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTag extends AppCompatActivity {
private Button submit;
String name;
String color = null;
EditText nameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);
        nameInput = (EditText) findViewById(R.id.Create_Tag_Name_Text_Input);
        //name = nameInput.getText().toString();
        //showText(name);



        submit = findViewById(R.id.Submit_CreateTag);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Tag to list of Tags, in TaskList
                //check if name is already inside list
                name = nameInput.getText().toString();
                showText(name);

                for(int i = 0; i < MainActivity.taskList.Tags.size(); i++){
                    //if there is a tag with the same name, simply return and do not follow thru with submitting
                    if(MainActivity.taskList.Tags.get(i).name.equals(name)){
                        //don't submit, just return try again
                        return;
                    }
                }
                if(color == null)
                    MainActivity.taskList.Tags.add(new Tag(name));
                else
                    MainActivity.taskList.Tags.add(new Tag(name, color));
                openMainView();
            }
        });
    }

    public void openMainView(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showText(String text){
        Toast.makeText(CreateTag.this, text, Toast.LENGTH_SHORT).show();
    }

}