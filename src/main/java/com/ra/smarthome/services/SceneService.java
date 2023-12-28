package com.ra.smarthome.services;

import com.ra.smarthome.models.Scene;

import java.util.List;

public interface SceneService {
    List<Scene> getAllScenes();
    Scene getSceneById(Long sceneId);
    Scene createScene(Scene newScene);
    Scene updateScene(Long sceneId, Scene updateScene);
    void deleteScene(Long sceneId);
}
