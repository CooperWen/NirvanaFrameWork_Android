package com.nirvana.code;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.nirvana.code.core.view.NVWebView;
import com.nirvana.code.core.view.RoundProgressBar;
import com.nirvana.code.model.Defaultcontent;
import com.nirvana.code.model.StyleUtil;
import com.nirvana.code.test.TestDefinedView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.umeng.socialize.utils.SocializeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Splash extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NVWebView webView;
    private ViewGroup mRootView;
    private RoundProgressBar progressBar;
    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private String mShareTitle;
    private String mUrl;
    public ArrayList<String> styles = new ArrayList<String>();
    private SHARE_MEDIA share_media;
    private UMImage imageurl,imagelocal;
    private UMVideo video;
    private UMusic music;
    private UMEmoji emoji;
    private File file;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();


    private void initPlatforms(){
        platforms.clear();
        platforms.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN_FAVORITE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.SINA.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QQ.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QZONE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.ALIPAY.toSnsPlatform());
        platforms.add(SHARE_MEDIA.DINGTALK.toSnsPlatform());
        platforms.add(SHARE_MEDIA.RENREN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.DOUBAN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.SMS.toSnsPlatform());
        platforms.add(SHARE_MEDIA.EMAIL.toSnsPlatform());
        platforms.add(SHARE_MEDIA.YNOTE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.EVERNOTE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LAIWANG.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LAIWANG_DYNAMIC.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LINKEDIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.YIXIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.YIXIN_CIRCLE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.TENCENT.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FACEBOOK.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FACEBOOK_MESSAGER.toSnsPlatform());
        platforms.add(SHARE_MEDIA.TWITTER.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WHATSAPP.toSnsPlatform());
        platforms.add(SHARE_MEDIA.GOOGLEPLUS.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LINE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.INSTAGRAM.toSnsPlatform());
        platforms.add(SHARE_MEDIA.KAKAO.toSnsPlatform());
        platforms.add(SHARE_MEDIA.PINTEREST.toSnsPlatform());
        platforms.add(SHARE_MEDIA.POCKET.toSnsPlatform());
        platforms.add(SHARE_MEDIA.TUMBLR.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FLICKR.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FOURSQUARE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.MORE.toSnsPlatform());

    }
    private void initStyles(SHARE_MEDIA share_media){
        styles.clear();
        if (share_media == SHARE_MEDIA.QQ){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.QZONE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.SINA){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.WEIXIN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
            styles.add(StyleUtil.EMOJI);
        }
        else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }
        else if (share_media == SHARE_MEDIA.WEIXIN_FAVORITE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);

        }  else if (share_media == SHARE_MEDIA.TENCENT){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        } else if (share_media == SHARE_MEDIA.DOUBAN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.RENREN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.ALIPAY){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }else if (share_media == SHARE_MEDIA.FACEBOOK){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.FACEBOOK_MESSAGER){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.TWITTER){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.EMAIL){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.SMS){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.YIXIN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.YIXIN_CIRCLE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.LAIWANG){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.LAIWANG_DYNAMIC){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.INSTAGRAM){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }else if (share_media == SHARE_MEDIA.PINTEREST){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.TUMBLR){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.LINE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.WHATSAPP){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.KAKAO){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.GOOGLEPLUS){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.EVERNOTE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.YNOTE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.FLICKR){

            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);

        }else if (share_media == SHARE_MEDIA.LINKEDIN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.POCKET) {
            styles.add(StyleUtil.WEB00);
        }else if (share_media == SHARE_MEDIA.FOURSQUARE) {
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }else if (share_media == SHARE_MEDIA.MORE) {
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.DINGTALK) {
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }

    }
    private void initMedia(){
        imageurl = new UMImage(this,Defaultcontent.imageurl);
        imageurl.setThumb(new UMImage(this, "http://www.haowuyun.com/assets/images/haowuicon.png"));
        imagelocal = new UMImage(this,"http://www.haowuyun.com/assets/images/haowuicon.png");
        imagelocal.setThumb(new UMImage(this, "http://www.haowuyun.com/assets/images/haowuicon.png"));

        music = new UMusic(Defaultcontent.musicurl);
        video = new UMVideo(Defaultcontent.videourl);
        music.setTitle("This is music title");
        music.setThumb(new UMImage(this, "http://www.haowuyun.com/assets/images/haowuicon.png"));
        music.setDescription("my description");
        //init video
        video.setThumb(new UMImage(this,"http://www.haowuyun.com/assets/images/haowuicon.png"));
        video.setTitle("This is video title");
        video.setDescription("my description");
        emoji = new UMEmoji(this,"http://img5.imgtn.bdimg.com/it/u=2749190246,3857616763&fm=21&gp=0.jpg");
        emoji.setThumb(imagelocal);
        file = new File(this.getFilesDir()+"test.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SocializeUtils.File2byte(file).length<=0){
            String content = "LoveInLog分享";
            byte[] contentInBytes = content.getBytes();
            try {
                FileOutputStream fop = new FileOutputStream(file);
                fop.write(contentInBytes);
                fop.flush();
                fop.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initPlatforms();
        initStyles(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform);
        initMedia();
        mShareListener = new CustomShareListener(this);
//        mShareAction = new ShareAction(this).setDisplayList(
//                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
//                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
//                SHARE_MEDIA.ALIPAY, SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN,
//                SHARE_MEDIA.SMS, SHARE_MEDIA.EMAIL, SHARE_MEDIA.YNOTE,
//                SHARE_MEDIA.EVERNOTE, SHARE_MEDIA.LAIWANG, SHARE_MEDIA.LAIWANG_DYNAMIC,
//                SHARE_MEDIA.LINKEDIN, SHARE_MEDIA.YIXIN, SHARE_MEDIA.YIXIN_CIRCLE,
//                SHARE_MEDIA.TENCENT, SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.TWITTER,
//                SHARE_MEDIA.WHATSAPP, SHARE_MEDIA.GOOGLEPLUS, SHARE_MEDIA.LINE,
//                SHARE_MEDIA.INSTAGRAM, SHARE_MEDIA.KAKAO, SHARE_MEDIA.PINTEREST,
//                SHARE_MEDIA.POCKET, SHARE_MEDIA.TUMBLR, SHARE_MEDIA.FLICKR,
//                SHARE_MEDIA.FOURSQUARE, SHARE_MEDIA.MORE)
//                .withText(Defaultcontent.text )
//                .setCallback(mShareListener);

        initView();
    }

    public void initView(){
        webView=(NVWebView) findViewById(R.id.webview);
        mRootView=(ViewGroup) findViewById(R.id.root_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (progressBar.getProgress()==progressBar.getMax()){
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {

                String webViewTitle = "分享自LoveInLog";
                super.onReceivedTitle(view, title);
                if (title != null && !TextUtils.isEmpty(title.trim())) {
                    webViewTitle = title;
                }
                mShareTitle=webViewTitle;
            }
        });
        webView.loadUrl("http:/www.haowuyun.com/index");
        webView.setLongClickable(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                mUrl=url;
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                int webviewContentHeight=webView.getContentHeight();
//                ViewGroup.LayoutParams layoutParams=webView.getLayoutParams();
//                layoutParams.height=webviewContentHeight;
//                webView.setLayoutParams(layoutParams);
//                Toast.makeText(getApplicationContext(),"webview height="+webviewContentHeight,Toast.LENGTH_SHORT).show();
                if (progressBar.getProgress()==progressBar.getMax()){
                    progressBar.setVisibility(View.GONE);
                }
            }


        });

        progressBar=(RoundProgressBar)findViewById(R.id.roundProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            UMImage image = new UMImage(this, "http://www.haowuyun.com/assets/images/haowuicon.png");//网络图片
            mShareAction = new ShareAction(this).setDisplayList(
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                    SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                    SHARE_MEDIA.ALIPAY, SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN,
                    SHARE_MEDIA.SMS, SHARE_MEDIA.EMAIL, SHARE_MEDIA.YNOTE,
                    SHARE_MEDIA.EVERNOTE, SHARE_MEDIA.LAIWANG, SHARE_MEDIA.LAIWANG_DYNAMIC,
                    SHARE_MEDIA.LINKEDIN, SHARE_MEDIA.YIXIN, SHARE_MEDIA.YIXIN_CIRCLE,
                    SHARE_MEDIA.TENCENT, SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.TWITTER,
                    SHARE_MEDIA.WHATSAPP, SHARE_MEDIA.GOOGLEPLUS, SHARE_MEDIA.LINE,
                    SHARE_MEDIA.INSTAGRAM, SHARE_MEDIA.KAKAO, SHARE_MEDIA.PINTEREST,
                    SHARE_MEDIA.POCKET, SHARE_MEDIA.TUMBLR, SHARE_MEDIA.FLICKR,
                    SHARE_MEDIA.FOURSQUARE, SHARE_MEDIA.MORE)
                    .withText(webView.getTitle())
                    .withTitle("LoveInLog分享")
                    .withTargetUrl(webView.getOriginalUrl())
                    .setShareboardclickCallback(new ShareBoardlistener() {
                        @Override
                        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                            if (share_media==SHARE_MEDIA.QZONE){
                                mShareAction.withMedia(imagelocal);

                            }
                            mShareAction.setPlatform(share_media);
                            mShareAction.share();
                        }
                    })
                    .setCallback(mShareListener);
//            mShareAction=new ShareAction(this).setDisplayList(
//                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
//                    SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
//                    SHARE_MEDIA.ALIPAY, SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN,
//                    SHARE_MEDIA.SMS, SHARE_MEDIA.EMAIL, SHARE_MEDIA.YNOTE,
//                    SHARE_MEDIA.EVERNOTE, SHARE_MEDIA.LAIWANG, SHARE_MEDIA.LAIWANG_DYNAMIC,
//                    SHARE_MEDIA.LINKEDIN, SHARE_MEDIA.YIXIN, SHARE_MEDIA.YIXIN_CIRCLE,
//                    SHARE_MEDIA.TENCENT, SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.TWITTER,
//                    SHARE_MEDIA.WHATSAPP, SHARE_MEDIA.GOOGLEPLUS, SHARE_MEDIA.LINE,
//                    SHARE_MEDIA.INSTAGRAM, SHARE_MEDIA.KAKAO, SHARE_MEDIA.PINTEREST,
//                    SHARE_MEDIA.POCKET, SHARE_MEDIA.TUMBLR, SHARE_MEDIA.FLICKR,
//                    SHARE_MEDIA.FOURSQUARE, SHARE_MEDIA.MORE)
//                    .withText(webView.getTitle())
//                    .withMedia(imagelocal)
//                    .withTargetUrl(webView.getOriginalUrl())
//                    .withTitle("LoveInLog分享")
//                    .setPlatform(share_media)
//                    .setCallback(mShareListener);
            ShareBoardConfig config = new ShareBoardConfig();
            config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
            mShareAction.open(config);
//            Intent in=new Intent(this,HomeActivity.class);
//            startActivity(in);
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String url="";
        int id = item.getItemId();

        switch (id){
            case R.id.nav_camera:
                url="http://www.haowuyun.com";
                break;
            case R.id.nav_gallery:
                url="http://www.haowuyun.com/gallery?g=2";
                break;
            case R.id.nav_slideshow:
                url="http://www.haowuyun.com/g/video";
                break;
            case R.id.nav_manage:
                url="http://www.haowuyun.com/about";
                break;
            case R.id.nav_android:
                url="http://www.haowuyun.com/tag/Android/";
                break;
            case R.id.nav_java:
                url="http://www.haowuyun.com/tag/java/";
                break;
            case R.id.nav_js:
                url="http://www.haowuyun.com/tag/JavaScript/";
                break;
            case R.id.nav_ask:
                url="http://www.haowuyun.com/g/ask";
                break;
            case R.id.nav_h5:
                url="http://www.haowuyun.com/tag/Html5/";
                break;
            case R.id.nav_lover:
                url="http://www.haowuyun.com/tag/%E4%BA%B2%E5%AD%90/";
                break;
        }

        Intent intent=new Intent(Splash.this,WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeCallbacks(null);
        mRootView.removeView(webView);
        webView.clearCache(true);
        webView.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
    private static class CustomShareListener implements UMShareListener {

        private WeakReference<Splash> mActivity;

        private CustomShareListener(Splash activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform!= SHARE_MEDIA.MORE&&platform!= SHARE_MEDIA.SMS
                        &&platform!= SHARE_MEDIA.EMAIL
                        &&platform!= SHARE_MEDIA.FLICKR
                        &&platform!= SHARE_MEDIA.FOURSQUARE
                        &&platform!= SHARE_MEDIA.TUMBLR
                        &&platform!= SHARE_MEDIA.POCKET
                        &&platform!= SHARE_MEDIA.PINTEREST
                        &&platform!= SHARE_MEDIA.LINKEDIN
                        &&platform!= SHARE_MEDIA.INSTAGRAM
                        &&platform!= SHARE_MEDIA.GOOGLEPLUS
                        &&platform!= SHARE_MEDIA.YNOTE
                        &&platform!= SHARE_MEDIA.EVERNOTE){
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform!= SHARE_MEDIA.MORE&&platform!= SHARE_MEDIA.SMS
                    &&platform!= SHARE_MEDIA.EMAIL
                    &&platform!= SHARE_MEDIA.FLICKR
                    &&platform!= SHARE_MEDIA.FOURSQUARE
                    &&platform!= SHARE_MEDIA.TUMBLR
                    &&platform!= SHARE_MEDIA.POCKET
                    &&platform!= SHARE_MEDIA.PINTEREST
                    &&platform!= SHARE_MEDIA.LINKEDIN
                    &&platform!= SHARE_MEDIA.INSTAGRAM
                    &&platform!= SHARE_MEDIA.GOOGLEPLUS
                    &&platform!= SHARE_MEDIA.YNOTE
                    &&platform!= SHARE_MEDIA.EVERNOTE){
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                    Log.d("throw", "throw:" + t.getMessage());
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

}
