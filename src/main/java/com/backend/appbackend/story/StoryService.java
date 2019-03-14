package com.backend.appbackend.story;

import java.util.List;

public interface StoryService {

    Story getStory(String id) throws StoryNotFoundException;

    List<Story> getStories();

    void insertStory(Story story);
}
