package github.service;

import github.dto.GithubRepositoryResponse;

import java.util.List;

interface GithubService {
    List<GithubRepositoryResponse> getUserRepos(String username);
}
