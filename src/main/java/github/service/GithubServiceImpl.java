package github.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.dto.BranchResponse;
import github.dto.GithubRepositoryResponse;
import github.exception.RestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static github.exception.ExceptionEnum.UNKNOWN_ERROR;
import static github.exception.ExceptionEnum.USER_NOT_FOUND;


@Service
public class GithubServiceImpl implements GithubService {

    @Value("${github.token}")
    private String accessToken;

    private final static HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private final static String BASE_URL = "https://api.github.com/";

    @Override
    public List<GithubRepositoryResponse> getUserRepos(String username) {

        final ObjectMapper objectMapper = new ObjectMapper();
        final HttpRequest request = buildGetHttpRequest("users/" + username + "/repos");

        try {
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            GithubRepositoryResponse[] repos = objectMapper.readValue(response.body(), GithubRepositoryResponse[].class);
            List<GithubRepositoryResponse> nonForkRepos = new ArrayList<>();

            for (GithubRepositoryResponse repo : repos) {
                if (!repo.isFork()) {
                    List<BranchResponse> branches = getBranches(username, repo.name());
                    final GithubRepositoryResponse updatedRepo = GithubRepositoryResponse.builder()
                            .name(repo.name())
                            .owner(repo.owner())
                            .branches(branches)
                            .isFork(repo.isFork())
                            .build();
                    nonForkRepos.add(updatedRepo);
                }
            }

            return nonForkRepos;

        } catch (IOException | InterruptedException e) {
            throw new RestException(USER_NOT_FOUND);
        }
    }

    private List<BranchResponse> getBranches(String username, String repoName) {

        final ObjectMapper objectMapper = new ObjectMapper();
        final HttpRequest request = buildGetHttpRequest("repos/" + username + "/" + repoName + "/branches");

        try {
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            BranchResponse[] branchesArray = objectMapper.readValue(response.body(), BranchResponse[].class);
            return Arrays.asList(branchesArray);
        }
        catch (IOException | InterruptedException e) {
            throw new RestException(UNKNOWN_ERROR);
        }
    }

    private HttpRequest buildGetHttpRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + uri))
                .header("Authorization", "token " + accessToken)
                .GET()
                .build();
    }
}
