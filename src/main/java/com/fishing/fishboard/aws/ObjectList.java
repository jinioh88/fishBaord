//package com.fishing.fishboard.aws;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ListObjectsRequest;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//
//import java.io.IOException;
//
//public class ObjectList {
//    public static void main(String[] args) throws IOException {
//
//        /*
//         * The ProfileCredentialsProvider will return your [default]
//         * credential profile by reading from the credentials file located at
//         * (~/.aws/credentials).
//         */
//        AWSCredentials credentials = null;
//        try {
//            credentials = new ProfileCredentialsProvider().getCredentials();
//        } catch (Exception e) {
//            throw new AmazonClientException(
//                    "Cannot load the credentials from the credential profiles file. " +
//                            "Please make sure that your credentials file is at the correct " +
//                            "location (~/.aws/credentials), and is in valid format.",
//                    e);
//        }
//
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion("us-west-2")
//                .build();
//
//        System.out.println("===========================================");
//        System.out.println("Getting Started with Amazon S3");
//        System.out.println("===========================================\n");
//
//        try {
//
//            /*
//             * List objects in your bucket by prefix - There are many options for
//             * listing the objects in your bucket.  Keep in mind that buckets with
//             * many objects might truncate their results when listing their objects,
//             * so be sure to check if the returned object listing is truncated, and
//             * use the AmazonS3.listNextBatchOfObjects(...) operation to retrieve
//             * additional results.
//             */
//            String bucketName = "jini-01.newdeal.bitcamp.net";
//
//            System.out.println("Listing objects");
//            ObjectListing objectListing =
//                    s3.listObjects(new ListObjectsRequest()
//                                    .withBucketName(bucketName)
//                            //.withPrefix("My")
//                    );
//            for (S3ObjectSummary objectSummary :
//                    objectListing.getObjectSummaries()) {
//                System.out.println(
//                        " - " + objectSummary.getKey() + "  " +
//                                "(size = " + objectSummary.getSize() + ")");
//            }
//            System.out.println();
//
//        } catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, which means your request made it "
//                    + "to Amazon S3, but was rejected with an error response for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            System.out.println("Caught an AmazonClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with S3, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message: " + ace.getMessage());
//        }
//    }
//
//}
