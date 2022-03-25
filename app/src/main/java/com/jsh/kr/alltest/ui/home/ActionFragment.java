package com.jsh.kr.alltest.ui.home;

import com.jsh.kr.alltest.C;
import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.model.MainMoveData;
import com.jsh.kr.alltest.ui.BaseMainMenuFragment;
import com.jsh.kr.alltest.ui.etc.AIDLTestActivity;
import com.jsh.kr.alltest.ui.etc.AlarmTestActivity;
import com.jsh.kr.alltest.ui.etc.AppInfoDataTestActivity;
import com.jsh.kr.alltest.ui.etc.BuildTypeFlavorTestActivity;
import com.jsh.kr.alltest.ui.etc.DBTestActivity;
import com.jsh.kr.alltest.ui.etc.DataBindingTestActivity;
import com.jsh.kr.alltest.ui.etc.EventTestActivity;
import com.jsh.kr.alltest.ui.etc.JobSchedulerTestActivity;
import com.jsh.kr.alltest.ui.etc.OpenGlTestActivity;
import com.jsh.kr.alltest.ui.etc.ResourceTestActivity;
import com.jsh.kr.alltest.ui.etc.SensorTestActivity;
import com.jsh.kr.alltest.ui.etc.ShareTestActivity;
import com.jsh.kr.alltest.ui.etc.SoundControlTestActivity;
import com.jsh.kr.alltest.ui.etc.ThreadTestActivity;
import com.jsh.kr.alltest.ui.etc.TimeTestActivity;
import com.jsh.kr.alltest.ui.etc.ViewModelTestActivity;
import com.jsh.kr.alltest.ui.etc.VoiceRecordTestActivity;
import com.jsh.kr.alltest.ui.etc.WebScriptTestActivity;
import com.jsh.kr.alltest.ui.etc.fragment.FragmentTestActivity;
import com.jsh.kr.alltest.ui.etc.IntentServiceTestActivity;
import com.jsh.kr.alltest.ui.etc.PermissionTestActivity;
import com.jsh.kr.alltest.ui.etc.PhoneStateCheckActivity;
import com.jsh.kr.alltest.ui.etc.PreferenceSaveActivity;
import com.jsh.kr.alltest.ui.etc.ServiceTestActivity;
import com.jsh.kr.alltest.ui.etc.start.StartTestActivity;

import java.util.ArrayList;

/**
 * library 및 기타 테스트
 */
public class ActionFragment extends BaseMainMenuFragment {

    private final ArrayList<MainMoveData> moveDataArrayList = new ArrayList<>();

    @Override
    public ArrayList<MainMoveData> initMoveDataList() {

        // base
        addActList(StartTestActivity.class, R.string.move_act_start_test, C.State.Test);
        addActList(DataBindingTestActivity.class, R.string.move_binding_test, C.State.Test);
        addActList(FragmentTestActivity.class, R.string.move_fragment_test, C.State.Test);
        addActList(PreferenceSaveActivity.class, R.string.move_preference_save, C.State.Test);
        addActList(DBTestActivity.class, R.string.move_db_test, C.State.Test);
        addActList(ThreadTestActivity.class, R.string.move_thread_test, C.State.Test);
        addActList(ServiceTestActivity.class, R.string.move_service_test, C.State.Test);
        addActList(IntentServiceTestActivity.class, R.string.move_intent_service_test, C.State.Test);
        addActList(PermissionTestActivity.class, R.string.move_permission_test, C.State.Test);
        addActList(WebScriptTestActivity.class, R.string.move_script_test, C.State.Test);
        addActList(JobSchedulerTestActivity.class, R.string.move_job_scheduler_test, C.State.Test);
        addActList(AlarmTestActivity.class, R.string.move_alarm_test, C.State.Test);
        addActList(AppInfoDataTestActivity.class, R.string.move_app_info_test, C.State.Test);
        addActList(PhoneStateCheckActivity.class, R.string.move_phone_state_test, C.State.Test);
        addActList(SensorTestActivity.class, R.string.move_sensor_test, C.State.Test);
        addActList(SoundControlTestActivity.class, R.string.move_sound_test, C.State.Test);
        addActList(EventTestActivity.class, R.string.move_event_test, C.State.Test);
        addActList(ViewModelTestActivity.class, R.string.move_view_model_test, C.State.Test);
        addActList(ResourceTestActivity.class, R.string.move_resource_test, C.State.Test);

        // etc
        addActList(VoiceRecordTestActivity.class, R.string.move_voice_record_test, C.State.Test);
        addActList(TimeTestActivity.class, R.string.move_time_test, C.State.Test);
        addActList(AIDLTestActivity.class, R.string.move_aidl_test, C.State.Test);
        addActList(ShareTestActivity.class, R.string.move_share_test, C.State.Test);
        addActList(OpenGlTestActivity.class, R.string.move_opengl_test, C.State.Test);
        addActList(BuildTypeFlavorTestActivity.class, R.string.move_build_flavor, C.State.Test);

        return moveDataArrayList;
    }

    private void addActList(Class moveAct, int titleId) {
        addActList(moveAct, titleId, C.State.No_State);
    }

    private void addActList(Class moveAct, int titleId, int state) {
        addList(moveAct, titleId, null, state);
    }

    private void addList(Class moveAct, int titleId, String tag, int state) {
        MainMoveData moveData = new MainMoveData(moveAct, titleId);
        moveData.setTag(tag);
        moveData.setState(state);

        moveDataArrayList.add(moveData);
    }

    @Override
    public int initTitle() {
        return R.string.menu_action;
    }
}

