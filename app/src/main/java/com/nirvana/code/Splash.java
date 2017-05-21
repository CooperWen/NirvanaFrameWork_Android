package com.nirvana.code;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nirvana.code.core.base.BaseActivity;
import com.nirvana.code.core.utils.config.BasicConfig;
import com.nirvana.code.core.view.NVWebView;
import com.nirvana.code.core.view.RoundProgressBar;
import com.nirvana.code.model.Channel;
import com.nirvana.code.model.MenuPopBean;
import com.nirvana.code.share.CustomShareListener;
import com.nirvana.code.share.ShareCommon;
import com.nirvana.code.task.KCTaskExecutor;
import com.nirvana.code.widgets.PopMenuView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class Splash extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NVWebView webView;
    private ViewGroup mRootView;
    private RoundProgressBar progressBar;
    private String mShareTitle;
    private String mUrl;
    private GridView mGridView;
    private ShareCommon shareCommon;
    private List<Channel> channels;
    private SearchView mSearchView;
    private String mSearchText;
    private Toolbar toolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mCoverImg;



    private void init() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbarLayout.setTitleEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
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

        View view=navigationView.getHeaderView(0);;
        mGridView=(GridView) view.findViewById(R.id.common_channel);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mCoverImg = (ImageView) findViewById(R.id.iv_header);
        Glide.with(this).load("http://www.haowuyun.com/store/orig/2016/1105/05113609sawr.jpeg").asBitmap().into(mCoverImg);

        KCTaskExecutor.executeTask(new Runnable() {
            @Override
            public void run() {
                initMenuView();
                shareCommon=new ShareCommon(Splash.this);
                shareCommon.initPlatforms();
                shareCommon.initStyles(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform);
            }
        });





        initView();
        init();
    }

    public void initMenuView(){
        channels=new ArrayList<>(8);
        Channel channel1=new Channel();
        channel1.setId(1);
        channel1.setName("首页");
        channel1.setUrl("http://www.haowuyun.com");
        channel1.setIconResourceId(R.drawable.ic_menu_camera);
        channels.add(channel1);



        Channel channel2=new Channel();
        channel2.setId(2);
        channel2.setName("走廊");
        channel2.setUrl("http://www.haowuyun.com/gallery?g=2");
        channel2.setIconResourceId(R.drawable.ic_menu_gallery);
        channels.add(channel2);

        Channel channel3=new Channel();
        channel3.setId(3);
        channel3.setName("视频");
        channel3.setUrl("http://www.haowuyun.com/g/video");
        channel3.setIconResourceId(R.drawable.ic_menu_slideshow);
        channels.add(channel3);

        Channel channel4=new Channel();
        channel4.setId(4);
        channel4.setName("问答");
        channel4.setUrl("http://www.haowuyun.com/g/ask");
        channel4.setIconResourceId(R.drawable.ic_menu_send);
        channels.add(channel4);

        Channel channel5=new Channel();
        channel5.setId(5);
        channel5.setName("亲子");
        channel5.setUrl("http://www.haowuyun.com/tag/%E4%BA%B2%E5%AD%90/");
        channel5.setIconResourceId(R.drawable.ic_menu_lover);
        channels.add(channel5);

        Channel channel6=new Channel();
        channel6.setId(6);
        channel6.setName("关于");
        channel6.setUrl("http://www.haowuyun.com/about");
        channel6.setIconResourceId(R.drawable.ic_menu_manage);
        channels.add(channel6);



        mGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return channels.size();
            }

            @Override
            public Object getItem(int position) {
                return channels.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView==null){
                    convertView= LayoutInflater.from(Splash.this).inflate(R.layout.channel_item,null);
                    Holder holder=new Holder();
                    holder.icon=(ImageView) convertView.findViewById(R.id.channel_icon);
                    holder.title=(TextView) convertView.findViewById(R.id.channel_title);
                    convertView.setTag(holder);

                }
                Holder holder=(Holder) convertView.getTag();
                Channel channel=(Channel) getItem(position);
                holder.icon.setImageDrawable(getResources().getDrawable(channel.getIconResourceId()));
                holder.title.setText(channel.getName());
                return convertView;
            }
            class Holder{
                TextView title;
                ImageView icon;
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Channel channel=channels.get(position);
                String channelName=channel.getName();
                String url=channel.getUrl();
                Intent intent=new Intent(Splash.this,WebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
//                webView.loadUrl(url);
//                toolbar.setTitle(channelName);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }
    private String firstPicUrl="";
    public void initView(){
        webView=(NVWebView) findViewById(R.id.webview);
        mRootView=(ViewGroup) findViewById(R.id.root_view);

        webView.initWebview();
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
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
                mUrl=url;
                //页面内跳转打开新的Activity
                Intent intent=new Intent(Splash.this,WebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!TextUtils.isEmpty(mUrl) && !mUrl.equals(url)){
                    firstPicUrl="";
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
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

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              String url) {
                // TODO Auto-generated method stub
                Log.e("Splash","url="+url.toString());
                if (url != null && (url.toString().startsWith("http://")
                        || url.toString().startsWith("https://"))) {
                    if ((url.toString().startsWith("http://www.haowuyun.com/store/orig/") ||url.toString().startsWith("http://www.haowuyun.com/store/thumbs/")) && (url.toString().endsWith(".png") || url.toString().endsWith(".jpg") || url.toString().endsWith(".jpeg") || url.toString().endsWith(".JPEG"))){
                        firstPicUrl=url.toString();
                        Log.e("Splash","firstPicUrl="+firstPicUrl);
                    }
                    return super.shouldInterceptRequest(view, url);
                } else {
                    try {
                        // 不支持的跳转协议调用外部组件处理
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri
                                .parse(url.toString()));
                        startActivity(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
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

    public void doSearch(final String searchText,final String historUrl){
        webView.loadUrl(BasicConfig.searchUrl+searchText);
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
        getMenuInflater().inflate(R.menu.splash, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchText = query ;
                if (!TextUtils.isEmpty(mSearchText)){
                    doSearch(mSearchText,mUrl);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
//        initMenuPop();
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
            shareCommon.sharePicText(webView.getTitle(),webView.getOriginalUrl(),firstPicUrl);
            return true;
        }else if (id==R.id.action_refresh){
            webView.reload();
            return true;
        }else if (id==R.id.action_login){
            Intent intent=new Intent(Splash.this,WebViewActivity.class);
            intent.putExtra("url","http://www.haowuyun.com/login");
            startActivity(intent);
            return true;
        }else if (id==R.id.action_sign){
            Intent intent=new Intent(Splash.this,WebViewActivity.class);
            intent.putExtra("url","http://www.haowuyun.com/reg");
            startActivity(intent);
            return true;
        }else if (id==R.id.action_close){
           webView.loadUrl("http://www.haowuyun.com/");
            return true;
        }else if (id==R.id.nav_share_pic){
            shareCommon.shareBigPic(firstPicUrl);
            return true;
        }else if (id == android.R.id.home){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String url="";
        String channelName="";

        int id = item.getItemId();

        switch (id){
            case R.id.nav_android:
                channelName="Android";
                url="http://www.haowuyun.com/tag/Android/";
                break;
            case R.id.nav_java:
                channelName="java";
                url="http://www.haowuyun.com/tag/java/";
                break;
            case R.id.nav_js:
                channelName="JavaScript";
                url="http://www.haowuyun.com/tag/JavaScript/";
                break;
            case R.id.nav_h5:
                channelName="Html5";
                url="http://www.haowuyun.com/tag/Html5/";
                break;

        }

        Intent intent=new Intent(Splash.this,WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);

//        webView.loadUrl(url);
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
        if (shareCommon !=null) {
            shareCommon.closeShare();
        }
    }


    public void initMenuPop(){
        int[] icons = {R.mipmap.icowebview_refresh, R.drawable.btn_close_v5};
        String[] texts = {"编辑", "删除"};
        List<MenuPopBean> list = new ArrayList<>();
        MenuPopBean bean = null;
        for (int i = 0; i < icons.length; i++) {
            bean = new MenuPopBean();
            bean.setIcon(icons[i]);
            bean.setText(texts[i]);
            list.add(bean);
        }
        PopMenuView pw = new PopMenuView(Splash.this, list);
        pw.setOnItemClick(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        pw.showPopupWindow(findViewById(R.id.drawer_layout));//点击右上角的那个button

    }
}
