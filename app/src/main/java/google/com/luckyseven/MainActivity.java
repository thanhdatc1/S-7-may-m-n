package google.com.luckyseven;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.EGLExt;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.Voice;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView text_1, text_2, text_3, txt_Title;
    Button button;
    boolean isPlay = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        autoChangeColer();


    }

    void initView(){
        text_1=(TextView) findViewById(R.id.txt1);
        text_2=(TextView) findViewById(R.id.txt2);
        text_3=(TextView) findViewById(R.id.txt3);
        txt_Title=(TextView) findViewById(R.id.txtTitle);
        button = (Button)findViewById(R.id.btnStop);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlay)
                {
                    pause();
                }else{
                    play();
                }
            }
        });
    }




    int colerIndex = 0;
    void autoChangeColer(){
        final int colerArr[] = new int[3];
        colerArr[0] = Color.BLUE;
        colerArr[1] = Color.RED;
        colerArr[2] = Color.YELLOW;


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(colerIndex == 2){
                    colerIndex = 0;
                }
                colerIndex ++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt_Title.setTextColor(colerArr[colerIndex]);
                    }
                });

            }
        }, 1000, 1000);

    }


    void pause() {
        isPlay = false;
        //Toast.makeText(this,"Pause", Toast.LENGTH_LONG).show();
        button.setText("Chạy");
        if (timer1 != null)
            timer1.cancel();





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timer2 != null)
                    timer2.cancel();
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timer3 != null)
                    timer3.cancel();
            }
        }, 1000);


        String num1 = text_1.getText().toString();
        String num2 = text_2.getText().toString();
        String num3 = text_3.getText().toString();

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        int n3 = Integer.parseInt(num3);

        if(n1 == n2 && n2 == n3 && n3 == 7){

            Toast.makeText(this,"BING", Toast.LENGTH_LONG).show();
            //Tạo hộp thaoi5 UI thôn g báo xác nhận hành động
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Bạn muốn lưu kết quả này không?")

                    .setMessage("Lưu kết quả cao nhất để xếp hạng, bạn có muốn tiếp tục không")
                    .setCancelable(false)

                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_LONG).show();
                        }
                    })
                    .create()
                    .show();
        }else{
            Toast.makeText(this,"Chúc bạn may mắn lần sau", Toast.LENGTH_LONG).show();

        }




    }

    void play(){
        isPlay = true;

        button.setText("Dừng");
        makeData1();
        makeData2();
        makeData3();
    }


    Timer timer1 = null;
    Timer timer2 = null;
    Timer timer3 = null;




    void makeData1(){
        final Random random = new Random();

        timer1 = new Timer();

        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text_1.setText(String.valueOf(random.nextInt(10)));
                    }
                });
            }
        },10,10);
    }


    void makeData2(){
        final Random random = new Random();

        timer2 = new Timer();

        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text_2.setText(String.valueOf(random.nextInt(10)));
                    }
                });
            }
        },10,10);
    }

    void makeData3(){
        final Random random = new Random();

        timer3 = new Timer();

        timer3.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text_3.setText(String.valueOf(random.nextInt(10)));
                    }
                });
            }
        },10,10);
    }





    private class NumberGenarator extends AsyncTask<Void, Void, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Integer doInBackground(Void... params) {
            return null;
        }
    }
}
