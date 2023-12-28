package com.ra.smarthome.controllers;

import com.ra.smarthome.models.AutomationRule;
import com.ra.smarthome.services.AutomationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/automation-rules")
public class AutomationRuleController {

    @Autowired
    private AutomationRuleService automationRuleService;

    @GetMapping
    public ResponseEntity<List<AutomationRule>> getAllAutomationRules() {
        List<AutomationRule> automationRules = automationRuleService.getAllAutomationRules();
        return new ResponseEntity<>(automationRules, HttpStatus.OK);
    }

    @GetMapping("/{ruleId}")
    public ResponseEntity<AutomationRule> getAutomationRuleById(@PathVariable Long ruleId) {
        AutomationRule automationRule = automationRuleService.getAutomationRuleById(ruleId);
        return new ResponseEntity<>(automationRule, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AutomationRule> createAutomationRule(@RequestBody AutomationRule automationRule) {
        AutomationRule createdAutomationRule = automationRuleService.createAutomationRule(automationRule);
        return new ResponseEntity<>(createdAutomationRule, HttpStatus.CREATED);
    }

    @PutMapping("/{ruleId}")
    public ResponseEntity<AutomationRule> updateAutomationRule(@PathVariable Long ruleId, @RequestBody AutomationRule automationRule) {
        AutomationRule updatedAutomationRule = automationRuleService.updateAutomationRule(ruleId, automationRule);
        return new ResponseEntity<>(updatedAutomationRule, HttpStatus.OK);
    }

    @DeleteMapping("/{ruleId}")
    public ResponseEntity<Void> deleteAutomationRule(@PathVariable Long ruleId) {
        automationRuleService.deleteAutomationRule(ruleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
