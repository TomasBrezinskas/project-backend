package com.backend.appbackend.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StoryController {

    private StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping(value = "/story/{id}")
    public Story getStory(@PathVariable String id) {
        try {
            return storyService.getStory(id);

        } catch (StoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value = "/stories")
    public List<StoryResponse> getStories() {
        try {
            return storyService.getStories();
        } catch (StoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/story")
    public ResponseEntity<Object> insertStory(@RequestBody StoryRegistration story) {
        storyService.insertStory(story);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(story.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/story/{id}/images")
    public List<String> getImagesFromStory(@PathVariable String id) {
        try {
            return storyService.getImagesFromStory(id);
        } catch (StoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
