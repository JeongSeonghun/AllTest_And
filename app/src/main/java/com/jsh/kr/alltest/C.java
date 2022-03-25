package com.jsh.kr.alltest;

import java.lang.annotation.Retention;

import androidx.annotation.StringDef;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class C {
    public static class BroadCast {

        public static final String CATEGORY_LOCAL = "com.example.jseonghun35.alltestapp.LOCAL";
        public static final String Data = "data";

        @Retention(SOURCE)
        @StringDef({
                ConnectState                    // 타이젠 연결 결과
                , ConnectStateC                 // 타이젠 costume 연결 요청 결과
                , FindPeerState                 // 타이젠 장비 연결 결과
                , ReceiveMessage                // 타이젠에서 보내온 메세지
                , ReceiveMessageNoEnc
                , ReceiveMessageEnc
                , DisConnect
                , DisConnectC
                , WearMessageReceive
                , CategoryEdit
                , PolarStateConnected
                , PolarStateDisconnected
                , PolarStateServiceDiscover
                , PolarDataChanged
                , AlarmTest
                , BatteryStatus
        })
        public @interface Action {}

        public static final String ConnectState = "ConnectState";
        public static final String ConnectStateC = "ConnectStateC";
        public static final String FindPeerState = "FindPeerState";
        public static final String ReceiveMessage = "ReceiveMessage";
        public static final String ReceiveMessageEnc = "ReceiveMessageEnc";
        public static final String ReceiveMessageNoEnc = "ReceiveMessageNoEnc";
        public static final String DisConnect = "DisConnect";
        public static final String DisConnectC = "DisConnectC";
        public static final String WearMessageReceive = "wearMessageReceive";
        public static final String CategoryEdit = "CategoryEdit";
        public static final String AlarmTest = "alltest.alarm";
        public static final String BatteryStatus = "alltest.BatteryStatus";

        //Polar
        public static final String PolarStateConnected = "polar.connected";
        public static final String PolarStateDisconnected = "polar.disconnected";
        public static final String PolarStateServiceDiscover= "polar.service_discover";
        public static final String PolarDataChanged = "polar.data.notification";
    }

    public static class What {
        public static final int AGENT_STATE_CONNECT = 1;
        public static final int AGENT_STATE_CONNECT_FAIL = 2;
    }

    public static class Extra {
        public static final String EXTRA_KEY_INT = "extraInt";
        public static final String EXTRA_KEY_STRING = "extraString";
        public static final String EXTRA_KEY_DATA = "extraData";
        public static final String EXTRA_KEY_ADDRESS = "extraAddress";
        public static final String EXTRA_KEY_NAME = "extraName";
        public static final String EXTRA_KEY_RSSI = "extraRssi";
        public static final String EXTRA_KEY_IBEACON = "extraIBeacon";
        public static final String EXTRA_KEY_EDDYSTONE = "extraEddystone";
        public static final String EXTRA_KEY_SCANRECORD = "extraScanRecord";
        public static final String EXTRA_KEY_TYPE = "extraType";
        public static final String EXTRA_KEY_PATH = "path";
    }

    public static final String PACKAGE = "com.example.jseonghun35.alltestapp";

    public static class RequestCode {
        public static final int PermissionRequest = 1;
        public static final int Camera = 2;
        public static final int Album = 3;
        public static final int CropImage = 4;
        public static final int PermissionRequestImageSelect = 5;
        public static final int PermissionRequestFileTest = 6;

        public static final int MoveTest = 7;
        public static final int EnableBluetooth = 8;

        public static final int EnableBluetoothToBeacon = 9;
        public static final int FileList = 10;
    }

    public static class Notification {
        // tizen
        public static final String SERVICE_NOTI_CHANNEL_ID = "TestServiceId";
        public static final String SERVICE_NOTI_CHANNEL_NAME = "TestServiceCh";
        public static final int SERVICE_NOTI_ID = 1;
        public static final String SERVICE_NOTI_CHANNEL_ID2 = "TestServiceId2";
        public static final String SERVICE_NOTI_CHANNEL_NAME2 = "TestServiceCh2";
        public static final int SERVICE_NOTI_ID2 = 2;
        public static final String SERVICE_GROUP = "TestServiceGroup";

        public static final String TEST_NOTI_CHANNEL_ID = "notificationTestChannelId";
        public static final String TEST_NOTI_CHANNEL_NAME = "notificationTestChannel";
        public static final String TEST_HIGH_NOTI_CHANNEL_ID = "notificationTestChannelIdHigh";
        public static final String TEST_HIGH_NOTI_CHANNEL_NAME = "notificationTestChannelHigh";
        public static final String TEST_LOW_NOTI_CHANNEL_ID = "notificationTestChannelIdLow";
        public static final String TEST_LOW_NOTI_CHANNEL_NAME = "notificationTestChannelLow";
        public static final int TEST_NOTI_ID = 3;

        public static final String Service_Polar_Channel_Id = "polarServiceChannelId";
        public static final String Service_Polar_Channel_Name = "polarServiceChannel";
        public static final int Service_Polar_Id = 4;

        public static final String FOREGROUND_TEST_SERVICE_CHANNEL_ID = "foregroundTestServiceChannelId";
        public static final String FOREGROUND_TEST_SERVICE_CHANNEL_NAME = "foregroundTestServiceChannel";
        public static final int FOREGROUND_TEST_SERVICE_NOTIGY_ID = 5;
    }

    public static class Preference {
        public static final String NAME_APP_DATA = "AppInfoData";
        public static final String KEY_MODE_LOGIN = "ModeLogin";
        public static final String KEY_MODE_MAINTAIN_TEST = "ModeMaintainTest";
        public static final String KEY_MAINTAIN_TEST_MENU = "MaintainTestMode";
        public static final String KEY_MAINTAIN_TEST_ACT = "MaintainTestAct";

        public static final String NAME_PREFERENCE_TEST = "PreferenceTest";
        public static final String KEY_TEST_STRING = "TestString";
        public static final String KEY_TEST_INT = "TestInt";
    }

    public static class Push {
        public static final String FCM_NOTIFICATION_CHANNEL_ID = "fcm_notification";
        public static final String FCM_NOTIFICATION_CHANNEL_NAME = "fcm_notification";
        public static final int FCM_NOTIFICATION_ID = 100;
    }

    /**
     *  2 : add file display table
     *  3 : add asset db test
     */
    public static class Database {
        public static final String Name = "alltestDB";
        public static final int Version = 3;

        public static class TbDBTest {
            public static final String Name = "dbTest";
            public static final String Idx = "idx";
            public static final String ColText = "col_txt";
            public static final String ColInt = "col_int";
        }

        public static class TbFileDisplay {
            public static final String Name = "fileDisplay";
            public static final String Idx = "idx";
            public static final String ColCategory = "category";
            public static final String ColSubCategory1 = "subCategory1";
            public static final String ColSubCategory2 = "subCategory2";
            public static final String ColFileName = "fileName";
            public static final String ColPath = "path";
            public static final String ColData = "data";
        }
        public static class TbFileDisplayCategory {
            public static final String Name = "fileDisplayCategory";
            public static final String ColIdx = "idx";
            public static final String ColParentId = "parentId";
            public static final String ColCategory = "category";
            public static final String ColName = "name";
            public static final String ColLevel = "level";
            public static final String ColSubCategory1 = "subCategory1";
            public static final String ColSubCategory2 = "subCategory2";
        }

        public static class TbFileHistory {
            public static final String Name = "fileHistory";
            public static final String ColIdx = "idx";
            public static final String ColName = "name";
            public static final String ColDate = "date";
        }

        public static class TbAssetDBTest {
            public static final String Name = "AssetDBTest";
            public static final String ColIdx = "idx";
            public static final String ColName = "name";

        }
    }

    public static class TAG {
        public static final String All = "all";
        public static final String Etc = "etc";
        public static final String Issue = "issue";
    }

    public static class State {
        public static final int No_State = 0;
        public static final int Proceed = 1;
        public static final int Complete = 2;
        public static final int Resolution = 3;
        public static final int Test = 4;
        public static final int Close = 5;
    }

    public static class StateColor {
        public static final String No_State = "#000000";
        public static final String Proceed = "#ff0000";
        public static final String Complete = "#00ff00";
        public static final String Resolution = "#0000ff";
        public static final String Test = "#ff0000";
        public static final String Close = "#0000ff";
    }

    public static class Maintain {
        public static final int Menu_UI = 1;
        public static final int Menu_Action = 2;
    }


    public static class Wear {
        public static final String PATH_WEAR = "/wear";
        public static final String PATH_MOBILE = "/mobile";
    }

    public static class BLE {
        public static final String DeviceNamePolar = "Polar H7";
        public static final String DeviceNameUrban = "URBAN-S";

        public static final String KeyDeviceName = "deviceName";
        public static final String KeyDeviceAddress = "deviceAddress";
    }

    public enum PatternType {
        TYPE1, TYPE2, UNKNOWN;

        public static PatternType getType(int orderNum) {
            for (PatternType type : PatternType.values()) {
                if (type.ordinal() == orderNum) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }

    public enum TestType {
        TYPE_ONLY_CMD, TYPE_WITH_STRING_DATA, TYPE_BATTERY_CHANGE;

        public static TestType findByName(String name) {
            for (TestType type : values()) {
                if (type.name().equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum TestAction {
        ACTION_ONE, ACTION_TWO;

        public static TestAction findByName(String name) {
            for (TestAction action : values()) {
                if (action.name().equals(name)) {
                    return action;
                }
            }
            return null;
        }
    }

    public class DataType {
        public static final int BLUETOOTH_ADRECORD = 1;
        public static final int BEACON_IBEACON = 2;
        public static final int BEACON_EDDYSTONE = 3;
        public static final int BEACON_ALTBEACON = 4;
    }


    public static class FileStr{
        public class SimpleType{
            public static final String UnKnown = "unknown";
            public static final String Directory = "dir";
            public static final String File = "file";
        }
    }

}
