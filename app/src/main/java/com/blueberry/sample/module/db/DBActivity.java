package com.blueberry.sample.module.db;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by blueberry on 2016/8/18.
 */
public class DBActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.tv_show)
    TextView tvShow;

    private String name;
    private String phone;

    private int id;
    private int clsId ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_db);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_add)
    public void onClick() {

        name = etName.getText().toString().trim();
        phone = etTel.getText().toString().trim();

        Student student = new Student();
        student.id = id++;
        student.cls_id = clsId++;
        student.name = name;
        student.tel_no = phone;

        student.save();

        ArrayList<Student> execute = new Select().from(Student.class)
                .execute();

        StringBuilder sb = new StringBuilder();
        for (Student stu:execute) {
            sb.append("id:"+stu.id).append(" name:"+stu.name).append(" tel:"+stu.tel_no)
                    .append("cls_id:"+stu.cls_id).append('\n');

            tvShow.setText(sb.toString());
        }

    }
}
