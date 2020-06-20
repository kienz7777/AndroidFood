package hcmute.edu.vn.foody_17;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class AssetManagers extends Activity {
    private AssetManager assetManager;


    public Bitmap getBitmap(String fileName, Context context) throws IOException {

        assetManager = context.getAssets();
        InputStream is = assetManager.open(fileName);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;

    }
}
