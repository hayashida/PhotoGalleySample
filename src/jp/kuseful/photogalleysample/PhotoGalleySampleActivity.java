package jp.kuseful.photogalleysample;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoGalleySampleActivity extends Activity {
	public final static int REQUEST_GALLEY = 0;
	
	private ImageView imageview = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // ImageView���擾
        imageview = (ImageView) findViewById(R.id.imageview);
        
        // Galley���Ăяo��
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, REQUEST_GALLEY);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if (requestCode != REQUEST_GALLEY) {
    		return;
    	}
    	if (resultCode != RESULT_OK) {
    		return;
    	}
    	
		try {
			// data����InputStream���J��
			InputStream is = getContentResolver().openInputStream(data.getData());
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			is.close();
			
			// �摜�f�[�^��ImageView�ɃZ�b�g����
			imageview.setImageBitmap(bitmap);
		} catch (IOException e) {
			Toast.makeText(this, "ImageLoad Error.", Toast.LENGTH_SHORT).show();
		}
    }
}