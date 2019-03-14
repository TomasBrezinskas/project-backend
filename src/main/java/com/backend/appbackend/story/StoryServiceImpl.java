package com.backend.appbackend.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    private StoryRepository storyRepository;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story getStory(String id) throws StoryNotFoundException {
        return storyRepository.findById(id).orElseThrow(() -> new StoryNotFoundException("Story not found in database"));
    }

    @Override
    public List<Story> getStories() {
        return storyRepository.findAll();
    }

    @Override
    public void insertStory(Story story) {
        storyRepository.save(story);
    }
}
