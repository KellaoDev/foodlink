/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_foodlink_service_DonationStatisticsService */

#ifndef _Included_com_foodlink_service_DonationStatisticsService
#define _Included_com_foodlink_service_DonationStatisticsService
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_foodlink_service_DonationStatisticsService
 * Method:    calculateTotalDonations
 * Signature: ([I)I
 */
JNIEXPORT jint JNICALL Java_com_foodlink_service_DonationStatisticsService_calculateTotalDonations
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_foodlink_service_DonationStatisticsService
 * Method:    calculateAverageDonation
 * Signature: ([I)I
 */
JNIEXPORT jint JNICALL Java_com_foodlink_service_DonationStatisticsService_calculateAverageDonation
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_foodlink_service_DonationStatisticsService
 * Method:    calculateMaxDonation
 * Signature: ([I)I
 */
JNIEXPORT jint JNICALL Java_com_foodlink_service_DonationStatisticsService_calculateMaxDonation
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_foodlink_service_DonationStatisticsService
 * Method:    calculateMinDonation
 * Signature: ([I)I
 */
JNIEXPORT jint JNICALL Java_com_foodlink_service_DonationStatisticsService_calculateMinDonation
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_foodlink_service_DonationStatisticsService
 * Method:    calculateStandardDeviation
 * Signature: ([I)D
 */
JNIEXPORT jdouble JNICALL Java_com_foodlink_service_DonationStatisticsService_calculateStandardDeviation
  (JNIEnv *, jobject, jintArray);

#ifdef __cplusplus
}
#endif
#endif
