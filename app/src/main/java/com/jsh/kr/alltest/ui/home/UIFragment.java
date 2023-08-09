package com.jsh.kr.alltest.ui.home;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.MainMoveData;
import com.jsh.kr.alltest.ui.BaseMainMenuFragment;
import com.jsh.kr.alltest.ui.lib.EffectTestActivity;
import com.jsh.kr.alltest.ui.lib.NaverMapActivity;
import com.jsh.kr.alltest.ui.view.AnimationTestActivity;
import com.jsh.kr.alltest.ui.view.BaseFstActivity;
import com.jsh.kr.alltest.ui.view.CustomBehaviorActivity;
import com.jsh.kr.alltest.ui.view.CustomPickTestActivity;
import com.jsh.kr.alltest.ui.view.CustomViewActivity;
import com.jsh.kr.alltest.ui.view.DatePickerTestActivity;
import com.jsh.kr.alltest.ui.view.DragAndDropListActivity;
import com.jsh.kr.alltest.ui.view.FloatActionButtonTestActivity;
import com.jsh.kr.alltest.ui.view.LeftMenuActivity;
import com.jsh.kr.alltest.ui.view.ListViewClickActivity;
import com.jsh.kr.alltest.ui.view.ListViewTestActivity;
import com.jsh.kr.alltest.ui.view.NotificationTestActivity;
import com.jsh.kr.alltest.ui.view.PagerTestActivity;
import com.jsh.kr.alltest.ui.view.ProgressTestActivity;
import com.jsh.kr.alltest.ui.view.RecycleViewTestActivity;
import com.jsh.kr.alltest.ui.view.RotateActivity;
import com.jsh.kr.alltest.ui.view.ScrollActionBarTestActivity;
import com.jsh.kr.alltest.ui.view.SpinnerTestActivity;
import com.jsh.kr.alltest.ui.view.TabTestActivity;
import com.jsh.kr.alltest.ui.view.TextViwHTMLTestActivity;
import com.jsh.kr.alltest.ui.view.TransparentActivity;

import java.util.ArrayList;

/**
 * UI 관련 테스트
 */
public class UIFragment extends BaseMainMenuFragment {
    ArrayList<MainMoveData> moveDataArrayList = new ArrayList<>();

    @Override
    public ArrayList<MainMoveData> initMoveDataList() {
        // base
        addUIList(BaseFstActivity.class, R.string.move_fst_base_activity, C.State.Test);
        addUIList(RecycleViewTestActivity.class, R.string.move_recycle_view_test, C.State.Test);
        addUIList(PagerTestActivity.class, R.string.move_viewpager, C.State.Test);
        addUIList(SpinnerTestActivity.class, R.string.move_spinner, C.State.Test);
        addUIList(DatePickerTestActivity.class, R.string.move_date_pick, C.State.Test);
        addUIList(TabTestActivity.class, R.string.move_tab_test, C.State.Test);
        addUIList(ProgressTestActivity.class, R.string.move_progress_test, C.State.Test);
        addUIList(FloatActionButtonTestActivity.class, R.string.move_float_button_test, C.State.Test);
        addUIList(NotificationTestActivity.class, R.string.move_notification_test, C.State.Test);
        addUIList(CustomViewActivity.class, R.string.move_custom_view_test, C.State.Test);
        addUIList(AnimationTestActivity.class, R.string.move_animation_test, C.State.Test);
        addUIList(ListViewTestActivity.class, R.string.move_listview_test, C.State.Test);
        addUIList(LeftMenuActivity.class,  R.string.move_left_menu, C.State.Test);
        addUIList(ScrollActionBarTestActivity.class, R.string.move_scroll_toolbar, C.State.Test);

        // lib
        addUIList(EffectTestActivity.class, R.string.move_effect_test, C.State.Test);
        addUIList(NaverMapActivity.class, R.string.move_naver_map_test, C.State.Test);

        // etc
        addIssueList(ListViewClickActivity.class, R.string.move_list_click_test, C.State.Resolution);
        addUIList(RotateActivity.class, R.string.move_rotate, C.State.Test);
        addUIList(TextViwHTMLTestActivity.class, R.string.move_textview_html_test, C.State.Test);
        addUIList(TransparentActivity.class, R.string.move_transparent_test, C.State.Test);
        addUIList(CustomBehaviorActivity.class, R.string.move_custom_behavior, C.State.Test);
        addIssueList(CustomPickTestActivity.class, R.string.move_custom_pick, C.State.Proceed);
        addIssueList(DragAndDropListActivity.class, R.string.move_custom_pick, C.State.Proceed);

        return moveDataArrayList;
    }

    private void addUIList(Class moveAct, int titleId) {
        addList(moveAct, titleId, null, C.State.No_State);
    }

    private void addUIList(Class moveAct, int titleId, int state) {
        addList(moveAct, titleId, null, state);
    }

    private void addIssueList(Class moveAct, int titleId) {
        addList(moveAct, titleId, C.TAG.Issue, C.State.No_State);
    }

    private void addIssueList(Class moveAct, int titleId, int state) {
        addList(moveAct, titleId, C.TAG.Issue, state);
    }

    private void addList(Class moveAct, int titleId, String tag, int state) {
        MainMoveData moveData = new MainMoveData(moveAct, titleId);
        moveData.setTag(tag);
        moveData.setState(state);

        moveDataArrayList.add(moveData);
    }


    @Override
    public int initTitle() {
        return R.string.menu_ui;
    }
}

