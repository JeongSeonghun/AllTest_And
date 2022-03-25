// AIDLTestInterface.aidl
package com.jsh.kr.alltestlib;

// Declare any non-default types here with import statements

import com.jsh.kr.alltestlib.AIDLStateCallback;

interface AIDLTestInterface {
     boolean isAvailable(); // 서비스 상태 체크
     boolean registerCallback(AIDLStateCallback callback); // 서비스 상태 변경 콜백 등록
     boolean unregisterCallback(AIDLStateCallback callback); // 서비스 상태 변경 콜백 등록 해제
}