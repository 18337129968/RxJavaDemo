package com.isoftstone.rxjavademo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.isoftstone.rxjavademo.R;
import com.isoftstone.rxjavademo.app.BaseActivity;
import com.isoftstone.rxjavademo.app.MyApplication;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {
    private TextView textView;

    @Inject
    protected SysUserResponseVo user;

    private SimpleDraweeView my_image_view;

    @Override
    protected int getContentResource() {
        return R.layout.activity_home;
    }
    Uri uri = Uri.parse("http://172.16.13.100/upload/2016/0908/15/28fc8613759a11e69539005056b7187b.jpeg");
    @Override
    protected void startWork(Bundle savedInstanceState) {
        textView = (TextView) findViewById(R.id.hometext);
        MyApplication.getInstance().getUserComponent().inject(this);
        textView.setText(user.userName);
        my_image_view = (SimpleDraweeView) findViewById(R.id.my_image_view);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setImageRequest(request.fromUri(uri))
                .build();

//        my_image_view.setImageURI(Uri.parse("http://172.16.13.100/upload/2016/0908/15/28fc8613759a11e69539005056b7187b.jpeg"));
        my_image_view.setController(controller);
        my_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
