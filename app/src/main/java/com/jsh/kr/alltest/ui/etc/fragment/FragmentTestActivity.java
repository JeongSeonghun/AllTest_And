package com.jsh.kr.alltest.ui.etc.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class FragmentTestActivity extends BaseActivity {
   private static final String TAG = FragmentTestActivity.class.getSimpleName();
   private static final String[] opMethod = {"add", "remove", "replace", "popBack", "show", "hide"};
   private static final String[] opSelType = {"new", "tag", "id", "currentFrag"}; // id = xml id(add, replace) or stack id(popBackStack)
   private static final String[] opFrag = {"fragment1", "fragment2", "fragment3"};
   private static final String[] opStack = {"null", "backStack1", "backStack2", "backStack3"};

   private Spinner method_sp;
   private Spinner sel_type_sp;
   private Spinner frags_sp;
   private CheckBox back_stack_cb;
   private Spinner back_stack_sp;
   private CheckBox set_tag_cb;
   private EditText set_tag_et;
   private Button add_btn;
   private Button remove_btn;
   private Button replace_btn;
   private Button pop_back_btn;
   private Button show_btn;
   private Button hide_btn;

   private StackAdapter stackAdapter;
   private FragAdapter fragAdapter;

   private HashMap<String, Fragment> stackTopFragMap = new HashMap<>();
   private HashMap<String, Fragment> memFragMap = new HashMap<>();

   private Handler handler = new Handler();

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_fragment_test);

      initUI();
      getSupportFragmentManager().addOnBackStackChangedListener(backStackChangedListener);
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      getSupportFragmentManager().removeOnBackStackChangedListener(backStackChangedListener);
   }

   private FragmentManager.OnBackStackChangedListener backStackChangedListener = () -> {
      LogUtil.d(TAG, "OnBackStackChanged");
   };

   private void initUI() {
      method_sp = findViewById(R.id.method_sp);
      sel_type_sp = findViewById(R.id.sel_type_sp);
      frags_sp = findViewById(R.id.frags_sp);
      back_stack_cb = findViewById(R.id.back_stack_cb);
      back_stack_sp = findViewById(R.id.back_stack_sp);
      set_tag_cb = findViewById(R.id.set_tag_cb);
      set_tag_et = findViewById(R.id.set_tag_et);
      add_btn = findViewById(R.id.add_btn);
      remove_btn = findViewById(R.id.remove_btn);
      replace_btn = findViewById(R.id.replace_btn);
      pop_back_btn = findViewById(R.id.pop_back_btn);
      show_btn = findViewById(R.id.show_btn);
      hide_btn = findViewById(R.id.hide_btn);

      RecyclerView list = findViewById(R.id.fragment_list_rv);
      RecyclerView stackList = findViewById(R.id.stack_list_rv);

      ArrayAdapter<String> methodAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opMethod);
      method_sp.setAdapter(methodAdapter);
      method_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            add_btn.setVisibility(View.GONE);
            remove_btn.setVisibility(View.GONE);
            replace_btn.setVisibility(View.GONE);
            pop_back_btn.setVisibility(View.GONE);
            show_btn.setVisibility(View.GONE);
            hide_btn.setVisibility(View.GONE);
            switch (i) {
               case 0:
                  add_btn.setVisibility(View.VISIBLE);
                  break;
               case 1:
                  remove_btn.setVisibility(View.VISIBLE);
                  break;
               case 2:
                  replace_btn.setVisibility(View.VISIBLE);
                  break;
               case 3:
                  pop_back_btn.setVisibility(View.VISIBLE);
                  break;
               case 4:
                  show_btn.setVisibility(View.VISIBLE);
                  break;
               case 5:
                  hide_btn.setVisibility(View.VISIBLE);
                  break;
               default: break;
            }
            initAddOption();
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
      });

      ArrayAdapter<String> selTypeAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opSelType);
      sel_type_sp.setAdapter(selTypeAdapter);
      sel_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            updateSelectFragList(i);
            initAddOption();
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
      });

      frags_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            initAddOption();
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
      });

      ArrayAdapter<String> selStackAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opStack);
      back_stack_sp.setAdapter(selStackAdapter);

      set_tag_cb.setOnCheckedChangeListener((compoundButton, b) -> {
         Fragment fragment = getSelectFragment();
         if (b && fragment != null) {
            set_tag_et.setText(fragment.getClass().getSimpleName());
         } else {
            set_tag_et.setText("");
         }
      });

      add_btn.setOnClickListener(v -> addFragment());
      remove_btn.setOnClickListener(v -> removeFragment());
      replace_btn.setOnClickListener(v -> replaceFragment());
      pop_back_btn.setOnClickListener(v -> popBack());
      show_btn.setOnClickListener(v -> showFragment());
      hide_btn.setOnClickListener(v -> hideFragment());

      stackAdapter = new StackAdapter();
      stackList.setAdapter(stackAdapter);
      fragAdapter = new FragAdapter();
      list.setAdapter(fragAdapter);

   }

   private void initAddOption() {
      back_stack_cb.setChecked(false);
      back_stack_sp.setSelection(0);

      set_tag_cb.setChecked(false);
      set_tag_et.setText("");
   }

   private void updateFragmentState() {
      handler.postDelayed(() -> {
         LogUtil.d(TAG, "updateFragmentState");
         FragmentManager fragmentManager = getSupportFragmentManager();
         List<FragmentManager.BackStackEntry> backStackEntries = new ArrayList<>();
         for (int idx = 0 ; idx < fragmentManager.getBackStackEntryCount() ; idx++) {
            backStackEntries.add(fragmentManager.getBackStackEntryAt(idx));
         }

         stackAdapter.update(backStackEntries);
         fragAdapter.update(fragmentManager.getFragments());

         updateSelectFragList(sel_type_sp.getSelectedItemPosition());
      }, 80);

   }

   private void updateSelectFragList(int selectType) {
      ArrayAdapter<String> fragsAdapter = null;
      FragmentManager fragmentManager = getSupportFragmentManager();
      List<Fragment> fragments = fragmentManager.getFragments();
      Fragment currentFragment = fragmentManager.findFragmentById(R.id.container_fl);

      switch (selectType) {
         case 0:
            fragsAdapter = new ArrayAdapter<>(FragmentTestActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opFrag);
            break;
         case 1:
            List<String> tags = new ArrayList<>();
            for (Fragment fragment : fragments) {
               if (!tags.contains(fragment.getTag())) {
                  tags.add(fragment.getTag());
               }
            }
            fragsAdapter = new ArrayAdapter<>(FragmentTestActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tags.toArray(new String[fragments.size()]));
            break;
         case 2:
            if (method_sp.getSelectedItemPosition() == 3) {
               List<String> stackIds = new ArrayList<>();
               for (int idx = 0; idx < fragmentManager.getBackStackEntryCount() ; idx++) {
                  FragmentManager.BackStackEntry stackEntry = fragmentManager.getBackStackEntryAt(idx);
                  String stackId = String.valueOf(stackEntry.getId());
                  if (!stackIds.contains(stackId)) {
                     stackIds.add(stackId);
                  }
               }

               fragsAdapter = new ArrayAdapter<>(FragmentTestActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stackIds.toArray(new String[0]));
            } else {
               fragsAdapter = new ArrayAdapter<>(FragmentTestActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{String.valueOf(R.id.container_fl)});
            }
            break;
         case 3:
            if (currentFragment != null) {
               fragsAdapter = new ArrayAdapter<>(FragmentTestActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{currentFragment.getClass().getSimpleName()});
            } else {
               fragsAdapter = new ArrayAdapter<>(FragmentTestActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{});
            }
            break;
         default:break;
      }
      if (fragsAdapter != null) {
         frags_sp.setAdapter(fragsAdapter);
      }
   }

   private void addFragment() {
      Fragment fragment = getSelectFragment();
      LogUtil.d(TAG, "addFragment : " + (fragment != null ? fragment.getClass().getSimpleName() : "null"));

      if (fragment != null) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         String tag = set_tag_et.getText().toString();
         String backStackName = getBackStackName(back_stack_sp.getSelectedItemPosition());

         if (set_tag_cb.isChecked() && !TextUtils.isEmpty(tag)) {
            LogUtil.d(TAG, "tag : "+tag);
            fragmentTransaction.add(R.id.container_fl, fragment, tag);
         } else {
            fragmentTransaction.add(R.id.container_fl, fragment);
         }

         if (back_stack_cb.isChecked()) {
            LogUtil.d(TAG, "addToBackStack : "+backStackName);
            fragmentTransaction.addToBackStack(backStackName);
         }
         fragmentTransaction.commit();

      }

      updateFragmentState();
   }

   private Fragment getSelectFragment() {
      Fragment fragment = null;
      FragmentManager fragmentManager = getSupportFragmentManager();

      switch (sel_type_sp.getSelectedItemPosition()) {
         case 0:
            fragment = getNewFragment(frags_sp.getSelectedItemPosition());
            break;
         case 1:
            fragment = fragmentManager.findFragmentByTag(set_tag_et.getText().toString());
            break;
         case 2:
            fragment = fragmentManager.findFragmentById((int) frags_sp.getSelectedItem());
            break;
         case 3:
            fragment = fragmentManager.findFragmentById(R.id.container_fl);
            break;
         default:
            break;

      }

      return fragment;
   }

   private Fragment getNewFragment(int idx) {
      Fragment fragment = null;
      switch (idx) {
         case 0:
            fragment = new TestFragment1();
            break;
         case 1:
            fragment = new TestFragment2();
            break;
         case 2:
            fragment = new TestFragment3();
            break;
         default:break;
      }

      return fragment;
   }

   private String getBackStackName(int idx) {
      String name = null;
      if (idx > 0 && idx < opStack.length) {
         name = opStack[idx];
      }

      return name;
   }

   private void removeFragment() {
      Fragment fragment = getSelectFragment();
      LogUtil.d(TAG, "removeFragment : " + (fragment != null ? fragment.getClass().getSimpleName() : "null"));

      if (fragment != null) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         String tag = set_tag_et.getText().toString();
         String backStackName = getBackStackName(back_stack_sp.getSelectedItemPosition());

         if (back_stack_cb.isChecked()) {
            LogUtil.d(TAG, "addToBackStack : "+backStackName);
            fragmentTransaction.addToBackStack(backStackName);
         }

         fragmentTransaction.remove(fragment);

         fragmentTransaction.commit();
      }

      updateFragmentState();
   }

   private void replaceFragment() {
      Fragment fragment = getSelectFragment();
      LogUtil.d(TAG, "replaceFragment : " + (fragment != null ? fragment.getClass().getSimpleName() : "null"));

      if (fragment != null) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         String tag = set_tag_et.getText().toString();
         String backStackName = getBackStackName(back_stack_sp.getSelectedItemPosition());

         if (back_stack_cb.isChecked()) {
            LogUtil.d(TAG, "addToBackStack : "+backStackName);
            fragmentTransaction.addToBackStack(backStackName);
         }

         if (set_tag_cb.isChecked() && !TextUtils.isEmpty(tag)) {
            LogUtil.d(TAG, "tag : "+tag);
            fragmentTransaction.replace(R.id.container_fl, fragment, tag);
         } else {
            fragmentTransaction.replace(R.id.container_fl, fragment);
         }

         fragmentTransaction.commit();
      }

      updateFragmentState();
   }

   private void showFragment() {
      Fragment fragment = getSelectFragment();
      LogUtil.d(TAG, "showFragment : " + (fragment != null ? fragment.getClass().getSimpleName() : "null"));

      if (fragment != null) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         String tag = set_tag_et.getText().toString();
         String backStackName = getBackStackName(back_stack_sp.getSelectedItemPosition());

         if (back_stack_cb.isChecked()) {
            LogUtil.d(TAG, "addToBackStack : "+backStackName);
            fragmentTransaction.addToBackStack(backStackName);
         }

         fragmentTransaction.show(fragment);

         fragmentTransaction.commit();
      }

      updateFragmentState();
   }

   private void hideFragment() {
      Fragment fragment = getSelectFragment();
      LogUtil.d(TAG, "hideFragment : " + (fragment != null ? fragment.getClass().getSimpleName() : "null"));

      if (fragment != null) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         String tag = set_tag_et.getText().toString();
         String backStackName = getBackStackName(back_stack_sp.getSelectedItemPosition());

         if (back_stack_cb.isChecked()) {
            LogUtil.d(TAG, "addToBackStack : "+backStackName);
            fragmentTransaction.addToBackStack(backStackName);
         }

         fragmentTransaction.hide(fragment);

         fragmentTransaction.commit();
      }

      updateFragmentState();
   }

   private void popBack() {
      switch (sel_type_sp.getSelectedItemPosition()) {
         case 1:
            String tag = String.valueOf(frags_sp.getSelectedItem());
            getSupportFragmentManager().popBackStack(tag, POP_BACK_STACK_INCLUSIVE);
            break;
         case 2:
            int id = Integer.valueOf(String.valueOf(frags_sp.getSelectedItem()));
            getSupportFragmentManager().popBackStack(id, POP_BACK_STACK_INCLUSIVE);
            break;
         default:
            getSupportFragmentManager().popBackStack();
            break;
      }
      updateFragmentState();
   }

   @Override
   public void onBackPressed() {
      updateFragmentState();
      super.onBackPressed();
   }

   class StackAdapter extends RecyclerView.Adapter<StackAdapter.StackViewHolder> {
      private List<FragmentManager.BackStackEntry> backStackEntries = new ArrayList<>();

      void update(List<FragmentManager.BackStackEntry> backStackEntries) {
         this.backStackEntries = backStackEntries;
         notifyDataSetChanged();
      }

      @NonNull
      @Override
      public StackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new StackViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stack, parent, false));
      }

      @Override
      public void onBindViewHolder(@NonNull StackViewHolder holder, int position) {
         FragmentManager.BackStackEntry backStackEntry = backStackEntries.get(position);

         holder.stack_name_tv.setText(backStackEntry.getName());
         holder.stack_id_tv.setText(String.valueOf(backStackEntry.getId()));
      }

      @Override
      public int getItemCount() {
         return backStackEntries != null ? backStackEntries.size() : 0;
      }

      class StackViewHolder extends RecyclerView.ViewHolder {
         TextView stack_name_tv;
         TextView stack_id_tv;
         public StackViewHolder(@NonNull View itemView) {
            super(itemView);
            stack_name_tv = itemView.findViewById(R.id.stack_name_tv);
            stack_id_tv = itemView.findViewById(R.id.stack_id_tv);
         }
      }
   }

   class FragAdapter extends RecyclerView.Adapter<FragAdapter.FragViewHolder>{
      private List<Fragment> fragments = new ArrayList<>();

      void update(List<Fragment> fragments) {
         this.fragments = fragments;
         notifyDataSetChanged();
      }

      @NonNull
      @Override
      public FragViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new FragViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frag, parent, false));
      }

      @Override
      public void onBindViewHolder(@NonNull FragViewHolder holder, int position) {
         Fragment fragment = fragments.get(position);
         String infoForm = "%1$s / %2$d";

         holder.name_tv.setText(fragment.getClass().getSimpleName());
         holder.id_tv.setText(String.format(Locale.getDefault(), infoForm, fragment.getTag(), fragment.getId()));
      }

      @Override
      public int getItemCount() {
         return fragments != null ? fragments.size() : 0;
      }

      class FragViewHolder extends RecyclerView.ViewHolder {
         TextView name_tv;
         TextView id_tv;

         FragViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.name_tv);
            id_tv = itemView.findViewById(R.id.id_tv);
         }
      }
   }

}

