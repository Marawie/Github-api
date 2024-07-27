package github.controller;

import github.dto.GithubRepositoryResponse;
import github.service.GithubServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("github/")
class GithubRepoController {

    private final GithubServiceImpl githubService;

    @GetMapping("user/")
    List<GithubRepositoryResponse> getAllRepoByUser(@RequestParam String username) {
        return githubService.getUserRepos(username);
    }
}