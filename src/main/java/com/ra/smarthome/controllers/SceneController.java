package com.ra.smarthome.controllers;

import com.ra.smarthome.models.Scene;
import com.ra.smarthome.services.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/scenes")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @GetMapping
    public ResponseEntity<List<Scene>> getAllScenes() {
        List<Scene> scenes = sceneService.getAllScenes();
        return new ResponseEntity<>(scenes, HttpStatus.OK);
    }

    @GetMapping("/{sceneId}")
    public ResponseEntity<Scene> getSceneById(@PathVariable Long sceneId) {
        Scene scene = sceneService.getSceneById(sceneId);
        return new ResponseEntity<>(scene, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Scene> createScene(@RequestBody Scene scene) {
        Scene createdScene = sceneService.createScene(scene);
        return new ResponseEntity<>(createdScene, HttpStatus.CREATED);
    }

    @PutMapping("/{sceneId}")
    public ResponseEntity<Scene> updateScene(@PathVariable Long sceneId, @RequestBody Scene scene) {
        Scene updatedScene = sceneService.updateScene(sceneId, scene);
        return new ResponseEntity<>(updatedScene, HttpStatus.OK);
    }

    @DeleteMapping("/{sceneId}")
    public ResponseEntity<Void> deleteScene(@PathVariable Long sceneId) {
        sceneService.deleteScene(sceneId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
