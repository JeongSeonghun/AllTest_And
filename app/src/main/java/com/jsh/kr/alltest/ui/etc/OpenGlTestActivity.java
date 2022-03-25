package com.jsh.kr.alltest.ui.etc;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.jsh.kr.alltest.custom.opengl.MyGLSurfaceView;
import com.jsh.kr.alltest.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * java OpenGL -> single process
 * https://webnautes.tistory.com/1009
 * https://blog.jayway.com/2009/12/04/opengl-es-tutorial-for-android-part-ii-building-a-polygon/
 *
 * native
 * https://github.com/tsaarni/android-native-egl-example
 *
 * jni OpenGL
 * http://blog.naver.com/PostView.nhn?blogId=mewizard&logNo=20137985817
 *
 * sdl2
 * http://www.aesop.or.kr/index.php?mid=Board_Community_Freeboard&document_srl=143749
 */
public class OpenGlTestActivity extends BaseActivity {

   private GLSurfaceView mGLView;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      // GLSurfaceView 인스턴스를 생성하여 Activity를 위한 Content View로 설정한다.
      mGLView = new MyGLSurfaceView(this);
      setContentView(mGLView);
   }

}