package com.jsh.kr.alltest.ui.view;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * TextView singleLine 적용 시 Html 적용 안됨
 * TextView width = wrap_content 일 경우 ellipsize 글마다 다르게 적용 될 수 있음
 */

public class TextViwHTMLTestActivity extends BaseActivity {

   private TextView tv_html_before;
   private TextView tv_html_after;
   private TextView tv_html_after1;
   private TextView tv_html_after2;
   private TextView tv_html_after3;
   private TextView tv_html_after4;
   private TextView tv_html_after5;
   private TextView tv_html_after6;
   private TextView tv_html_after7;
   private TextView tv_html_after8;
   private TextView tv_html_after9;

   private final String testText = "안녕하세요.\r\n테스트용으로 발송된 공지사항입니다.\r\n정상적으로 수신되는지와 줄바꿈 처리를 확인해주세요.";
   private final String testText2 = "테스트용으로 발송된 공지사항입니다. 공지사항 수신되는지와 줄바꿈 처리를 공지사항입니다.\r\n정상적으로 수신되는지와 줄바꿈 처리를 확인해주세요.";

   private final String testText3 = "푸쉬 테스트 상세\r\n줄넘김\r\n하하하하";
   private final String testText4 = "푸쉬 테스트 상세.\r\n줄넘김\r\n하하하하";
   private final String testText5 = "푸쉬 테스트 상세 푸쉬 테스트 상세1 푸쉬 테스트 상세2 푸쉬 테스트 상세3 푸쉬 테스트 상세4\r\n하하하하";

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_textview_html_test);

      initUI();
      init();
   }

   private void initUI() {
      tv_html_before = findViewById(R.id.tv_html_before);
      tv_html_after = findViewById(R.id.tv_html_after);
      tv_html_after1 = findViewById(R.id.tv_html_after1);
      tv_html_after2 = findViewById(R.id.tv_html_after2);
      tv_html_after3 = findViewById(R.id.tv_html_after3);
      tv_html_after4 = findViewById(R.id.tv_html_after4);
      tv_html_after5 = findViewById(R.id.tv_html_after5);
      tv_html_after6 = findViewById(R.id.tv_html_after6);
      tv_html_after7 = findViewById(R.id.tv_html_after7);
      tv_html_after8 = findViewById(R.id.tv_html_after8);
      tv_html_after9 = findViewById(R.id.tv_html_after9);

   }

   private void init() {
      tv_html_before.setText(testText);

      String contents = testText.replaceAll("\r\n", "<br/>");
      String contents2 = testText2.replaceAll("\r\n", "<br/>");
      String contents3 = testText3.replaceAll("\r\n", "<br/>");
      String contents4 = testText4.replaceAll("\r\n", "<br/>");
      String contents5 = testText3.replaceAll("\r\n", " <br/>");
      String contents6 = testText5.replaceAll("\r\n", "<br/>");

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         tv_html_after.setText(Html.fromHtml(contents, Html.FROM_HTML_MODE_LEGACY));
      } else {
         tv_html_after.setText(Html.fromHtml(contents));
      }
      tv_html_after1.setText(Html.fromHtml(contents));

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         tv_html_after2.setText(Html.fromHtml(contents, Html.FROM_HTML_MODE_LEGACY));
      } else {
         tv_html_after2.setText(Html.fromHtml(contents));
      }

      tv_html_after3.setText(Html.fromHtml(contents2));
      tv_html_after4.setText(Html.fromHtml(contents2));

      tv_html_after5.setText(Html.fromHtml(contents3));
      tv_html_after6.setText(Html.fromHtml(contents4));
//        tv_html_after7.setText(Html.fromHtml(contents5));
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         tv_html_after7.setText(Html.fromHtml(contents3, Html.FROM_HTML_MODE_LEGACY));
      } else {
         tv_html_after7.setText(Html.fromHtml(contents3));
      }

      tv_html_after8.setText(Html.fromHtml(contents6));
      tv_html_after9.setText(Html.fromHtml(contents6));

   }
}
