package com.hanbit.kakao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hanbit.kakao.factory.Composite;
import com.hanbit.kakao.member.MemberList;

import java.util.HashMap;

public class Index extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = Index.this;
        HashMap<?,?>components=init(context);
        Button button = (Button) components.get("btnIndex");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MemberList.class));
            }
        });

    }
    public HashMap<?,?>init(Context context){
        Composite compo = new Composite(context,"Index");
        compo.execute();
        setContentView(compo.getFrame());
        return compo.getComponents();
    }
}