package com.backend.appbackend.story;

import java.util.List;

public interface StoryService {

    Story getStory(String id) throws StoryNotFoundException;

    List<StoryResponse> getStories() throws StoryNotFoundException;

    void insertStory(Story story);

    List<String> getImagesFromStory(String id) throws StoryNotFoundException;
}
