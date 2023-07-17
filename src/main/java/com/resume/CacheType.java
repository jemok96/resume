package com.resume;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

    USER_PROFILE("userProfile", 10, 10000),
    POST_COMMENTS("postComments", 20, 10000);

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maximumSize;

}