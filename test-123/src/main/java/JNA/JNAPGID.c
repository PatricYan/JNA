// HelloWorld.c

#include "com_test_JNAPGID.h"
#include <unistd.h>

#ifdef __cplusplus
extern "C"
{
#endif

/*
 * Class:     com_study_jnilearn_HelloWorld
 * Method:    sayHello
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_test_JNAPGID_sayHello(JNIEnv *env, jclass cls, jstring j_str)
{
    const char *c_str = NULL;
    char buff[128] = { 0 };
    c_str = (*env)->GetStringUTFChars(env, j_str, NULL);
    if (c_str == NULL)
    {
        printf("out of memory.\n");
        return NULL;
    }
    (*env)->ReleaseStringUTFChars(env, j_str, c_str);
    printf("Java Str:%s\n", c_str);
    sprintf(buff, "hello %s", c_str);
    return (*env)->NewStringUTF(env, buff);
}


JNIEXPORT void JNICALL Java_com_test_JNAPGID_setsid(JNIEnv *env, jclass cls){
    setsid();
}


JNIEXPORT void JNICALL Java_com_test_JNAPGID_setpgid(JNIEnv *env, jclass cls, jint pid, jint pgid){
    setpgid(pid, pgid);
}

#ifdef __cplusplus
}
#endif