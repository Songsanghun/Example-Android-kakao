package com.hanbit.kakao.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanbit.kakao.action.IUpdate;
import com.hanbit.kakao.factory.Composite;
import com.hanbit.kakao.factory.WriteQuery;

import java.util.HashMap;
import java.util.Map;



public class MemberUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context= MemberUpdate.this;
        Intent intent=this.getIntent();

        String spec=intent.getExtras().getString("spec").toString();
        Log.d("넘어온 스펙",spec);
        String[] specs=spec.split(",");
        final Map<String,String> map=new HashMap<>();
        String[]keys=new String[specs.length/2];
        String[]vals=new String[specs.length/2];
        int k=0,v=0;
        for(int i=0;i<specs.length;i++){
            String key="",val="";
            if((i%2)==0){
                Log.d("keys["+k+"] = specs["+i+"] :"+k,specs[i]);
                keys[k]=specs[i];
                k++;
            }else{
                Log.d("vals["+v+"] = specs["+i+"] :"+v,specs[i]);
                vals[v]=specs[i];
                v++;
            }
        }
        v=0;
        for(String key:keys){
            map.put(key,vals[v++]);
        }

        HashMap<String,Object>components = (HashMap<String,Object>)init(context);

        TextView tvIdContent = (TextView) components.get("tvUpdateId");
        tvIdContent.setText(map.get("id"));
        TextView tvNameContent = (TextView) components.get("tvUpdateName");
        tvNameContent.setText(map.get("name"));
        final EditText etPhoneContent = (EditText) components.get("etUpdatePhone");
        etPhoneContent.setHint(map.get("phone"));
        TextView tvAgeContent = (TextView) components.get("tvUpdateAge");
        tvAgeContent.setText(map.get("age"));
        final EditText etAddressContent = (EditText) components.get("etUpdateAddress");
        etAddressContent.setHint(map.get("address"));
        final EditText etSalaryContent = (EditText) components.get("etUpdateSalary");
        etSalaryContent.setHint(map.get("salary"));
        setContentView((LinearLayout) components.get("llUpdateFrame"));

        final UpdateDAO dao=new UpdateDAO(context);
        final IUpdate updateService = new IUpdate() {
            @Override
            public void update() {
                String sql=String.format("update member set phone='%s',address='%s',salary='%s' where _id='%s'",
                        map.get("phone"),map.get("address"),map.get("salary"),map.get("id"));
                dao.update(sql);
            }
        };

        Button btConfirm = (Button) components.get("btnUpdateCancel");
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etPhoneContent.getText().toString().equals("")){
                    map.put("phone",etPhoneContent.getText().toString());
                }
                if(!etAddressContent.getText().toString().equals("")){
                    map.put("address",etAddressContent.getText().toString());
                }
                if(!etSalaryContent.getText().toString().equals("")){
                    map.put("salary",etSalaryContent.getText().toString());
                }
                updateService.update();
                Intent intent=new Intent(context,MemberDetail.class);
                intent.putExtra("id",map.get("id"));
                startActivity(intent);
            }
        });
        Button btCancel = (Button) components.get("btnUpdateConfirm");
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MemberList.class));
            }
        });
    }
    class UpdateDAO extends WriteQuery {
        public UpdateDAO(Context context) {
            super(context);
        }
        @Override
        public void update(String sql) {
            super.getDatabase().execSQL(sql);
        }
    }
    public HashMap<?,?>init(Context context){
        Composite compo = new Composite(context,"MemberUpdate");
        compo.execute();
        setContentView(compo.getFrame());
        return compo.getComponents();
    }
}