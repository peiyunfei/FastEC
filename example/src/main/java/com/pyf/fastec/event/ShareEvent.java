package com.pyf.fastec.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.delegate.web.event.Event;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/7
 */

public class ShareEvent extends Event {

    @Override
    public String execute(String params) {
        JSONObject jsonObject = JSON.parseObject(params).getJSONObject("params");
        String imageUrl = jsonObject.getString("imageUrl");
        String url = jsonObject.getString("url");
        String title = jsonObject.getString("title");
        String text = jsonObject.getString("text");
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setImageUrl(imageUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        //        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        //        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getContext());
        return null;
    }
}
