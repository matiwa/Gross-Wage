package com.example.grosswage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import java.text.DecimalFormat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText eTGW,eTcogi,eTaip;
    RadioButton rBs,rBo;
    ImageButton imgbtnc,imgbtne;
    TextView tv;
    double a=0,b1=0,b2=0,b3=0,b3e=0,b3r=0,b4=0,b4e=0,b4r=0,b5=0,b6=0,be=0,br=0,c=0,d=0,e=0,f=0,g=0,h=0,ie=0,ir=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializecomponents();

        imgbtnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    calculate();
                }catch (Exception e){
                    tv.setText(e.getMessage());
                }finally {
                    screenshow();
                }
            }
        });

        imgbtne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initializecomponents(){
        eTGW=findViewById(R.id.eTGW);
        rBs=findViewById(R.id.rBs);
        rBs.setChecked(false);
        rBo=findViewById(R.id.rBo);
        rBo.setChecked(true);
        eTcogi=findViewById(R.id.eTcogi);
        eTaip=findViewById(R.id.eTaip);
        eTaip.setHint("From 0.67 to 3.86 %");
        eTaip.setText("1.67");
        setf();
        imgbtnc=findViewById(R.id.imgbtnc);
        imgbtne=findViewById(R.id.imgbtne);
        tv=findViewById(R.id.tv);
    }

    public void calculate(){
        a=Double.parseDouble(eTGW.getText().toString());
        b1=Double.parseDouble(String.format("%.2f",a*Double.parseDouble(eTaip.getText().toString())/100));
        b2=Double.parseDouble(String.format("%.2f",a*2.45/100));
        b3=Double.parseDouble(String.format("%.2f",a*8/100));
        b3e=Double.parseDouble(String.format("%.2f",a*1.5/100));
        b3r=Double.parseDouble(String.format("%.2f",a*6.5/100));
        b4=Double.parseDouble(String.format("%.2f",a*19.52/100));
        b4e=Double.parseDouble(String.format("%.2f",a*9.76/100));
        b4r=Double.parseDouble(String.format("%.2f",a*9.76/100));
        b5=Double.parseDouble(String.format("%.2f",a*2.45/100));
        b6=Double.parseDouble(String.format("%.2f",a*0.1/100));
        be=Double.parseDouble(String.format("%.2f",b2+b3e+b4e));
        br=Double.parseDouble(String.format("%.2f",b1+b3r+b4r+b5+b6));
        c=Double.parseDouble(String.format("%.2f",a-be));
        d=Double.parseDouble(String.format("%.2f",c*9/100));
        e=Double.parseDouble(String.format("%.2f",c*7.75/100));
        f=Double.parseDouble(String.format("%.2f",Double.parseDouble(eTcogi.getText().toString())));
        //g=Double.parseDouble(String.format("%f",a-f-be));
        g=roundTwoDecimals(a-f-be);
        //h=Double.parseDouble(String.format("%f",(18*g/100)-46.33-e));
        h=roundTwoDecimals((18*g/100)-46.33-e);
        ie=Double.parseDouble(String.format("%.2f",a-be-d-h));
        ir=Double.parseDouble(String.format("%.2f",a+br));
    }

    public void setf(){
        if(rBs.isChecked()){
            eTcogi.setText("111.25");
            eTcogi.setHint("From 111.25 to 1335 PLN / month");
        }
        else if(rBo.isChecked()){
            eTcogi.setText("139.06");
            eTcogi.setHint("From 139.06 to 1668.72 PLN / month ");
        }
    }

    public void screenshow(){
        tv.setText("");
        tv.setText(tv.getText().toString()+"[a] Gross Wage: "+a+" PLN\r\n\r\n"+
                "Type of contribution:"+"\r\n"+
                "+ [b1] for accident insurance (employer): "+eTaip.getText().toString()+"%\t"+b1+" PLN\r\n"+
                "+ [b2] for sickness insurance (employee): 2.45%\t"+b2+" PLN\r\n"+
                "+ [b3] for disability insurance: 8%\t"+b3+"PLN:\r\n"+
                "\t- [b3e] employee: 1.5%\t"+b3e+" PLN\r\n"+
                "\t- [b3r] employer: 6.5%\t"+b3r+" PLN\r\n"+
                "+ [b4] for retirement insurance: 19.52%\t"+b4+" PLN\r\n"+
                "\t- [b4e] employee: 9.76%\t"+b4e+" PLN\r\n"+
                "\t- [b4r] employer: 9.76%\t"+b4r+" PLN\r\n"+
                "+ [b5] for the Labor Fund (employer): 2.45%\t"+b5+" PLN\r\n"+
                "+ [b6] for the Employee Benefit Guarantee Fund (employer): 0.1%\t"+b6+" PLN\r\n"+
                "[be] Contributions financed by the employee (total) (b2+b3e+b4e): "+be+" PLN\r\n"+
                "[br] Contributions financed by the employer (total) (b1+b3r+b4r+b5+b6): "+br+" PLN\r\n\r\n"+
                "[c] The basis of the health insurance contribution (employee) (a-be): "+c+" PLN\r\n\r\n"+
                "[d] Health premium to be paid (employee) (9%*c): "+d+" PLN\r\n\r\n"+
                "[e] The health contribution is tax deductible (employee) (7.75%*c): "+e+" PLN\r\n\r\n"+
                "[f] Cost of getting income (employee): "+f+" PLN\r\n\r\n"+
                "[g] Basis for deduction of PIT (Personal Income Tax) advance (rounded to full zlotys) " +
                        "(employee) (a-f-be): "+g+" PLN\r\n\r\n"+
                "[h] Advance payment for PIT (Personal Income Tax) (rounded to full zlotys) "+
                "(employee) (18%*g)-46.33-e): "+h+" PLN\r\n\r\n"+
                "[ie] Net salary to be paid (for employee): "+ie+" PLN\r\n\r\n"+
                "[ir] Total cost of employee maintenance (employer) (a+b): "+ir+" PLN");
    }

    public double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#"); //#.## two decimal places
        return Double.valueOf(twoDForm.format(d));
    }
}
