package com.backend.appbackend.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<StoryResponse> getStories() {

        return convertStories(sortStoryByDate());
    }

    @Override
    public void insertStory(Story story) {
        if (story.getImages().size() >= 1) {
            story.setHasImagesToTrue();
        }
        storyRepository.save(story);
    }

    @Override
    public List<String> getImagesFromStory(String id) {
        Story story = storyRepository.findStoryById(id);
        return story.getImages();
    }

    private List<StoryResponse> convertStories(List<Story> stories) {

        List<StoryResponse> convertedStories = new ArrayList<>();

        for (Story story : stories) {
            StoryResponse convertedStory = new StoryResponse(
                    story.getId(),
                    story.getDescription(),
                    story.getJob(),
                    story.isHasImages()
            );
            convertedStories.add(convertedStory);
        }
        return convertedStories;
    }

    private List<Story> sortStoryByDate() {
        Sort sort = new Sort(Sort.Direction.DESC, "job.date");
        return storyRepository.findAll(sort);
    }
}
