/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class me_wtao_service_ScreenCaptureService */

#ifndef _Included_me_wtao_service_ScreenCaptureService
#define _Included_me_wtao_service_ScreenCaptureService
#ifdef __cplusplus
extern "C" {
#endif
#undef me_wtao_service_ScreenCaptureService_MODE_PRIVATE
#define me_wtao_service_ScreenCaptureService_MODE_PRIVATE 0L
#undef me_wtao_service_ScreenCaptureService_MODE_WORLD_READABLE
#define me_wtao_service_ScreenCaptureService_MODE_WORLD_READABLE 1L
#undef me_wtao_service_ScreenCaptureService_MODE_WORLD_WRITEABLE
#define me_wtao_service_ScreenCaptureService_MODE_WORLD_WRITEABLE 2L
#undef me_wtao_service_ScreenCaptureService_MODE_APPEND
#define me_wtao_service_ScreenCaptureService_MODE_APPEND 32768L
#undef me_wtao_service_ScreenCaptureService_MODE_MULTI_PROCESS
#define me_wtao_service_ScreenCaptureService_MODE_MULTI_PROCESS 4L
#undef me_wtao_service_ScreenCaptureService_MODE_ENABLE_WRITE_AHEAD_LOGGING
#define me_wtao_service_ScreenCaptureService_MODE_ENABLE_WRITE_AHEAD_LOGGING 8L
#undef me_wtao_service_ScreenCaptureService_BIND_AUTO_CREATE
#define me_wtao_service_ScreenCaptureService_BIND_AUTO_CREATE 1L
#undef me_wtao_service_ScreenCaptureService_BIND_DEBUG_UNBIND
#define me_wtao_service_ScreenCaptureService_BIND_DEBUG_UNBIND 2L
#undef me_wtao_service_ScreenCaptureService_BIND_NOT_FOREGROUND
#define me_wtao_service_ScreenCaptureService_BIND_NOT_FOREGROUND 4L
#undef me_wtao_service_ScreenCaptureService_BIND_ABOVE_CLIENT
#define me_wtao_service_ScreenCaptureService_BIND_ABOVE_CLIENT 8L
#undef me_wtao_service_ScreenCaptureService_BIND_ALLOW_OOM_MANAGEMENT
#define me_wtao_service_ScreenCaptureService_BIND_ALLOW_OOM_MANAGEMENT 16L
#undef me_wtao_service_ScreenCaptureService_BIND_WAIVE_PRIORITY
#define me_wtao_service_ScreenCaptureService_BIND_WAIVE_PRIORITY 32L
#undef me_wtao_service_ScreenCaptureService_BIND_IMPORTANT
#define me_wtao_service_ScreenCaptureService_BIND_IMPORTANT 64L
#undef me_wtao_service_ScreenCaptureService_BIND_ADJUST_WITH_ACTIVITY
#define me_wtao_service_ScreenCaptureService_BIND_ADJUST_WITH_ACTIVITY 128L
#undef me_wtao_service_ScreenCaptureService_CONTEXT_INCLUDE_CODE
#define me_wtao_service_ScreenCaptureService_CONTEXT_INCLUDE_CODE 1L
#undef me_wtao_service_ScreenCaptureService_CONTEXT_IGNORE_SECURITY
#define me_wtao_service_ScreenCaptureService_CONTEXT_IGNORE_SECURITY 2L
#undef me_wtao_service_ScreenCaptureService_CONTEXT_RESTRICTED
#define me_wtao_service_ScreenCaptureService_CONTEXT_RESTRICTED 4L
#undef me_wtao_service_ScreenCaptureService_START_CONTINUATION_MASK
#define me_wtao_service_ScreenCaptureService_START_CONTINUATION_MASK 15L
#undef me_wtao_service_ScreenCaptureService_START_STICKY_COMPATIBILITY
#define me_wtao_service_ScreenCaptureService_START_STICKY_COMPATIBILITY 0L
#undef me_wtao_service_ScreenCaptureService_START_STICKY
#define me_wtao_service_ScreenCaptureService_START_STICKY 1L
#undef me_wtao_service_ScreenCaptureService_START_NOT_STICKY
#define me_wtao_service_ScreenCaptureService_START_NOT_STICKY 2L
#undef me_wtao_service_ScreenCaptureService_START_REDELIVER_INTENT
#define me_wtao_service_ScreenCaptureService_START_REDELIVER_INTENT 3L
#undef me_wtao_service_ScreenCaptureService_START_FLAG_REDELIVERY
#define me_wtao_service_ScreenCaptureService_START_FLAG_REDELIVERY 1L
#undef me_wtao_service_ScreenCaptureService_START_FLAG_RETRY
#define me_wtao_service_ScreenCaptureService_START_FLAG_RETRY 2L
/*
 * Class:     me_wtao_service_ScreenCaptureService
 * Method:    nativeTakeScreenCapture
 * Signature: ()Landroid/graphics/Bitmap;
 */
JNIEXPORT jobject JNICALL Java_me_wtao_service_ScreenCaptureService_nativeTakeScreenCapture
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
