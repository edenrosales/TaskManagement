package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class CreateTag extends AppCompatActivity {
    public static String EXTRA_NAME =
            "empty";
    public static String EXTRA_COLOR =
            "empty";
    private Button submit;
    boolean is_tag_added;
    private FloatingActionButton colorButton;
    TaskViewModel taskViewModel;
    String name;
    int color;
    EditText nameInput;
    int defaultColor;
    FrameLayout frameLayout;
    ImageView tagColorBackground;
    ConstraintLayout cL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        nameInput = (EditText) findViewById(R.id.Create_Tag_Name_Text_Input);
        tagColorBackground = findViewById(R.id.Create_Tag_Block);
        colorButton = (FloatingActionButton) findViewById(R.id.pick_color);
        //default color for tags is "#6200EE"
        //color = "#6200EE";

        defaultColor = ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        submit = (Button) findViewById(R.id.Submit_CreateTag);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Tag to list of Tags, in TaskList
                //check if name is already inside list
                name = nameInput.getText().toString();
                //showText(name);
                //for(int i = 0; i < MainActivity.taskList.Tags.size(); i++){
                    //if there is a tag with the same name, simply return and do not follow thru with submitting
                //    if(MainActivity.taskList.Tags.get(i).name.equals(name)){
                        //don't submit, just return try again
                //        return;
                //    }
                //}
                //MainActivity.taskList.Tags.add(new Tag(name, color));
                //openMainView();
                saveTag();
            }
        });
    }
    //open color picker method
    public void openColorPicker(){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {}

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                tagColorBackground.setBackgroundColor(defaultColor);
                color = Integer.valueOf(defaultColor);
                System.out.println("Value of color is: " + defaultColor);

            }
        });
        ambilWarnaDialog.show();
    }

    private void saveTag(){
        Intent data = new Intent();
        //check all tags again refresh them
        List<Tag> t = new ArrayList<>();
        taskViewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                for(int i = 0; i < tags.size(); i++){
                    if(tags.get(i).getName().equals(name)){
                        is_tag_added = false;
                    }
                }
                is_tag_added = true;
            }
        });
        if(is_tag_added == true){
            data.putExtra("EXTRA_NAME", name);
            data.putExtra("EXTRA_COLOR", color);
            setResult(RESULT_OK, data);
            finish();
        }
        else{
            return;
        }
        /*taskViewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                for(int i = 0; i < tags.size(); i++){
                    if(tags.get(i).getName().equals(name)){
                        is_tag_added = false;
                    }
                }
                is_tag_added = true;
                if(is_tag_added == true) {
                    Intent data = new Intent();
                    data.putExtra("EXTRA_NAME", name);
                    data.putExtra("EXTRA_COLOR", color);
                    setResult(RESULT_OK);
                    finish();
                }
                    else{
                        return;
                    }
            }
        });*/
    }
}
