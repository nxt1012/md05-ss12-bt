package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.Scene;
import com.ra.smarthome.repositories.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneRepository sceneRepository;

    @Override
    public List<Scene> getAllScenes() {
        return sceneRepository.findAll();
    }

    @Override
    public Scene getSceneById(Long sceneId) {
        return sceneRepository.findById(sceneId).orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));
    }

    @Override
    public Scene createScene(Scene newScene) {
        // TODO: Add validation or business logic
        return sceneRepository.save(newScene);
    }

    @Override
    public Scene updateScene(Long sceneId, Scene updateScene) {
        // Check if the scene exists before updating
        Scene existingScene = sceneRepository.findById(sceneId).orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));
        // TODO: Update scene properties
        return sceneRepository.save(existingScene);
    }

    @Override
    public void deleteScene(Long sceneId) {
        // Check if the scene exists before deleting
        sceneRepository.findById(sceneId).orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));
        sceneRepository.deleteById(sceneId);
    }
}
