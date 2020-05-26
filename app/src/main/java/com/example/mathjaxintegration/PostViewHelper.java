package com.example.mathjaxintegration;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PostViewHelper implements View.OnClickListener {
    private static PostViewHelper instance = new PostViewHelper();


    private PostViewHelper(){}

    public void parsePostAndRender(String questionBody, final LinearLayout layout,
                                   Context context,
                                   LayoutInflater inflater, final View.OnClickListener listener) {

        if (questionBody == null)
            return;

            WebView view = getWebView(context, questionBody);
            layout.addView(view);


    }

    private static WebView getWebView(Context context, String text) {

        WebView w = new WebView(context);

        w.getSettings().setJavaScriptEnabled(true);
//        w.getSettings().setUseWideViewPort(true);
//        w.getSettings().setBuiltInZoomControls(true);
//        w.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        String data = "<script type='text/x-mathjax-config'>"
                + "MathJax.Hub.Config({ "
                + "showMathMenu: false, "
                + "jax: ['input/TeX','output/HTML-CSS'], "
                + "extensions: ['tex2jax.js'], "
                + "TeX: { extensions: ['AMSmath.js','AMSsymbols.js',"
                + "'noErrors.js','noUndefined.js'] } "
                + "});</script>"
                + "<script type='text/javascript' "
                + "src='file:///android_asset/MathJax/MathJax.js'"
                + "></script><span id='math'>dynamic_content</span>";
        /*
        This is inner html When $a \\ne 0$, there are two solutions to \\(ax^2 + bx + c = 0\\) and they are\\n\" +\n" +
                "                \"    \\(x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.\\)
         */
        data = data.replace("dynamic_content", text);

        w.loadDataWithBaseURL("http://localhost", data,"text/html","utf-8","");
//        w.loadUrl("http://www.google.com");
//        w.loadUrl("javascript:document.getElementById('math').innerHTML='\\\\["
//                +doubleEscapeTeX(text)+"\\\\]';");
        w.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);");
//        String unencodedHtml =
//                "&lt;html&gt;&lt;body&gt;'%23' is the percent code for ‘#‘ &lt;/body&gt;&lt;/html&gt;";
//        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
//                Base64.NO_PADDING);
//        w.loadData(encodedHtml, "text/html", "base64");

        return w;
    }

    private static String doubleEscapeTeX(String s) {
        String t="";
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') t += '\\';
            if (s.charAt(i) != '\n') t += s.charAt(i);
            if (s.charAt(i) == '\\') t += "\\";
        }
        return t;
    }

    public static String getImageUrlIfContentHasImages(String content) {
        int index = -1;
        if((index = content.indexOf("{{")) != -1){
            return  content.substring(index + 2, content.indexOf("}}"));
        }
        return null;
    }

    public static PostViewHelper getInstance() {
        return instance;
    }

    @Override
    public void onClick(View v) {
        ImageView img = (ImageView) v;
        Bitmap bitmap = (Bitmap) img.getTag();
        img.getDrawable();
    }

}
