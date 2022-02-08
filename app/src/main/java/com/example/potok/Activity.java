package com.example.potok;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                TextView contentView = findViewById(R.id.content);
                WebView webView = findViewById(R.id.webView);
                webView.getSettings().setJavaScriptEnabled(true);
                Button btnFetch = findViewById(R.id.downloadBtn);
                btnFetch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                contentView.setText("Загрузка...");
                                new Thread(new Runnable() {
                                        public void run() {
                                                try{
                                                        String content = getContent("https://postman-echo.com/post");
                                                        webView.post(new Runnable() {
                                                                public void run() {
                                                                        webView.loadDataWithBaseURL("https://postman-echo.com/post",content, "text/html", "UTF-8", "https://postman-echo.com/post");
                                                                        Toast.makeText(getApplicationContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
                                                                }
                                                        });
                                                        contentView.post(new Runnable() {
                                                                public void run() {
                                                                        contentView.setText(content);
                                                                }
                                                        });
                                                }
                                                catch (IOException ex){
                                                        contentView.post(new Runnable() {
                                                                public void run() {
                                                                        contentView.setText("Ошибка: " + ex.getMessage());
                                                                        Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                                                                }
                                                        });
                                                }
                                        }
                                }).start();
                        }
                });
        }
        private String getContent(String path) throws IOException {
                BufferedReader reader=null;
                InputStream stream = null;
                HttpsURLConnection connection = null;
                try {
                        URL url=new URL(path);
                        connection =(HttpsURLConnection)url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setReadTimeout(10000);
                        connection.connect();
                        stream = connection.getInputStream();
                        reader= new BufferedReader(new InputStreamReader(stream));
                        StringBuilder buf=new StringBuilder();
                        String line;
                        while ((line=reader.readLine()) != null) {
                                buf.append(line).append("\n");
                        }
                        return(buf.toString());
                }
                finally {
                        if (reader != null) {
                                reader.close();
                        }
                        if (stream != null) {
                                stream.close();
                        }
                        if (connection != null) {
                                connection.disconnect();
                        }
                }
        }}












































































































//Runnable runnable = new Runnable() {
//        public void run() {
//            //код
//            thread.start();
//            long endTime = System.currentTimeMillis()
//                    + 20 * 1000;
//
//            while (System.currentTimeMillis() < endTime) {
//                synchronized (this) {
//                    try {
//                        wait(endTime -
//                                System.currentTimeMillis());
//                    } catch (Exception e) {
//                    }
//                }
//            }
//
//        }
//    };
//
//    Thread thread = new Thread(runnable);



//myThread.setDaemon(true);
//    //завершение потока

//myThread.sleep(2000);                                                                                                                                                                                                                                                                                                                                         //временное усыпление потока (миллисекунды)

//@Override                 //КАЛЕНДАРЬ+ВСПЛЫВАЮЩАЯ ДАТА
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        CalendarView calendarView = findViewById(R.id.calendarView);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year,
//                                            int month, int dayOfMonth) {
//                int mYear = year;
//                int mMonth = month;
//                int mDay = dayOfMonth;
//                String selectedDate = new StringBuilder().append(mMonth + 1)
//                        .append("-").append(mDay).append("-").append(mYear)
//                        .append(" ").toString();
//                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
//            }
//        });
//    }}
//android:maxDate="6/28/2020"
//    android:minDate="6/10/2020" // ограничения диапозона дат




//@Override          //Переход на 2 Activity
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }
//
//
//    public void onClick(View view) {
//        Toast toast = Toast.makeText(MainActivity.this, "Переход на новую Activity", Toast.LENGTH_LONG);
//        toast.show();
//
//        Intent intent = new Intent(this, TwoActivity.class);
//startActivity(intent);
//
//
//    }}


//Calendar c = Calendar.getInstance(); // получение текущего времени и даты
//
//int seconds = c.get(Calendar.SECOND);
//int minutes = c.get(Calendar.MINUTE);
//int hour = c.get(Calendar.HOUR);
//String time = hour + ":" + minutes + ":" + seconds;
//
//
//int day = c.get(Calendar.DAY_OF_MONTH);
//int month = c.get(Calendar.MONTH);
//int year = c.get(Calendar.YEAR);
//String date = day + "/" + month + "/" + year;
//
//txt_date.setText(date);
//txt_time.setText(time);



//@Override       //меню три точки
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // получим идентификатор выбранного пункта меню
//        int id = item.getItemId();
//
//        TextView infoTextView = findViewById(R.id.textView);
//
//        // Операции для выбранного пункта меню
//        switch (id) {
//            case R.id.action_cat1:
//                infoTextView.setText("Вы выбрали кота!");
//                return true;
//            case R.id.action_cat2:
//                infoTextView.setText("Вы выбрали кошку!");
//                return true;
//            case R.id.action_cat3:
//                infoTextView.setText("Вы выбрали котёнка!");
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    public void onSettingsMenuClick(MenuItem item) {
//        TextView infoTextView = (TextView) findViewById(R.id.textView);
//        infoTextView.setText("Вы выбрали пункт Settings, лучше бы выбрали кота");
//    }}
