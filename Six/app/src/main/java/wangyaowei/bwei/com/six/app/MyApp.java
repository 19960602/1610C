package wangyaowei.bwei.com.six.app;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * *********************************
 * *
 * 作者:王耀威
 * 时间:2018年1月7号
 * 异步解析
 * <p>
 * <p>
 * ****************************************
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String path= Environment.getExternalStorageDirectory() + "/image";
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(new File(path)))
                .build();
        ImageLoader.getInstance().init(build);
    }
}
