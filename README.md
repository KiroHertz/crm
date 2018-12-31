# CRM API
Example API for a CRM using Spring Boot. The application is ready to go since it uses H2 as database source. All except photo handling should work properly.
In order to test the photo uploads we need to septup our authentication for AWS (Check Storage section).

## User creation

The application uses Bcrypt to encode the user password. The application expects to receive the password encoded when creation/updating an user.
## Storage

For the implementation of the management of customer images I've decided to use AWS S3. In order to make it work we need one of the following:
* Java system properties–aws.accessKeyId and aws.secretAccessKey. The AWS SDK for Java uses the SystemPropertyCredentialsProvider to load these credentials.
* Environment variables–AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY. The AWS SDK for Java uses the EnvironmentVariableCredentialsProvider class to load these credentials.
* The default credential profiles file– typically located at ~/.aws/credentials (location can vary per platform), and shared by many of the AWS SDKs and by the AWS CLI. The AWS SDK for Java uses the ProfileCredentialsProvider to load these credentials.
* Amazon ECS container credentials– loaded from the Amazon ECS if the environment variable AWS_CONTAINER_CREDENTIALS_RELATIVE_URI is set. The AWS SDK for Java uses the ContainerCredentialsProvider to load these credentials.
* Instance profile credentials– used on Amazon EC2 instances, and delivered through the Amazon EC2 metadata service. The AWS SDK for Java uses the InstanceProfileCredentialsProvider to load these credentials.

More information here: https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/credentials.html#using-the-default-credential-provider-chain
