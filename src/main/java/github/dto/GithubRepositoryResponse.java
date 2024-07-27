package github.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepositoryResponse(String name,
                                       OwnerResponse owner,
                                       List<BranchResponse> branches,
                                       @JsonProperty("fork")
                                       boolean isFork) {
}