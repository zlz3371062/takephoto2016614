package zlz.by.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

               Uri uri = data.getData();
               Toast.makeText(this,uri + "",Toast.LENGTH_LONG).show();
                String _ss = uri.getPath();
                File _file = new File(_ss);
//              Intent  _intent = new Intent("com.android.camera.action.CROP");
//               _intent.setDataAndType(uri,"image/*");
//               _intent.putExtra("crop","true");
//               _intent.putExtra("aspectX",1);
//               _intent.putExtra("aspectY",1);
//               _intent.putExtra("outputX",150);
//               _intent.putExtra("outputY",150);
//               _intent.putExtra("return-data",true);
//               startActivityForResult(_intent,3);
               Log.e("zlz",_ss);
               Bitmap _bitmap = BitmapFactory.decodeFile(_ss);
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

