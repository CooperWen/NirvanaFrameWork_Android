package com.nirvana.code;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.nirvana.code.core.view.NVWebView;
import com.nirvana.code.core.view.RoundProgressBar;
import com.nirvana.code.test.TestDefinedView;

public class Splash extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NVWebView webView;
    private ViewGroup mRootView;
    private RoundProgressBar progressBar;
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
        });
        webView.loadUrl("http:/www.haowuyun.com/index");
        webView.setLongClickable(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
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
            return true;
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
}
