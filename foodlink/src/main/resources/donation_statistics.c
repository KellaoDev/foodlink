#include <jni.h>
#include <stdio.h>
#include <limits.h>

JNIEXPORT jint JNICALL Java_com_foodlink_service_DonationStatisticsService_calculateTotalDonations
  (JNIEnv *env, jobject obj, jintArray doacoes) {
    jint *array = (*env)->GetIntArrayElements(env, doacoes, NULL);
    jsize tamanho = (*env)->GetArrayLength(env, doacoes);

    int total = 0;
    for (int i = 0; i < tamanho; i++) {
        total += array[i];
    }

    (*env)->ReleaseIntArrayElements(env, doacoes, array, 0);
    return total;
  }
}
