package com.jsh.kr.alltestkt.ui.base;

import android.os.Bundle;

import com.jsh.kr.alltestkt.R;
import com.jsh.kr.alltestkt.manager.KotlinTestManager;
import com.jsh.kr.alltestkt.model.base.KotlinTestClass;
import com.jsh.kr.alltestkt.model.base.KotlinTestData;
import com.jsh.kr.alltestkt.model.base.KotlinTestObject;
import com.jsh.kr.alltestkt.model.base.KotlinToJavaKt;
import com.jsh.kr.alltestkt.ui.BaseActivity;
import com.jsh.kr.alltestkt.view.KotlinTestDataView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Random;

/**
 * https://academy.realm.io/kr/posts/kotlin-with-java/
 */
public class KotlinJavaTestActivity extends BaseActivity {

   private KotlinTestManager testManager;
   private KotlinTestDataView ui_kotlin_java_test;
   private ArrayList<KotlinTestData> testDataList = new ArrayList<>();

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_kotlin_java_test);
      initUI();
      initData();
   }

   private void initUI() {
      ui_kotlin_java_test = findViewById(R.id.ui_kotlin_java_test);
   }

   private void initData() {
      testManager = new KotlinTestManager();
      testDataList.clear();

      initObjectData();
      initClassData();
      actKotlinFileNoClass();

      ui_kotlin_java_test.refresh(testDataList);
   }

   private void initObjectData() {
      String msg = KotlinTestObject.INSTANCE.makeText(testManager.makeRandomNum());
      String msgJavaStatic = KotlinTestObject.makeTextJavaStatic(testManager.makeRandomNum());
      KotlinTestData testData = makeTestData(msg);
      KotlinTestData testData2 = makeTestData(msgJavaStatic);

      testDataList.add(testData);
      testDataList.add(testData2);
   }

   private void initClassData() {
      String msg = KotlinTestClass.Companion.makeText(testManager.makeRandomNum());
      String msgJavaStatic = KotlinTestClass.makeTextJavaStatic(testManager.makeRandomNum());
      // beforeTest() null return 시 exception 발생
//        String msg2 = KotlinTestClass.Companion.makeText("abc", beforeTest());
//        String msg2 = KotlinTestClass.Companion.makeText("abc", testManager.beforeTest());

      KotlinTestData testData = makeTestData(msg);
      KotlinTestData testData2 = makeTestData(msgJavaStatic);

      testDataList.add(testData);
      testDataList.add(testData2);
   }

   private String beforeTest() {
      return beforeTestText();
   }

   private String beforeTestText() {
      return new Random().nextInt(2) % 2 == 0 ? "Not null" : null;
   }

   private void actKotlinFileNoClass() {
      KotlinTestData testData = makeTestData(KotlinToJavaKt.makeRandomText());
      testDataList.add(testData);
   }

   private KotlinTestData makeTestData(String msg) {
      KotlinTestData testData = new KotlinTestData();

      testData.setDataVar(msg);

      testData.getDataVal();
      testData.getDataVar();

      return testData;
   }
}

