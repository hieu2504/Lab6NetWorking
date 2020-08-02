package vn.lucifer.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private TextView tvkq;
    private TextInputEditText edtNhap;
    private Button btnFtoC,btnCtoF;
    String kqCtoF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        tvkq=findViewById(R.id.tvkq);
        btnCtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNhap.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"Bạn chưa nhập nhiệt độ!",Toast.LENGTH_SHORT).show();
                }else {
                    final int a= Integer.parseInt( edtNhap.getText().toString());
                    AsyncTask asyncTask= new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            String namespace="https://www.w3schools.com/xml/";
                            String url ="https://www.w3schools.com/xml/tempconvert.asmx?WSDL";
                            String param="Celsius";
                            String method="CelsiusToFahrenheit";
                            String action=namespace+method;

                            SoapObject soapObject=new SoapObject(namespace,method);

//                PropertyInfo propertyInfo=new PropertyInfo();
//                propertyInfo.setName(param);
//                propertyInfo.setValue("100");
//                propertyInfo.setType(String.class);

                            soapObject.addProperty(param,a);


                            SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(soapObject);

                            envelope.dotNet = true;

                            HttpTransportSE httpsTransportSE=new HttpTransportSE(url);

                            try {
                                httpsTransportSE.call(action,envelope);
                                SoapPrimitive soapPrimitive=(SoapPrimitive) envelope.getResponse();
                                kqCtoF=soapPrimitive.toString();


                            }catch (Exception e){

                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            super.onPostExecute(o);
                            tvkq.setText(a+" degree Celsius = "+kqCtoF+" degree Fahrenheit");
                        }
                    };
                    asyncTask.execute();
                }

            }
        });

        btnFtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNhap.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"Bạn chưa nhập nhiệt độ!",Toast.LENGTH_SHORT).show();
                }else {
                    final int a= Integer.parseInt( edtNhap.getText().toString());
                    AsyncTask asyncTask= new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            String namespace="https://www.w3schools.com/xml/";
                            String url ="https://www.w3schools.com/xml/tempconvert.asmx?WSDL";
                            String param="Fahrenheit";
                            String method="FahrenheitToCelsius";
                            String action=namespace+method;

                            SoapObject soapObject=new SoapObject(namespace,method);

//                PropertyInfo propertyInfo=new PropertyInfo();
//                propertyInfo.setName(param);
//                propertyInfo.setValue("100");
//                propertyInfo.setType(String.class);

                            soapObject.addProperty(param,a);


                            SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(soapObject);

                            envelope.dotNet = true;

                            HttpTransportSE httpsTransportSE=new HttpTransportSE(url);

                            try {
                                httpsTransportSE.call(action,envelope);
                                SoapPrimitive soapPrimitive=(SoapPrimitive) envelope.getResponse();
                                kqCtoF=soapPrimitive.toString();


                            }catch (Exception e){

                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            super.onPostExecute(o);
                            tvkq.setText(a+" degree Fahrenheit = "+kqCtoF+" degree Celsius");
                        }
                    };
                    asyncTask.execute();
                }

            }
        });

    }
    void initView(){
        edtNhap=findViewById(R.id.edtNhap);
        btnCtoF=findViewById(R.id.btnCtoF);
        btnFtoC=findViewById(R.id.btnFtoC);

    }
}