package com.ticketmanagement.model;

import com.ticketmanagement.model.ticket.Story;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Sprint {

    private final Set<String> storyIds = new HashSet<>();

    public void addStory(Story story) {
        storyIds.add(story.getId());
    }

    public boolean removeStory(String storyId) {
        return storyIds.remove(storyId);
    }

    public Set<String> getStoryIds() {
        return Collections.unmodifiableSet(storyIds);
    }
}
