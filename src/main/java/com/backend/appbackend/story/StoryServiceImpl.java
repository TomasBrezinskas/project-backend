package com.backend.appbackend.story;

import com.backend.appbackend.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    private StoryRepository storyRepository;
    private JobService jobService;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository, JobService jobService) {
        this.storyRepository = storyRepository;
        this.jobService = jobService;
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
    public void insertStory(StoryRegistration story) {
        if (story.getImages().size() >= 1) {
            story.setHasImagesToTrue();
        }
        Story newStory = new Story(
                story.getId(),
                story.getDescription(),
                jobService.fetchJobByIdea(story.getIdea()),
                story.getImages(),
                story.isHasImages()

        );
        storyRepository.save(newStory);
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
