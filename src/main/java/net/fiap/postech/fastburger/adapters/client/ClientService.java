package net.fiap.postech.fastburger.adapters.client;

import net.fiap.postech.fastburger.application.domain.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

@Service
public class ClientService {

    @Value("${aws_access_key_id}")
    private String awsClientId;

    @Value("${aws_secret_access_key}")
    private String awsClientSecret;

    @Value("${aws_cognito_user_pool_id}")
    private String awsUserPollCognito;

    public AdminCreateUserResponse saveClientOnCognito(Client client) {
        AwsBasicCredentials auth = AwsBasicCredentials.create(awsClientId, awsClientSecret);
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .credentialsProvider(() -> auth)
                .region(Region.US_EAST_1)
                .build();
        Result result = getResult(client);
        String pass = PasswordGenerator.generatePassword(20);

        AdminCreateUserRequest request = AdminCreateUserRequest.builder()
                .userPoolId(awsUserPollCognito)
                .username(client.getNome().split(" ")[0])
                .temporaryPassword(pass)
                .userAttributes(result.emailAttribute(), result.cpfAttribute(), result.usernamePreferredAttribute())
                .build();
        AdminCreateUserResponse response = cognitoClient.adminCreateUser(request);
        return response;
    }
    private static Result getResult(Client client) {
        AttributeType usernamePreferredAttribute = AttributeType.builder()
                .name("preferred_username")
                .value(client.getNome().split(" ")[0])
                .build();

        AttributeType emailAttribute = AttributeType.builder()
                .name("email")
                .value(client.getEmail())
                .build();

        AttributeType cpfAttribute = AttributeType.builder()
                .name("custom:cpf")
                .value(client.getCpf())
                .build();
        Result result = new Result(usernamePreferredAttribute, emailAttribute, cpfAttribute);
        return result;
    }
    private record Result(AttributeType usernamePreferredAttribute, AttributeType emailAttribute, AttributeType cpfAttribute) {
    }
}
