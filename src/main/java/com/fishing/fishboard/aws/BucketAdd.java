package com.fishing.fishboard.aws;

import java.io.IOException;

public class BucketAdd {
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
//            String bucketName = "t1-01.newdeal.bitcamp.net";
//            /*
//             * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
//             * so once a bucket name has been taken by any user, you can't create
//             * another bucket with that same name.
//             *
//             * You can optionally specify a location for your bucket if you want to
//             * keep your data closer to your applications or users.
//             */
//            System.out.println("Creating bucket " + bucketName + "\n");
//            s3.createBucket(bucketName);
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
}
