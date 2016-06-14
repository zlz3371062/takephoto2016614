package zlz.by.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class MainActivity extends Activity {
    private Button btnxiangji,btntuku;
    public static int CAMEAR_REQUEST_COOD = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = Environment.getExternalStorageDirectory()+ "";
        Log.e("zlz",path); //      /storage/emulated/0


        btnxiangji = (Button) findViewById(R.id.btn_xiangji);
        btntuku = (Button) findViewById(R.id.btn_tuku);
        btnxiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent _intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(_intent,CAMEAR_REQUEST_COOD);

            }
        });
        btntuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(Intent.ACTION_GET_CONTENT);

           Log.e("zlz",Intent.ACTION_GET_CONTENT + "");

                _intent.setType("image/*");
                startActivityForResult(_intent,2);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("zlz",requestCode + "");
           if(requestCode == CAMEAR_REQUEST_COOD)
           {
                   if(data == null){

                       Log.e("zlz","null");

                   }else{
                     Bundle  _extras  = data.getExtras();
                       if(_extras != null){


                            Bitmap _bm = _extras.getParcelable("data");
                            Log.e("zlz","1");
                            ImageView img = (ImageView) findViewById(R.id.img_touxiang);
                             img.setImageBitmap(_bm);

                       }


                   }


           }else  if( requestCode == 2){
             //  file:///storage/emulated/0/DCIM/Camera/IMG_20160609_133358.jpg
               String uri = data.getDataString();
               uri = uri.substring(7);
               Log.e("zlz",uri);

               Toast.makeText(this,uri + "",Toast.LENGTH_LONG).show();
               Bitmap _bitmap = BitmapFactory.decodeFile(uri);
               if(_bitmap != null) {

                   ImageView img = (ImageView) findViewById(R.id.img_touxiang);
                   img.setImageBitmap(_bitmap);
               }else {

                Log.e("zlz","null bitmap");
           }


           }else if(requestCode == 3){
               Bundle  _extras  = data.getExtras();
               if(_extras != null){


                   Bitmap _bm
                           = _extras.getParcelable("data");
                   Log.e("zlz","1");
                   ImageView img = (ImageView) findViewById(R.id.img_touxiang);
                   img.setImageBitmap(_bm);

               }



        }


           }


    }

