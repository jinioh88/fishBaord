//package com.fishing.fishboard.aws;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.AccessControlList;
//import com.amazonaws.services.s3.model.GroupGrantee;
//import com.amazonaws.services.s3.model.Permission;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//
//import java.io.File;
//import java.io.IOException;
//
//public class ObjectUpoad {
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
//             * Upload an object to your bucket - You can easily upload a file to
//             * S3, or upload directly an InputStream if you know the length of
//             * the data in the stream. You can also specify your own metadata
//             * when uploading to S3, which allows you set a variety of options
//             * like content-type and content-encoding, plus additional metadata
//             * specific to your applications.
//             */
//            String bucketName = "jini-01.newdeal.bitcamp.net";
//
//            AccessControlList acl = new AccessControlList();
//            acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
//
//            PutObjectRequest objRequest = new PutObjectRequest(
//                    bucketName, "img01", new File("/Users/osejin/fishImg/img01.jpeg"));
//            objRequest.setAccessControlList(acl);
//
//            System.out.println("Uploading a new object to S3 from a file\n");
//            s3.putObject(objRequest);
//
//            System.out.println("업로드 완료!");
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
//            ace.printStackTrace();
//        }
//    }
//
//}
